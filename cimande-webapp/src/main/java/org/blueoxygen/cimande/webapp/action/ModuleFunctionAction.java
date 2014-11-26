package org.blueoxygen.cimande.webapp.action;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.blueoxygen.cimande.modulefunction.ModuleFunction;
import org.blueoxygen.cimande.modulefunction.ModuleFunctionManager;
import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.ActionParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.opensymphony.xwork2.ActionSupport;

@Action(name = "/admin/modulefunctions")
public class ModuleFunctionAction extends ActionSupport {
	@Inject
	private ModuleFunctionManager moduleFunctionManager;
	
	@Action(method = HttpMethod.GET)
	public ActionResult moduleFunctionList(@ActionParam("q") String q, @ActionParam("max") int max, @ActionParam("page") int page) {
		max = max == 0 ? 10 : max;
		
		Page<? extends ModuleFunction> moduleFunctions = moduleFunctionManager.findModuleFunctionByKeyword(q, new PageRequest(page, max));
		
		return new ActionResult("freemarker", "/view/admin/modulefunction/modulefunction-list.ftl").addToModel("moduleFunctions", moduleFunctions);
	}
	
	@Action(name = "/{id}/edit", method = HttpMethod.GET)
	public ActionResult moduleFunctionForm(@ActionParam("id") String id) {
		ModuleFunction moduleFunction = new ModuleFunction();
		
		if (!StringUtils.equalsIgnoreCase(id, "-"))
			moduleFunction = moduleFunctionManager.findModuleFunctionById(id);
		
		return new ActionResult("freemarker", "/view/admin/modulefunction/modulefunction-form.ftl").addToModel("moduleFunction", moduleFunction);
	}
	
	@Action(name = "/{id}/edit", method = HttpMethod.POST)
	public ActionResult updateModuleFunction(@ActionParam("id") String id, @ActionParam("moduleFunction") ModuleFunction moduleFunction) {
		validateModuleFunction(moduleFunction);
		
		if (hasActionErrors())
			return new ActionResult("freemarker", "/view/admin/modulefunction/modulefunction-form.ftl");
		
		ModuleFunction m = moduleFunctionManager.saveModuleFunction(moduleFunction);
		
		return new ActionResult("redirect", StringUtils.join("/admin/modulefunctions/", m.getId(), "/edit?success"));
	}
	
	@Action(name = "/{id}/master", method = HttpMethod.GET)
	public ActionResult masterModuleFunctionList(@ActionParam("id") String id, @ActionParam("q") String q, @ActionParam("max") int max, @ActionParam("page") int page) {
		max = max == 0 ? 10 : max;
		
		Page<? extends ModuleFunction> moduleFunctions = moduleFunctionManager.findMasterModuleFunctionsByKeyword(q, id, new PageRequest(page, max));
		
		return new ActionResult("freemarker", "/view/admin/modulefunction/modulefunction-list.ftl").addToModel("moduleFunctions", moduleFunctions);
	}
	
	@Action(name = "/{id}/delete", method = HttpMethod.POST)
	public ActionResult updateRoleStatus(@ActionParam("id") final String id) {
		ModuleFunction moduleFunction = moduleFunctionManager.findModuleFunctionById(id);
		
		moduleFunctionManager.removeModuleFunction(moduleFunction);
		
		return new ActionResult("redirect", "/admin/modulefunctions/");
	}
	
	private void validateModuleFunction(ModuleFunction moduleFunction) {
		if (StringUtils.isBlank(moduleFunction.getName())) {
			addFieldError("moduleFunction.name", getText("message.moduleFunction.name.notempty"));
		}
		
		if (StringUtils.isBlank(moduleFunction.getModuleUrl())) {
			addFieldError("moduleFunction.moduleUrl", getText("message.moduleFunction.moduleUrl.notempty"));
		}
	}
}
