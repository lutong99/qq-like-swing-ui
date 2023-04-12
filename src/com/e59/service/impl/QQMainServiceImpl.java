package com.e59.service.impl;

import com.e59.beans.QQ;
import com.e59.dao.QQMainDAO;
import com.e59.dao.impl.QQMainDAOImpl;
import com.e59.service.QQMainService;

public class QQMainServiceImpl implements QQMainService{
	
	private QQMainDAO qqMainDAO = new QQMainDAOImpl();

	@Override
	public boolean updateInfo(QQ updateQq) {
		boolean updateInfo = qqMainDAO.updateInfo(updateQq);
		return updateInfo;
	}

}
