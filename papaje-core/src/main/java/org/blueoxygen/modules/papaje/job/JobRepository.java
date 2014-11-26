package org.blueoxygen.modules.papaje.job;

import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends DefaultRepository<Job>{
	Page<Job> findByNameContainingAndLogInformationSite(String companyId, String siteId, Pageable pageable);
	Page<Job> findByNameContainingAndLogInformationActiveFlagAndLogInformationSite(String companyId, int status, String siteId, Pageable pageable);
	Page<Job> findByCompanyIdContainingAndLogInformationSite(String companyId, String siteId, Pageable pageable);
}
