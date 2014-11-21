/**
 * 
 */
package org.blueoxygen.modules.papaje.company;

import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author dianw
 *
 */
@Repository
public interface CompanyRepository extends DefaultRepository<Company> {
	Page<Company> findByNameContaining(String name, Pageable pageable);
}
