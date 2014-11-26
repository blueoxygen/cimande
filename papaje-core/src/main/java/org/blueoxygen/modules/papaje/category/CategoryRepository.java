package org.blueoxygen.modules.papaje.category;

import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends DefaultRepository<Category> {
	Page<Category> findByNameContainingAndLogInformationActiveFlag(String name, int status, Pageable pageable);
}
