package org.blueoxygen.modules.papaje.employer;

import org.meruvian.yama.core.DefaultRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends DefaultRepository<Employer>{
	Employer findByPersonalInfoUserId(String userId);
}
