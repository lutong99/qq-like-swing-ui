package com.e59.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;

import com.e59.beans.QQ;
import com.e59.dao.QQLoginDAO;
import com.e59.utils.MD5Utils;

/**
 * QQLoginDAO的实现类
 *
 * @author  Lutong99
 *
 */
public class QQLoginDAOImpl implements QQLoginDAO {
	@Override
	public QQ loginCheck(QQ qq) {
		String sql = "select qq_number number, qq_nickname nickname, qq_photo photo, qq_gender gender, qq_birthday birthday, qq_country country, "
				+ "qq_province province, qq_city city, qq_nation nation, qq_email email, qq_register from qq_table where qq_number = ? and qq_password = ?";
		try {
			BeanHandler<QQ> rsh = new BeanHandler<QQ>(QQ.class);
			Object[] params = { qq.getNumber(), MD5Utils.encrypByMD5(qq.getPassword()) };
			QQ query = queryRunner.query(sql, rsh, params);
			return query;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
