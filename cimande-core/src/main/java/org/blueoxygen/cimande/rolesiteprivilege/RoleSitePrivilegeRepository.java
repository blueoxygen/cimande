package org.blueoxygen.cimande.rolesiteprivilege;

import java.util.List;

import org.blueoxygen.cimande.modulefunction.ModuleFunction;
import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleSitePrivilegeRepository extends DefaultRepository<RoleSitePrivilege> {
	@Query("SELECT rsp FROM RoleSitePrivilege rsp WHERE rsp.roleSite.id = ?1 "
			+ " AND rsp.logInformation.activeFlag = ?2 ")
	Page<RoleSitePrivilege> findByRoleSite(String roleSiteId, int status, Pageable pageable);
	
	@Query("SELECT rsp.moduleFunction FROM RoleSitePrivilege rsp WHERE rsp.roleSite.id IN "
			+ " (SELECT rs.id FROM RoleSite rs WHERE rs.role.name IN ?1 AND rs.site.id = ?2 AND rs.logInformation.activeFlag = ?3 ) "
			+ " AND rsp.logInformation.activeFlag = ?3 ")
	Page<ModuleFunction> findModuleFunctionByRoleSite(List<String> roles, String siteId, int status, Pageable pageable);
}
