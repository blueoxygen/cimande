package org.blueoxygen.cimande.usersite;

import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSiteRepository extends DefaultRepository<UserSite>{
	UserSite findByUserIdAndSiteId(String userId, String siteId);
	
	UserSite findByUserUsernameAndSiteName(String username, String sitename);
	
	Page<UserSite> findByUserUsername(String username, Pageable pageable);
}
