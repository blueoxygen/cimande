package org.blueoxygen.modules.papaje.jobemployee;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.blueoxygen.cimande.core.CimandeJpaPersistence;
import org.blueoxygen.modules.papaje.employee.Employee;
import org.blueoxygen.modules.papaje.job.Job;

@Entity
@Table(name = "papaje_job_employee")
public class JobEmployee extends CimandeJpaPersistence {
	private Employee employee;
	private Job job;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@ManyToOne
	@JoinColumn(name = "job_id")
	public Job getJob() {
		return job;
	}
	
	public void setJob(Job job) {
		this.job = job;
	}
}
