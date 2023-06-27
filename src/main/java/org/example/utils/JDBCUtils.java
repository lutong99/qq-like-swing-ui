package org.example.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 这个类里面主要是一些静态方法.关闭资源, 获取链接等等. 但是关闭资源好像没有怎么用到
 *
 * @author Lutong99
 */
public class JDBCUtils {

    /**
     * druid 的数据源
     */
    private static DataSource dataSource;

    static {
        Properties pros = new Properties();
        InputStream inStream = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            pros.load(inStream);
            dataSource = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return 数据源
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 获取德鲁伊的数据库连接池
     *
     * @return 返回我们需要的数据库连接
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = dataSource.getConnection();
        return conn;
    }

    /**
     * 关闭我们可能用到的资源
     *
     * @param conn 数据库连接
     * @param ps   数据库使用的 sql 声明
     * @param rs   查询到的结果集
     */
    public static void closeResources(Connection conn, Statement ps, ResultSet rs) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
