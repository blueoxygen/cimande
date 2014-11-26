package org.blueoxygen.cimande.site;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.yama.core.LogInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DefaultSiteManager implements SiteManager {

	@Inject
	private SiteRepository siteRepository;

	@Override
	@Transactional
	public Site saveSite(Site site) {
		String path = "";

		if (site.getMaster() != null) {
			Site master = site.getMaster();
			if (StringUtils.isBlank(master.getId())) {
				site.setMaster(null);
			} else {
				master = siteRepository.findById(master.getId());
				site.setMaster(master);
				path = master.getPath();
			}
		}

		path = StringUtils.isBlank(path) ? "" : (path + ".");
		
		if (StringUtils.isBlank(site.getId())) {
			site.setId(null);
			site.setPath(path);
			return siteRepository.save(site);
		} else {
			Site s = siteRepository.findById(site.getId());
			s.setName(site.getName());
			s.setDescription(site.getDescription());
			s.setAdminEmail(site.getAdminEmail());
			s.setTitle(site.getTitle());
			s.setUrlBranding(site.getUrlBranding());
			s.setSiteUrl(site.getSiteUrl());
			s.setNotifyFlag(site.getNotifyFlag());
			s.setNotifyEmail(site.getNotifyEmail());
			s.setNotifyFrom(site.getNotifyFrom());
			s.setNotifyMessage(site.getNotifyMessage());
			s.setLevel(site.getLevel());
			s.setMaster(site.getMaster());
			s.setPath(path + site.getId());
			s.setVirtualHost(site.getVirtualHost());
			
			return s;
		}
	}

	@Override
	public Site findSiteById(String siteId) {
		return siteRepository.findById(siteId);
	}

	@Override
	public Site findSiteByName(String name) {
		return siteRepository.findByName(name);
	}
	
	@Override
	public Page<Site> findSiteForName(String name, Pageable pageable) {
		name = StringUtils.defaultIfBlank(name, "");
		return siteRepository.findByNameContainingAndLogInformationActiveFlag(name, LogInformation.ACTIVE, pageable);
	}

	@Override
	@Transactional
	public Site updateStatus(Site site, int status) {
		site = siteRepository.findById(site.getId());
		site.getLogInformation().setActiveFlag(status);
		
		return site;
	}

	@Override
	@Transactional
	public boolean removeSite(Site site) {
		if (site == null)
			return false;
		
		siteRepository.delete(site);
		
		return true;
	}

	@Override
	public Site findSiteByVirtualHost(String virtualHost) {
		return siteRepository.findByVirtualHost(virtualHost);
	}

}
