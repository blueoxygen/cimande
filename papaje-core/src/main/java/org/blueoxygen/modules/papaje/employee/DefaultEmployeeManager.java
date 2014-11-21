package org.blueoxygen.modules.papaje.employee;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.yama.core.user.JpaUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DefaultEmployeeManager implements EmployeeManager {

	@Inject
	private EmployeeRepository employeeRepository;
	
	@Override
	@Transactional
	public Employee saveEmployee(Employee employee) {
		if (StringUtils.isBlank(employee.getId())) {
			employee.setId(null);
			
			return employeeRepository.save(employee);
		} else {
			Employee e = employeeRepository.findById(employee.getId());
			e.setPersonalInfo(employee.getPersonalInfo());
			e.setPersonalPage(employee.getPersonalPage());
			e.setBlogPage(employee.getBlogPage());
			e.setCompanyPage(employee.getCompanyPage());
			
			return e;
		}
	}

	@Override
	public Employee findByUsername(String username) {
		return employeeRepository.findByPersonalInfoUserUsername(username);
	}

	@Override
	public Employee findEmployeeById(String id) {
		return employeeRepository.findByPersonalInfoUserId(id);
	}
	
}
