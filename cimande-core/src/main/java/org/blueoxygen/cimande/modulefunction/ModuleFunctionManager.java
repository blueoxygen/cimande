package org.blueoxygen.cimande.modulefunction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ModuleFunctionManager {
	ModuleFunction saveModuleFunction(ModuleFunction moduleFunction);
	
	boolean removeModuleFunction(ModuleFunction moduleFunction);
	
	ModuleFunction findModuleFunctionById(String id);
	
	Page<ModuleFunction> findModuleFunctionByKeyword(String keyword, Pageable pageable);
	
	Page<ModuleFunction> findChildModuleFunctionsByKeyword(String keyword, String moduleFunctionId, Pageable pageable);
	
	Page<ModuleFunction> findMasterModuleFunctionsByKeyword(String keyword, String moduleFunctionId, Pageable pageable);
	
}
