package org.blueoxygen.cimande.roleprivilege;

import java.util.List;

import org.blueoxygen.cimande.modulefunction.ModuleFunction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RolePrivilegeManager {
	RolePrivilege saveRolePrivilege(RolePrivilege rolePrivilege);
	
	boolean removeRolePrivilege(RolePrivilege rolePrivilege);
	
	boolean removeRolePrivilege(String roleId, String moduleFunctionId);
	
	RolePrivilege findRolePrivilegeById(String id);
	
	Page<RolePrivilege> findRolePrivilegeByRole(String roleId, Pageable pageable);
	
	Page<ModuleFunction> findModuleFunctionByRoles(List<String> roles, Pageable pageable);

	Page<ModuleFunction> findUnselectedModuleFunctionByRole(String q, String roleId, Pageable pageable);
}
