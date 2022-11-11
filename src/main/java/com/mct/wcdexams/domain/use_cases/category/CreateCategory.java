package com.mct.wcdexams.domain.use_cases.category;

import com.mct.wcdexams.domain.model.Category;
import com.mct.wcdexams.domain.repository.CategoryRepository;

public class CreateCategory {

    private final CategoryRepository categoryRepository;

    public CreateCategory(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Long invoke(Category category) {
        return categoryRepository.createCategory(category);
    }
}
