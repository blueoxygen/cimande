package org.blueoxygen.cimande.rolesite;

import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleSiteRepository extends DefaultRepository<RoleSite> {
	RoleSite findByRoleIdAndSiteId(String roleId, String siteId);
	
	Page<RoleSite> findByRoleId(String roleId, Pageable pageable);
}
