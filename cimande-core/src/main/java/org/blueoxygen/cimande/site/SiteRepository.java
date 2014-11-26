package org.blueoxygen.cimande.site;

import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends DefaultRepository<Site>{
	Site findByName(String name);
	
	Site findByVirtualHost(String virtualHost);
	
	Page<Site> findByNameContainingAndLogInformationActiveFlag(String name, int activeFlag, Pageable pageable);
}
