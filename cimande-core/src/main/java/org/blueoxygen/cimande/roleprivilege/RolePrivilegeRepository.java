package org.blueoxygen.cimande.roleprivilege;

import java.util.List;

import org.blueoxygen.cimande.modulefunction.ModuleFunction;
import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePrivilegeRepository extends DefaultRepository<RolePrivilege>{
	@Query("SELECT rp FROM RolePrivilege rp WHERE rp.role.id = ?1 AND rp.logInformation.activeFlag = ?2 ")
	Page<RolePrivilege> findRolePrivilegeByRole(String roleId, int status, Pageable pageable);

	@Query("SELECT rp.moduleFunction FROM RolePrivilege rp WHERE rp.role.name IN ?1 AND rp.logInformation.activeFlag = ?2 ")
	Page<ModuleFunction> findModuleFunctionByRoles(List<String> roles, int status, Pageable pageable);
	
	@Query("SELECT rp FROM RolePrivilege rp WHERE (rp.moduleFunction.name LIKE %?1% OR rp.moduleFunction.description LIKE %?2%) "
			+ " AND rp.role.id = ?4 "
			+ " AND rp.moduleFunction.id NOT IN (SELECT moduleFunction.id FROM RolePrivilege WHERE role.id = ?3 AND logInformation.activeFlag = ?5) "
			+ " AND rp.logInformation.activeFlag = ?6 ")
	Page<RolePrivilege> findRolePrivilegeByRole(String n, String d, String roleId, String masterId, int stat, int status, Pageable pageable);
	
	RolePrivilege findByRoleIdAndModuleFunctionId(String roleId, String moduleFunctionId);
}
