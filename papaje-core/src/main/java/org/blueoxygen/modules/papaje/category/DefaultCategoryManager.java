package org.blueoxygen.modules.papaje.category;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.yama.core.LogInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DefaultCategoryManager implements CategoryManager {

	@Inject
	private CategoryRepository categoryRepository;
	
	@Override
	@Transactional
	public Category saveCategory(Category category) {
		if (category.getMaster() != null) {
			Category master = category.getMaster();
			if (StringUtils.isBlank(master.getId())) {
				category.setMaster(null);
			} else {
				master = categoryRepository.findById(master.getId());
				category.setMaster(master);
			}
		}
		
		if (StringUtils.isBlank(category.getId())) {
			category.setId(null);
			return categoryRepository.save(category);
		} else {
			Category c = categoryRepository.findById(category.getId());
			c.setName(category.getName());
			c.setDescription(category.getDescription());
			c.setMaster(category.getMaster());
			
			return c;
		}
	}

	@Override
	public Category findCategoryById(String id) {
		return categoryRepository.findById(id);
	}

	@Override
	@Transactional
	public Category updateStatus(Category category, int status) {
		category = categoryRepository.findById(category.getId());
		category.getLogInformation().setActiveFlag(status);
		return category;
	}

	@Override
	@Transactional
	public boolean removeCategory(Category category) {
		if (category == null)
			return false;
		
		categoryRepository.delete(category);
		
		return true;
	}

	@Override
	public Page<Category> findCategoryByName(String name, Pageable pageable) {
		name = StringUtils.defaultIfBlank(name, "");
		return categoryRepository.findByNameContainingAndLogInformationActiveFlag(name, LogInformation.ACTIVE, pageable);
	}
	
}
