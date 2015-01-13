package org.blueoxygen.cimande.roleprivilege;

import org.blueoxygen.cimande.modulefunction.ModuleFunction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RolePrivilegeManager {
	RolePrivilege saveRolePrivilege(RolePrivilege rolePrivilege);
	
	boolean removeRolePrivilege(RolePrivilege rolePrivilege);
	
	boolean removeRolePrivilege(String roleId, String moduleFunctionId);
	
	RolePrivilege findRolePrivilegeById(String id);
	
	Page<ModuleFunction> findModuleFunctionByRole(String roleId, Pageable pageable);

	Page<ModuleFunction> findModuleFunctionByRole(String q, String roleId, Pageable pageable);

}
