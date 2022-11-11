package com.mct.wcdexams.data.server.dao;

import com.mct.wcdexams.domain.model.Category;

import java.util.List;

public interface CategoryDao extends BaseDao<Category> {

    long getTotalCategoryBySearch(String search);

    List<Category> getCategories(String search, int limit, int offset);

    Category getCategory(long id);

    boolean isExists(long id, String search);
}
