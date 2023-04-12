package com.e59.game.fishing;

/**
 * 游戏的启动类
 *
 * @author 浩哥
 *
 */
public class StartGame {
	/*
	 * 启动游戏,并将Helper中的类对象初始化
	 */
	public void PlayGame() {
		new Bgm().start();
		StartFishing sf = new StartFishing(1200, 628, "捕鱼达人");
		Helper.sf = sf;
		sf.setVisible(true);
		MyFishing mf = new MyFishing(1280, 750, "捕鱼达人");
		Helper.mf = mf;
		Rangking rk = new Rangking(400, 600, "排行榜");
		Helper.rk = rk;
		Endchoos ec = new Endchoos(400, 100, "游戏结束");
		Helper.ec = ec;
	}

//	public static void main(String[] args) {
//		new StartGame().PlayGame();
//	}
}
