package org.example.controller;

import org.example.beans.QQ;
import org.example.service.QQMainService;
import org.example.service.impl.QQMainServiceImpl;

import java.util.concurrent.locks.ReentrantLock;

/**
 * QQ 主面板的控制器, 目前已知的功能是, 完成修改资料的功能
 *
 * @author Lutong99
 */
public class QQMainController extends QQController {

    /**
     * QQMainController的单例, 懒汉模式
     */
    private static QQMainController qqMainController = null;

    /**
     * 锁, 控制线程安全
     */
    private static ReentrantLock lock = new ReentrantLock();

    /**
     * 关联一个MainService
     */
    private QQMainService qqMainService = new QQMainServiceImpl();

    /**
     * 私有化构造器, 做一个单例模式
     */
    private QQMainController() {

    }

    /**
     * 获取一个QQMainController的一个实例
     *
     * @return QQMainController的一个实例
     */
    public static QQMainController getInstance() {
        if (qqMainController == null) {
            lock.lock();
            try {
                if (qqMainController == null) {
                    qqMainController = new QQMainController();
                }
            } finally {
                lock.unlock();
            }
        }
        return qqMainController;
    }

    /**
     * 更新信息
     *
     * @param loginQq  登陆的qq
     * @param nickname 修改后的昵称
     * @param password 修改后的密码
     * @param photo    修改后的头像
     * @param gender   性别
     * @param birthday 生日
     * @param country  国家
     * @param province 省
     * @param city     城市
     * @param email    电子邮件
     * @param nation   民族
     * @return {@code true}修改成功, {@code false} 修改失败
     */
    public boolean updateInfo(QQ loginQq, String nickname, String password, String photo, String gender,
                              String birthday, String country, String province, String city, String email, String nation) {
        QQ updateQq = new QQ(loginQq.getNumber(), nickname, password, photo, gender, birthday, country, province, city,
                email, nation, '1');

        boolean updateInfo = qqMainService.updateInfo(updateQq);
        return updateInfo;
    }

}
