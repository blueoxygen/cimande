package org.blueoxygen.cimande.rolesiteprivilege;

import java.util.List;

import org.blueoxygen.cimande.modulefunction.ModuleFunction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleSitePrivilegeManager {
	RoleSitePrivilege saveRoleSitePrivilege(RoleSitePrivilege roleSitePrivilege);
	
	boolean removeRoleSitePrivilege(String roleSitePrivilegeId);
	
	RoleSitePrivilege findRoleSitePrivilegeById(String id);
	
	Page<RoleSitePrivilege> findRoleSitePrivilegeByRoleSite(String roleSiteId, Pageable pageable);
	
	Page<ModuleFunction> findModuleFunctionByRoleSite(List<String> roles, String siteId, Pageable pageable);
	
	Page<ModuleFunction> findUnselectedModuleFunctionByRoleSite(String q, String roleSiteId, Pageable pageable);
}
