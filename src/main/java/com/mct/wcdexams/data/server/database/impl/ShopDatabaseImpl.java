package com.mct.wcdexams.data.server.database.impl;

import com.mct.wcdexams.data.server.dao.CategoryDao;
import com.mct.wcdexams.data.server.dao.impl.CategoryDaoImpl;
import com.mct.wcdexams.data.server.database.ShopDatabase;
import com.mct.wcdexams.data.server.database.connection.ConnectionInfo;
import com.mct.wcdexams.utils.Config;

public class ShopDatabaseImpl extends ShopDatabase {

    private volatile CategoryDao categoryDao;

    public ShopDatabaseImpl() {
        super(new ConnectionInfo(Config.HOSTNAME, Config.USERNAME, Config.PASSWORD, Config.DATABASE));
    }

    @Override
    public CategoryDao categoryDao() {
        if (categoryDao == null) {
            synchronized (this) {
                if (categoryDao == null) {
                    categoryDao = new CategoryDaoImpl(this);
                }
            }
        }
        return categoryDao;
    }

}
