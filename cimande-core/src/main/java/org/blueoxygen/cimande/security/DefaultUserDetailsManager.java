package org.blueoxygen.cimande.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.blueoxygen.cimande.site.Site;
import org.blueoxygen.cimande.site.SiteManager;
import org.blueoxygen.cimande.usersite.UserSite;
import org.blueoxygen.cimande.usersite.UserSiteManager;
import org.meruvian.yama.core.LogInformation;
import org.meruvian.yama.core.role.Role;
import org.meruvian.yama.core.role.UserRole;
import org.meruvian.yama.core.user.DefaultUser;
import org.meruvian.yama.core.user.User;
import org.meruvian.yama.core.user.UserManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class DefaultUserDetailsManager implements UserDetailsService, ServletRequestAware  {
	@Value("${site.virtualhost}")
	private String siteVirtualHost = "";
	@Value("${site.login}")
	private String siteLogin = "";
	@Value("${role.default}")
	private String defaultRole = "";
	
	private UserManager userManager;
	
	private HttpServletRequest request;
	
	@Inject
	private SiteManager siteManager;
	
	@Inject
	private UserSiteManager userSiteManager;
	
	@PostConstruct
	public void postConstruct() {
		Validate.notNull(userManager, "UserManager must be provided");
	}
	
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if("true".equalsIgnoreCase(siteLogin)) {
			UserSite userSite = null;
			User user = null;
			Site site = null;
			if("true".equalsIgnoreCase(siteVirtualHost)) {
				/** Login Using Site VirtualHost **/
				userSite = userSiteManager.findByUserUserameAndSiteVirtualHost(username, request.getHeader("host"));
				if (StringUtils.isNotBlank(defaultRole))
					user = userManager.getUserByUsername(username);
					site = siteManager.findSiteByVirtualHost(request.getHeader("host"));
			} else {
				/** Login Using Site (user@site) **/
				String usersite[] = new String[2];
				usersite = username.split("@");
				userSite = userSiteManager.findByUserUserameAndSiteName(usersite[0], usersite[1]);
				if (StringUtils.isNotBlank(defaultRole))
					user = userManager.getUserByUsername(usersite[0]);
					site = siteManager.findSiteByVirtualHost(usersite[1]);
			}
			
			if (userSite != null) {
				return details(userSite.getUser(), userSite.getSite());
			} else if (StringUtils.isNotBlank(defaultRole)) {
				/** Login Social: comparing user social role with defaultRole **/
				boolean isDefaultRole = false;
				Page<? extends UserRole> userRoles = userManager.findRoleByUser(user, null);
				for (UserRole userRole : userRoles) {
					if (defaultRole.equalsIgnoreCase(userRole.getRole().getName()))
						isDefaultRole = true;
				}
				if (user != null && site != null && isDefaultRole)
					return details(user, site);
			}
		} else {
			User user = userManager.getUserByUsername(username);
			if (user != null)
				return details(user, null);
		}
		
		return null;
	}
	
	private DefaultCimandeUserDetails details(User user, Site site){
		boolean enabled = user.getLogInformation().getActiveFlag() == LogInformation.ACTIVE;
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		Page<? extends UserRole> userRoles = userManager.findRoleByUser(user, null);
		for (UserRole userRole : userRoles) {
			Role role = userRole.getRole();
			authorities.add(new SimpleGrantedAuthority(StringUtils.upperCase(role.getName())));
		}
		
		DefaultCimandeUserDetails details = new DefaultCimandeUserDetails(user.getUsername(), user.getPassword(), enabled, true, true, true, authorities);
		details.setId(user.getId());
		details.setUser(new DefaultUser(user));
		if(site != null) {
			details.setSiteId(site.getId());
			details.setSite(site);
		}
		
		return details;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
