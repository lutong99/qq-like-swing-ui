package com.e59.game.fishing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * 继承Thread类重写run方法,并引入jar包,为游戏添加背景音乐
 * 
 * @author 浩哥
 *
 */
public class Bgm extends Thread {
	@Override
	public void run() {
		try {
			Player bgm = new Player(new FileInputStream(new File("resources/Fishing/bgm.mp3")));
			bgm.play();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}
}
