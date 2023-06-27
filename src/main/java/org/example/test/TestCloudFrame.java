package org.example.test;

import org.example.beans.QQ;
import org.example.view.QQCloudFrame;

/**
 * 测试微云的窗口
 *
 * @author Lutong99
 */
public class TestCloudFrame {
    public static void main(String[] args) {
        QQ qq = new QQ("123456", "123456");
        qq.setPhoto("static/RegisterImages/Profiles/5_online.png");
        qq.setNickname("乔广通");
        qq.setLevel(1);
        System.out.println(qq.getPhoto());
        qq.setSignature("Hello world");
        new QQCloudFrame(qq);
    }
}
