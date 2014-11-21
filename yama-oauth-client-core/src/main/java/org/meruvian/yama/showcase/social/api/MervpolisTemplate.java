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
package org.meruvian.yama.showcase.social.api;

import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;

/**
 * @author Dian Aditya
 *
 */
public class MervpolisTemplate extends AbstractOAuth2ApiBinding implements Mervpolis {
	private UserOperations userOperations;
	
	public MervpolisTemplate(String accessToken) {
		super(accessToken);
		
		this.userOperations = new UserTemplate(getRestTemplate());
	}
	
	@Override
	public UserOperations userOperations() {
		return userOperations;
	}

}
