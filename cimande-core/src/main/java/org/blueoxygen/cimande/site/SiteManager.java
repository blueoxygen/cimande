package org.blueoxygen.cimande.site;

import org.blueoxygen.cimande.social.SocialConfiguration;
import org.meruvian.yama.core.commons.FileInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SiteManager {
	
	Site saveSite(Site site);
	
	Site findSiteById(String siteId);
	
	Site findSiteByName(String name);
	
	Site findSiteByVirtualHost(String virtualHost);
	
	Site updateStatus(Site site, int status);
	
	FileInfo setSiteLogo(Site site, FileInfo fileInfo);
	
	SocialConfiguration setSocialConfig(Site site, SocialConfiguration socialConfig);
	
	boolean removeSite(Site site);

	Page<Site> findSiteForName(String name, Pageable pageable);
}
