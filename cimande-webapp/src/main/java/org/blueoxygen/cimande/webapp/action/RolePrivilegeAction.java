package org.blueoxygen.cimande.webapp.action;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.blueoxygen.cimande.modulefunction.ModuleFunction;
import org.blueoxygen.cimande.modulefunction.ModuleFunctionManager;
import org.blueoxygen.cimande.roleprivilege.RolePrivilege;
import org.blueoxygen.cimande.roleprivilege.RolePrivilegeManager;
import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.ActionParam;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.yama.core.role.JpaRole;
import org.meruvian.yama.core.role.JpaRoleManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.opensymphony.xwork2.ActionSupport;

@Action(name = "/admin/roleprivilege")
public class RolePrivilegeAction extends ActionSupport {
	@Inject
	private RolePrivilegeManager rolePrivilegeManager;
	
	@Inject
	private ModuleFunctionManager moduleFunctionManager;
	
	@Inject
	private JpaRoleManager roleManager;
	
	@Action(name = "/{roleId}/edit", method = HttpMethod.GET)
	public ActionResult rolePrivilegeForm(@ActionParam("roleId") String roleId) {
		Page<? extends RolePrivilege> rolePrivileges = rolePrivilegeManager.findRolePrivilegeByRole(roleId, new PageRequest(0, 10));
		
		return new ActionResult("freemarker", "/view/admin/roleprivilege/roleprivilege-list.ftl")
			.addToModel("rolePrivileges", rolePrivileges)
			.addToModel("role", new JpaRole(roleManager.getRoleById(roleId)));
	}

	@Action(name = "/{roleId}/edit", method = HttpMethod.POST)
	public ActionResult updateRolePrivilege(@ActionParam("roleId") String roleId, @ActionParam("moduleFunctionId") String moduleFunctionId) {
		if (StringUtils.isNotBlank(roleId) && StringUtils.isNotBlank(moduleFunctionId)) {
			RolePrivilege rolePrivilege = new RolePrivilege();
			rolePrivilege.setRole(new JpaRole(roleManager.getRoleById(roleId)));
			rolePrivilege.setModuleFunction(moduleFunctionManager.findModuleFunctionById(moduleFunctionId));
			
			rolePrivilegeManager.saveRolePrivilege(rolePrivilege);
		}
		return new ActionResult("redirect", StringUtils.join("/admin/roleprivilege/", roleId, "/edit?success"));
	}
	
	@Action(name = "/{rolePrivilegeId}/delete", method = HttpMethod.POST)
	public ActionResult removeRolePrivilege(@ActionParam("rolePrivilegeId") String rolePrivilegeId) {
		rolePrivilegeManager.removeRolePrivilege(rolePrivilegeManager.findRolePrivilegeById(rolePrivilegeId));
		
		return null;
	}
	
	@Action(name = "/{roleId}/modulefunction", method = HttpMethod.GET)
	public ActionResult findModuleFunction(@ActionParam("q") String q, @ActionParam("roleId") String roleId) {
		Page<? extends ModuleFunction> moduleFunctions = rolePrivilegeManager.findUnselectedModuleFunctionByRole(q, roleId,  new PageRequest(0, 10));
		
		return new ActionResult("freemarker", "/view/admin/modulefunction/modulefunction-list.ftl")
		.addToModel("moduleFunctions", moduleFunctions);
	}
}
