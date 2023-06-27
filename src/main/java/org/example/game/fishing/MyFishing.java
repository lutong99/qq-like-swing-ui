package org.example.game.fishing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 我们的捕鱼达人的框
 *
 * @author 浩哥
 */
public class MyFishing extends CenterJframe {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JPanel pan = new MyJPanel();
    JLabel coinNum = new JLabel(Helper.score + "");// 金币
    JLabel time = new JLabel(Helper.times + "");// 时间
    BufferedImage cannon;// 大炮图片缓冲区
    int cannonX = 590;// 大炮x坐标
    int cannonY = 610;// 大炮y坐标
    boolean cannon_Right = true;// true代表大炮可以向右移动
    BufferedImage coin;// 金币图片缓冲区
    BufferedImage background;// 背景图片缓冲区
    BufferedImage timeimage;// 时间图片缓冲区
    Random ran = new Random();
    Bullet bullet = null;// 设置子弹对象为null
    List<Bullet> bullets = new ArrayList<Bullet>();// 新建子弹集合
    boolean fishlive = true;// 判断鱼是否存活
    int index = -1;
    TimeThread timethread = new TimeThread();// new时间线程类

    public MyFishing(int width, int height, String title) {
        super(width, height, title);
        this.init();// 初始化
        this.addpan();// 加入基本组件
        this.coinNumLis();// 大炮射击事件
        this.addCannonLis();// 大炮移动事件
    }

    /**
     * MyJPanel类继承JPanel类,重写paintComponent方法实现重绘
     *
     * @author dell
     */
    class MyJPanel extends JPanel {
        /**
         *
         */
        private static final long serialVersionUID = 1L;
        Addfish addfish = null;// Addfish类对象
        Addfish[] addfishs = new Addfish[11];// Addfish类对象数组

        /**
         * 构造方法 new出每种鱼的Addfish对象放入数组中,并启动线程
         */
        public MyJPanel() {
            for (int i = 0; i < 11; i++) {
                /*
                 * 使用for循环创建Addfish对象,调用Addfish对象的构造,将fishname表示的鱼的所有图片加载 并获取鱼属性
                 */
                addfish = new Addfish("fish0" + (i + 1));// new出fishname代表的鱼
                addfishs[i] = addfish;// 将Addfish对象存入数组中
                addfish.start();// 每当加载一种鱼时,调用Addfish中的run()方法启动一个线程
            }
        }

        /**
         * 判断子弹是否打中鱼
         *
         * @return 若打中 返回true
         */
        public boolean capture() {
            boolean ishit = false;
            // 二位数组,存放每种鱼的xy坐标和宽度高度,每重绘一次 x,y,长度,宽度都会改变
            int[][] fishinfor = new int[11][4];
            // for循环存入数据
            for (int i = 0; i < addfishs.length; i++) {
                fishinfor[i][0] = addfishs[i].fishx;
                fishinfor[i][1] = addfishs[i].fishy;
                fishinfor[i][2] = addfishs[i].fishwidth;
                fishinfor[i][3] = addfishs[i].fishheight;
            }
            /*
             * 子弹的属性,随着重绘改变
             */
            int bulletx = bullet.bulletx;
            int bullety = bullet.bullety;
            int bulletwidth = bullet.bulletwidth;
            @SuppressWarnings("unused")
            int bulletheight = bullet.bulletheight;
            /**
             * 循环每条鱼的属性和子弹的属性比较 若if成立,则表明有鱼和子弹相撞,则表示子弹击中鱼 ishit =true index表示击中的是那条鱼
             * 分数+20并设置到游戏界面中 break跳出循环
             *
             */
            for (int i = 0; i < fishinfor.length; i++) {
                if (fishinfor[i][1] + fishinfor[i][3] >= bullety && bulletx <= fishinfor[i][0] + fishinfor[i][2]
                        && bulletx + bulletwidth > fishinfor[i][0]) {
                    ishit = true;
                    bullet.live = false;
                    index = i;
                    Helper.score += 20;
                    coinNum.setText(Helper.score + "");
                    break;
                }
            }
            return ishit;
        }

