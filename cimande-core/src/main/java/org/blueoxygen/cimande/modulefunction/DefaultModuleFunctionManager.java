package org.blueoxygen.cimande.modulefunction;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.yama.core.LogInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DefaultModuleFunctionManager implements ModuleFunctionManager {
	@Inject
	private ModuleFunctionRepository moduleFunctionRepository;
	
	@Override
	@Transactional
	public ModuleFunction saveModuleFunction(ModuleFunction moduleFunction) {
		String treePath = "";
		String url = "";
		
		if (moduleFunction.getMaster() != null) {
			ModuleFunction master = moduleFunction.getMaster();
			if (StringUtils.isBlank(master.getId())) {
				moduleFunction.setMaster(null);
			} else {
				master = moduleFunctionRepository.findById(master.getId());
				moduleFunction.setMaster(master);
				treePath = master.getTreePath();
				url = prependWithSlash(master.getModuleUrl());
			}
		}
		
		treePath = StringUtils.isBlank(treePath) ? "" : (treePath + ".");
		
		if (StringUtils.isBlank(moduleFunction.getId())) {
			moduleFunction.setId(null);
			moduleFunction.setTreePath(treePath);
			return moduleFunctionRepository.save(moduleFunction);
		} else {
			ModuleFunction md = moduleFunctionRepository.findById(moduleFunction.getId());
			md.setName(moduleFunction.getName());
			md.setDescription(moduleFunction.getDescription());
			md.setModuleUrl(url + prependWithSlash(moduleFunction.getModuleUrl()));
			md.setMaster(moduleFunction.getMaster());
			md.setLevel(moduleFunction.getLevel());
			md.setTableReferences(moduleFunction.getTableReferences());
			md.setViewActive(moduleFunction.getViewActive());
			md.setTreePath(treePath + moduleFunction.getId());
			
			return md;
		}
	}

	@Override
	@Transactional
	public boolean removeModuleFunction(ModuleFunction moduleFunction) {
		if (moduleFunction == null)
			return false;
		
		moduleFunctionRepository.delete(moduleFunction);
		return true;
	}

	@Override
	public ModuleFunction findModuleFunctionById(String id) {
		return moduleFunctionRepository.findById(id);
	}

	@Override
	public Page<ModuleFunction> findModuleFunctionByKeyword(String keyword, Pageable pageable) {
		keyword = StringUtils.defaultString(keyword, "");
		return moduleFunctionRepository.findModuleFunctions(keyword, keyword, keyword, LogInformation.ACTIVE, pageable);
	}

	@Override
	public Page<ModuleFunction> findChildModuleFunctionsByKeyword(
			String keyword, String moduleFunctionId, Pageable pageable) {
		return moduleFunctionRepository.findChildModuleFunctions(keyword, keyword, moduleFunctionId, LogInformation.ACTIVE, pageable);
	}

	@Override
	public Page<ModuleFunction> findMasterModuleFunctionsByKeyword(
			String keyword, String moduleFunctionId, Pageable pageable) {
		return moduleFunctionRepository.findMasterModuleFunctions(keyword, keyword, moduleFunctionId, LogInformation.ACTIVE, pageable);
	}
	
	private String prependWithSlash(String moduleUrl) {
		if (StringUtils.isBlank(moduleUrl)) 
			return "";
		
		if (!StringUtils.startsWith(moduleUrl, "/"))
			moduleUrl = StringUtils.join("/", moduleUrl);
		
		return StringUtils.removeEnd(moduleUrl, "/");	
	}

}
