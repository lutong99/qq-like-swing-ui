package com.e59.service.impl;

import com.e59.beans.QQ;
import com.e59.dao.QQRegisterDAO;
import com.e59.dao.impl.QQRegisterDAOImpl;
import com.e59.service.QQRegisterService;

/**
 * QQ注册服务的实现类
 *
 * @author  Lutong99
 *
 */
public class QQRegisterServiceImpl implements QQRegisterService {
	/**
	 * 关联DAO
	 */
	private QQRegisterDAO qqRegisterDAO = new QQRegisterDAOImpl();

	@Override
	public String registerQQ(QQ qq) {
		
		String number = qqRegisterDAO.registerQQ(qq);

		return number;
	}

}
