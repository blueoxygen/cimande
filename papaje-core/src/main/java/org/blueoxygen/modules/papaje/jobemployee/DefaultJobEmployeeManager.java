package org.blueoxygen.modules.papaje.jobemployee;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.blueoxygen.modules.papaje.employee.Employee;
import org.blueoxygen.modules.papaje.employee.EmployeeRepository;
import org.blueoxygen.modules.papaje.job.Job;
import org.blueoxygen.modules.papaje.job.JobRepository;
import org.meruvian.yama.core.LogInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DefaultJobEmployeeManager implements JobEmployeeManager {
	@Inject
	private JobEmployeeRepository jobEmployeeRepository;
	
	@Inject
	private JobRepository jobRepository;
	
	@Inject
	private EmployeeRepository employeeRepository;
	
	@Override
	@Transactional
	public JobEmployee saveJobEmployee(JobEmployee jobEmployee) {
		if (StringUtils.isBlank(jobEmployee.getId())) {
			jobEmployee.setId(null);
			
			return jobEmployeeRepository.save(jobEmployee);
		} else {
			JobEmployee je = jobEmployeeRepository.findById(jobEmployee.getId());
			je.setEmployee(jobEmployee.getEmployee());
			je.setJob(jobEmployee.getJob());
			
			return je;
		}
	}
	
	@Override
	@Transactional
	public JobEmployee saveJobEmployee(String jobId, String employeeId) {
		JobEmployee jobEmployee = findByJobIdAndEmployeeId(jobId, employeeId);
		if (jobEmployee == null) {
			jobEmployee = new JobEmployee();
			jobEmployee.setId(null);
			jobEmployee.setJob(jobRepository.findById(jobId));
			jobEmployee.setEmployee(employeeRepository.findById(employeeId));
			jobEmployee = jobEmployeeRepository.save(jobEmployee);
		}
		return jobEmployee;
	}

	@Override
	public JobEmployee findByJobIdAndEmployeeId(String jobId, String employeeId) {
		return jobEmployeeRepository.findByJobIdAndEmployeeIdAndLogInformationActiveFlag(jobId, employeeId, LogInformation.ACTIVE);
	}

	@Override
	public Page<Job> findAppliedJobByEmployee(String q, String employeeId, Pageable pageable) {
		q = StringUtils.defaultIfBlank(q, "");
		return jobEmployeeRepository.findJobByEmployeeId(q, employeeId, pageable);
//		return jobEmployeeRepository.findJobByEmployeeId(employeeId, pageable);
	}

	@Override
	public Page<Employee> findJobApplierByJobId(String q, String jobId, Pageable pageable) {
		return jobEmployeeRepository.findEmployeeByJobId(q, jobId, pageable);
	}
}
