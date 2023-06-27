package org.example.service.impl;

import org.example.beans.QQ;
import org.example.dao.QQLoginDAO;
import org.example.dao.impl.QQLoginDAOImpl;
import org.example.service.QQLoginService;

/**
 * QQLoginService的实现类
 *
 * @author Lutong99
 */
public class QQLoginServiceImpl implements QQLoginService {
    /**
     * 关联我们的DAO层
     */
    private final QQLoginDAO qqLoginDAO = new QQLoginDAOImpl();

    @Override
    public QQ loginCheck(QQ qq) {
        return qqLoginDAO.loginCheck(qq);
    }

}
