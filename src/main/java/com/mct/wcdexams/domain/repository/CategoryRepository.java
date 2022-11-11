package com.mct.wcdexams.domain.repository;

import com.mct.wcdexams.domain.model.Category;

import java.util.List;

public interface CategoryRepository {

    long createCategory(Category category);

    boolean updateCategory(Category category);

    boolean removeCategory(Category category);

    boolean removeCategory(int id);

    long getTotalCategoryBySearch(String search);

    List<Category> getCategories(String search, int limit, int offset);

    Category getCategory(long id);

    boolean isExists(long id, String search);

}
