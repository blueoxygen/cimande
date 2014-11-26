package org.blueoxygen.modules.papaje.job.action;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.blueoxygen.cimande.site.Site;
import org.blueoxygen.modules.papaje.category.Category;
import org.blueoxygen.modules.papaje.category.CategoryManager;
import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.ActionParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.opensymphony.xwork2.ActionSupport;

@Action(name = "/backend/categories")
public class CategoryAction extends ActionSupport {
	@Inject
	private CategoryManager categoryManager;
	
	@Action(method = HttpMethod.GET)
	public ActionResult categoryList(@ActionParam("q") String q, @ActionParam("max") int max, @ActionParam("page") int page) {
		max = max == 0 ? 10 : max;
		
		Page<? extends Category> categories = categoryManager.findCategoryByName(q, new PageRequest(page, max));
		
		return new ActionResult("freemarker", "/view/papaje/category/category-list.ftl").addToModel("categories", categories);
	}
	
	@Action(name = "/{id}/edit", method = HttpMethod.GET)
	public ActionResult categoryForm(@ActionParam("id") String id) {
		Category category = new Category();
		
		if (!StringUtils.equalsIgnoreCase(id, "-"))
			category = categoryManager.findCategoryById(id);
		
		return new ActionResult("freemarker", "/view/papaje/category/category-form.ftl").addToModel("category", category);
	}
	
	@Action(name = "/{id}/edit", method = HttpMethod.POST)
	public ActionResult updateCategory(@ActionParam("id") String id, @ActionParam("category") Category category) {
		validateCategory(category);
		
		if (hasActionErrors())
			return new ActionResult("freemarker", "/view/papaje/category/category-form.ftl");
		
		Category c = categoryManager.saveCategory(category);
		
		return new ActionResult("redirect", StringUtils.join("/backend/categories/", c.getId(), "/edit?success"));
	}
	
	@Action(name = "/{id}/edit/status", method = HttpMethod.POST)
	public ActionResult updateCategoryStatus(@ActionParam("id") final String id, @ActionParam("status") int status) {
		Category category = categoryManager.findCategoryById(id);
		category = categoryManager.updateStatus(category, status);
		
		String redirectUri = "/backend/categories/" + category.getName() + "/edit?success";
		
		return new ActionResult("redirect", redirectUri);
	}
	
	@Action(name = "/{id}/delete", method = HttpMethod.POST)
	public ActionResult updateRoleStatus(@ActionParam("id") final String id) {
		Category category = categoryManager.findCategoryById(id);
		
		categoryManager.removeCategory(category);
		
		return new ActionResult("redirect", "/backend/categories/");
	}
	
	private void validateCategory(Category category) {
		if (StringUtils.isBlank(category.getName())) {
			addFieldError("category.name", getText("message.category.name.notempty"));
		}
	}
}
