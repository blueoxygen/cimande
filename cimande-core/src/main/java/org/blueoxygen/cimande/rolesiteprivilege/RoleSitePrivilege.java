package org.blueoxygen.cimande.rolesiteprivilege;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.blueoxygen.cimande.core.CimandeJpaPersistence;
import org.blueoxygen.cimande.modulefunction.ModuleFunction;
import org.blueoxygen.cimande.rolesite.RoleSite;
import org.blueoxygen.cimande.site.Site;

@Entity
@Table(name = "cimande_role_site_privilege")
public class RoleSitePrivilege extends CimandeJpaPersistence {
	private RoleSite roleSite = new RoleSite();
	private Site site = new Site();
	private ModuleFunction moduleFunction = new ModuleFunction();
	private int privilege_flag;
	private String url_xml;

	@ManyToOne
	@JoinColumn(name = "role_site_id")
	public RoleSite getRoleSite() {
		return roleSite;
	}
	
	public void setRoleSite(RoleSite roleSite) {
		this.roleSite = roleSite;
	}

	@ManyToOne
	@JoinColumn(name = "site_id")
	public Site getSite() {
		return site;
	}
	
	public void setSite(Site site) {
		this.site = site;
	}

	@ManyToOne
	@JoinColumn(name = "module_function_id")
	public ModuleFunction getModuleFunction() {
		return moduleFunction;
	}
	
	public void setModuleFunction(ModuleFunction moduleFunction) {
		this.moduleFunction = moduleFunction;
	}
	
	public int getPrivilege_flag() {
		return privilege_flag;
	}
	
	public void setPrivilege_flag(int privilege_flag) {
		this.privilege_flag = privilege_flag;
	}
	
	public String getUrl_xml() {
		return url_xml;
	}

	public void setUrl_xml(String url_xml) {
		this.url_xml = url_xml;
	}
}
