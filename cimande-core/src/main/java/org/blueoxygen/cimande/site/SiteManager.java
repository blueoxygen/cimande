package org.blueoxygen.cimande.site;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SiteManager {
	
	Site saveSite(Site site);
	
	Site findSiteById(String siteId);
	
	Site findSiteByName(String name);
	
	Site updateStatus(Site site, int status);
	
	boolean removeSite(Site site);

	Page<Site> findSiteForName(String name, Pageable pageable);
}
