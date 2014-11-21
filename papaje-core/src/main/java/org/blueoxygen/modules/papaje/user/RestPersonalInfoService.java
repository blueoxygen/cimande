/**
 * 
 */
package org.blueoxygen.modules.papaje.user;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.blueoxygen.modules.papaje.company.CompanyRepository;
import org.meruvian.yama.core.JpaLogInformation;
import org.meruvian.yama.core.LogInformation;
import org.meruvian.yama.core.user.JpaUser;
import org.meruvian.yama.core.user.JpaUserRepository;
import org.meruvian.yama.web.SessionCredentials;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dianw
 * 
 */
@Service
@Transactional(readOnly = true)
public class RestPersonalInfoService implements PersonalInfoService {
	@Inject
	private PersonalInfoRepository personalInfoRepository;

	@Inject
	private CompanyRepository companyRepository;

	@Inject
	private UserCompanyRepository userCompanyRepository;

	@Inject
	private UserEducationRepository userEducationRepository;

	@Inject
	private UserAccountRepository userAccountRepository;

	@Inject
	private UserSkillRepository userSkillRepository;

	@Inject
	private JpaUserRepository userRepository;
	
	@Inject
	private UserAchievementRepository userAchievementRepository;
	
	@Inject
	private UserLanguageRepository userLanguageRepository;
	
	@Inject
	private UserFamilyRepository userFamilyRepository;
	
	@Inject
	private UserReferenceRepository userReferenceRepository;

	@Override
	@Transactional
	public PersonalInfo getUserByUsername(String username) {
		PersonalInfo info = personalInfoRepository.findByUserUsername(username);
		JpaUser jpaUser = userRepository.findByUsername(username);
		
		if (jpaUser != null && info == null) {
			info = new PersonalInfo();
			info.setUser(jpaUser);
			personalInfoRepository.save(info);
		}
		return info;
	}

	@Override
	public Page<PersonalInfo> findUserByKeyword(String keyword, int max,
			int page) {
		return null;
	}

	@Override
	public String removeUser(String username) {
		return null;
	}

	@Override
	@Transactional
	public PersonalInfo saveUser(PersonalInfo user) {
		if (StringUtils.isBlank(user.getId())) {
			user = personalInfoRepository.save(user);
		} else {
			PersonalInfo u = personalInfoRepository.findById(user.getId());
			u.update(user);

			user = u;
		}

		return user;
	}

	@Override
	@Transactional
	public PersonalInfo updateUser(PersonalInfo user) {
		PersonalInfo u = personalInfoRepository.findById(user.getId());
		u.update(user);

		return u;
	}

	@Override
	public PersonalInfo updateUserPassword(String username,
			String currentPassword, String newPassword) {
		return null;
	}

	@Override
	public Page<UserAccount> findAccountForUser(String username,
			Pageable pageable) {
		return null;
	}

	@Override
	public UserAccount addAccountForUser(String username, UserAccount account) {
		return null;
	}

	@Override
	public boolean removeAccountFromUser(String username, String id) {
		UserAccount account = userAccountRepository.findById(id);
		if(account.getPersonalInfo() != null && account.getPersonalInfo().getUser() != null
				&& username.equals(account.getPersonalInfo().getUser().getUsername())){
			account.getLogInformation().setActiveFlag(LogInformation.INACTIVE);
			return true;
		}
		return false;
	}

	@Override
	public Page<UserCompany> findCompanyForUser(String username,
			Pageable pageable) {
		return userCompanyRepository.findByPersonalInfoUserUsernameAndLogInformationActiveFlag(username, LogInformation.ACTIVE, pageable);
	}

	@Override
	@Transactional
	public UserCompany addCompanyForUser(String username, UserCompany company) {
		PersonalInfo user = personalInfoRepository.findByUserUsername(username);
		company.setPersonalInfo(user);
		company = userCompanyRepository.save(company);

		return company;
	}

	@Override
	@Transactional
	public boolean removeCompanyFromUser(String username, String id) {
		UserCompany company = userCompanyRepository.findById(id);
		if(company.getPersonalInfo() != null && company.getPersonalInfo().getUser() != null
				&& username.equals(company.getPersonalInfo().getUser().getUsername())){
			company.getLogInformation().setActiveFlag(LogInformation.INACTIVE);
			return true;
		}
		return false;
	}

	@Override
	public Page<UserEducation> findEducationForUser(String username,
			Pageable pageable) {
		return userEducationRepository.findByPersonalInfoUserUsernameAndLogInformationActiveFlag(username, LogInformation.ACTIVE, pageable);
	}

