package com.e59.service.impl;

import com.e59.beans.QQ;
import com.e59.dao.QQLoginDAO;
import com.e59.dao.impl.QQLoginDAOImpl;
import com.e59.service.QQLoginService;

/**
 * QQLoginService的实现类
 *
 * @author  Lutong99
 *
 */
public class QQLoginServiceImpl implements QQLoginService {
	/**
	 * 关联我们的DAO层
	 */
	private QQLoginDAO qqLoginDAO = new QQLoginDAOImpl();

	@Override
	public QQ loginCheck(QQ qq) {
		QQ loginCheck = qqLoginDAO.loginCheck(qq);
		return loginCheck;
	}

}
