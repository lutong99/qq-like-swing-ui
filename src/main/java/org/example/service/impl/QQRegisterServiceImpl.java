package org.example.service.impl;

import org.example.beans.QQ;
import org.example.dao.QQRegisterDAO;
import org.example.dao.impl.QQRegisterDAOImpl;
import org.example.service.QQRegisterService;

/**
 * QQ注册服务的实现类
 *
 * @author Lutong99
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
