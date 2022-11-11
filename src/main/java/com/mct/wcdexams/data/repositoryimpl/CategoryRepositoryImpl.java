package com.mct.wcdexams.data.repositoryimpl;

import com.mct.wcdexams.domain.repository.CategoryRepository;
import com.mct.wcdexams.data.server.dao.CategoryDao;
import com.mct.wcdexams.domain.model.Category;

import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryDao categoryDao;

    public CategoryRepositoryImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public long createCategory(Category category) {
        return categoryDao.create(category);
    }

    @Override
    public boolean updateCategory(Category category) {
        return categoryDao.update(category);
    }

    @Override
    public boolean removeCategory(Category category) {
        return categoryDao.delete(category);
    }

    @Override
    public boolean removeCategory(int id) {
        return categoryDao.delete(id);
    }

    @Override
    public long getTotalCategoryBySearch(String search) {
        return categoryDao.getTotalCategoryBySearch(search);
    }

    @Override
    public List<Category> getCategories(String search, int limit, int offset) {
        return categoryDao.getCategories(search, limit, offset);
    }

    @Override
    public Category getCategory(long id) {
        return categoryDao.getCategory(id);
    }

    @Override
    public boolean isExists(long id, String search) {
        return categoryDao.isExists(id, search);
    }
}
