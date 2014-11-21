package org.blueoxygen.cimande.rolesite;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.blueoxygen.cimande.site.Site;
import org.meruvian.yama.core.DefaultJpaPersistence;
import org.meruvian.yama.core.role.JpaRole;

@Entity
@Table(name = "cimande_role_site", uniqueConstraints = { @UniqueConstraint(columnNames = {"role_id", "site_id" }) })
public class RoleSite extends DefaultJpaPersistence {
	private JpaRole role = new JpaRole();
	private Site site = new Site();

	@ManyToOne
	@JoinColumn(name = "role_id")
	public JpaRole getRole() {
		return role;
	}
	
	public void setRole(JpaRole role) {
		this.role = role;
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
