package org.blueoxygen.cimande.rolesite;

import java.util.List;

import org.blueoxygen.cimande.site.Site;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleSiteManager {
	
	RoleSite saveRoleSite(RoleSite roleSite);
	
	void delete(RoleSite roleSite);
	
	RoleSite findById(String roleSiteId);
	
	RoleSite findByRoleIdAndSiteId(String roleId, String siteId);

	Page<RoleSite> findByRole(String roleId, Pageable pageable);
	
	Page<RoleSite> findByRoles(List<String> roleNames, Pageable pageable);
	
	Page<Site> findSiteByRole(String q, String roleId, Pageable pageable);
}