	@Override
	@Transactional
	public UserEducation addEducationForUser(String username,
			UserEducation education) {
		PersonalInfo user = personalInfoRepository.findByUserUsername(username);
		education.setPersonalInfo(user);
		education = userEducationRepository.save(education);

		return education;
	}

	@Override
	public boolean removeEducationFromUser(String username, String id) {
		UserEducation education = userEducationRepository.findById(id);
		if(education.getPersonalInfo() != null && education.getPersonalInfo().getUser() != null
				&& username.equals(education.getPersonalInfo().getUser().getUsername())){
			education.getLogInformation().setActiveFlag(LogInformation.INACTIVE);
			return true;
		}
		return false;
	}

	@Override
	public Page<UserSkill> findSkillForUser(String username, Pageable pageable) {
		return userSkillRepository.findByPersonalInfoUserUsernameAndLogInformationActiveFlag(username, LogInformation.ACTIVE, pageable);
	}

	@Override
	@Transactional
	public UserSkill addSkillForUser(String username, UserSkill skill) {
		PersonalInfo user = personalInfoRepository.findByUserUsername(username);
		skill.setPersonalInfo(user);
		skill = userSkillRepository.save(skill);

		return skill;
	}

	@Override
	public boolean removeSkillFromUser(String username, String id) {
		UserSkill skill = userSkillRepository.findById(id);
		if(skill.getPersonalInfo() != null && skill.getPersonalInfo().getUser() != null
				&& username.equals(skill.getPersonalInfo().getUser().getUsername())){
			skill.getLogInformation().setActiveFlag(LogInformation.INACTIVE);
			return true;
		}
		return false;
	}

	@Override
	public UserSkill getUserSkill(String username, String id) {
		UserSkill userSkill = userSkillRepository.findOne(id);
		if (userSkill != null
				&& userSkill.getPersonalInfo() != null
				&& userSkill.getPersonalInfo().getUser() != null
				&& username.equals(userSkill.getPersonalInfo().getUser()
						.getUsername()))
			return userSkill;
		else
			return null;
	}

	@Override
	public UserCompany getUserCompany(String username, String id) {
		UserCompany userCompany = userCompanyRepository.findOne(id);
		if (userCompany != null
				&& userCompany.getPersonalInfo() != null
				&& userCompany.getPersonalInfo().getUser() != null
				&& username.equals(userCompany.getPersonalInfo().getUser()
						.getUsername()))
			return userCompany;
		else
			return null;
	}

	@Override
	public UserCompany updateUserCompany(String username, String id,
			UserCompany userCompany) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEducation getUserEducation(String username, String id) {
		UserEducation userEducation = userEducationRepository.findOne(id);
		if (userEducation != null
				&& userEducation.getPersonalInfo() != null
				&& userEducation.getPersonalInfo().getUser() != null
				&& username.equals(userEducation.getPersonalInfo().getUser()
						.getUsername()))
			return userEducation;
		else
			return null;
	}

	@Override
	public Page<UserAchievement> findAchievementForUser(String username,
			Pageable pageable) {
		return userAchievementRepository.findByPersonalInfoUserUsernameAndLogInformationActiveFlag(username, LogInformation.ACTIVE, pageable);
	}

	@Override
	@Transactional
	public UserAchievement addAchievementForUser(String username,
			UserAchievement achievement) {
		PersonalInfo user = personalInfoRepository.findByUserUsername(username);
		achievement.setPersonalInfo(user);
		achievement = userAchievementRepository.save(achievement);

		return achievement;
	}

	@Override
	public boolean removeAchievementFromUser(String username, String id) {
		UserAchievement achievement = userAchievementRepository.findById(id);
		if(achievement.getPersonalInfo() != null && achievement.getPersonalInfo().getUser() != null
				&& username.equals(achievement.getPersonalInfo().getUser().getUsername())){
			achievement.getLogInformation().setActiveFlag(LogInformation.INACTIVE);
			return true;
		}
		return false;
	}

	@Override
	public UserAchievement getUserAchievement(String username, String id) {
		UserAchievement userAchievement = userAchievementRepository.findOne(id);
		if (userAchievement != null
				&& userAchievement.getPersonalInfo() != null
				&& userAchievement.getPersonalInfo().getUser() != null
				&& username.equals(userAchievement.getPersonalInfo().getUser()
						.getUsername()))
			return userAchievement;
		else
			return null;
	}

