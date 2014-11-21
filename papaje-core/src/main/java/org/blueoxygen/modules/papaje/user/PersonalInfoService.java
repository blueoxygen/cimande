/**
 * 
 */
package org.blueoxygen.modules.papaje.user;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author dianw
 *
 */
@Path(value = "/users")
@Produces(MediaType.APPLICATION_JSON)
public interface PersonalInfoService {
	@GET
	@Path("/{username}")
	PersonalInfo getUserByUsername(@PathParam("username") String username);

	@GET
	Page<PersonalInfo> findUserByKeyword(@QueryParam("q") @DefaultValue("") String keyword, 
			@QueryParam("max") @DefaultValue("10") int max,
			@QueryParam("page") @DefaultValue("0") int page);

	@DELETE
	@Path("/{username}")
	String removeUser(@PathParam("username") String username);

	@POST
	PersonalInfo saveUser(PersonalInfo user);
	
	@PUT
	PersonalInfo updateUser(PersonalInfo user);

	@POST
	@Path("/{username}/password")
	PersonalInfo updateUserPassword(@PathParam("username") String username,
			@FormParam("cPassword") String currentPassword, 
			@FormParam("nPassword") String newPassword);
	
	@GET
	@Path("/{username}/accounts")
	Page<UserAccount> findAccountForUser(@PathParam("username") String username, Pageable pageable);
	
	@POST
	@Path("/{username}/accounts")
	UserAccount addAccountForUser(@PathParam("username") String username, UserAccount account);
	
	@DELETE
	@Path("/{username}/accounts")
	boolean removeAccountFromUser(@PathParam("username") String username, String id);
	
	@GET
	@Path("/{username}/works")
	Page<UserCompany> findCompanyForUser(@PathParam("username") String username, Pageable pageable);
	
	@POST
	@Path("/{username}/works")
	UserCompany addCompanyForUser(@PathParam("username") String username, UserCompany company);
	
	UserCompany addCompanyForUser(PersonalInfo personalInfo, UserCompany company);
	
	@DELETE
	@Path("/{username}/works")
	boolean removeCompanyFromUser(@PathParam("username") String username, String id);
	
	@GET
	@Path("/{username}/works/{id}")
	UserCompany getUserCompany(@PathParam("username") String username, @PathParam("id") String id);
	
	@POST
	@Path("/{username}/works/{id}")
	UserCompany updateUserCompany(@PathParam("username") String username, @PathParam("id") String id, UserCompany userCompany);
	
	@GET
	@Path("/{username}/educations")
	Page<UserEducation> findEducationForUser(@PathParam("username") String username, Pageable pageable);
	
	@POST
	@Path("/{username}/educations")
	UserEducation addEducationForUser(@PathParam("username") String username, UserEducation education);
	
	UserEducation addEducationForUser(PersonalInfo personalInfo, UserEducation education);
	
	@DELETE
	@Path("/{username}/educations")
	boolean removeEducationFromUser(@PathParam("username") String username, String id);
	
	@GET
	@Path("/{username}/educations/{id}")
	UserEducation getUserEducation(@PathParam("username") String username, @PathParam("id") String id);
	
	@GET
	@Path("/{username}/skills")
	Page<UserSkill> findSkillForUser(@PathParam("username") String username, Pageable pageable);
	
	@POST
	@Path("/{username}/skills")
	UserSkill addSkillForUser(@PathParam("username") String username, UserSkill skill);
	
	@DELETE
	@Path("/{username}/skills")
	boolean removeSkillFromUser(@PathParam("username") String username, String id);
	
	@GET
	@Path("/{username}/skills/{id}")
	UserSkill getUserSkill(@PathParam("username") String username, @PathParam("id") String id);
	
	@GET
	@Path("/{username}/achievements")
	Page<UserAchievement> findAchievementForUser(@PathParam("username") String username, Pageable pageable);
	
	@POST
	@Path("/{username}/achievements")
	UserAchievement addAchievementForUser(@PathParam("username") String username, UserAchievement achievement);
	
	@DELETE
	@Path("/{username}/achievements")
	boolean removeAchievementFromUser(@PathParam("username") String username, String id);
	
	@GET
	@Path("/{username}/achievements/{id}")
	UserAchievement getUserAchievement(@PathParam("username") String username, @PathParam("id") String id);
	
	@GET
	@Path("/{username}/languanges")
	Page<UserLanguage> findLanguageForUser(@PathParam("username") String username, Pageable pageable);
	
	@POST
	@Path("/{username}/languanges")
	UserLanguage addLanguageForUser(@PathParam("username") String username, UserLanguage language);
	
	@DELETE
	@Path("/{username}/languanges")
	boolean removeLanguageFromUser(@PathParam("username") String username, String id);
	
	@GET
	@Path("/{username}/languanges/{id}")
	UserLanguage getUserLanguage(@PathParam("username") String username, @PathParam("id") String id);
	
	@GET
	@Path("/{username}/families")
	Page<UserFamily> findFamilyForUser(@PathParam("username") String username, Pageable pageable);
	
	@POST
	@Path("/{username}/families")
	UserFamily addFamilyForUser(@PathParam("username") String username, UserFamily family);
	
	@DELETE
	@Path("/{username}/families")
	boolean removeFamilyFromUser(@PathParam("username") String username, String id);
	
	@GET
	@Path("/{username}/families/{id}")
	UserFamily getUserFamily(@PathParam("username") String username, @PathParam("id") String id);
	
	@GET
	@Path("/{username}/references")
	Page<UserReference> findReferenceForUser(@PathParam("username") String username, Pageable pageable);
	
	@POST
	@Path("/{username}/references")
	UserReference addReferenceForUser(@PathParam("username") String username, UserReference reference);
	
	@DELETE
	@Path("/{username}/references")
	boolean removeReferenceFromUser(@PathParam("username") String username, String id);
	
	@GET
	@Path("/{username}/references/{id}")
	UserReference getUserReference(@PathParam("username") String username, @PathParam("id") String id);
}
