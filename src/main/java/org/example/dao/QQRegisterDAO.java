package org.example.dao;

import org.example.beans.QQ;

/**
 * 注册的时候为我们分配一个QQ号码
 *
 * @author Lutong99
 */
public interface QQRegisterDAO extends QQDAO {

    /**
     * 注册QQ用户, 向数据库中插入我们的QQ号码
     *
     * @param qq 没有密码的QQ对象
     * @return 我们已经注册成功的QQ号码的字符串
     */
     String registerQQ(QQ qq);

}
