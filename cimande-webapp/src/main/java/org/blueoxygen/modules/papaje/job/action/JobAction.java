package org.blueoxygen.modules.papaje.job.action;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.blueoxygen.cimande.security.SessionCredentials;
import org.blueoxygen.modules.papaje.category.Category;
import org.blueoxygen.modules.papaje.category.CategoryManager;
import org.blueoxygen.modules.papaje.employee.Employee;
import org.blueoxygen.modules.papaje.employee.EmployeeManager;
import org.blueoxygen.modules.papaje.employer.Employer;
import org.blueoxygen.modules.papaje.employer.EmployerManager;
import org.blueoxygen.modules.papaje.job.Job;
import org.blueoxygen.modules.papaje.job.JobManager;
import org.blueoxygen.modules.papaje.jobemployee.JobEmployeeManager;
import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.ActionParam;
import org.meruvian.yama.core.LogInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.opensymphony.xwork2.ActionSupport;

@Action(name = "/backend/jobs")
public class JobAction extends ActionSupport {
	@Inject
	private JobManager jobManager;
	
	@Inject
	private EmployerManager employerManager;
	
	@Inject
	private EmployeeManager employeeManager;
	
	@Inject
	private JobEmployeeManager jobEmployeeManager;
	
	@Inject
	private CategoryManager categoryManager;
	
	@Action(method = HttpMethod.GET)
	public ActionResult jobList(@ActionParam("q") String q, @ActionParam("max") int max, @ActionParam("page") int page) {
		max = max == 0 ? 10 : max;
		
		Page<? extends Job> jobs = jobManager.findByName(q, new PageRequest(page, max));
		
		return new ActionResult("freemarker", "/view/papaje/job/job-list.ftl").addToModel("jobs", jobs);
	}

	@Action(name = "/{id}/edit", method = HttpMethod.GET)
	public ActionResult jobForm(@ActionParam("id") String id) {
		Job job = new Job();
		
		if (!StringUtils.equalsIgnoreCase(id, "-"))
			job = jobManager.findById(id);
		
		Employer employer = employerManager.findByUserId(SessionCredentials.getCurrentUser().getId());
		if(employer.getCompany() != null)
			job.setCompany(employer.getCompany());
		
		Page<? extends Category> categories = categoryManager.findCategoryByName( "", new PageRequest(0, 10));
		
		return new ActionResult("freemarker", "/view/papaje/job/job-form.ftl")
			.addToModel("job", job)
			.addToModel("categories", categories);
	}

	@Action(name = "/{id}/edit", method = HttpMethod.POST)
	public ActionResult updateJob(@ActionParam("id") String id, @ActionParam("job") Job job) {
		validateJob(job);
		
		if (hasActionErrors())
			return new ActionResult("freemarker", "/view/papaje/job/job-form.ftl");
		
		jobManager.saveJob(job);
		
		return new ActionResult("redirect", "/backend/jobs?success");
	}
	
	@Action(name = "/{id}/edit/status", method = HttpMethod.POST)
	public ActionResult updateRoleStatus(@ActionParam("id") final String id, @ActionParam("status") int status) {
		Job job = jobManager.findById(id);
		job = jobManager.updateStatus(job, status);
		
		String redirectUri = "/backend/jobs/" + job.getName() + "/edit?success";
		
		return new ActionResult("redirect", redirectUri);
	}
	
	@Action(name = "/list", method = HttpMethod.GET)
	public ActionResult jobsAll(@ActionParam("q") String q, @ActionParam("max") int max, @ActionParam("page") int page) {
		max = max == 0 ? 10 : max;
		
		Page<? extends Job> jobs = jobManager.findByName(q, LogInformation.ACTIVE, new PageRequest(page, max));
		
		return new ActionResult("freemarker", "/view/papaje/job/job-all.ftl").addToModel("jobs", jobs);
	}
	
	@Action(name = "/{id}/apply", method = HttpMethod.POST)
	public ActionResult jobApply(@ActionParam("id") String id) {
		Employee employee = employeeManager.findByUsername(SessionCredentials.getCurrentUsername());
		
		if (employee != null)
			jobEmployeeManager.saveJobEmployee(id, employee.getId());
		
		return null;
	}
	
	@Action(name = "/applied", method = HttpMethod.GET)
	public ActionResult jobsApplied(@ActionParam("q") String q, @ActionParam("max") int max, @ActionParam("page") int page) {
		max = max == 0 ? 10 : max;
		
		String employeeId = employeeManager.findByUsername(SessionCredentials.getCurrentUsername()).getId();
		Page<? extends Job> jobs = jobEmployeeManager.findAppliedJobByEmployee(q, employeeId, new PageRequest(page, max));
		
		return new ActionResult("freemarker", "/view/papaje/job/job-applied.ftl").addToModel("jobs", jobs);
	}
	
	@Action(name = "/{id}/applier", method = HttpMethod.GET)
	public ActionResult jobsApplier(@ActionParam("id") String id, @ActionParam("q") String q, 
			@ActionParam("max") int max, @ActionParam("page") int page) {
		max = max == 0 ? 10 : max;
		
		Job job = jobManager.findById(id);
		Page<? extends Employee> employees = jobEmployeeManager.findJobApplierByJobId(q, id, new PageRequest(page, max));
		
		return new ActionResult("freemarker", "/view/papaje/job/job-applier.ftl")
			.addToModel("employees", employees)
			.addToModel("job", job);
	}
	
	private void validateJob(Job job) {
		if (StringUtils.isBlank(job.getName())) {
			addFieldError("job.name", getText("message.job.name.notempty"));
		}
	}
	
}
