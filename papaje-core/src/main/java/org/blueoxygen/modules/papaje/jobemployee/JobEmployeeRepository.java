package org.blueoxygen.modules.papaje.jobemployee;

import org.blueoxygen.modules.papaje.employee.Employee;
import org.blueoxygen.modules.papaje.job.Job;
import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JobEmployeeRepository extends DefaultRepository<JobEmployee>{
	JobEmployee findByJobIdAndEmployeeIdAndLogInformationActiveFlag(String jobId, String employeeId, int status);
	
	@Query("SELECT je.job FROM JobEmployee je WHERE je.employee.id = ?2 AND je.job.name LIKE ?1%")
	Page<Job> findJobByEmployeeId(String q, String employeeId, Pageable pageable);
	
	@Query("SELECT je.job FROM JobEmployee je WHERE je.employee.id = ?1")
	Page<Job> findJobByEmployeeId(String employeeId, Pageable pageable);

	@Query("SELECT je.employee FROM JobEmployee je WHERE je.job.id = ?2 AND (je.employee.personalInfo.user.name.first LIKE ?1% OR je.employee.personalInfo.user.name.last LIKE ?1% )")
	Page<Employee> findEmployeeByJobId(String q, String jobId, Pageable pageable);
}
