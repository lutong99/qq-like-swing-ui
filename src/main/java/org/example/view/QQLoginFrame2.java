package org.example.view;

import org.example.beans.QQ;
import org.example.controller.QQLoginController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Vector;

/**
 * 这个框继承自我们的{@link CenterFrame}, 我们可以在这里输入qq的用户名和密码后, 进行登陆,
 * 从而来到了{@link QQMainFrame}
 *
 * @author Lutong99
 * @see {@link QQMainFrame}
 */
public class QQLoginFrame2 extends CenterFrame {

    /**
     * 登陆面板的固定宽度
     */
    public static final int LOGIN_WIDTH = 430;
    /**
     * 登陆面板的固定高度
     */
    public static final int LOGIN_HEIGHT = 310;
    /**
     * 没有什么实际意义, 完全是为了避免警告
     */
    private static final long serialVersionUID = 1L;
    /**
     * 我们的背景
     */
    private static BufferedImage backgroundImage;
    private static Vector<String> vlist = new Vector<>();

    static {
        try {
            // 读取我们的背景
            backgroundImage = ImageIO.read(Objects.requireNonNull(QQLoginFrame2.class.getResourceAsStream("/static/LoginImages/login.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 是否可以拖拽
     */
    protected boolean isDragged;
    /**
     * 登陆板 这个是一个内部类的实例, 因为我们只在这一个类中使用
     */
    private LoginPanel loginPanel;
    /**
     * 关闭, 主要是为了美化界面
     */
    private JLabel close;
    /**
     * 最小化, 也是为了美化界面
     */
    private JLabel minimize;
    /**
     * 弹出的框, 和最小化, 关闭在一起的
     */
    private JLabel pop;
    /**
     * 记住密码
     */
    private JCheckBox rememberPassword;
    /**
     * 自动登录
     */
    private JCheckBox autoLogin;
    //	private JTextField userName;
    private JPasswordField passwordField;
    private JComboBox<String> selectQQNumber;
    private JLabel forgetPassword;
    private JLabel register;
    private JLabel loginProfile;
    private JLabel loginButton;
    private JPopupMenu states;
    private JMenuItem onlineState;
    private JMenuItem hideState;
    private JMenuItem busyState;
    private JMenuItem leaveState;
    private JLabel stateSelect;
    private MouseListener showState = new ShowStates();
    /**
     * 右上角的三个按钮的鼠标监听器
     */
    private RightUp rightUp = new RightUp();
    private ActionListener setState = new SetState();
    private FocusListener inputFoucs = new InputFocus();
    private MouseListener mouseClick = new MouseClick();
    private Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
    /**
     * 临时变量
     */
    private Point tempPoint = null;
    /**
     * 位置
     */
    private Point location = null;

    {
        vlist.add("QQ/手机/邮箱");
    }

    /**
     * 无参构造, java bean 的反射必须, 保持优良习惯
     */
    public QQLoginFrame2() {

    }

    @Override
    protected void addEvents() {
        // 为了方便就把所有的适配器封装到了私有内部类里面了
        // 然后加在了我们的addComponent() 方法里面了

        loginPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginCheck();
                }
            }
        });
    }

    @Override
    protected void addComponent() {
        loginPanel = new LoginPanel();
        loginPanel.setPreferredSize(new Dimension(QQLoginFrame2.LOGIN_WIDTH, QQLoginFrame2.LOGIN_HEIGHT));

        close = new JLabel();
        close.setBounds(400, 0, 29, 30);
        loginPanel.add(close);
        close.setCursor(cursor);// 把鼠标弄成手的形状
        close.addMouseListener(rightUp);

        minimize = new JLabel();
        minimize.setBounds(370, 0, 29, 30);
        loginPanel.add(minimize);
        minimize.setCursor(cursor);// 把鼠标弄成手的形状
        minimize.addMouseListener(rightUp);

        pop = new JLabel();
        pop.setBounds(340, 0, 29, 30);
        loginPanel.add(pop);
        pop.setCursor(cursor);// 把鼠标弄成手的形状
        pop.addMouseListener(rightUp);

//		selectQQNumber = new JTextField("QQ/手机/邮箱");
//		userName.setBounds(135, 190, 190, 25);
//		loginPanel.add(userName);
//		userName.setFont(new Font("", Font.PLAIN, 14));
//		userName.setOpaque(false);
//		userName.addFocusListener(inputFoucs );

        selectQQNumber = new JComboBox<String>(vlist);
        selectQQNumber.setBounds(135, 190, 190, 25);
        loginPanel.add(selectQQNumber);
        selectQQNumber.setEditable(true);
        selectQQNumber.setFont(new Font("", Font.PLAIN, 14));
        selectQQNumber.addFocusListener(inputFoucs);

        passwordField = new JPasswordField();
        passwordField.setBounds(135, 225, 190, 25);
        loginPanel.add(passwordField);
        passwordField.setFont(new Font("", Font.PLAIN, 14));
        passwordField.setOpaque(false);

        Color myc = new Color(166, 166, 166);
        rememberPassword = new JCheckBox("记住密码");
        rememberPassword.setBounds(130, 250, 80, 20);
        rememberPassword.setForeground(myc);
        loginPanel.add(rememberPassword);

        autoLogin = new JCheckBox("自动登录");
        autoLogin.setBounds(230, 250, 80, 20);
        autoLogin.setForeground(myc);
        loginPanel.add(autoLogin);

        Color mycolor = new Color(15, 177, 255);// 设置颜色
        register = new JLabel("注册账号");
        register.setBounds(340, 190, 60, 25);
        loginPanel.add(register);
        register.setForeground(mycolor);
        register.setCursor(cursor);// 手
        register.addMouseListener(mouseClick);

        forgetPassword = new JLabel("忘记密码");
        forgetPassword.setBounds(340, 225, 60, 25);
        loginPanel.add(forgetPassword);
        forgetPassword.setForeground(mycolor);
        forgetPassword.setCursor(cursor);// 手

        ImageIcon buttomIcon = new ImageIcon(Objects.requireNonNull(QQLoginFrame2.class.getResource("/static/LoginImages/loginButton.png")));
        loginButton = new JLabel();
        loginButton.setIcon(buttomIcon);
        loginButton.setBounds(130, 270, 232, 40);
        loginPanel.add(loginButton);
        loginButton.setCursor(cursor);
        loginButton.addMouseListener(new LoginClick());

        stateSelect = new JLabel(new ImageIcon(Objects.requireNonNull(QQLoginFrame2.class.getResource("/static" +
                "/LoginImages/s_online.png"))));
        stateSelect.setBounds(97, 235, 15, 15);
        loginPanel.add(stateSelect);
        stateSelect.addMouseListener(showState);

        loginProfile = new JLabel(new ImageIcon(Objects.requireNonNull(QQLoginFrame2.class.getResource("/static" +
                "/LoginImages/loginProfile.png"))));
        loginProfile.setBounds(50, 190, 60, 60);
        loginPanel.add(loginProfile);

        states = new JPopupMenu();
        onlineState = new JMenuItem("我在线上", new ImageIcon(Objects.requireNonNull(QQLoginFrame2.class.getResource(
                "/static/LoginImages/s_online.png"))));
        onlineState.addActionListener(setState);
        leaveState = new JMenuItem("离开", new ImageIcon(Objects.requireNonNull(QQLoginFrame2.class.getResource("/static/LoginImages/s_leave.png"))));
        leaveState.addActionListener(setState);
        busyState = new JMenuItem("忙碌", new ImageIcon(Objects.requireNonNull(QQLoginFrame2.class.getResource("/static/LoginImages/s_busy.png"))));
        busyState.addActionListener(setState);
        hideState = new JMenuItem("隐身", new ImageIcon(Objects.requireNonNull(QQLoginFrame2.class.getResource("/static/LoginImages/s_hide.png"))));
        hideState.addActionListener(setState);
        states.add(onlineState);
        states.addSeparator();
        states.add(leaveState);
        states.addSeparator();
        states.add(busyState);
        states.addSeparator();
        states.add(hideState);

        super.pack();
        this.add(loginPanel);
    }

    @Override
    protected void specilAction() {
        // 将我们的边框去掉, 因为太不好看了, 我们的qq面板是纯蓝色的, 显示边框会非常的不好看
        this.setUndecorated(true);
        // 设置可以拖拽
        setDragable();
    }

    /**
     * 设置我们的面板可以移动,
     */
    private void setDragable() {
        this.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseReleased(java.awt.event.MouseEvent e) {
                isDragged = false;
                QQLoginFrame2.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 默认鼠标的箭头
            }

            public void mousePressed(java.awt.event.MouseEvent e) {
                tempPoint = new Point(e.getX(), e.getY());

                isDragged = true;
                QQLoginFrame2.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
                if (isDragged) {
                    location = new Point(QQLoginFrame2.this.getLocation().x + e.getX() - tempPoint.x,
                            QQLoginFrame2.this.getLocation().y + e.getY() - tempPoint.y);
                    QQLoginFrame2.this.setLocation(location);
                }
            }
        });
    }

    private void loginCheck() {
        if ("".equals(selectQQNumber.getSelectedItem()) || selectQQNumber.getSelectedItem() == null
                || "QQ/手机/邮箱".equals(selectQQNumber.getSelectedItem())) {
            JOptionPane.showMessageDialog(loginPanel, "qq号码为空");
        }
        if ("".equals(String.valueOf(passwordField.getPassword())) || passwordField.getPassword() == null) {
            JOptionPane.showMessageDialog(loginPanel, "请输入密码");
        } else {
            String qqNumber = String.valueOf(selectQQNumber.getSelectedItem());
            String qqPassword = String.valueOf(passwordField.getPassword());
            QQ loginCheck = QQLoginController.getQqLoginController().loginCheck(qqNumber, qqPassword);
            if (loginCheck == null) {
                JOptionPane.showMessageDialog(loginPanel, "用户名或密码错误, 请重新输入");
                passwordField.setText("");
                selectQQNumber.grabFocus();
            } else {
                JOptionPane.showMessageDialog(loginPanel, "登陆成功");
                vlist.add(String.valueOf(selectQQNumber.getSelectedItem()));
            }
        }
    }

    /**
     * 内部类, 主要是为了让我们的图能够完整的在我们的面板上显示
     *
     * @author Lutong99
     */
    private class LoginPanel extends JPanel {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        /**
         * 设置为 null 布局
         */
        public LoginPanel() {
            this.setLayout(null);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.drawImage(backgroundImage, 0, 0, null);
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
            if (e.getSource() == close) {
                close.setIcon(new ImageIcon(Objects.requireNonNull(QQLoginFrame2.class.getResource("/static/LoginImages/loginclose.png"))));
            } else if (e.getSource() == minimize) {
                minimize.setIcon(new ImageIcon(Objects.requireNonNull(QQLoginFrame2.class.getResource("/static/LoginImages/loginMin.png"))));
            } else if (e.getSource() == pop) {
                pop.setIcon(new ImageIcon(Objects.requireNonNull(QQLoginFrame2.class.getResource("/static/LoginImages/loginpop.png"))));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == close) {
                close.setIcon(new ImageIcon("")); // 空就是设置为原来的样子
            } else if (e.getSource() == minimize) {
                minimize.setIcon(new ImageIcon("")); // 空就是设置为原来的样子
            } else if (e.getSource() == pop) {
                pop.setIcon(new ImageIcon("")); // 空就是设置为原来的样子
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == close) {
                System.exit(0); // 点到关闭就给退出就完事了
            } else if (e.getSource() == minimize) {
                QQLoginFrame2.this.setExtendedState(ICONIFIED); // 最小化, 说实话我连源码写的注释翻译完成之后都不认识, 但是这个可以最小化, 还是很厉害的
            } else if (e.getSource() == pop) {
                // TODO 写一点点弹框出来的东西
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
     * 设置我们的登陆状态使其改变我们的状态显示
     *
     * @author Lutong99
     */
    class SetState implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == onlineState) {
                // 点击菜单项改变这个用户的状态
                stateSelect.setIcon(new ImageIcon(Objects.requireNonNull(QQLoginFrame2.class.getResource("/static/LoginImages/s_online.png"))));
            } else if (e.getSource() == hideState) {
                stateSelect.setIcon(new ImageIcon(Objects.requireNonNull(QQLoginFrame2.class.getResource("/static/LoginImages/s_hide.png"))));

            } else if (e.getSource() == busyState) {
                stateSelect.setIcon(new ImageIcon(Objects.requireNonNull(QQLoginFrame2.class.getResource("/static/LoginImages/s_busy.png"))));

            } else if (e.getSource() == leaveState) {
                stateSelect.setIcon(new ImageIcon(Objects.requireNonNull(QQLoginFrame2.class.getResource("/static/LoginImages/s_leave.png"))));
            }
        }
    }

    /**
     * 主要是为了验证登陆使用
     *
     * @author Lutong99
     */
    private class LoginClick extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == loginButton) {
                loginCheck();
            }
        }

        ;
    }

    private class InputFocus extends FocusAdapter {
        @Override
        public void focusGained(FocusEvent e) {
            if (e.getSource() == selectQQNumber) {
                if ("QQ/手机/邮箱".equals(String.valueOf(selectQQNumber.getSelectedItem()))) {
                    selectQQNumber.setSelectedItem("");
                }
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (e.getSource() == selectQQNumber) {
                if ("".equals(selectQQNumber.getSelectedItem())) {
                    selectQQNumber.setSelectedItem("QQ/手机/邮箱");
                }
            }
        }
    }

    /**
     * @author Lutong99
     */
    private class MouseClick extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == register) {
                new QQRegisterFrame();
            }
        }
    }
}
