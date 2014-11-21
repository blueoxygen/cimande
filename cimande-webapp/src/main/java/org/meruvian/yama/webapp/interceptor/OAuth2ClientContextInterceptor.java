/**
 * Copyright 2014 Meruvian
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.meruvian.yama.webapp.interceptor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.util.Assert;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.NestedServletException;
import org.springframework.web.util.UriComponents;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author Dian Aditya
 * 
 */
public class OAuth2ClientContextInterceptor extends AbstractInterceptor implements InitializingBean {

	/**
	 * Key in request attributes for the current URI in case it is needed by rest client code that needs to send a
	 * redirect URI to an authorization server.
	 */
	public static final String CURRENT_URI = "currentUri";

	private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	@PostConstruct
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(redirectStrategy, "A redirect strategy must be supplied.");
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) invocation.getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		request.setAttribute(CURRENT_URI, calculateCurrentUri(request));
		
		String result = "";
		
		try {
			result = invocation.invoke();
		}
		catch (IOException ex) {
			throw ex;
		}
		catch (Exception ex) {
			// Try to extract a SpringSecurityException from the stacktrace
			Throwable[] causeChain = throwableAnalyzer.determineCauseChain(ex);
			UserRedirectRequiredException redirect = (UserRedirectRequiredException) throwableAnalyzer
					.getFirstThrowableOfType(UserRedirectRequiredException.class, causeChain);
			if (redirect != null) {
				redirectUser(redirect, request, response);
			}
			else {
				if (ex instanceof ServletException) {
					throw (ServletException) ex;
				}
				if (ex instanceof RuntimeException) {
					throw (RuntimeException) ex;
				}
				throw new NestedServletException("Unhandled exception", ex);
			}
		}
		
		return result;
	}

	/**
	 * Redirect the user according to the specified exception.
	 * 
	 * @param resourceThatNeedsAuthorization
	 * @param e The user redirect exception.
	 * @param request The request.
	 * @param response The response.
	 */
	protected void redirectUser(UserRedirectRequiredException e, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String redirectUri = e.getRedirectUri();
		StringBuilder builder = new StringBuilder(redirectUri);
		Map<String, String> requestParams = e.getRequestParams();
		char appendChar = redirectUri.indexOf('?') < 0 ? '?' : '&';
		for (Map.Entry<String, String> param : requestParams.entrySet()) {
			try {
				builder.append(appendChar).append(param.getKey()).append('=')
						.append(URLEncoder.encode(param.getValue(), "UTF-8"));
			}
			catch (UnsupportedEncodingException uee) {
				throw new IllegalStateException(uee);
			}
			appendChar = '&';
		}

		if (e.getStateKey() != null) {
			builder.append(appendChar).append("state").append('=').append(e.getStateKey());
		}

		this.redirectStrategy.sendRedirect(request, response, builder.toString());

	}

	/**
	 * Calculate the current URI given the request.
	 * 
	 * @param request The request.
	 * @return The current uri.
	 */
	protected String calculateCurrentUri(HttpServletRequest request) throws UnsupportedEncodingException {
		ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromRequest(request);
		// Now work around SPR-10172...
		String queryString = request.getQueryString();
		boolean legalSpaces = queryString != null && queryString.contains("+");
		if (legalSpaces) {
			builder.replaceQuery(queryString.replace("+", "%20"));
		}
		UriComponents uri = null;
        try {
		    uri = builder.replaceQueryParam("code").build(true);
        } catch (IllegalArgumentException ex) {
            // ignore failures to parse the url (including query string). does't make sense
            // for redirection purposes anyway.
            return null;
        }
		String query = uri.getQuery();
		if (legalSpaces) {
			query = query.replace("%20", "+");
		}
		return ServletUriComponentsBuilder.fromUri(uri.toUri()).replaceQuery(query).build().toString();
	}

	public void destroy() {
	}

	public void setThrowableAnalyzer(ThrowableAnalyzer throwableAnalyzer) {
		this.throwableAnalyzer = throwableAnalyzer;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}
}
