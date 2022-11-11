package com.mct.wcdexams.data.server.dao;

public interface BaseDao<Model> {

    long create(Model model);

    boolean update(Model model);

    boolean delete(Model model);

    boolean delete(long id);

}
