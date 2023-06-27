package org.example.service.impl;

import org.example.beans.QQ;
import org.example.dao.QQMainDAO;
import org.example.dao.impl.QQMainDAOImpl;
import org.example.service.QQMainService;

public class QQMainServiceImpl implements QQMainService {

    private QQMainDAO qqMainDAO = new QQMainDAOImpl();

    @Override
    public boolean updateInfo(QQ updateQq) {
        boolean updateInfo = qqMainDAO.updateInfo(updateQq);
        return updateInfo;
    }

}
