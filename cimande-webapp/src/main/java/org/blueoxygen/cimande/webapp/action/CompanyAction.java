package org.blueoxygen.cimande.webapp.action;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.blueoxygen.cimande.security.SessionCredentials;
import org.blueoxygen.modules.papaje.company.Company;
import org.blueoxygen.modules.papaje.company.CompanyService;
import org.blueoxygen.modules.papaje.employer.Employer;
import org.blueoxygen.modules.papaje.employer.EmployerManager;
import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.ActionParam;

import com.opensymphony.xwork2.ActionSupport;

@Action(name = "/backend/company")
public class CompanyAction extends ActionSupport {
	@Inject
	private CompanyService companyService;
	
	@Inject
	private EmployerManager employerManager;
	
	@Action(method = HttpMethod.GET)
	public ActionResult companyForm() {
		ActionResult result = new ActionResult("freemarker", "/view/backend/company/company-form.ftl");
		
		Employer employer = employerManager.findByUserId(SessionCredentials.getCurrentUser().getId());
		if (employer.getCompany() != null)
			result.addToModel("company", employer.getCompany());
		else result.addToModel("company", new Company());
		
		return result;
	}
	
	@Action(method = HttpMethod.POST)
	public ActionResult updateCompany(@ActionParam("company") Company company) {
		validateCompany(company);
		
		if (hasActionErrors())
			return new ActionResult("freemarker", "/view/backend/company/company-form.ftl");
		
		company = companyService.saveCompany(company);
		Employer employer = employerManager.findByUserId(SessionCredentials.getCurrentUser().getId());
		employer.setCompany(company);
		employerManager.saveEmployer(employer);
		
		return new ActionResult("redirect", "/backend/company?success");
	}
	
	private void validateCompany(Company company) {
		if (StringUtils.isBlank(company.getName())) {
			addFieldError("company.name", getText("message.company.name.notempty"));
		}
	}
	
}
