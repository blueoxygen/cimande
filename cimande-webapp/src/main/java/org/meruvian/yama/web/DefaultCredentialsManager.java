package org.meruvian.yama.web;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.Validate;
import org.meruvian.yama.core.user.User;
import org.meruvian.yama.core.user.UserManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Dian Aditya
 *
 */
public class DefaultCredentialsManager implements CredentialsManager {
	private UserManager userManager;
	private UserDetailsService userDetailsService;
	
	@PostConstruct
	public void postConstruct() {
		Validate.notNull(userManager, "UserManager must be provided");
		Validate.notNull(userDetailsService, "UserDetailsService must be provided");
	}
	
	@Override
	public void registerAuthentication(String userId) {
		User user = userManager.getUserById(userId);
		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
		Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
}