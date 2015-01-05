package org.meruvian.yama.showcase.social;

import javax.inject.Inject;

import org.apache.struts2.ServletActionContext;
import org.blueoxygen.cimande.site.Site;
import org.blueoxygen.cimande.site.SiteManager;
import org.meruvian.yama.showcase.social.connect.MervpolisConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class MervpolisSocialConfig {
	@Inject
	private SiteManager siteManager;
	
	@Bean
	@Scope(value ="request", proxyMode = ScopedProxyMode.DEFAULT)
	public MervpolisConnectionFactory mervpolisConnectionFactory() {
		Site site = siteManager.findSiteByVirtualHost(ServletActionContext.getRequest().getHeader("host"));
		return new MervpolisConnectionFactory(site.getSocialConfiguration().getAppId(), site.getSocialConfiguration().getAppSecret());
	}
}
