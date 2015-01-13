package org.meruvian.yama.social.core;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.meruvian.yama.core.role.DefaultRole;
import org.meruvian.yama.core.user.User;
import org.meruvian.yama.core.user.UserManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

/**
 * @author Dian Aditya
 *
 */
public class SocialConnectionSignUp implements ConnectionSignUp {
	private SocialManagerLocator socialManagerLocator;
	private UserManager userManager;
	@Value("${role.default}")
	private String defaultRole = "";
	
	public void setSocialManagerLocator(SocialManagerLocator socialManagerLocator) {
		this.socialManagerLocator = socialManagerLocator;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
	@PostConstruct
	public void postConstruct() {
		Validate.notNull(socialManagerLocator, "SocialManagerLocator must be provided");
		Validate.notNull(userManager, "UserManager must be provided");
	}
	
	@Override
	public String execute(Connection<?> connection) {
		SocialManager<?> socialManager = socialManagerLocator.getSocialManager(connection.getKey().getProviderId());
		User createdUser = socialManager.createUser(connection);
		User user = userManager.findUser(createdUser);
		
		if (user != null) {
			createdUser = user;
		} else {
			createdUser = userManager.saveUser(createdUser);			
			if (StringUtils.isNotBlank(defaultRole)) {
				DefaultRole role = new DefaultRole();
				role.setName(defaultRole);
				
				userManager.addRoleToUser(createdUser, role);
			}
		}
		
		return createdUser.getId();
	}
}