package org.blueoxygen.cimande.webapp.action;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.blueoxygen.cimande.modulefunction.ModuleFunction;
import org.blueoxygen.cimande.rolesite.RoleSite;
import org.blueoxygen.cimande.rolesite.RoleSiteManager;
import org.blueoxygen.cimande.site.Site;
import org.blueoxygen.cimande.site.SiteManager;
import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.ActionParam;
import org.meruvian.yama.core.role.JpaRole;
import org.meruvian.yama.core.role.JpaRoleManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.opensymphony.xwork2.ActionSupport;

@Action(name = "/admin/rolesite")
public class RoleSiteAction extends ActionSupport {
	@Inject
	private RoleSiteManager roleSiteManager;
	
	@Inject
	private SiteManager siteManager;
	
	@Inject
	private JpaRoleManager roleManager;
	
	@Action(name = "/{roleId}/edit", method = HttpMethod.GET)
	public ActionResult roleSiteForm(@ActionParam("roleId") String roleId) {
		Page<? extends RoleSite> roleSites = roleSiteManager.findByRole(roleId, new PageRequest(0, 10));
		
		return new ActionResult("freemarker", "/view/admin/rolesite/rolesite-list.ftl")
			.addToModel("roleSites", roleSites)
			.addToModel("role", new JpaRole(roleManager.getRoleById(roleId)));
	}

	@Action(name = "/{roleId}/edit", method = HttpMethod.POST)
	public ActionResult updateRoleSite(@ActionParam("roleId") String roleId, @ActionParam("siteId") String siteId) {
		if (StringUtils.isNotBlank(roleId) && StringUtils.isNotBlank(siteId)) {
			RoleSite roleSite = new RoleSite();
			roleSite.setRole(new JpaRole(roleManager.getRoleById(roleId)));
			roleSite.setSite(siteManager.findSiteById(siteId));
			
			roleSiteManager.saveRoleSite(roleSite);
		}
		return new ActionResult("redirect", StringUtils.join("/admin/rolesite/", roleId, "/edit?success"));
	}
	
	@Action(name = "/{roleSiteId}/delete", method = HttpMethod.POST)
	public ActionResult removeRoleSite(@ActionParam("roleSiteId") String roleSiteId) {
		roleSiteManager.delete(roleSiteManager.findById(roleSiteId));
		
		return null;
	}

	@Action(name = "/{roleId}/site", method = HttpMethod.GET)
	public ActionResult findSite(@ActionParam("q") String q, @ActionParam("roleId") String roleId) {
		Page<? extends Site> sites = roleSiteManager.findSiteByRole(q, roleId,  new PageRequest(0, 10));
		
		return new ActionResult("freemarker", "/view/admin/site/site-list.ftl")
		.addToModel("sites", sites);
	}
}
