package org.blueoxygen.modules.papaje.language;

import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends DefaultRepository<Language>{
	Page<Language> findByNameContaining(String name, Pageable pageable);
}
