package org.blueoxygen.cimande.core.listener;

import java.util.Date;

import org.blueoxygen.cimande.core.CimandeJpaLogInformation;
import org.blueoxygen.cimande.security.SessionCredentials;
import org.blueoxygen.cimande.site.Site;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreUpdateEvent;
import org.meruvian.yama.core.DefaultPersistence;
import org.meruvian.yama.core.user.User;
import org.meruvian.yama.service.jpa.LogInformationListener;

public class CimandeLogInformationListener extends LogInformationListener {
	private String getCurrentUserId() {
		User user = SessionCredentials.getCurrentUser();
		String userId = null;
		
		if (user != null)
			userId = user.getId();
		
		return userId;
	}
	
	private String getCurrentSiteId() {
		Site site = SessionCredentials.getCurrentSite();
		String siteId = null;
		
		if (site != null)
			siteId = site.getId();
		
		return siteId;
	}
	
	@Override
	public boolean onPreInsert(PreInsertEvent event) {
		String userId = getCurrentUserId();
		String siteId = getCurrentSiteId();

		if (event.getEntity() instanceof DefaultPersistence) {
			DefaultPersistence p = (DefaultPersistence) event.getEntity();
			CimandeJpaLogInformation logInfo = new CimandeJpaLogInformation(p.getLogInformation());
		
			logInfo.setCreateDate(new Date());
			logInfo.setLastUpdateDate(new Date());
			logInfo.setCreateBy(userId);
			logInfo.setLastUpdateBy(userId);
			logInfo.setSite(siteId);
			
			Object[] state = event.getState();
			
			for (int i = 0; i < state.length; i++) {
				if (state[i] instanceof CimandeJpaLogInformation) {
					state[i] = logInfo;
					
					break;
				}
			}
		}
		
		return false;
	}

	@Override
	public boolean onPreUpdate(PreUpdateEvent event) {
		String userId = getCurrentUserId();
		String siteId = getCurrentSiteId();

		if (event.getEntity() instanceof DefaultPersistence) {
			DefaultPersistence p = (DefaultPersistence) event.getEntity();
			CimandeJpaLogInformation logInfo = new CimandeJpaLogInformation(p.getLogInformation());

			Object[] oldState = event.getOldState();
			
			for (int i = 0; i < oldState.length; i++) {
				if (oldState[i] instanceof CimandeJpaLogInformation) {
					CimandeJpaLogInformation oldLogInfo = (CimandeJpaLogInformation) oldState[i];
					
					logInfo.setCreateDate(oldLogInfo.getCreateDate());
					logInfo.setLastUpdateDate(new Date());
					logInfo.setCreateBy(oldLogInfo.getCreateBy());
					logInfo.setLastUpdateBy(userId);
					logInfo.setSite(siteId);
					logInfo.setActiveFlag(oldLogInfo.getActiveFlag());
					
					break;
				}
			}
		}
		
		return false;
	}
}
