package com.github.holalee.jdbc;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

@FunctionalInterface
public interface DbCallBack {

    /**
     * 数据库元数据操作
     * @param db
     * @throws SQLException
     */
    void call(DatabaseMetaData db) throws SQLException;
}
