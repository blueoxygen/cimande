package org.blueoxygen.modules.papaje.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryManager {
	Category saveCategory(Category category);
	
	Category findCategoryById(String id);
	
	Category updateStatus(Category category, int status);
	
	boolean removeCategory(Category category);
	
	Page<Category> findCategoryByName(String name, Pageable pageable);
}
