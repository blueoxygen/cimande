package org.blueoxygen.cimande.rolesiteprivilege;

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
public class DefaultRoleSitePrivilegeManager implements RoleSitePrivilegeManager {
	@Inject
	private RoleSitePrivilegeRepository roleSitePrivilegeRepository;
	
	@Inject
	private ModuleFunctionRepository moduleFunctionRepository;
	
	@Override
	@Transactional
	public RoleSitePrivilege saveRoleSitePrivilege(
			RoleSitePrivilege roleSitePrivilege) {
		if (roleSitePrivilege != null && StringUtils.isBlank(roleSitePrivilege.getId())) {
			roleSitePrivilege.setId(null);
			return roleSitePrivilegeRepository.save(roleSitePrivilege);
		}
		return null;
	}

	@Override
	@Transactional
	public boolean removeRoleSitePrivilege(String roleSitePrivilegeId) {
		if (StringUtils.isNotBlank(roleSitePrivilegeId)) {
			RoleSitePrivilege roleSitePrivilege = findRoleSitePrivilegeById(roleSitePrivilegeId);
			if (roleSitePrivilege != null) {
				roleSitePrivilegeRepository.delete(roleSitePrivilege);
				return true;
			}
		}
		return false;
	}

	@Override
	public RoleSitePrivilege findRoleSitePrivilegeById(String id) {
		return roleSitePrivilegeRepository.findById(id);
	}

	@Override
	public Page<RoleSitePrivilege> findRoleSitePrivilegeByRoleSite(
			String roleSiteId, Pageable pageable) {
		return roleSitePrivilegeRepository.findByRoleSite(roleSiteId, LogInformation.ACTIVE, pageable);
	}

	@Override
	public Page<ModuleFunction> findUnselectedModuleFunctionByRoleSite(String q, String roleSiteId,
			Pageable pageable) {
		q = StringUtils.defaultString(q, "");
		return moduleFunctionRepository.findByRoleSite(q, roleSiteId, LogInformation.ACTIVE, pageable);
	}

	@Override
	public Page<ModuleFunction> findModuleFunctionByRoleSite(List<String> roles, String siteId, Pageable pageable) {
		return roleSitePrivilegeRepository.findModuleFunctionByRoleSite(roles, siteId, LogInformation.ACTIVE, pageable);
	}
}
