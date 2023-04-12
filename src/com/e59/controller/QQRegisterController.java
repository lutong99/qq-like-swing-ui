package com.e59.controller;

import java.util.concurrent.locks.ReentrantLock;

import com.e59.beans.QQ;
import com.e59.service.QQRegisterService;
import com.e59.service.impl.QQRegisterServiceImpl;

/**
 * QQ注册的控制器
 *
 * @author  Lutong99
 *
 */
public class QQRegisterController extends QQController {
	/** QQ注册控制器的单例 */
	private static QQRegisterController qqRegisterController = null;

	/** 锁 */
	private static ReentrantLock lock = new ReentrantLock();

	/** 关联业务层 */
	private QQRegisterService qqRegisterService = new QQRegisterServiceImpl();

	/**
	 * 私有化构造, 作为一个单例
	 */
	private QQRegisterController() {

	}

	/**
	 * @return qq注册控制器的单例
	 */
	public static QQRegisterController getQQLoginController() {
		if (qqRegisterController == null) {
			lock.lock(); // 加上锁, 为了安全, 其实在这个项目里面没有什么用
			try {
				if (qqRegisterController == null) {
					qqRegisterController = new QQRegisterController();
				}
			} finally {
				lock.unlock();
			}
		}
		return qqRegisterController;
	}

	/**
	 * 注册的时候使用的方法
	 * 
	 * @param nickname 昵称
	 * @param password 密码
	 * @param photo    头像的路径
	 * @param gender   性别
	 * @param birthday 生日
	 * @param country  国家
	 * @param province 省
	 * @param city     城市
	 * @param email    电子邮件
	 * @param nation   国家
	 * @param register 注册时间
	 * @return 返回一个QQ号码
	 */
	public String registerQQ(String nickname, String password, String photo, String gender, String birthday,
			String country, String province, String city, String email, String nation, String register) {

		QQ qq = new QQ(nickname, password, photo, gender, birthday, country, province, city, email, nation, '1',
				register, 1, "");
		String number = qqRegisterService.registerQQ(qq);
		return number;
	}

}
