package org.example.game.fishing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 子弹类,初始化子弹的基本属性
 *
 * @author 浩哥
 */
public class Bullet {
    public static final int SPEED = 3;// 子弹的移动速度
    int bulletx;// 子弹的x坐标
    int bullety;// 子弹的y坐标
    BufferedImage bullet;// 存放子弹图片的图片缓冲区
    boolean live = true;// 判断子弹是否打中鱼或者子弹是否移动出游戏界面,若打中或出界,live = false
    int bulletwidth;// 子弹的宽度
    int bulletheight;// 子弹的高度

    /**
     * @param bulletx 子弹的x坐标
     * @param bullety 子弹的y坐标
     */
    public Bullet(int bulletx, int bullety) {
        this.bulletx = bulletx;
        this.bullety = bullety;
    }

    public boolean isLive() {
        return live;
    }

    /**
     * 画子弹
     *
     * @param g 画笔
     */
    public void draw(Graphics g) {
        try {
            // 初始化子弹的图片缓冲区
            bullet = ImageIO.read(new File("static/Fishing/images/buttle01.png"));
            g.drawImage(bullet, bulletx, bullety, null);// 画子弹
        } catch (IOException e) {
            e.printStackTrace();
        }
        bullety -= SPEED;
        bulletwidth = bullet.getWidth();// 获取子弹的宽度
        bulletheight = bullet.getHeight();// 获取子弹的高度
        // 若子弹出界,则live = false
        if (bullety + bullet.getWidth() < 0) {
            live = false;
        }
    }
}
