package org.meruvian.yama.webapp.action;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;

import com.opensymphony.xwork2.ActionSupport;

@Action(name = "/dashboard")
public class DashboardAction extends ActionSupport{
	
	@Action
	public ActionResult index() {
		return new ActionResult("freemarker", "/view/dashboard.ftl");
	}
}