	@Override
	public Page<UserLanguage> findLanguageForUser(String username, Pageable pageable) {
		return userLanguageRepository.findByPersonalInfoUserUsernameAndLogInformationActiveFlag(username, LogInformation.ACTIVE, pageable);
	}

	@Override
	@Transactional
	public UserLanguage addLanguageForUser(String username, UserLanguage language) {
		PersonalInfo user = personalInfoRepository.findByUserUsername(username);
		language.setPersonalInfo(user);
		language = userLanguageRepository.save(language);

		return language;
	}

	@Override
	public boolean removeLanguageFromUser(String username, String id) {
		UserLanguage language = userLanguageRepository.findById(id);
		if(language.getPersonalInfo() != null && language.getPersonalInfo().getUser() != null
				&& username.equals(language.getPersonalInfo().getUser().getUsername())){
			language.getLogInformation().setActiveFlag(LogInformation.INACTIVE);
			return true;
		}
		return false;
	}

	@Override
	public UserLanguage getUserLanguage(String username, String id) {
		UserLanguage userLanguage = userLanguageRepository.findOne(id);
		if (userLanguage != null
				&& userLanguage.getPersonalInfo() != null
				&& userLanguage.getPersonalInfo().getUser() != null
				&& username.equals(userLanguage.getPersonalInfo().getUser()
						.getUsername()))
			return userLanguage;
		else
			return null;
	}

	@Override
	public Page<UserFamily> findFamilyForUser(String username, Pageable pageable) {
		return userFamilyRepository.findByPersonalInfoUserUsernameAndLogInformationActiveFlag(username, LogInformation.ACTIVE, pageable);
	}

	@Override
	@Transactional
	public UserFamily addFamilyForUser(String username, UserFamily family) {
		PersonalInfo user = personalInfoRepository.findByUserUsername(username);
		family.setPersonalInfo(user);
		family = userFamilyRepository.save(family);

		return family;
	}

	@Override
	public boolean removeFamilyFromUser(String username, String id) {
		UserFamily family = userFamilyRepository.findById(id);
		if(family.getPersonalInfo() != null && family.getPersonalInfo().getUser() != null
				&& username.equals(family.getPersonalInfo().getUser().getUsername())){
			family.getLogInformation().setActiveFlag(LogInformation.INACTIVE);
			return true;
		}
		return false;
	}

	@Override
	public UserFamily getUserFamily(String username, String id) {
		UserFamily userFamily = userFamilyRepository.findOne(id);
		if (userFamily != null
				&& userFamily.getPersonalInfo() != null
				&& userFamily.getPersonalInfo().getUser() != null
				&& username.equals(userFamily.getPersonalInfo().getUser()
						.getUsername()))
			return userFamily;
		else
			return null;
	}

	@Override
	@Transactional
	public UserCompany addCompanyForUser(PersonalInfo personalInfo,
			UserCompany company) {
		company.setPersonalInfo(personalInfo);
		company = userCompanyRepository.save(company);
		return company;
	}

	@Override
	@Transactional
	public UserEducation addEducationForUser(PersonalInfo personalInfo,
			UserEducation education) {
		education.setPersonalInfo(personalInfo);
		education = userEducationRepository.save(education);
		return education;
	}

	@Override
	public Page<UserReference> findReferenceForUser(String username,
			Pageable pageable) {
		return userReferenceRepository.findByPersonalInfoUserUsernameAndLogInformationActiveFlag(username, LogInformation.ACTIVE, pageable);
	}

	@Override
	@Transactional
	public UserReference addReferenceForUser(String username,
			UserReference reference) {
		PersonalInfo user = personalInfoRepository.findByUserUsername(username);
		reference.setPersonalInfo(user);
		reference = userReferenceRepository.save(reference);

		return reference;
	}

	@Override
	@Transactional
	public boolean removeReferenceFromUser(String username,
			String id) {
		UserReference reference = userReferenceRepository.findById(id);
		if(reference.getPersonalInfo() != null && reference.getPersonalInfo().getUser() != null
				&& username.equals(reference.getPersonalInfo().getUser().getUsername())){
			reference.getLogInformation().setActiveFlag(LogInformation.INACTIVE);
			return true;
		}
		return false;
	}

	@Override
	public UserReference getUserReference(String username, String id) {
		UserReference userReference = userReferenceRepository.findOne(id);
		if (userReference != null
				&& userReference.getPersonalInfo() != null
				&& userReference.getPersonalInfo().getUser() != null
				&& username.equals(userReference.getPersonalInfo().getUser()
						.getUsername()))
			return userReference;
		else
			return null;
	}
}
