package org.blueoxygen.cimande.webapp.action;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.blueoxygen.cimande.site.Site;
import org.blueoxygen.cimande.site.SiteManager;
import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.ActionParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.opensymphony.xwork2.ActionSupport;

@Action(name = "/admin/sites")
public class SiteAction extends ActionSupport {
	@Inject
	private SiteManager siteService;
	
	@Action(method = HttpMethod.GET)
	public ActionResult siteList(@ActionParam("q") String q, @ActionParam("max") int max, @ActionParam("page") int page) {
		max = max == 0 ? 10 : max;
		
		Page<? extends Site> sites = siteService.findSiteForName(q, new PageRequest(page, max));
		
		return new ActionResult("freemarker", "/view/admin/site/site-list.ftl").addToModel("sites", sites);
	}
	
	@Action(name = "/{sitename}/edit", method = HttpMethod.GET)
	public ActionResult siteForm(@ActionParam("sitename") String sitename) {
		Site site = new Site();
		
		if (!StringUtils.equalsIgnoreCase(sitename, "-"))
			site = siteService.findSiteByName(sitename);
		
		return new ActionResult("freemarker", "/view/admin/site/site-form.ftl").addToModel("site", site);
	}
	
	@Action(name = "/{sitename}/edit", method = HttpMethod.POST)
	public ActionResult updateSite(@ActionParam("sitename") String sitename, @ActionParam("site") Site site) {
		validateSite(site);
		
		if (hasActionErrors())
			return new ActionResult("freemarker", "/view/admin/site/site-form.ftl");
		
		Site s = siteService.saveSite(site);
		
		return new ActionResult("redirect", StringUtils.join("/admin/sites/", s.getName(), "/edit?success"));
	}
	
	@Action(name = "/{sitename}/edit/status", method = HttpMethod.POST)
	public ActionResult updateRoleStatus(@ActionParam("id") final String id, @ActionParam("status") int status) {
		Site site = siteService.findSiteById(id);
		site = siteService.updateStatus(site, status);
		
		String redirectUri = "/admin/sites/" + site.getName() + "/edit?success";
		
		return new ActionResult("redirect", redirectUri);
	}
	
	private void validateSite(Site site) {
		if (StringUtils.isBlank(site.getName())) {
			addFieldError("site.name", getText("message.site.name.notempty"));
		}
	}
}
