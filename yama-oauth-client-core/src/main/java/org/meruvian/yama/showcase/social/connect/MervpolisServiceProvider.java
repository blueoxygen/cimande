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
package org.meruvian.yama.showcase.social.connect;

import org.meruvian.yama.showcase.social.api.Mervpolis;
import org.meruvian.yama.showcase.social.api.MervpolisTemplate;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @author Dian Aditya
 *
 */
public class MervpolisServiceProvider extends AbstractOAuth2ServiceProvider<Mervpolis> {

	public MervpolisServiceProvider(String clientId, String clientSecret) {
		super(new OAuth2Template(clientId, clientSecret, 
				"http://www.merv.id/oauth/authorize", "http://www.merv.id/oauth/token"){{ setUseParametersForClientAuthentication(true); }});
	}

	@Override
	public Mervpolis getApi(String accessToken) {
		return new MervpolisTemplate(accessToken);
	}

}
