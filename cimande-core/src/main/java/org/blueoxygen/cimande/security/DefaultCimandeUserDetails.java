package org.blueoxygen.cimande.security;

import java.util.Collection;

import org.blueoxygen.cimande.site.Site;
import org.meruvian.yama.web.security.DefaultUserDetails;
import org.springframework.security.core.GrantedAuthority;

public class DefaultCimandeUserDetails extends DefaultUserDetails {
	private String siteId;
	private Site site;

	public DefaultCimandeUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public DefaultCimandeUserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}
}
