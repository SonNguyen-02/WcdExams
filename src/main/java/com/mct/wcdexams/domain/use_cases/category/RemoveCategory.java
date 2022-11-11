package com.mct.wcdexams.domain.use_cases.category;

import com.mct.wcdexams.domain.model.Category;
import com.mct.wcdexams.domain.repository.CategoryRepository;

public class RemoveCategory {

    private final CategoryRepository categoryRepository;

    public RemoveCategory(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public boolean invoke(int id) {
        return categoryRepository.removeCategory(id);
    }

    public boolean invoke(Category category) {
        return categoryRepository.removeCategory(category);
    }

}
