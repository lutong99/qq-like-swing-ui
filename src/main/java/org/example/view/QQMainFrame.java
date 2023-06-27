package org.example.view;

import org.example.beans.QQ;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;

/**
 * qq 登陆完成的主界面, 包括消息列表, 好友列表, 还有游戏呀, 云呀, 查找好友等等的一些小功能
 *
 * @author Lutong99
 */
public class QQMainFrame extends CenterFrame {

    /**
     * 主面板的高度
     */
    public static final int MAIN_WIDTH = 280;
    /**
     * 主面版的宽度
     */
    public static final int MAIN_HEIGHT = 600;
    /**
     * 只是为了不显示警告, 其实没有什么意义
     */
    private static final long serialVersionUID = 1L;
    /**
     * 背景
     */
    private static BufferedImage mainImage;

    /*
      静态读取图片
     */
    static {
        try {
            mainImage = ImageIO.read(Objects.requireNonNull(QQLoginFrame.class.getResourceAsStream("/static/MainImages/MainBackground2.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 登陆过来的qq
     */
    private QQ loginQq;
    /**
     * 关闭的标签
     */
    private JLabel closeJLabel;
    /**
     * 最小化的标签
     */
    private JLabel minimizeJLabel;
    /**
     * 头像的标签
     */
    private JLabel profileJLabel;
    /**
     * 昵称的标签
     */
    private JLabel nicknameJLabel;
    /**
     * 手型的鼠标
     */
    private Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
    /**
     * 主面板的容器
     */
    private QQMainPanel qqMainPanel;

    /**
     * 右上角的按钮的事件
     */
    private MouseListener rightUp = new RightUp();

    /**
     * 状态设置
     */
    private JPopupMenu states;

    /**
     * 状态选择的地方
     */
    private JLabel stateSelect;

    /**
     * 我的状态设置
     */
    private JLabel myStateJLabel;

    /**
     * 在线状态
     */
    private JMenuItem onlineState;

    /**
     * 离开
     */
    private JMenuItem leaveState;

    /**
     * 忙碌
     */
    private JMenuItem busyState;

    /**
     * 隐身
     */
    private JMenuItem hideState;

    /**
     * 设置状态的ActionListenner
     */
    private ActionListener setState = new SetState();

    /**
     * 设置状态的鼠标监听, 显示状态, 弹出菜单
     */
    private MouseListener showStates = new ShowStates();

    /**
     * 查找好友
     */
    private JLabel findFriendJLabel;

    /**
     * 主题, 但还没有写
     */
    private JLabel themeJLabel;

    /**
     * qq游戏的图标
     */
    private JLabel qqGameJLabel;

    /**
     * qq音乐的图标, 只是启动我们自己的qq音乐
     */
    private JLabel qqMusicJLabel;

    /**
     * 微云的图标, 用来点
     */
    private JLabel qqCloudJLabel;

    /**
     * qq等级显示
     */
    private JLabel qqLeveLabel;

    /**
     * qq个性签名, 还没有写
     */
    private JLabel qqSignatureJLabel;

    /**
     * 朋友列表, 还没有 想好用什么, 显示好友
     */
    private JList<?> friendJList;

    /**
     * 家人
     */
    private JList<?> familyJList;

    /**
     * 同学
     */
    private JList<?> schoolmatesJList;

    /**
     * 黑名单
     */
    private JList<?> hatesJList;

    /**
     * 底部的逐渐
     */
    private JLabel mainBottom;

    /**
     * 鼠标点击事件的一个实例, 为了显示方便
     */
    private MouseListener mouseClick = new MouseClick();

    /**
     * 右下角的弹框
     */
    private ActionListener popupDownMenu = new PopupMenuAction();

    /**
     * QQ logo
     */
    private JLabel logoJLabel;

    /**
     * 背景图片
     */
    private String[] backgroundIamges = {
            "/static/MainImages/MainBackground2.png",
            "/static/MainImages/MainBackground3.jpg",
            "/static/MainImages/MainBackground4.jpg",
            "/static/MainImages/MainBackground5.jpg",
            "/static/MainImages/MainBackground6.jpg",
            "/static/MainImages/MainBackground7.png"
    };
    /**
     * 拖动状态
     */
    private boolean isDragged;
    /**
     * 位置
     */
    private Point location;
    /**
     * 临时位置
     */
    private Point tempPoint;
    /**
     * 展示显示菜单, 自动生成的, 所以在下面
     */
    private MenuItem showItem;
    /**
     * 退出的选项
     */
    private MenuItem exitItem;
    /**
     * 弹出的菜单
     */
    private PopupMenu popup;
    /**
     * 托盘的图标, 都是同上面的自动生成的, 在下面
     */
    private TrayIcon trayIcon;

    /**
     * 登陆成功的时候加载进来
     *
     * @param loginQq
     */
    public QQMainFrame(QQ loginQq) {
        this.loginQq = loginQq;
        init(null, null);
        Image image = new ImageIcon(Objects.requireNonNull(QQMainFrame.class.getResource(loginQq.getPhoto()))).getImage();
        this.setIconImage(image);
        this.setBounds(1400, 200, MAIN_WIDTH, MAIN_HEIGHT); // 让这个面板在右边

        trayIcon = new TrayIcon(image);
        trayIcon.setImageAutoSize(true);// 自适应托盘大小


        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowIconified(WindowEvent e) {
                dispose();
            }
        });

        trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    setExtendedState(NORMAL);
                }
                setVisible(true);
            }
        });
//		String i1 = null;
//		String i2 = null;
//		try {
//			i1 = new String("显示主面板".getBytes("UTF8"));
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
//		i2 = i1;

