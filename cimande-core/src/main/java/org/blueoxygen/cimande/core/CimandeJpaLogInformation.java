package org.blueoxygen.cimande.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.meruvian.yama.core.JpaLogInformation;
import org.meruvian.yama.core.LogInformation;

@Embeddable
public class CimandeJpaLogInformation extends JpaLogInformation {
	private String site;
	
	public CimandeJpaLogInformation() {
	}
	
	public CimandeJpaLogInformation(LogInformation logInfo) {
		super(logInfo);
	}
	
	public CimandeJpaLogInformation(LogInformation logInfo, String site) {
		super(logInfo);
		this.site = site;
	}

	
	@Column(name = "site")
	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	@Column(name = "create_by")
	public String getCreateBy() {
		return super.getCreateBy();
	}

	@Column(name = "create_date")
	public Date getCreateDate() {
		return super.getCreateDate();
	}

	@Column(name = "update_by")
	public String getLastUpdateBy() {
		return super.getLastUpdateBy();
	}

	@Column(name = "update_date")
	public Date getLastUpdateDate() {
		return super.getLastUpdateDate();
	}

	@Column(name = "active_flag")
	public int getActiveFlag() {
		return super.getActiveFlag();
	}

	@Transient
	public boolean isActive() {
		return (getActiveFlag() == ACTIVE);
	}

	@Transient
	public boolean isInactive() {
		return (getActiveFlag() == INACTIVE);
	}
}
