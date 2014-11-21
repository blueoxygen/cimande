package org.blueoxygen.modules.papaje.user;

import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFamilyRepository extends DefaultRepository<UserFamily>{
	Page<UserFamily> findByPersonalInfoUserUsernameAndLogInformationActiveFlag(String username, int status, Pageable pageable);
}
