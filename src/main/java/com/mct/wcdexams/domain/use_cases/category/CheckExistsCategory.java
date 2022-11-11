package com.mct.wcdexams.domain.use_cases.category;

import com.mct.wcdexams.domain.model.Category;
import com.mct.wcdexams.domain.repository.CategoryRepository;
import org.jetbrains.annotations.NotNull;

public class CheckExistsCategory {

    private final CategoryRepository categoryRepository;

    public CheckExistsCategory(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public boolean invoke(long id, String name) {
        return categoryRepository.isExists(id, name);
    }

    public boolean invoke(@NotNull Category category) {
        return invoke(category.getId(), category.getName());
    }

}
