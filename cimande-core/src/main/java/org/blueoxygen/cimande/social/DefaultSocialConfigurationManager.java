package org.blueoxygen.cimande.social;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DefaultSocialConfigurationManager implements SocialConfigurationManager {

	@Inject
	private SocialConfigurationRepository socialConfigRepository;
	
	@Override
	@Transactional
	public SocialConfiguration saveSocialConfig(SocialConfiguration socialConfig) {
		if(StringUtils.isBlank(socialConfig.getId())) {
			socialConfig.setId(null);
			return socialConfigRepository.save(socialConfig);
		} else {
			SocialConfiguration sc = socialConfigRepository.findById(socialConfig.getId());
			sc.setActive(socialConfig.isActive());
			sc.setAppId(socialConfig.getAppId());
			sc.setAppSecret(socialConfig.getAppSecret());
			sc.setRedirectUri(socialConfig.getRedirectUri());
			sc.setScope(socialConfig.getScope());
			sc.setState(socialConfig.getState());
			
			return sc;
		}
	}

}
