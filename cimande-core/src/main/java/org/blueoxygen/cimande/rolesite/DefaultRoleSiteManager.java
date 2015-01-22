package org.blueoxygen.cimande.rolesite;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.blueoxygen.cimande.site.Site;
import org.blueoxygen.cimande.site.SiteRepository;
import org.meruvian.yama.core.LogInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DefaultRoleSiteManager implements RoleSiteManager {
	@Inject
	private RoleSiteRepository roleSiteRepository;
	
	@Inject
	private SiteRepository siteRepository;
	
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

	@Override
	public Page<Site> findSiteByRole(String q, String roleId, Pageable pageable) {
		q = StringUtils.defaultString(q, "");
		return siteRepository.findByRole(q, roleId, LogInformation.ACTIVE, pageable);
	}

	@Override
	public Page<RoleSite> findByRoles(List<String> roleNames, Pageable pageable) {
		return roleSiteRepository.findByRoles(roleNames, LogInformation.ACTIVE, pageable);
	}

}
