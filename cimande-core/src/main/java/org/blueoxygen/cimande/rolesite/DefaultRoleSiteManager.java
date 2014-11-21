package org.blueoxygen.cimande.rolesite;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DefaultRoleSiteManager implements RoleSiteManager {

	@Inject
	private RoleSiteRepository roleSiteRepository;
	
	@Override
	@Transactional
	public RoleSite saveRoleSite(RoleSite roleSite) {
		if(StringUtils.isBlank(roleSite.getId()))
			return roleSiteRepository.save(roleSite);
		return roleSite;
	}

	@Override
	@Transactional
	public void delete(RoleSite roleSite) {
		if(roleSite != null){
			roleSite = roleSiteRepository.findById(roleSite.getId());
			roleSiteRepository.delete(roleSite);
		}
	}

	@Override
	public RoleSite findById(String roleSiteId) {
		return roleSiteRepository.findById(roleSiteId);
	}

	@Override
	public RoleSite findByRoleIdAndSiteId(String roleId, String siteId) {
		return roleSiteRepository.findByRoleIdAndSiteId(roleId, siteId);
	}

	@Override
	public Page<RoleSite> findByRole(String roleId, Pageable pageable) {
		return roleSiteRepository.findByRoleId(roleId, pageable);
	}

}
