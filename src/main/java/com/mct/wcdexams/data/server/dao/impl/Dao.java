package com.mct.wcdexams.data.server.dao.impl;


import com.mct.wcdexams.data.server.database.connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * @author Sonnc
 */
abstract class Dao {

    protected final DatabaseConnection connection;

    protected Dao(DatabaseConnection connection) {
        this.connection = connection;
    }

    Connection openConnection() {
        return connection.open();
    }

    void closeConnection() {
        connection.close();
    }

    ResultSet execSelect(String sql) {
        return connection.execSelect(sql);
    }

    long execInsertAndClose(String sql) {
        long id = connection.execInsert(sql);
        closeConnection();

        return id;
    }

    boolean execUpdateAndClose(String sql) {
        boolean success = connection.execUpdate(sql) > 0;
        closeConnection();

        return success;
    }
}
