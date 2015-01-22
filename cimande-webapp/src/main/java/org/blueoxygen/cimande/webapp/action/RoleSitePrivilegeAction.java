package org.blueoxygen.cimande.webapp.action;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.blueoxygen.cimande.modulefunction.ModuleFunction;
import org.blueoxygen.cimande.modulefunction.ModuleFunctionManager;
import org.blueoxygen.cimande.rolesite.RoleSiteManager;
import org.blueoxygen.cimande.rolesiteprivilege.RoleSitePrivilege;
import org.blueoxygen.cimande.rolesiteprivilege.RoleSitePrivilegeManager;
import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.ActionParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.opensymphony.xwork2.ActionSupport;

@Action(name = "/admin/rolesiteprivilege")
public class RoleSitePrivilegeAction extends ActionSupport {
	@Inject
	private RoleSitePrivilegeManager roleSitePrivilegeManager;
	
	@Inject
	private RoleSiteManager roleSiteManager;
	
	@Inject
	private ModuleFunctionManager moduleFunctionManager;
	
	@Action(name = "/{roleSiteId}/edit", method = HttpMethod.GET)
	public ActionResult roleSitePrivilegeForm(@ActionParam("roleSiteId") String roleSiteId) {
		Page<? extends RoleSitePrivilege> roleSitePrivileges = roleSitePrivilegeManager
				.findRoleSitePrivilegeByRoleSite(roleSiteId, new PageRequest(0, 10));
		
		return new ActionResult("freemarker", "/view/admin/rolesiteprivilege/rolesiteprivilege-list.ftl")
			.addToModel("roleSitePrivileges", roleSitePrivileges)
			.addToModel("roleSite", roleSiteManager.findById(roleSiteId));
	}

	@Action(name = "/{roleSiteId}/edit", method = HttpMethod.POST)
	public ActionResult updateRoleSitePrivilege(@ActionParam("roleSiteId") String roleSiteId, 
			@ActionParam("moduleFunctionId") String moduleFunctionId) {
		if (StringUtils.isNotBlank(roleSiteId) && StringUtils.isNotBlank(moduleFunctionId)) {
			RoleSitePrivilege roleSitePrivilege = new RoleSitePrivilege();
			roleSitePrivilege.setRoleSite(roleSiteManager.findById(roleSiteId));
			roleSitePrivilege.setSite(roleSitePrivilege.getRoleSite().getSite());
			roleSitePrivilege.setModuleFunction(moduleFunctionManager.findModuleFunctionById(moduleFunctionId));
			
			roleSitePrivilegeManager.saveRoleSitePrivilege(roleSitePrivilege);
		}
		return new ActionResult("redirect", StringUtils.join("/admin/rolesiteprivilege/", roleSiteId, "/edit?success"));
	}
	
	@Action(name = "/{roleSitePrivilegeId}/delete", method = HttpMethod.POST)
	public ActionResult removeRoleSitePrivilege(@ActionParam("roleSitePrivilegeId") String roleSitePrivilegeId) {
		roleSitePrivilegeManager.removeRoleSitePrivilege(roleSitePrivilegeId);
		return null;
	}
	
	@Action(name = "/{roleSiteId}/modulefunction", method = HttpMethod.GET)
	public ActionResult findModuleFunction(@ActionParam("q") String q, @ActionParam("roleSiteId") String roleSiteId) {
		Page<? extends ModuleFunction> moduleFunctions = roleSitePrivilegeManager
				.findUnselectedModuleFunctionByRoleSite(q, roleSiteId,  new PageRequest(0, 10));
		
		return new ActionResult("freemarker", "/view/admin/modulefunction/modulefunction-list.ftl")
		.addToModel("moduleFunctions", moduleFunctions);
	}
}
