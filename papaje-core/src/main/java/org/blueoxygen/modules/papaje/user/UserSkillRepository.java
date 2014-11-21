/**
 * 
 */
package org.blueoxygen.modules.papaje.user;

import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author dianw
 *
 */
@Repository
public interface UserSkillRepository extends DefaultRepository<UserSkill> {
	Page<UserSkill> findByPersonalInfoUserUsernameAndLogInformationActiveFlag(String username, int status, Pageable pageable);
}
