package org.example.game.moveBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;

/**
 * 推箱子的剧中窗口
 *
 * @author Lutong99
 */
public class MoveBoxCenterPanel extends JPanel {
    /**
     * 仅仅为了不让显示警告
     */
    private static final long serialVersionUID = 1L;
    private int hua = 1;
    private int up = 1;
    private int down = 2;
    private int left = 3;
    private int right = 4;
    private int direction = down;// 默认向下

    private int hero = 5;// 定位人的位置
    private int heroX;
    private int heroY;// 人当前位置
    private int preOneX;
    private int preOneY;
    private int preTwoX;
    private int preTwoY;// 人前一格前两格位置

    // 草地 箱子代表的值
    private int grass = 2;
    private int box = 3;
    private int destination = 4;
    private int hong = 9;
    private int previousMap[][];
    int level;
    private int map[][];// 获取地图大小
    // 步骤
    private Stack<step> steps = new Stack<step>();

    JLabel levelJLabel;
    // 引入图片
    private Image imgs[] = {new ImageIcon("static/GameImages/.gif").getImage(), // 0
            new ImageIcon("static/GameImages/1.gif").getImage(), // 1
            new ImageIcon("static/GameImages/2.jpg").getImage(), // 2
            new ImageIcon("static/GameImages/9.jpg").getImage(), // 3
            new ImageIcon("static/GameImages/4a.GIF").getImage(), // 4
            new ImageIcon("static/GameImages/5.gif").getImage(), // 5
            new ImageIcon("static/GameImages/6.gif").getImage(), // 6
            new ImageIcon("static/GameImages/7.gif").getImage(), // 7
            new ImageIcon("static/GameImages/8.gif").getImage(), // 8
            new ImageIcon("static/GameImages/9a.GIF").getImage(), // 9
            new ImageIcon("static/GameImages/aaaa.jpg").getImage()};// 10

    // 构造函数

    public MoveBoxCenterPanel() {
        map = MoveBoxMap.getMap(level);
        this.initGame();
        this.addKeyListener(new B());// 授权给键盘事件
        this.addMouseListener(new C());// 授权给鼠标事件

    }

    public void setText() {
        levelJLabel.setText("LEVEL:" + level);
    }

