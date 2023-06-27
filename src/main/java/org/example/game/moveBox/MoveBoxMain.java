package org.example.game.moveBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

/**
 * 推箱子的主界面
 * @author 来源自网络
 */
public class MoveBoxMain extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    int hua = 1;
    JButton b1, b2, b3, b4, b5, b6, b7, b8, b9;
    JComboBox<String> cmmusic;
    String musicfile[] = {"flourish.mid", "guang.mid", "popo.mid", "eyes on me.mid", "qin.mid"};// 音乐文件
    String musiceName[] = {"默认", "灌篮高手", "泡泡堂", "eyes on me", "琴箫合奏"};// 为下拉框添加内容
    JPanel dong;
    MoveBoxSound sound;// 声音
    Container con;
    JLabel close;
    MoveBoxCenterPanel centerPanel;// 调用中心面板

    public MoveBoxMain() {
        super("推箱子");
        centerPanel = new MoveBoxCenterPanel();

        // 窗口图片
        setIconImage(new ImageIcon(Objects.requireNonNull(MoveBoxMain.class.getResource("/static/GameImages/9.GIF"))).getImage());

        // 音乐文件
        sound = new MoveBoxSound();

        con = getContentPane();
        setLayout(new BorderLayout());

        dong = new JPanel();
        dong.setLayout(null);
        centerPanel.setLayout(null);
        dong.setPreferredSize(new Dimension(100, 600));// 自定义偏好大小
        centerPanel.setPreferredSize(new Dimension(600, 600));

        centerPanel.levelJLabel = new JLabel("LEVEL:" + centerPanel.level);
        dong.add(centerPanel.levelJLabel);
        centerPanel.levelJLabel.setBounds(0, 20, 100, 30);
        Font f = new Font("宋体", Font.PLAIN, 24);
        centerPanel.levelJLabel.setFont(f);
        centerPanel.levelJLabel.setForeground(Color.red);

        b1 = new JButton("重来");
        dong.add(b1);
        b1.setBounds(10, 70, 80, 20);

        b2 = new JButton("悔一步");
        dong.add(b2);
        b2.setBounds(10, 120, 80, 20);

        b3 = new JButton("第一关");
        dong.add(b3);
        b3.setBounds(10, 170, 80, 20);

        b4 = new JButton("上一关");
        dong.add(b4);
        b4.setBounds(10, 220, 80, 20);

        b5 = new JButton("下一关");
        dong.add(b5);
        b5.setBounds(10, 270, 80, 20);

        b6 = new JButton("最终关");
        dong.add(b6);
        b6.setBounds(10, 320, 80, 20);

        b7 = new JButton("选关");
        dong.add(b7);
        b7.setBounds(10, 370, 80, 20);

        b8 = new JButton("帮助");
        dong.add(b8);
        b8.setBounds(10, 420, 80, 20);

        b9 = new JButton("音乐关");
        dong.add(b9);
        b9.setBounds(10, 470, 80, 20);
        b9.setActionCommand("music开关");// 设置提交内容 要不然用不了

        cmmusic = new JComboBox<String>(musiceName);
        cmmusic.setBounds(10, 520, 80, 20);
        dong.add(cmmusic);

        ImageIcon z = new ImageIcon(Objects.requireNonNull(MoveBoxMain.class.getResource("/static/GameImages/aaaa1" +
                ".jpg")));
        JLabel jl = new JLabel(z);
        jl.setBounds(0, 0, 100, 600);// 添加背景图片
        dong.add(jl);

        ImageIcon x =
                new ImageIcon(Objects.requireNonNull(MoveBoxMain.class.getResource("/static/GameImages/aaaa.jpg")));
        JLabel j2 = new JLabel(x);
        j2.setBounds(0, 0, 600, 600);// 添加背景图片
        centerPanel.add(j2);

        close = new JLabel();
        close.setBounds(670, 0, 30, 30);
        con.add(close);
        sound.mystop();// 关闭音乐
        // 授权
        close.addMouseListener(new B());
        b1.addActionListener(new A());
        b2.addActionListener(new A());
        b3.addActionListener(new A());
        b4.addActionListener(new A());
        b5.addActionListener(new A());
        b6.addActionListener(new A());
        b7.addActionListener(new A());
        b8.addActionListener(new A());
        b9.addActionListener(new A());
        cmmusic.addItemListener(new E());// 在当前类实现
        con.add(dong, BorderLayout.EAST);
        con.add(centerPanel, BorderLayout.CENTER);
        addWindowListener(new C());
        pack();
        // setSize(700,600);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public static void main(String dsf[]) {
        // new 主界面();

    }

    class C extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            // TODO 自动生成的方法存根
            // 保存关卡数
            dispose();
        }
    }

    class B extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == close) {
                dispose();
            }
        }

    }

    class A implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO 自动生成的方法存根
            if (e.getActionCommand().equals("重来")) {
                int level = centerPanel.level;// 获取当前关卡接收
                centerPanel.setlevel(level);// 传值
                centerPanel.requestFocus();
            } else if (e.getActionCommand().equals("悔一步")) {
                centerPanel.huiyibu();
                centerPanel.requestFocus();
            } else if (e.getActionCommand().equals("第一关")) {
                centerPanel.setlevel(1);
                centerPanel.levelJLabel.setText("LEVEL:1");
                centerPanel.requestFocus();
            } else if (e.getActionCommand().equals("上一关")) {
                int level = centerPanel.level;// 获取当前关卡接收
                if (level <= 1) {
                    JOptionPane.showMessageDialog(null, "没有关卡可以往前了！");

                } else {
                    centerPanel.setlevel(level - 1);
                    centerPanel.levelJLabel.setText("LEVEL:" + (level - 1));
                }
                centerPanel.requestFocus();
            } else if (e.getActionCommand().equals("下一关")) {
//				int level = centerPanel.level;// 获取当前关卡接收
                JOptionPane.showMessageDialog(con, "对不起, 无法跳关");
                centerPanel.requestFocus();
            } else if (e.getActionCommand().equals("最终关")) {
                // 会员检测
                centerPanel.requestFocus();
            } else if (e.getActionCommand().equals("选关")) {

                JOptionPane.showMessageDialog(null, "对不起！无法选择关卡");

                centerPanel.requestFocus();
            } else if (e.getActionCommand().equals("帮助")) {
                new MoveBoxHelp();
            } else if (e.getActionCommand().equals("music开关")) {// 提交内容。。。。。
                String title = b9.getText();
                if (title.equals("音乐关")) {
                    sound.mystop();// 关闭音乐
                    b9.setText("音乐开");
                } else {
                    sound.loadSound();// 关闭音乐
                    b9.setText("音乐关");

                }
                centerPanel.requestFocus();
            }

        }

    }

    class E implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            // TODO 自动生成的方法存根
            int index = cmmusic.getSelectedIndex();// 获取索引 0,1,2,3
            sound.setmusic(musicfile[index]);// 与索引对应文件0,1,2,3
            if (sound.isplay()) {
                sound.mystop();// 判断是否再放 在放就关掉
            }
            sound.loadSound();// 首先播放索引0 默认的
            centerPanel.requestFocus();
        }
    }
}