package org.blueoxygen.cimande.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.blueoxygen.cimande.site.Site;
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

public class DefaultUserDetailsManager implements UserDetailsService  {

	@Value("${site.login}")
	private String siteLogin = "";

	@Value("${site.login.select}")
	private String siteLoginSelect = "";

	@Value("${site.default}")
	private String siteDefault = "";

	@Value("${site.recorder}")
	private String siteRecorder = "";

	@Value("${site.virtualhost}")
	private String siteVirtualHost = "";
	
	private UserManager userManager;
	
	@Inject
	private UserSiteManager userSiteService;
	
	@PostConstruct
	public void postConstruct() {
		Validate.notNull(userManager, "UserManager must be provided");
	}
	
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		String userandsite[] = StringUtils.split(username, "@", 2);
		if (userandsite.length == 2 && StringUtils.isNotBlank(userandsite[1])) {
			UserSite userSite = userSiteService.findByUserUserameAndSiteName(userandsite[0], userandsite[1]);
			if (userSite != null)
				return details(userSite.getUser(), userSite.getSite());
		} else {
			User user = userManager.getUserByUsername(userandsite[0]);
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
}
