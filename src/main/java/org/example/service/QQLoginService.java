package org.example.service;

import org.example.beans.QQ;

/**
 * QQ登陆的验证方法
 *
 * @author Lutong99
 */
public interface QQLoginService extends QQService {

    /**
     * 登陆验证的方法
     *
     * @param qq 传入的只有账号密码的qq
     * @return 返回登陆的qq的所有的属性
     */
    public abstract QQ loginCheck(QQ qq);

}
