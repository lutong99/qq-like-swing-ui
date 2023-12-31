package org.example.service;

import org.example.beans.QQ;

/**
 * QQ注册服务
 *
 * @author Lutong99
 */
public interface QQRegisterService extends QQService {

    /**
     * 注册QQ号码的服务方法
     *
     * @param qq 我们传入的没有密码的QQ号码
     * @return 返回注册成功后的QQ号码
     */
     String registerQQ(QQ qq);

}
