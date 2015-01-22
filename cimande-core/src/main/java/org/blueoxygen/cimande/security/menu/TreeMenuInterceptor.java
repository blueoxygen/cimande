/**
 * Copyright 2012 Meruvian
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
package org.blueoxygen.cimande.security.menu;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;
import org.blueoxygen.cimande.modulefunction.ModuleFunction;
import org.blueoxygen.cimande.roleprivilege.RolePrivilegeManager;
import org.blueoxygen.cimande.rolesiteprivilege.RoleSitePrivilegeManager;
import org.blueoxygen.cimande.security.SessionCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.ValueStack;

public class TreeMenuInterceptor extends AbstractInterceptor implements
		StrutsStatics {

	private final static Logger log = Logger
			.getLogger(TreeMenuInterceptor.class);

	@Inject
	private RoleSitePrivilegeManager roleSitePrivilegeManager;
	
	@Inject
	private RolePrivilegeManager rolePrivilegeManager;

	@Value("${site.login}")
	private String siteLogin = "";
	
	@Value("${site.login.select}")
	private String siteLoginSelect = "";

	@Value("${site.default}")
	private String siteDefault = "";

	@Value("${site.virtualhost}")
	private String siteVirtualHost = "";

	@Value("${role.admin}")
	private String roleAdmin = "";

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ValueStack stack = invocation.getStack();
		if (SessionCredentials.getCurrentUser() == null)
			return invocation.invoke();
		
		if (!SessionCredentials.getAuthorities().isEmpty()) {
			if (SessionCredentials.getCurrentSite() != null) {
				Page<? extends ModuleFunction> moduleFunctions = roleSitePrivilegeManager.
						findModuleFunctionByRoleSite(SessionCredentials.getAuthorities(), 
								SessionCredentials.getCurrentSite().getId(), null);
				stack.set("menus", moduleFunctions);
			} else {
				Page<? extends ModuleFunction> moduleFunctions = rolePrivilegeManager.
						findModuleFunctionByRoles(SessionCredentials.getAuthorities(), null);
				stack.set("menus", moduleFunctions);
			}
		}	
		
		return invocation.invoke();
	}
}
