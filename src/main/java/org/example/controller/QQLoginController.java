package org.example.controller;

import org.example.beans.QQ;
import org.example.service.QQLoginService;
import org.example.service.impl.QQLoginServiceImpl;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 登陆的控制器
 *
 * @author Lutong99
 */
public class QQLoginController extends QQController {
    /**
     * 单例控制器
     */
    private static QQLoginController qqLoginController = null;

    /**
     * 锁, 控制懒汉模式的线程安全, 虽然在这里没用
     */
    private static ReentrantLock lock = new ReentrantLock();

    /**
     * 关联业务层
     */
    private QQLoginService qqLoginService = new QQLoginServiceImpl();

    /**
     * 私有化构造, 作为一个单例
     */
    private QQLoginController() {

    }

    /**
     * @return 单例模式
     */
    public static QQLoginController getQqLoginController() {
        if (qqLoginController == null) {
            lock.lock(); // 加上锁, 为了安全, 其实在这个项目里面没有什么用
            try {
                if (qqLoginController == null) {
                    qqLoginController = new QQLoginController();
                }
            } finally {
                lock.unlock();
            }
        }
        return qqLoginController;
    }

    /**
     * 封装对象, 交给业务层来做数据验证
     *
     * @param qqNumber   qq号码,
     * @param qqPassword qq密码
     * @return 返回验证的qq, 主要用来放在我们的qq主面板中
     */
    public QQ loginCheck(String qqNumber, String qqPassword) {
        QQ qq = new QQ(qqNumber, qqPassword);

        QQ loginedQQ = qqLoginService.loginCheck(qq);

        System.out.println("loginedQQ = " + loginedQQ);

        return loginedQQ;

    }

}
