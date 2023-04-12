package com.e59.dao;

import com.e59.beans.QQ;

/**
 * QQ登陆的时候使用的DAO, 
 *
 * @author  Lutong99
 *
 */
public interface QQLoginDAO extends QQDAO{
	/**
	 * qq的验证登陆
	 * 
	 * @param qq 登陆过来的QQ
	 * @return 成功则返回对象, 失败返回{@code null}
	 */
	public abstract QQ loginCheck(QQ qq);
}
