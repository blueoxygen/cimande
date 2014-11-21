package org.meruvian.yama.webapp.action;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.blueoxygen.cimande.site.Site;
import org.blueoxygen.cimande.site.SiteManager;
import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.ActionParam;
import org.springframework.beans.factory.annotation.Value;

import com.opensymphony.xwork2.ActionSupport;

@Action(name = "/landing")
public class LandingAction extends ActionSupport implements ServletRequestAware {

	private HttpServletRequest request;
	
	@Value(value="${site.domain}")
	private String domain;
	
	@Inject
	private SiteManager siteService;
	
	@Action
	public ActionResult index() {
		ActionResult result = new ActionResult("freemarker", "/view/landing-page.ftl");
		String sitename = StringUtils.removeEnd(request.getHeader("host"), "."+domain);
		return site(sitename, result);
	}
	
	@Action(name = "/{sitename}")
	public ActionResult s(@ActionParam("sitename") String sitename){
		ActionResult result = new ActionResult("freemarker", "/view/landing-page.ftl");
		return site(sitename, result);
	}
	
	private ActionResult site(String sitename, ActionResult result) {
		Site site = siteService.findSiteByName(sitename);
		
		result.addToModel("site", site);
		
		return result;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
