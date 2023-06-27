package org.example.game.fishing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

/**
 * 添加小鱼
 *
 * @author 浩哥
 */
public class Addfish extends Thread {
    int fishx = 1280;// 鱼的x坐标
    int fishy;// 鱼的y坐标
    int fishwidth;// 鱼的长度
    int fishheight;// 鱼的宽度
    int index = 0;
    BufferedImage fishimage;// 存放鱼图片的缓冲区
    BufferedImage[] fishimages = new BufferedImage[9];// 存放某种鱼的所有图片的图片缓冲区数组
    Random ran = new Random();
    boolean live = true;// 判断鱼是否被打中,若被大众,live =false

    /**
     * 当鱼被击中或者游出游戏界面时,初始化鱼的位置
     */
    void getOut() {
        fishx = 1280;
        fishy = ran.nextInt(470 - fishheight);
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    /**
     * 线程类的构造方法,每当new一个线程类.就初始化fishname代表的一种鱼,把改鱼的所有图片以及鱼的属性初始化
     *
     * @param fishname fishname一种鱼的名字
     */
    Addfish(String fishname) {
        for (int i = 0; i < 9; i++) {
            BufferedImage tempimage = null;// 临时存放鱼的缓冲区
            try {
                // 根据fishname使用for循环将一种鱼的图片放入数组中
                tempimage = ImageIO.read(Objects.requireNonNull(Addfish.class.getResourceAsStream("/static/Fishing" +
                        "/images/" + fishname + "_0" + (i + 1) + ".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            fishimages[i] = tempimage;// 将临时缓冲区中的图片放入数组中
        }
        fishimage = fishimages[index];// 表示下标为0的图片，鱼出现的起始图片
        fishwidth = fishimage.getWidth();// 获取鱼图片的宽度
        fishheight = fishimage.getHeight();// 获取鱼图片的高度
        fishy = ran.nextInt(500 - fishheight);// 使用随机数随机初始化鱼的y坐标
    }

    /**
     * run方法实现每条鱼的游动,每创建一个Addfish对象,初始化一种鱼,并启动一个线程
     */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(80); // 每80ms刷新一次鱼的位置和图片，来实现鱼的移动以及鱼的动作
                index++;
                fishimage = fishimages[index % fishimages.length];
                fishx -= ran.nextInt(15);// 鱼每次移动的像素数
                // 如果鱼游出游戏界面,则调用getOut()方法,重新初始化鱼的位置
                if (fishx + fishwidth < 0 || fishy <= 0 || fishy >= 750) {
                    getOut();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
