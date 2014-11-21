package org.blueoxygen.cimande.webapp.action;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.blueoxygen.cimande.site.SiteManager;
import org.blueoxygen.cimande.usersite.UserSite;
import org.blueoxygen.cimande.usersite.UserSiteManager;
import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.ActionParam;
import org.meruvian.yama.core.user.JpaUserManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.opensymphony.xwork2.ActionSupport;

@Action(name = "/admin/usersite")
public class UserSiteAction extends ActionSupport {
	@Inject
	private UserSiteManager userSiteService;
	
	@Inject
	private SiteManager siteService;
	
	@Inject
	private JpaUserManager userManager;
	
	@Action(name = "/{username}/edit", method = HttpMethod.GET)
	public ActionResult userSiteForm(@ActionParam("username") String username) {
		Page<? extends UserSite> userSites = userSiteService.findByUser(username, new PageRequest(0, 10));
		
		return new ActionResult("freemarker", "/view/admin/site/usersite/usersite-list.ftl")
			.addToModel("userSites", userSites)
			.addToModel("username", username);
	}
	
	@Action(name = "/{username}/edit", method = HttpMethod.POST)
	public ActionResult updateUserSite(@ActionParam("username") String username, @ActionParam("siteId") String siteId) {
		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(siteId)) {
			UserSite userSite = new UserSite();
			userSite.setSite(siteService.findSiteById(siteId));
			userSite.setUser(userManager.getUserByUsername(username));
			
			userSiteService.saveUserSite(userSite);
		}
		
		return new ActionResult("redirect", StringUtils.join("/admin/usersite/", username, "/edit?success"));
	}
	
}
