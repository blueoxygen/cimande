package org.blueoxygen.modules.papaje.jobemployee;

import org.blueoxygen.modules.papaje.employee.Employee;
import org.blueoxygen.modules.papaje.job.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobEmployeeManager {
	JobEmployee saveJobEmployee(JobEmployee jobEmployee);
	
	JobEmployee saveJobEmployee(String jobId, String employeeId);
	
	JobEmployee findByJobIdAndEmployeeId(String jobId, String employeeId);
	
	Page<Job> findAppliedJobByEmployee(String q, String employeeId, Pageable pageable); 
	
	Page<Employee> findJobApplierByJobId(String q, String jobId, Pageable pageable);
	 
}