        // 重写paintComponent方法实现重绘
        @Override
        protected void paintComponent(Graphics g) {
            // 每次重绘前判断分数是否小于0和游戏时间是否为0,若有一个成立则isover =true
            if (Helper.score <= 0 || Helper.times == 0) {// 判断游戏是否结束
                Helper.isover = true;// 若成立,isover = true
                Helper.ec.setVisible(true);// 若成立 使结束游戏界面可见
            }
            super.paintComponent(g);
            g.drawImage(background, 0, 0, 1280, 720, this);// 游戏背景
            g.drawImage(coin, 1090, 5, 45, 40, this);// 金币图片
            g.drawImage(cannon, cannonX, cannonY, this);// 大炮图片
            g.drawImage(timeimage, 0, 0, this);// 游戏时间图片
            // 循环遍历子弹对象集合,打印子弹
            for (int i = 0; i < bullets.size(); i++) {
                bullet = bullets.get(i);// 初始化bullet
                // 若bullet 中live = false说明子弹打中鱼或者移动出游戏界面,从集合中移除
                if (!bullet.isLive()) {
                    bullets.remove(i);
                } else {
                    bullet.draw(g);// 调用bullet的draw方法打印自担
                    // 每次打印子弹判断该子弹是否打中鱼
                    if (capture()) {
                        fishlive = false;
                        // 若打中,使下表为index的鱼调用getOut()方法
                        addfishs[index].getOut();
                    }
                }
            }
            /*
             * 使用for循环调用addfishs中的所有Addfish对象并将fishimage(鱼的初始图片)打印到游戏界面
             * 配合线程,实现鱼的游动和游动过程中鱼的动作变化(图片变化)
             */
            for (int i = 0; i < addfishs.length; i++) {
                Addfish tempfish = addfishs[i];
                g.drawImage(tempfish.fishimage, tempfish.fishx, tempfish.fishy, null);
            }
            pan.repaint();// 鱼和子弹的重绘s
//			
        }
    }

    /**
     * 在pan中加入基本组件并将pan加入this
     */
    void addpan() {
        pan.setSize(1280, 720);
        pan.setLayout(null);
        pan.setFocusable(true);
        this.add(pan);
        try {
            this.setIconImage(ImageIO.read(new File("static/Fishing/章鱼.png")));// 设置图标
            /**
             * 初始化图片缓冲区
             */
            cannon = ImageIO.read(new File("static/Fishing/pao.png"));
            coin = ImageIO.read(new File("static/Fishing/金币.png"));
            background = ImageIO.read(new File("static/Fishing/bg.jpg"));
            timeimage = ImageIO.read(new File("static/Fishing/时间.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
         * 设置金币JLabel和事件JLabel的位置,字体格式.字体颜色,并放入pan中
         */
        coinNum.setBounds(1150, 0, 200, 50);
        coinNum.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
        coinNum.setForeground(new Color(0XFFD700));
        time.setBounds(70, 0, 100, 50);
        time.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
        time.setForeground(new Color(0X40E0D0));
        pan.add(coinNum);
        pan.add(time);
    }

    /**
     * 大炮移动事件 按下左键大炮向左移动,按下右键,大炮向右移动
     */
    void addCannonLis() {
        pan.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();// 获取按下的按键
                if (keyCode == KeyEvent.VK_LEFT) {
                    cannonX -= 20;
                    if (cannonX <= 0) {
                        cannonX = 0;
                    }
                }
                if (keyCode == KeyEvent.VK_RIGHT) {
                    cannonX += 20;
                    if (cannonX >= 1180) {
                        cannonX = 1180;
                    }
                }
                pan.repaint();// pan重绘
            }
        });
    }

    /**
     * 发射子弹事件,按下空格,发射一颗子弹
     */
    void coinNumLis() {
        pan.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_SPACE) {
                    // 按下空格,将file()方法初始化的bullet对象放入bullets集合中
                    bullets.add(file());
                    Helper.score -= 10;// 分数-10
                    coinNum.setText(Helper.score + "");// 设置分数到游戏界面
                    if (Helper.score < 0) {// 若分数<0 isover = true
                        Helper.isover = true;
                    }
                }
            }
        });
    }

    /**
     * 初始化bullet
     *
     * @return Bullet对象
     */
    Bullet file() {
        bullet = new Bullet(cannonX + 35, cannonY);
        return bullet;
    }
}
