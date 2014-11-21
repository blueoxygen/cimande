package org.blueoxygen.modules.papaje.employee;


public interface EmployeeManager {
	Employee saveEmployee(Employee employee);
	
	Employee findByUsername(String username);
	
	Employee findEmployeeById(String id);
}
