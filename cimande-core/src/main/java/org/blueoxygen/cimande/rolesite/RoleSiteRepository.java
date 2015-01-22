package org.blueoxygen.cimande.rolesite;

import java.util.List;

import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleSiteRepository extends DefaultRepository<RoleSite> {
	RoleSite findByRoleIdAndSiteId(String roleId, String siteId);
	
	Page<RoleSite> findByRoleId(String roleId, Pageable pageable);

	@Query("SELECT rs FROM RoleSite rs WHERE rs.role.name IN ?1 AND rs.logInformation.activeFlag = ?2 ")
	Page<RoleSite> findByRoles(List<String> roles, int status, Pageable pageable);
}
