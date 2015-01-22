package org.blueoxygen.cimande.roleprivilege;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.blueoxygen.cimande.modulefunction.ModuleFunction;
import org.blueoxygen.cimande.modulefunction.ModuleFunctionRepository;
import org.meruvian.yama.core.LogInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DefaultRolePrivilegeManager implements RolePrivilegeManager {
	@Inject
	private RolePrivilegeRepository rolePrivilegeRepository;
	
	@Inject
	private ModuleFunctionRepository moduleFunctionRepository;

	@Override
	@Transactional
	public RolePrivilege saveRolePrivilege(RolePrivilege rolePrivilege) {
		if(rolePrivilege != null && StringUtils.isBlank(rolePrivilege.getId())) {
			rolePrivilege.setId(null);
			return rolePrivilegeRepository.save(rolePrivilege);
		}
		return null;
	}

	@Override
	@Transactional
	public boolean removeRolePrivilege(RolePrivilege rolePrivilege) {
		if (rolePrivilege == null)
			return false;
		rolePrivilegeRepository.delete(rolePrivilege);
		return true;
	}

	@Override
	@Transactional
	public boolean removeRolePrivilege(String roleId, String moduleFunctionId) {
		RolePrivilege rolePrivilege = rolePrivilegeRepository.findByRoleIdAndModuleFunctionId(roleId, moduleFunctionId);
		if (rolePrivilege == null)
			return false;
		rolePrivilegeRepository.delete(rolePrivilege);
		return true;
	}

	@Override
	public RolePrivilege findRolePrivilegeById(String id) {
		return rolePrivilegeRepository.findById(id);
	}

	@Override
	public Page<RolePrivilege> findRolePrivilegeByRole(String roleId,
			Pageable pageable) {
		return rolePrivilegeRepository.findRolePrivilegeByRole(roleId, LogInformation.ACTIVE, pageable);
	}

	@Override
	public Page<ModuleFunction> findUnselectedModuleFunctionByRole(String q, String roleId, Pageable pageable) {
		q = StringUtils.defaultString(q, "");
		return moduleFunctionRepository.findByRolePrivilege(q, roleId, LogInformation.ACTIVE, pageable);
	}

	@Override
	public Page<ModuleFunction> findModuleFunctionByRoles(List<String> roles, Pageable pageable) {
		return rolePrivilegeRepository.findModuleFunctionByRoles(roles, LogInformation.ACTIVE, pageable);
	}
	
}
