package org.blueoxygen.cimande.usersite;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DefaultUserSiteManager implements UserSiteManager {
	
	@Inject
	private UserSiteRepository userSiteRepository;

	@Override
	@Transactional
	public UserSite saveUserSite(UserSite userSite) {
		if(StringUtils.isBlank(userSite.getId()))
			return userSiteRepository.save(userSite);
		
		return userSite;
	}

	@Override
	@Transactional
	public void delete(UserSite userSite) {
		if (userSite != null) {
			userSite = userSiteRepository.findById(userSite.getId());
			userSiteRepository.delete(userSite);
		}
	}

	@Override
	public UserSite findById(String userSiteId) {
		return userSiteRepository.findById(userSiteId);
	}

	@Override
	public UserSite findByUserIdAndSiteId(String userId, String siteId) {
		return userSiteRepository.findByUserIdAndSiteId(userId, siteId);
	}

	@Override
	public UserSite findByUserUserameAndSiteName(String username, String sitename) {
		return userSiteRepository.findByUserUsernameAndSiteName(username, sitename);
	}

	@Override
	public Page<UserSite> findByUser(String username, Pageable pageable) {
		return userSiteRepository.findByUserUsername(username, pageable);
	}

}
