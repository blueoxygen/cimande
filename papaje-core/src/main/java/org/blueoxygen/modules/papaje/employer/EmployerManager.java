package org.blueoxygen.modules.papaje.employer;

public interface EmployerManager {
	
	Employer findByUserId(String userId);
	
	Employer saveEmployer(Employer employer);
	
}
