package org.example.test;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.example.beans.QQ;
import org.example.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 对 JDBC 的一个测试
 *
 * @author Lutong99
 */
public class TestJDBC {

    @Test
    public void testGetConnection() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        System.out.println(conn);
    }

    @Test
    public void testCreateTable() throws Exception {
        // 测试创建表
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "create table if not exists test_table(id int primary key, test varchar(20) not null);";
        int execute = queryRunner.execute(sql);
        System.out.println(execute);

    }

    @Test
    public void testInsert() throws Exception {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "insert into qq_table (qq_number, qq_password) values (?, ?)";

        Object[] params = {"54321", "123"};
        System.out.println(queryRunner.execute(sql, params));

        ResultSetHandler<QQ> rsHandler = new ResultSetHandler<QQ>() {

            @Override
            public QQ handle(ResultSet rs) throws SQLException {
                if (rs.next()) {
                    String password = rs.getString("qq_password");
                    String nickname = rs.getString("qq_nickname");

                    QQ qq = new QQ(nickname, password);
                    return qq;
                }

                return null;
            }

        };

        // BeanHandler<QQ> rsh = new BeanHandler<>(QQ.class);
        System.out.println("Hello world");
        QQ insert = queryRunner.query(sql, rsHandler, params);

        System.out.println(insert);
        System.out.println("Hello world");
    }

}
