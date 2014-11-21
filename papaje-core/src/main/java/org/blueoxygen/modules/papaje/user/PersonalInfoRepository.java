/**
 * 
 */
package org.blueoxygen.modules.papaje.user;

import org.meruvian.yama.core.DefaultRepository;
import org.springframework.stereotype.Repository;

/**
 * @author dianw
 *
 */
@Repository
public interface PersonalInfoRepository extends DefaultRepository<PersonalInfo> {
	PersonalInfo findByUserUsername(String username);
}
