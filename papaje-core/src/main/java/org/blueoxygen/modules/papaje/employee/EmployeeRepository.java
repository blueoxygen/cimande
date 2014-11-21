package org.blueoxygen.modules.papaje.employee;

import org.meruvian.yama.core.DefaultRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends DefaultRepository<Employee>{
	Employee findByPersonalInfoUserId(String userId);
	
	Employee findByPersonalInfoUserUsername(String username);
}
