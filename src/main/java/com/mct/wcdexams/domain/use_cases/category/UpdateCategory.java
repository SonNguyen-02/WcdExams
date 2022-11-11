package com.mct.wcdexams.domain.use_cases.category;

import com.mct.wcdexams.domain.model.Category;
import com.mct.wcdexams.domain.repository.CategoryRepository;

public class UpdateCategory {

    private final CategoryRepository categoryRepository;

    public UpdateCategory(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public boolean invoke(Category category) {
        return categoryRepository.updateCategory(category);
    }
}
