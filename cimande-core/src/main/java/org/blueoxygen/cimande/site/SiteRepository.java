package org.blueoxygen.cimande.site;

import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends DefaultRepository<Site>{
	Site findByName(String name);
	
	Site findByVirtualHost(String virtualHost);
	
	Page<Site> findByNameContainingAndLogInformationActiveFlag(String name, int activeFlag, Pageable pageable);
	
	@Query("SELECT s FROM Site s WHERE (s.name LIKE %?1% OR s.description LIKE %?1%) "
			+ " AND s.id NOT IN (SELECT site.id FROM RoleSite WHERE role.id = ?2 AND logInformation.activeFlag = ?3) "
			+ " AND s.logInformation.activeFlag = ?3 ")
	Page<Site> findByRole(String q, String roleId, int status, Pageable pageable);
}
