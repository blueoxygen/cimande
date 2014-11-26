package org.blueoxygen.cimande.modulefunction;

import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleFunctionRepository extends DefaultRepository<ModuleFunction> {
	@Query("SELECT m FROM ModuleFunction m WHERE m.name LIKE %?1% AND m.description LIKE %?2% AND m.moduleUrl LIKE %?3% AND m.logInformation.activeFlag = ?4")
	Page<ModuleFunction> findModuleFunctions(String n, String d, String m, int status, Pageable pageable);
	
	@Query("SELECT m FROM ModuleFunction m WHERE m.name LIKE %?1% AND m.description LIKE %?2% AND m.master.id = ?3 AND m.logInformation.activeFlag = ?4")
	Page<ModuleFunction> findChildModuleFunctions(String n, String d, String moduleFunctionId, int status, Pageable pageable);
	
	@Query("SELECT m FROM ModuleFunction m WHERE m.name LIKE %?1% AND m.description LIKE %?2% AND m.treePath NOT LIKE ?3 AND m.logInformation.activeFlag = ?4")
	Page<ModuleFunction> findMasterModuleFunctions(String n, String d, String moduleFunctionId, int status, Pageable pageable);
}
