package org.blueoxygen.cimande.social;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.blueoxygen.cimande.core.CimandeJpaPersistence;

@Entity
@Table(name = "cimande_social_config")
public class SocialConfiguration extends CimandeJpaPersistence {
	private String appId;
	private String appSecret;
	private String redirectUri;
	private String scope;
	private String state;
	private boolean active = true;

	@Column(name = "app_id", unique = true)
	public String getAppId() {
		return appId;
	}
	
	public void setAppId(String appId) {
		this.appId = appId;
	}

	@Column(name = "app_secret", unique = true)
	public String getAppSecret() {
		return appSecret;
	}
	
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	@Column(name = "redirect_uri", unique = true)
	public String getRedirectUri() {
		return redirectUri;
	}
	
	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
	
	public String getScope() {
		return scope;
	}
	
	public void setScope(String scope) {
		this.scope = scope;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
}
