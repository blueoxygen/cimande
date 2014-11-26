package org.blueoxygen.modules.papaje.registration;

import javax.inject.Inject;

import org.blueoxygen.modules.papaje.employee.Employee;
import org.blueoxygen.modules.papaje.employee.EmployeeRepository;
import org.blueoxygen.modules.papaje.employer.Employer;
import org.blueoxygen.modules.papaje.employer.EmployerRepository;
import org.blueoxygen.modules.papaje.user.PersonalInfo;
import org.blueoxygen.modules.papaje.user.PersonalInfoRepository;
import org.meruvian.yama.core.user.JpaUser;
import org.meruvian.yama.core.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DefaultRegistrationManager implements RegistrationManager {

	@Inject
	private EmployeeRepository employeeRepository;
	
	@Inject
	private EmployerRepository employerRepository;
	
	@Inject
	private PersonalInfoRepository personalInfoRepository;
	
	@Override
	@Transactional
	public User register(User user, String roleName) {
		
		PersonalInfo personalInfo = new PersonalInfo();
		personalInfo.setUser(new JpaUser(user));
		personalInfo.setId(null);
		personalInfoRepository.save(personalInfo);
		
		if ("EMPLOYEE".equalsIgnoreCase(roleName)) {
			Employee employee = new Employee();
			employee.setId(null);
			employee.setPersonalInfo(personalInfo);
			
			employeeRepository.save(employee);
		} else if ("EMPLOYER".equalsIgnoreCase(roleName)) {
			Employer employer = new Employer();
			employer.setId(null);
			employer.setPersonalInfo(personalInfo);
			
			employerRepository.save(employer);
		}
		
		return user;
	}
	
}
