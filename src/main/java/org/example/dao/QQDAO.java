package org.example.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.example.utils.JDBCUtils;

/**
 * 所有的DAO接口的父接口, 主要是为了统一
 *
 * @author Lutong99
 */
public interface QQDAO {

    /**
     * 为了更加方便的使用DbUtils 这个工具类
     */
    QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
}
