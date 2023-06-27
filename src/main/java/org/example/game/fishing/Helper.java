package org.example.game.fishing;

/**
 * 一些捕鱼达人游戏会用到的常量
 *
 * @author 浩哥
 */
public class Helper {
    public static StartFishing sf;// 获取静态StartFishing对象sf
    public static MyFishing mf;// 获取静态MyFishing对象mf
    public static Rangking rk;// 获取静态Rangking对象rk
    public static Endchoos ec;// 获取静态Endchoos对象ec
    public static volatile boolean isover = false;// 判断游戏是否结束
    public static int score = 100;// 初始分数
    public static int times = 60;// 初始游戏时间
}
