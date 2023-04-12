package com.e59.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.e59.beans.QQ;
import com.e59.view.QQMainFrame;

/**
 * 测试主面板
 *
 * @author  Lutong99
 *
 */
public class TestMainFrame {
	public static void main(String[] args) {
		QQ qq = new QQ("123456", "123456");
		qq.setPhoto("resources/RegisterImages/Profiles/5_online.png");
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
