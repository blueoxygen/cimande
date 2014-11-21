package org.blueoxygen.cimande.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.blueoxygen.cimande.site.Site;
import org.meruvian.yama.core.user.DefaultUser;
import org.meruvian.yama.core.user.User;
import org.meruvian.yama.web.security.DefaultUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class SessionCredentials {
	public static User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}
		
		if (authentication.getPrincipal() instanceof DefaultUserDetails) {
			DefaultUserDetails user = (DefaultUserDetails) authentication.getPrincipal();
			return user.getUser();
		}
		
		if (authentication.getPrincipal() instanceof String) {
			String principal = (String) authentication.getPrincipal();
			if ("anonymousUser".equals(principal)) {
				return null;
			}
			
			DefaultUser user = new DefaultUser();
			user.setUsername((String) authentication.getPrincipal());
			return user;
		}
		
		return null;
	}
	
	public static String getCurrentUsername() {
		org.meruvian.yama.core.user.User user = getCurrentUser();
		
		if (user != null) {
			return user.getUsername();
		}
		
		return null;
	}

	public static Site getCurrentSite() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}
		
		if (authentication.getPrincipal() instanceof DefaultUserDetails) {
			DefaultCimandeUserDetails user = (DefaultCimandeUserDetails) authentication.getPrincipal();
			return user.getSite();
		}
		
		if (authentication.getPrincipal() instanceof String) {
			String principal = (String) authentication.getPrincipal();
			if ("anonymousSite".equals(principal)) {
				return null;
			}
			
			Site site = new Site();
			site.setName((String) authentication.getPrincipal());
			return site;
		}
		
		return null;
	}
	
	public static String getCurrentSitename() {
		Site site = getCurrentSite();
		
		if (site != null) {
			return site.getName();
		}
		
		return null;
	}
	
	public static List<String> getAuthorities() {
		List<String> roles = new ArrayList<String>();
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		for (GrantedAuthority authority : authorities) {
			roles.add(authority.getAuthority());
		}
		
		return roles;
	}
}
