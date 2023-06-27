package org.example.test;

import org.example.beans.QQ;
import org.example.view.QQMainFrame;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 测试主面板
 *
 * @author Lutong99
 */
public class TestMainFrame {
    public static void main(String[] args) {
        QQ qq = new QQ("123456", "123456");
        qq.setPhoto("static/RegisterImages/Profiles/5_online.png");
        qq.setNickname("乔广通");
        qq.setBirthday("1999-03-22");
        qq.setGender("男");
        qq.setCountry("中国");
        qq.setProvince("山东");
        qq.setCity("济南");
        qq.setNation("汉族");
        qq.setEmail("");
        Date date = new Date();
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        qq.setRegister(simpleFormat.format(date));
        qq.setLevel(1);
        System.out.println(qq.getPhoto());
        qq.setSignature("Hello world");
        new QQMainFrame(qq);
        System.out.println(qq);
    }
}
