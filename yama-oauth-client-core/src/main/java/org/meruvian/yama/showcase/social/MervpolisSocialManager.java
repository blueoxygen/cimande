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
package org.meruvian.yama.showcase.social;

import org.apache.commons.lang3.RandomStringUtils;
import org.meruvian.yama.core.LogInformation;
import org.meruvian.yama.core.commons.Address;
import org.meruvian.yama.core.commons.DefaultFileInfo;
import org.meruvian.yama.core.commons.Name;
import org.meruvian.yama.core.user.DefaultUser;
import org.meruvian.yama.core.user.User;
import org.meruvian.yama.showcase.social.api.Mervpolis;
import org.meruvian.yama.social.core.AbstractSocialManager;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @author Dian Aditya
 *
 */
public class MervpolisSocialManager extends AbstractSocialManager<Mervpolis> {

	public MervpolisSocialManager(OAuth2ConnectionFactory<Mervpolis> connectionFactory) {
		super(connectionFactory);
	}

	@Override
	public User createUser(Connection<?> connection) {
		Mervpolis mervpolis = (Mervpolis) connection.getApi();
		
		String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		String randomUsername = RandomStringUtils.random(8, alphanumeric);
		
		DefaultUser user = new DefaultUser(mervpolis.userOperations().getUser());
		user.setId(null);
		user.setAddress(new Address());
		user.setLogInformation(new LogInformation());
		user.setFileInfo(new DefaultFileInfo());
		
		if (user.getName() == null) {
			user.setName(new Name("", user.getUsername(), "", ""));
		}
		user.setUsername(user.getName().getFirst() + user.getName().getLast() + randomUsername);
		
		String password = RandomStringUtils.random(8, alphanumeric);
		user.setPassword(password);
		
		return user;
	}

	@Override
	public boolean isAuthorized(Connection<?> connection) {
		Mervpolis mervpolis = (Mervpolis) connection.getApi();
		
		return mervpolis.isAuthorized();
	}

}
