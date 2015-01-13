package org.blueoxygen.cimande.roleprivilege;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.blueoxygen.cimande.modulefunction.ModuleFunction;
import org.blueoxygen.cimande.modulefunction.ModuleFunctionRepository;
import org.meruvian.yama.core.LogInformation;
import org.meruvian.yama.core.role.JpaRole;
import org.meruvian.yama.core.role.JpaRoleRepository;
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
	
	@Inject
	private JpaRoleRepository roleRepository;

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
	public Page<ModuleFunction> findModuleFunctionByRole(String roleId,
			Pageable pageable) {
		return rolePrivilegeRepository.findModuleFunctionByRole(roleId, LogInformation.ACTIVE, pageable);
	}

	@Override
	public Page<ModuleFunction> findModuleFunctionByRole(String q, String roleId, Pageable pageable) {
		q = StringUtils.defaultString(q, "");
		return moduleFunctionRepository.findByRolePrivilege(q, q, roleId, LogInformation.ACTIVE, LogInformation.ACTIVE, pageable);
	}
	
}
