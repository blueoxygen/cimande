package org.blueoxygen.modules.papaje.job;

import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends DefaultRepository<Job>{
	Page<Job> findByNameContaining(String companyId, Pageable pageable);
	Page<Job> findByNameContainingAndLogInformationActiveFlag(String companyId, int status, Pageable pageable);
	Page<Job> findByCompanyIdContaining(String companyId, Pageable pageable);
}
