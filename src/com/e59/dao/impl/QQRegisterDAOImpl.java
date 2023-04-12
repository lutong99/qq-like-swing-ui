package com.e59.dao.impl;

import java.sql.SQLException;
import java.util.Random;

import com.e59.beans.QQ;
import com.e59.dao.QQRegisterDAO;

/**
 * 注册qq的数据库实现类
 *
 * @author  Lutong99
 *
 */
@SuppressWarnings("unused")
public class QQRegisterDAOImpl implements QQRegisterDAO {

	@Override
	public String registerQQ(QQ qq) {
		Random random = new Random();
		// TODO 注册验证
		String number = String.valueOf(random.nextInt(89999) + 10000);
		QQ qqRegister = new QQ(number, qq);

		// 向数据库中添加一个我们的qq号码
		/*
		 * qq_number, qq_nickname, qq_password, qq_photo, qq_gender, qq_birthday,
		 * qq_country, qq_province, qq_city, qq_nation, qq_email, qq_register
		 */

		String sql = "insert into qq_table (qq_number, qq_nickname, qq_password, qq_photo, qq_gender, qq_birthday, qq_country, qq_province, "
				+ "qq_city, qq_nation, qq_email, qq_register) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );";
		Object[] params = { qqRegister.getNumber(), qqRegister.getNickname(), qqRegister.getPassword(),
				qqRegister.getPhoto(), qqRegister.getGender(), qqRegister.getBirthday(), qqRegister.getCountry(),
				qqRegister.getProvince(), qqRegister.getCity(), qqRegister.getNation(), qqRegister.getEmail(),
				qqRegister.getRegister() };
		try {
			int isSuccess = queryRunner.update(sql, params);
			if (isSuccess > 0) {
				// createFriendTable(qqRegister.getNumber());
//				insertIntoQQGame(qqRegister.getNumber());
				createCloudTable(qqRegister.getNumber());
				return qqRegister.getNumber();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 向QQ游戏表中默认插入一条记录
	 * 
	 * @param number QQ号码
	 */
	private void insertIntoQQGame(String number) {
		String sql = "insert into qqgame_table values(" + number + ", 0);";
		try {
			int execute = queryRunner.execute(sql);
			if (execute > 0) {
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建一个qq好友的表格
	 * 
	 * @param number qq号码, 用来找到我们的QQ好友表
	 * @deprecated
	 */
	private void createFriendTable(String number) {

	}

	/**
	 * /** 创建一个qq好友的表格
	 * 
	 * @param number qq号码, 用来找到我们的QQ好友表
	 */
	private void createFriendsTable(String number) {
		String sql = "create table qq_friends_table" + number + " (qq_number int(5) primary key, qq_friends int(5), "
				+ "constraint id_qq_friend foreign key (qq_number) references qq_table (qq_number) on delete cascade on update cascade,"
				+ "constraint qq_friend foreign key (qq_friends) references qq_table (qq_number) on delete cascade on update cascade);";

		try {
			int execute = queryRunner.execute(sql);
			if (execute > 0) {
			} else {
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建一个微云表格
	 * 
	 * @param number QQ号码, 绑定微云的
	 */
	private void createCloudTable(String number) {
		String sql = "create table qq_cloud" + number
				+ "(cloud_id int(3) primary key auto_increment, cloud_name varchar(255), cloud_path varchar(255), cloud_time datetime, cloud_size varchar(30), cloud_state char(1) default '1');";
		try {
			queryRunner.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
