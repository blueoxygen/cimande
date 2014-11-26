package org.blueoxygen.cimande.usersite;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserSiteManager {
	
	UserSite saveUserSite(UserSite userSite);
	
	void delete(UserSite userSite);

	UserSite findById(String userSiteId);
	
	UserSite findByUserIdAndSiteId(String userId, String siteId);

	UserSite findByUserUserameAndSiteName(String username, String sitename);
	
	UserSite findByUserUserameAndSiteVirtualHost(String username, String virtualHost);
	
	Page<UserSite> findByUser(String userId, Pageable pageable);
}