        // 显示托盘的菜单
        showItem = new MenuItem("显示主界面");
        exitItem = new MenuItem("退出");
        Charset charset = Charset.forName("GBK");
        showItem.setLabel(new String("Show Main Frame".getBytes(charset), charset));
        exitItem.setLabel(new String("Exit".getBytes(charset), charset));
        // 设置字体为"微软雅黑"
//        Font font = new Font("微软雅黑", Font.PLAIN, 12);
        Font font = new Font("SimSun", Font.PLAIN, 12);
        showItem.setFont(font);
        exitItem.setFont(font);
        showItem.addActionListener(popupDownMenu);
        exitItem.addActionListener(popupDownMenu);
        popup = new PopupMenu();
        popup.add(showItem);
        popup.add(exitItem);

        trayIcon.setPopupMenu(popup);
        SystemTray systemTray = SystemTray.getSystemTray();
        try {
            systemTray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void addEvents() {

    }

    @Override
    protected void addComponent() {
        qqMainPanel = new QQMainPanel();
        qqMainPanel.setPreferredSize(new Dimension(MAIN_WIDTH, MAIN_HEIGHT));

        // 关闭
        closeJLabel = new JLabel(new ImageIcon(Objects.requireNonNull(QQLoginFrame.class.getResource("/static" +
                "/MainImages/Mainyclose.png"))));
        closeJLabel.setBounds(250, 0, 29, 28);
        qqMainPanel.add(closeJLabel);
        closeJLabel.setCursor(cursor);
        closeJLabel.addMouseListener(rightUp);
        closeJLabel.setToolTipText("关闭");

        // 最小化
        minimizeJLabel = new JLabel(new ImageIcon(Objects.requireNonNull(QQLoginFrame.class.getResource("/static" +
                "/MainImages/MainyMin.png"))));
        minimizeJLabel.setBounds(222, 0, 29, 28);
        qqMainPanel.add(minimizeJLabel);
        minimizeJLabel.setCursor(cursor);
        minimizeJLabel.addMouseListener(rightUp);
        minimizeJLabel.setToolTipText("最小化");

        // 我的状态
        myStateJLabel = new JLabel(new ImageIcon(Objects.requireNonNull(QQLoginFrame.class.getResource("/static" +
                "/MainImages/s_online.png"))));
        myStateJLabel.setBounds(55, 95, 15, 15);
        qqMainPanel.add(myStateJLabel);

        // 头像
        profileJLabel = new JLabel(new ImageIcon(Objects.requireNonNull(QQ.class.getResource(loginQq.getPhoto()))));
        profileJLabel.setBounds(10, 50, 60, 60);
        qqMainPanel.add(profileJLabel);
        profileJLabel.setCursor(cursor);
        profileJLabel.addMouseListener(mouseClick);

        // 状态选择
        stateSelect = new JLabel(new ImageIcon(Objects.requireNonNull(QQLoginFrame.class.getResource("/static/LoginImages/s_online.png"))));
        stateSelect.setBounds(140, 46, 15, 15);
        qqMainPanel.add(stateSelect);
        stateSelect.addMouseListener(showStates);
        stateSelect.setCursor(cursor);
        stateSelect.setToolTipText("状态调整");

        // 昵称
        nicknameJLabel = new JLabel(loginQq.getNickname());
        nicknameJLabel.setBounds(80, 40, 60, 30);
        nicknameJLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        qqMainPanel.add(nicknameJLabel);
        nicknameJLabel.setToolTipText(loginQq.getNickname());
        nicknameJLabel.setForeground(Color.WHITE);

        // 状态选择
        states = new JPopupMenu();
        onlineState = new JMenuItem("我在线上", new ImageIcon(Objects.requireNonNull(QQLoginFrame.class.getResource(
                "/static/LoginImages/s_online.png"))));
        onlineState.addActionListener(setState);
        leaveState = new JMenuItem("离开", new ImageIcon(Objects.requireNonNull(QQLoginFrame.class.getResource("/static" +
                "/LoginImages/s_leave.png"))));
        leaveState.addActionListener(setState);
        busyState = new JMenuItem("忙碌", new ImageIcon(Objects.requireNonNull(QQLoginFrame.class.getResource("/static" +
                "/LoginImages/s_busy.png"))));
        busyState.addActionListener(setState);
        hideState = new JMenuItem("隐身", new ImageIcon(Objects.requireNonNull(QQLoginFrame.class.getResource("/static" +
                "/LoginImages/s_hide.png"))));
        hideState.addActionListener(setState);
        states.add(onlineState);
        states.addSeparator();
        states.add(leaveState);
        states.addSeparator();
        states.add(busyState);
        states.addSeparator();
        states.add(hideState);

        // 游戏和皮肤 还有下面
        themeJLabel = new JLabel(new ImageIcon(Objects.requireNonNull(QQLoginFrame.class.getResource("/static" +
                "/MainImages/theme.png"))));
        themeJLabel.setBounds(250, 110, 20, 20);
        qqMainPanel.add(themeJLabel);
        themeJLabel.setCursor(cursor);
        themeJLabel.setToolTipText("主题");
        themeJLabel.addMouseListener(mouseClick);

        // 游戏
        qqGameJLabel = new JLabel(new ImageIcon(Objects.requireNonNull(QQLoginFrame.class.getResource("/static" +
                "/MainImages/qqGame.png"))));
        qqGameJLabel.setBounds(215, 105, 28, 28);
        qqMainPanel.add(qqGameJLabel);
        qqGameJLabel.setCursor(cursor);
        qqGameJLabel.setToolTipText("QQ游戏");
        qqGameJLabel.addMouseListener(mouseClick);

        // qq音乐
        qqMusicJLabel = new JLabel(new ImageIcon(Objects.requireNonNull(QQLoginFrame.class.getResource("/static" +
                "/MainImages/qqMusic.png"))));
        qqMusicJLabel.setBounds(180, 110, 20, 20);
        qqMainPanel.add(qqMusicJLabel);
        qqMusicJLabel.setCursor(cursor);
        qqMusicJLabel.setToolTipText("QQ音乐");
        qqMusicJLabel.addMouseListener(mouseClick);

        // 微云
        qqCloudJLabel = new JLabel(new ImageIcon(Objects.requireNonNull(QQLoginFrame.class.getResource("/static" +
                "/MainImages/qqCloud.png"))));
        qqCloudJLabel.setBounds(145, 110, 20, 20);
        qqMainPanel.add(qqCloudJLabel);
        qqCloudJLabel.setToolTipText("微云");
        qqCloudJLabel.setCursor(cursor);
        qqCloudJLabel.addMouseListener(mouseClick);

        // 等级
        qqLeveLabel = new JLabel("LV" + loginQq.getLevel());
        qqLeveLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        qqLeveLabel.setBounds(80, 55, 100, 50);
        qqMainPanel.add(qqLeveLabel);
        qqLeveLabel.setForeground(Color.yellow);// 颜色

        // 个性签名
        qqSignatureJLabel = new JLabel(loginQq.getSignature());
        qqSignatureJLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        qqSignatureJLabel.setBounds(80, 90, 100, 30);
        qqMainPanel.add(qqSignatureJLabel);
        qqSignatureJLabel.setForeground(Color.WHITE);
        qqSignatureJLabel.setCursor(cursor);
        qqSignatureJLabel.addMouseListener(rightUp);

        // 好友列表
        JTabbedPane tab = new JTabbedPane();
        // 新建列表
        friendJList = new JList<>();
        familyJList = new JList<>();
        schoolmatesJList = new JList<>();
        hatesJList = new JList<>();
        // 改变列表信息
        tab.add("  好友   ", friendJList);
        tab.add("  家人    ", familyJList);
        tab.add("  同学    ", schoolmatesJList);
        tab.add("  黑名单   ", hatesJList);
        tab.setForeground(Color.BLUE);
        tab.setBounds(0, 135, 300, 430);
        qqMainPanel.add(tab);

        // 列表
        findFriendJLabel = new JLabel(new ImageIcon(Objects.requireNonNull(QQLoginFrame.class.getResource("/static" +
                "/MainImages/findFriend.png"))));
        findFriendJLabel.setBounds(157, 576, 50, 17);
        qqMainPanel.add(findFriendJLabel);
        findFriendJLabel.setToolTipText("查找好友");
        findFriendJLabel.setCursor(cursor);

        // 主面板的下面
        mainBottom = new JLabel(new ImageIcon(Objects.requireNonNull(QQLoginFrame.class.getResource("/static" +
                "/MainImages/qqBottom.png"))));
        mainBottom.setBounds(0, 565, 280, 35);
        qqMainPanel.add(mainBottom);

        // QQ Logo
        logoJLabel = new JLabel(new ImageIcon(Objects.requireNonNull(QQLoginFrame.class.getResource("/static" +
                "/MainImages/logo.png"))));
        logoJLabel.setBounds(0, 0, MAIN_WIDTH, MAIN_HEIGHT);
        qqMainPanel.add(logoJLabel);

        this.add(qqMainPanel);
    }

    @Override
    protected void specilAction() {
        setDragable();
        this.setUndecorated(true);

        // 不断地重新绘制
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(41);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    qqMainPanel.repaint();
                }
            }
        }).start();
    }

    /**
     * 设置我们的面板可以移动,
     */
    private void setDragable() {
        this.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseReleased(java.awt.event.MouseEvent e) {
                isDragged = false;
                QQMainFrame.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 默认鼠标的箭头
            }

            public void mousePressed(java.awt.event.MouseEvent e) {
                tempPoint = new Point(e.getX(), e.getY());

                isDragged = true;
                QQMainFrame.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {

            public void mouseDragged(java.awt.event.MouseEvent e) {
                if (isDragged) {
                    location = new Point(QQMainFrame.this.getLocation().x + e.getX() - tempPoint.x,
                            QQMainFrame.this.getLocation().y + e.getY() - tempPoint.y);
                    QQMainFrame.this.setLocation(location);
                }
            }
        });
    }

    /**
     * 主界面的容器
     *
     * @author Lutong99
     */
    private class QQMainPanel extends JPanel {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        /**
         * 空参构造, 设置布局
         */
        public QQMainPanel() {
            this.setLayout(null);
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(mainImage, 0, 0, null);
        }

    }

    /**
     * 如果对于每一个都要写一个 adapter 的话, 代码不太美观, 所以简单的封装一下下
     *
     * @author Lutong99
     */
    private class RightUp extends MouseAdapter {

        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == closeJLabel) {
                closeJLabel.setIcon(new ImageIcon(Objects.requireNonNull(QQLoginFrame.class.getResource("/static" +
                        "/MainImages/mainClose.png"))));
            } else if (e.getSource() == minimizeJLabel) {
                minimizeJLabel.setIcon(new ImageIcon(Objects.requireNonNull(QQLoginFrame.class.getResource("/static" +
                        "/MainImages/MainMin.png"))));
            } else {

            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == closeJLabel) {
                closeJLabel.setIcon(new ImageIcon(Objects.requireNonNull(QQLoginFrame.class.getResource("/static" +
                        "/MainImages/Mainyclose.png")))); // 设置为原来的样子
            } else if (e.getSource() == minimizeJLabel) {
                minimizeJLabel.setIcon(new ImageIcon(Objects.requireNonNull(QQLoginFrame.class.getResource("/static" +
                        "/MainImages/MainyMin.png")))); // 设置为原来的样子
            } else {

            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == closeJLabel) {
                Object[] options = {"确认", "最小化"};
                int showOptionDialog = JOptionPane.showOptionDialog(qqMainPanel, "确认退出QQ吗?", null,
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (showOptionDialog == 0) {
                    System.exit(0); // 点击关闭就是关闭
                } else if (showOptionDialog == 1) {
                    QQMainFrame.this.setExtendedState(ICONIFIED);
                }

            } else if (e.getSource() == minimizeJLabel) {
                QQMainFrame.this.setExtendedState(ICONIFIED); // 最小化, 说实话我连源码写的注释翻译完成之后都不认识, 但是这个可以最小化, 还是很厉害的
                minimizeJLabel.setIcon(new ImageIcon(Objects.requireNonNull(QQLoginFrame.class.getResource("/static" +
                        "/MainImages/MainyMin.png")))); // 设置为原来的样子
            } else {
                // TODO 写一点点弹框出来的东西
            }
        }
    }

    /**
     * 设置我们的登陆状态使其改变我们的状态显示
     *
     * @author Lutong99
     */
    class SetState implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == onlineState) {
                // 点击菜单项改变这个用户的状态
                stateSelect.setIcon(new ImageIcon("/static/LoginImages/s_online.png"));
                myStateJLabel.setIcon(new ImageIcon("/static/LoginImages/s_online.png"));

            } else if (e.getSource() == hideState) {
                stateSelect.setIcon(new ImageIcon("/static/LoginImages/s_hide.png"));
                myStateJLabel.setIcon(new ImageIcon("/static/LoginImages/s_hide.png"));

            } else if (e.getSource() == busyState) {
                stateSelect.setIcon(new ImageIcon("/static/LoginImages/s_busy.png"));
                myStateJLabel.setIcon(new ImageIcon("/static/LoginImages/s_busy.png"));

            } else if (e.getSource() == leaveState) {
                stateSelect.setIcon(new ImageIcon("/static/LoginImages/s_leave.png"));
                myStateJLabel.setIcon(new ImageIcon("/static/LoginImages/s_leave.png"));
            } else {

            }
        }
    }

    /**
     * 为选择状态设置的内部类
     *
     * @author Lutong99
     */
    private class ShowStates extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == stateSelect) {
                // 在点击的位置显示我们的状态选择框
                states.show(stateSelect, e.getX(), e.getY());
            }
        }
    }

    /**
     * 所有的鼠标点击事件
     *
     * @author Lutong99
     */
    private class MouseClick extends MouseAdapter {

//		private Random random;
        /**
         * 索引, 主要用来更新背景
         */
        private int i = 0;

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == qqGameJLabel) {
                new QQGameSelectorFrame();
            } else if (e.getSource() == qqMusicJLabel) {
                String cmd = "F:\\Program Files (x86)\\Tencent\\QQMusic\\QQMusic.exe";
                try {
                    Runtime.getRuntime().exec(cmd);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else if (e.getSource() == qqCloudJLabel) {
                new QQCloudFrame(loginQq);
            } else if (e.getSource() == themeJLabel) {
                try {
//					random = new Random();
                    mainImage =
                            ImageIO.read(Objects.requireNonNull(QQLoginFrame.class.getResourceAsStream(backgroundIamges[i])));
                    i++;
                    if (i > 5) {
                        i = 0;
                    }

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else if (e.getSource() == profileJLabel) {
                new QQUpdateInfoFrame(loginQq);

            }
        }
    }

    /**
     * 推盘区的菜单事件
     *
     * @author Lutong99
     */
    private class PopupMenuAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == showItem) {
                QQMainFrame.this.setExtendedState(NORMAL);
            } else if (e.getSource() == exitItem) {
                System.exit(0); // 点击推出之后就退出
            }
        }
    }
}