    // 请求焦点继承鼠标事件
    class C extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO 自动生成的方法存根
            MoveBoxCenterPanel.this.requestFocus();
            hua = 2;
            MoveBoxCenterPanel.this.repaint();
        }

    }

    // 继承键值
    class B extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            // TODO 自动生成的方法存根
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                direction = up;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                direction = down;
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                direction = left;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                direction = right;
            }

            gethanglie();// 获取行列
            move();
            MoveBoxCenterPanel.this.repaint();// 重新绘画
            // 过关
            boolean bin = iswin();
            if (bin) {
                JOptionPane.showMessageDialog(null, "恭喜过关！");
                if (level <= 11) {
                    steps.clear();// 清空步骤 防止悔一步返回
                    level += 1;// 加一
                    setText();// 过关等级加
                    initGame();// 游戏初始化
                    MoveBoxCenterPanel.this.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "恭喜通关！");
                    return;
                }
            }
        }

    }

    // 定义步骤
    class step {
        /**
         * @return the mapData
         */
        public int[][] getMapData() {
            return mapData;
        }

        /**
         * @param mapData the mapData to set
         */
        public void setMapData(int[][] mapData) {
            this.mapData = mapData;
        }

        /**
         * @return the direc
         */
        public int getDirec() {
            return direc;
        }

        /**
         * @param direc the direc to set
         */
        public void setDirec(int direc) {
            this.direc = direc;
        }

        private int mapData[][] = new int[20][20];
        private int direc;
    }

    // 主要调用方法
    public void huiyibu() {
        if (steps.size() <= 0) {
            JOptionPane.showMessageDialog(null, "没有步骤可以返回了!");
            return;
        }
        step s = steps.pop();// 从堆栈中取出步骤输出
        this.map = s.getMapData();
        this.direction = s.getDirec();// 一次一次输出
        this.repaint();// 重新绘制

    }

    // 判断是否通关
    public boolean iswin() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (map[i][j] == box) {// 如果有一个黄色箱子
                    return false;
                }
            }
        }
        return true;

    }

    // 记录步骤方法
    public void JLstep() {
        int d[][] = new int[20][20];
        step s = new step();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                d[i][j] = map[i][j];
                s.setMapData(d);// 记录地图数据
            }
        }
        s.setDirec(this.direction);// 记录方向
        steps.push(s);// 把得到的步骤压栈

    }

    // 判断能否可以走
    public void move() {
        // 如果前一格是草地或目的地
        if (map[preOneX][preOneY] == grass || map[preOneX][preOneY] == destination) {
            JLstep();
            int yuanshi = getyuanshi(heroX, heroY);// 接收
            map[heroX][heroY] = yuanshi;// 人展的位置为原来的物体 草货目的地
            map[preOneX][preOneY] = hero;// 前一格变为人
        }
        // 如果钱一个是箱子货红色箱子
        if (map[preOneX][preOneY] == box || map[preOneX][preOneY] == hong) {
            if (map[preTwoX][preTwoY] == grass) {// 再判断第二个是否为草地
                JLstep();
                int yuanshi = getyuanshi(heroX, heroY);
                map[heroX][heroY] = yuanshi;// 人展的位置为原来的物体 草货目的地
                map[preOneX][preOneY] = hero;// 前一格为人
                map[preTwoX][preTwoY] = box;// qian两格为箱子
            } else if (map[preTwoX][preTwoY] == destination) {// 如果第二格为目的地
                JLstep();
                int yuanshi = getyuanshi(heroX, heroY);
                map[heroX][heroY] = yuanshi;// 人展的位置为原来的物体 草货目的地
                map[preOneX][preOneY] = hero;// 前一格为人
                map[preTwoX][preTwoY] = hong;// qian两格为红箱子

            }

        }

    }

    public int getyuanshi(int i, int j) {
        // 从原始地图中获取值
        int value = previousMap[i][j];
        // 只有人和箱子才能变为草地
        if (value == hero || value == box) {
            value = grass;
        }
        // 红箱子变为目的地
        if (value == hong) {
            value = destination;
        }
        return value;// 返回值
    }

    public void gethanglie() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (map[i][j] == 5) {
                    heroX = i;
                    heroY = j;// 获取人的位置行和列
                }
            }
        }
        if (direction == up) {// 获取人移动的位置行和列
            preOneX = heroX - 1;
            preOneY = heroY;
            preTwoX = heroX - 2;
            preTwoY = heroY;
        } else if (direction == down) {
            preOneX = heroX + 1;
            preOneY = heroY;
            preTwoX = heroX + 2;
            preTwoY = heroY;
        } else if (direction == left) {
            preOneX = heroX;
            preOneY = heroY - 1;
            preTwoX = heroX;
            preTwoY = heroY - 2;
        } else if (direction == right) {
            preOneX = heroX;
            preOneY = heroY + 1;
            preTwoX = heroX;
            preTwoY = heroY + 2;
        }
    }

    // 游戏开始具体实现方法
    public void initGame() {
        this.direction = down;// 默认向下 解决悔一步和过关的面向问题
        map = MoveBoxMap.getMap(level);// 引入地图值
        previousMap = MoveBoxMap.getMap(level);// 获取当前位置
    }

    // 根据要求改变关卡地图信息
    public void setlevel(int level) {
        this.level = level;// 记录传过来的关卡
        initGame();// 获取地图信息
        this.repaint();// 重新绘画
        steps.clear();// 清空步骤防止选关后返回上一关
    }

    @Override
    public void paint(Graphics g) {
        // TODO 自动生成的方法存根
        super.paint(g);
        if (hua == 2) {
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    int tag = map[i][j];// 赋值
                    int top = i * 30;
                    int left1 = j * 30;
                    if (tag == hero) {
                        if (direction == up) {
                            g.drawImage(imgs[8], left1, top, this);
                        } else if (direction == down) {
                            g.drawImage(imgs[5], left1, top, this);
                        } else if (direction == left) {
                            g.drawImage(imgs[6], left1, top, this);
                        } else if (direction == right) {
                            g.drawImage(imgs[7], left1, top, this);
                        }
                    } else {
                        g.drawImage(imgs[tag], left1, top, this);
                    }

                }

            }
            // 让面板获取焦点
            // 中心面板.this.requestFocus();//让绘制玩图就获取焦点
        }
    }
}
