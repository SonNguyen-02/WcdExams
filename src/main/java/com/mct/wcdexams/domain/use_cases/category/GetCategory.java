package com.mct.wcdexams.domain.use_cases.category;

import com.mct.wcdexams.domain.model.Category;
import com.mct.wcdexams.domain.repository.CategoryRepository;

public class GetCategory {

    private final CategoryRepository categoryRepository;

    public GetCategory(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category invoke(long id) {
        return categoryRepository.getCategory(id);
    }

}
