package com.mct.wcdexams.di;

import com.mct.wcdexams.data.repositoryimpl.CategoryRepositoryImpl;
import com.mct.wcdexams.data.server.dao.CategoryDao;
import com.mct.wcdexams.data.server.database.ShopDatabase;
import com.mct.wcdexams.domain.repository.CategoryRepository;
import com.mct.wcdexams.domain.use_cases.CategoryUseCases;
import com.mct.wcdexams.domain.use_cases.category.*;
import org.jetbrains.annotations.NotNull;

public class DataInjection {

    private static final Object lock = new Object();
    private static volatile CategoryUseCases categoryUseCases;

    @NotNull
    public static CategoryUseCases provideCategoryUseCases() {
        if (categoryUseCases == null) {
            synchronized (lock) {
                if (categoryUseCases == null) {
                    CategoryDao categoryDao = ShopDatabase.getInstance().categoryDao();
                    CategoryRepository repository = new CategoryRepositoryImpl(categoryDao);
                    categoryUseCases = new CategoryUseCases(
                            new CreateCategory(repository),
                            new UpdateCategory(repository),
                            new RemoveCategory(repository),
                            new GetCategories(repository),
                            new GetCategory(repository),
                            new CheckExistsCategory(repository));
                }
            }
        }
        return categoryUseCases;
    }


    private DataInjection() {
        throw new RuntimeException("This class can't instance");
    }
}
