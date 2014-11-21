package org.blueoxygen.cimande.usersite;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.blueoxygen.cimande.site.Site;
import org.meruvian.yama.core.DefaultJpaPersistence;
import org.meruvian.yama.core.user.JpaUser;

@Entity
@Table(name = "cimande_user_site", uniqueConstraints = { @UniqueConstraint(columnNames = {"user_id", "site_id" }) })
public class UserSite extends DefaultJpaPersistence {
	private JpaUser user = new JpaUser();
	private Site site = new Site();

	@ManyToOne
	@JoinColumn(name = "user_id")
	public JpaUser getUser() {
		return user;
	}
	
	public void setUser(JpaUser user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name = "site_id")
	public Site getSite() {
		return site;
	}
	
	public void setSite(Site site) {
		this.site = site;
	}
}
