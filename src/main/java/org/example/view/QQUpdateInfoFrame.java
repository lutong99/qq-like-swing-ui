package org.example.view;

import org.example.beans.QQ;
import org.example.controller.QQMainController;
import org.example.utils.MD5Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 和注册使用的是一个界面, 只是做了一部分修改
 *
 * @author Lutong99
 */
public class QQUpdateInfoFrame extends CenterFrame {

    /**
     * 仅仅是为了不显示警告
     */
    private static final long serialVersionUID = 1L;

    /**
     * 需要修改的qq
     */
    private QQ loginQq;

    /**
     * 背景
     */
    private static BufferedImage background = null;

    /**
     * 注册界面的容器
     */
    private QQRegisterPanel qqUpdateInfoPanel;

    /**
     * 固定宽度
     */
    public static final int REGISTER_WIDTH = 400;

    /**
     * 固定高度
     */
    public static final int REGISTER_HEIGHT = 450;

    /**
     * 关闭标签
     */
    private JLabel close;

    /**
     * 最小化的标签
     */
    private JLabel minimize;

    /**
     * 注册的标题文字
     */
    private JLabel registerTitle;

    /**
     * 昵称
     */
    private JLabel nickname;

    /**
     * 昵称的文本框
     */
    private JTextField nicknameText;

    /**
     * 密码:
     */
    private JLabel passwordLabel;

    /**
     * 密码框
     */
    private JPasswordField password;

    /**
     * 重复一遍密码
     */
    private JLabel passwordRepeatLabel;

    /**
     * 重复的密码框
     */
    private JPasswordField passwordRepeat;

    /**
     * 选择头像的文字
     */
    private JLabel selectProfiles;

    /**
     * 选择头像的选择框
     */
    private JComboBox<ImageIcon> profilesBox;

    /**
     * 选择性别
     */
    private JLabel selectGender;

    /**
     * 性别的选择框
     */
    private JComboBox<String> genderBox;

    /**
     * 民族选择
     */
    private JLabel nationJLabel;

    /**
     * 民族选择Box
     */
    private JComboBox<String> nationsBox;

    /**
     * 生日标签
     */
    private JLabel birthdayJLabel;

    /**
     * 生日中年的标签
     */
    private JLabel yearLabel;

    /**
     * 年的盒子
     */
    private JComboBox<String> yearsBox;

    /**
     * 月
     */
    private JLabel monthLabel;

    /**
     * 年的盒子, 从1970开始, 2019 结束
     */
    private JComboBox<String> monthsBox;

    /**
     * 日
     */
    private JLabel dayLabel;

    /**
     * 日的选择, 1-31 , 有一个bug, 按说应该把这个跟月份进行关联, 但是为了方便, 这边没有写
     */
    private JComboBox<String> daysBox;

    /**
     * 地址
     */
    private JLabel addressJLabel;

    /**
     * 国家
     */
    private JLabel countryJLabel;

    /**
     * 国家框
     */
    private JTextField countryField;

    /**
     * 省
     */
    private JLabel provinceJLabel;

    /**
     * 省的输入框
     */
    private JTextField provinceField;

    /**
     * 城市
     */
    private JLabel cityJLabel;

    /**
     * 城市的输入框
     */
    private JTextField cityField;

    /**
     * 电子邮件
     */
    private JLabel emailLabel;

    /**
     * 输入右键的框
     */
    private JTextField emailField;

    /**
     * 提交按钮
     */
    private JButton commitButton;

    /**
     * 重置按钮
     */
    private JButton resetButton;

    /**
     * 手型的鼠标
     */
    private Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

    /**
     * 读取图片
     */
    static {
        try {
            background = ImageIO.read(new File("static/RegisterImages/register_background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // private String[] dateKindSelect = { "公历", "农历" };// 为下拉框添加内容
    // private String[] months = { "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月",
    // "九月", "十月", "十一月", "十二月" };// 为下拉框添加内容
    // private String[] constellation = { "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座",
    // "天秤座", "天蝎座", "射手座", "水瓶座", "魔羯座", "双鱼座" };

    /**
     * 空参构造方法
     */
    public QQUpdateInfoFrame(QQ loginQq) {
        this.loginQq = loginQq;
        init(null, null, REGISTER_WIDTH, REGISTER_HEIGHT);
    }

    @Override
    protected void addEvents() {

    }

    /**
     * 右上角的监听
     */
    private MouseListener rightUp = new RightUp();

    /**
     * 字体
     */
    private Font font = new Font("宋体", Font.ITALIC, 16);

    /**
     * 颜色
     */
    private Color color = new Color(1, 126, 177);

    /**
     * 输入框中的字体
     */
    private Font enterFont = new Font("宋体", Font.PLAIN, 15);

    /**
     * 提交的事件监听
     */
    private MouseListener commitMouse = new CommitMouse();

    /**
     * 重置按钮的事件监听
     */
    private MouseListener resetMouse = new ResetMouse();

    /**
     * 焦点监听, 主要是为了对有默认值的设置
     */
    private FocusListener foucsListener = new FocusEvent();

    @Override
    protected void addComponent() {

        /**
         * 性别数据
         */
        String[] genders = {loginQq.getGender(), "未选择", "男", "女"};

        /**
         * 年的数据
         */
        String[] years = {loginQq.getBirthday().substring(0, 4), "1970", "1971", "1972", "1973", "1974", "1975",
                "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988",
                "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001",
                "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014",
                "2015", "2016", "2017", "2018", "2019"};

        /**
         * 月的数据
         */
        String[] months = {loginQq.getBirthday().substring(5, 7), "01", "02", "03", "04", "05", "06", "07", "08", "09",
                "10", "11", "12"};

        /**
         * 日的数据, 这里有一个BUG
         */
        String[] days = {loginQq.getBirthday().substring(8, 10), "01", "02", "03", "04", "05", "06", "07", "08", "09",
                "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26",
                "27", "28", "29", "30", "31"};
        // TODO 在这里我应该写一下days跟月份有关的方法

        /**
         * 几个头像, 还可以再添加
         */
        ImageIcon[] profiles = {new ImageIcon(loginQq.getPhoto()),
                new ImageIcon("static/RegisterImages/Profiles/0_online.png"),
                new ImageIcon("static/RegisterImages/Profiles/1_online.png"),
                new ImageIcon("static/RegisterImages/Profiles/2_online.png"),
                new ImageIcon("static/RegisterImages/Profiles/3_online.png"),
                new ImageIcon("static/RegisterImages/Profiles/4_online.png"),
                new ImageIcon("static/RegisterImages/Profiles/5_online.png"),
                new ImageIcon("static/RegisterImages/Profiles/6_online.png"),
                new ImageIcon("static/RegisterImages/Profiles/7_online.png"),
                new ImageIcon("static/RegisterImages/Profiles/8_online.png"),
                new ImageIcon("static/RegisterImages/Profiles/9_online.png"),
                new ImageIcon("static/RegisterImages/Profiles/10_online.png")};

        /**
         * 民族的数据
         */
        String[] nations = {loginQq.getNation(), "汉族 ", "满族 ", "蒙古族 ", "回族 ", "藏族 ", "维吾尔族 ", "苗族 ", "彝族 ", "壮族 ",
                "布依族 ", "侗族 ", "瑶族 ", "白族 ", "土家族 ", "哈尼族 ", "哈萨克族 ", "傣族 ", "黎族 ", "傈僳族 ", "佤族 ", "畲族 ", "高山族 ",
                "拉祜族 ", "水族 ", "东乡族 ", "纳西族 ", "景颇族 ", "柯尔克孜族 ", "土族 ", "达斡尔族 ", "仫佬族 ", "羌族 ", "布朗族 ", "撒拉族 ", "毛南族 ",
                "仡佬族 ", "锡伯族 ", "阿昌族 ", "普米族 ", "朝鲜族 ", "塔吉克族 ", "怒族 ", "乌孜别克族 ", "俄罗斯族 ", "鄂温克族 ", "德昂族 ", "保安族 ",
                "裕固族 ", "京族 ", "塔塔尔族 ", "独龙族 ", "鄂伦春族 ", "赫哲族 ", "门巴族 ", "珞巴族 ", "基诺族"};

        qqUpdateInfoPanel = new QQRegisterPanel();
        qqUpdateInfoPanel.setPreferredSize(new Dimension(REGISTER_WIDTH, REGISTER_HEIGHT));

        // 关闭
        close = new JLabel(new ImageIcon("static/RegisterImages/close.png"));
        close.setBounds(375, 0, 25, 20);
        qqUpdateInfoPanel.add(close);
        close.setCursor(cursor);// 把鼠标弄成手的形状
        close.addMouseListener(rightUp);
        close.setToolTipText("关闭");

        // 最小化
        minimize = new JLabel(new ImageIcon("static/RegisterImages/minimize.png"));
        minimize.setBounds(350, 0, 25, 20);
        qqUpdateInfoPanel.add(minimize);
        minimize.setCursor(cursor);// 把鼠标弄成手的形状
        minimize.addMouseListener(rightUp);
        minimize.setToolTipText("最小化");

        // 注册
        registerTitle = new JLabel("修改个人信息");
        registerTitle.setBounds(130, 20, 200, 40);
        registerTitle.setFont(new Font("楷体", Font.BOLD, 24));
        qqUpdateInfoPanel.add(registerTitle);

        // 选择
        selectProfiles = new JLabel("请选择头像");
        selectProfiles.setBounds(80, 75, 80, 30);
        qqUpdateInfoPanel.add(selectProfiles);
        selectProfiles.setFont(font);
        selectProfiles.setForeground(color);

        // 头像
        profilesBox = new JComboBox<>(profiles);
        qqUpdateInfoPanel.add(profilesBox);
        profilesBox.setBounds(170, 60, 80, 62);
        profilesBox.setCursor(cursor);

        // 昵称
        nickname = new JLabel("    昵称: ");
        nickname.setBounds(40, 130, 100, 30);
        qqUpdateInfoPanel.add(nickname);
        nickname.setFont(font);
        nickname.setForeground(color);

        // 输入昵称
        nicknameText = new JTextField(loginQq.getNickname());
        nicknameText.setBounds(120, 132, 200, 25);
        qqUpdateInfoPanel.add(nicknameText);
        nicknameText.setOpaque(false);
        nicknameText.setFont(enterFont);
        nicknameText.addFocusListener(foucsListener);

        // 密码
        passwordLabel = new JLabel("    密码: ");
        passwordLabel.setBounds(40, 165, 100, 30);
        qqUpdateInfoPanel.add(passwordLabel);
        passwordLabel.setFont(font);
        passwordLabel.setForeground(color);

        // 密码框
        password = new JPasswordField();
        password.setBounds(120, 167, 200, 25);
        qqUpdateInfoPanel.add(password);
        password.setOpaque(false);
        password.setFont(enterFont);

        // 重复密码
        passwordRepeatLabel = new JLabel("确认密码: ");
        passwordRepeatLabel.setBounds(40, 200, 100, 30);
        qqUpdateInfoPanel.add(passwordRepeatLabel);
        passwordRepeatLabel.setFont(font);
        passwordRepeatLabel.setForeground(color);

        // 重复密码的密码框
        passwordRepeat = new JPasswordField();
        passwordRepeat.setBounds(120, 202, 200, 25);
        qqUpdateInfoPanel.add(passwordRepeat);
        passwordRepeat.setOpaque(false);
        passwordRepeat.setFont(enterFont);

        // 性别选择
        selectGender = new JLabel("    性别: ");
        selectGender.setBounds(40, 230, 80, 30);
        qqUpdateInfoPanel.add(selectGender);
        selectGender.setFont(font);
        selectGender.setForeground(color);

        // 性别盒子
        genderBox = new JComboBox<String>(genders);
        genderBox.setBounds(120, 232, 100, 30);
        qqUpdateInfoPanel.add(genderBox);
        genderBox.setFont(enterFont);
        genderBox.setCursor(cursor);

        // 民族选择文字
        nationJLabel = new JLabel("民族: ");
        nationJLabel.setBounds(230, 230, 80, 30);
        qqUpdateInfoPanel.add(nationJLabel);
        nationJLabel.setFont(font);
        nationJLabel.setForeground(color);

        // 民族盒子
        nationsBox = new JComboBox<String>(nations);
        nationsBox.setBounds(270, 232, 100, 30);
        qqUpdateInfoPanel.add(nationsBox);
        nationsBox.setFont(enterFont);
        nationsBox.setCursor(cursor);

        // 生日
        birthdayJLabel = new JLabel("    生日: ");
        birthdayJLabel.setBounds(40, 270, 80, 30);
        qqUpdateInfoPanel.add(birthdayJLabel);
        birthdayJLabel.setFont(font);
        birthdayJLabel.setForeground(color);

        // 年
        yearLabel = new JLabel("年: ");
        yearLabel.setBounds(125, 270, 40, 30);
        qqUpdateInfoPanel.add(yearLabel);
        yearLabel.setFont(font);
        yearLabel.setForeground(color);

        // 年的盒子
        yearsBox = new JComboBox<String>(years);
        yearsBox.setBounds(150, 268, 60, 30);
        qqUpdateInfoPanel.add(yearsBox);
        yearsBox.setFont(enterFont);
        yearsBox.setCursor(cursor);

        // 月
        monthLabel = new JLabel("月: ");
        monthLabel.setBounds(215, 270, 40, 30);
        qqUpdateInfoPanel.add(monthLabel);
        monthLabel.setFont(font);
        monthLabel.setForeground(color);

        // 月的盒子
        monthsBox = new JComboBox<String>(months);
        monthsBox.setBounds(240, 268, 60, 30);
        qqUpdateInfoPanel.add(monthsBox);
        monthsBox.setFont(enterFont);
        monthsBox.setOpaque(false);
        monthsBox.setCursor(cursor);

        // 日
        dayLabel = new JLabel("日: ");
        dayLabel.setBounds(305, 270, 40, 30);
        qqUpdateInfoPanel.add(dayLabel);
        dayLabel.setFont(font);
        dayLabel.setForeground(color);

        // 日的盒子
        daysBox = new JComboBox<String>(days);
        daysBox.setBounds(332, 268, 60, 30);
        qqUpdateInfoPanel.add(daysBox);
        daysBox.setFont(enterFont);
        daysBox.setOpaque(false);
        daysBox.setCursor(cursor);

        // 地址的标签
        addressJLabel = new JLabel("    地址: ");
        addressJLabel.setBounds(40, 303, 80, 30);
        qqUpdateInfoPanel.add(addressJLabel);
        addressJLabel.setFont(font);
        addressJLabel.setForeground(color);

        // 国家标签
        countryJLabel = new JLabel("国: ");
        countryJLabel.setBounds(120, 303, 80, 30);
        qqUpdateInfoPanel.add(countryJLabel);
        countryJLabel.setFont(font);
        countryJLabel.setForeground(color);

        // 国家的输入框
        countryField = new JTextField(loginQq.getCountry());
        countryField.setBounds(150, 303, 60, 30);
        qqUpdateInfoPanel.add(countryField);
        countryField.setFont(enterFont);
        countryField.setOpaque(false);
        countryField.addFocusListener(foucsListener);

        // 省
        provinceJLabel = new JLabel("省: ");
        provinceJLabel.setBounds(215, 303, 40, 30);
        qqUpdateInfoPanel.add(provinceJLabel);
        provinceJLabel.setFont(font);
        provinceJLabel.setForeground(color);

        // 省的输入框
        provinceField = new JTextField(loginQq.getProvince());
        provinceField.setBounds(240, 303, 60, 30);
        qqUpdateInfoPanel.add(provinceField);
        provinceField.setFont(enterFont);
        provinceField.setOpaque(false);
        provinceField.addFocusListener(foucsListener);

        // 市
        cityJLabel = new JLabel("市: ");
        cityJLabel.setBounds(305, 303, 40, 30);
        qqUpdateInfoPanel.add(cityJLabel);
        cityJLabel.setFont(font);
        cityJLabel.setForeground(color);

        // 城市的选择框
        cityField = new JTextField(loginQq.getCity());
        cityField.setBounds(332, 303, 60, 30);
        qqUpdateInfoPanel.add(cityField);
        cityField.setOpaque(false);
        cityField.setFont(enterFont);
        cityField.addFocusListener(foucsListener);

        // 电子邮件
        emailLabel = new JLabel("电子邮件: ");
        emailLabel.setBounds(40, 338, 100, 30);
        qqUpdateInfoPanel.add(emailLabel);
        emailLabel.setFont(font);
        emailLabel.setForeground(color);

        // 电子邮件的输入
        emailField = new JTextField();
        emailField.setText(loginQq.getEmail());
        emailField.setBounds(120, 340, 200, 25);
        qqUpdateInfoPanel.add(emailField);
        emailField.setOpaque(false);
        emailField.setFont(enterFont);
        emailField.addFocusListener(foucsListener);

        // 提交按钮
        commitButton = new JButton("确认");
        commitButton.setBounds(120, 380, 70, 30);
        qqUpdateInfoPanel.add(commitButton);
        commitButton.addMouseListener(commitMouse);

        // 重置按钮
        resetButton = new JButton("重置");
        resetButton.setBounds(240, 380, 70, 30);
        qqUpdateInfoPanel.add(resetButton);
        resetButton.addMouseListener(resetMouse);

        qqUpdateInfoPanel.setBounds(0, 0, QQUpdateInfoFrame.REGISTER_WIDTH, QQUpdateInfoFrame.REGISTER_HEIGHT);
        this.add(qqUpdateInfoPanel);
    }

    @Override
    protected void specilAction() {
        this.setUndecorated(true);// 无边框
        setDragable();
    }

    /**
     * 是否可以拖拽
     */
    protected boolean isDragged;

    /**
     * 临时变量
     */
    private Point tempPoint = null;

    /**
     * 位置
     */
    private Point location = null;

    /**
     * 设置我们的面板可以移动,
     */
    private void setDragable() {
        this.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseReleased(java.awt.event.MouseEvent e) {
                isDragged = false;
                QQUpdateInfoFrame.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 默认鼠标的箭头
            }

            public void mousePressed(java.awt.event.MouseEvent e) {
                tempPoint = new Point(e.getX(), e.getY());

                isDragged = true;
                QQUpdateInfoFrame.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
                if (isDragged) {
                    location = new Point(QQUpdateInfoFrame.this.getLocation().x + e.getX() - tempPoint.x,
                            QQUpdateInfoFrame.this.getLocation().y + e.getY() - tempPoint.y);
                    QQUpdateInfoFrame.this.setLocation(location);
                }
            }
        });
    }

    /**
     * 注册面板的容器
     *
     * @author Lutong99
     */
    private class QQRegisterPanel extends JPanel {
        /**
         * 仅仅是为了不显示警告, 但是我也不知道为什么有时候需要, 有时候 不需要
         */
        private static final long serialVersionUID = 1L;

        /**
         * 无参构造方法
         */
        public QQRegisterPanel() {
            this.setLayout(null);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(background, 0, 0, null);

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
                close.setIcon(new ImageIcon("static/LoginImages/loginclose.png"));
            } else if (e.getSource() == minimize) {
                minimize.setIcon(new ImageIcon("static/LoginImages/loginMin.png"));
            } else {
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == close) {
                close.setIcon(new ImageIcon("static/RegisterImages/close.png")); // 设置为原来的样子
            } else if (e.getSource() == minimize) {
                minimize.setIcon(new ImageIcon("static/RegisterImages/minimize.png")); // 设置为原来的样子
            } else {
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == close) {
                QQUpdateInfoFrame.this.dispose();
            } else if (e.getSource() == minimize) {
                QQUpdateInfoFrame.this.setExtendedState(ICONIFIED); // 最小化, 说实话我连源码写的注释翻译完成之后都不认识,
            } else {

            }
        }
    }

    /**
     * 提交的时候处理的事件, 比如验证密码是否输入, 密码是否重复输入, 电子邮件格式是否正确
     *
     * @author Lutong99
     */
    private class CommitMouse extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            // 验证密码是否相同
            String password1 = String.valueOf(password.getPassword());
            String password2 = String.valueOf(passwordRepeat.getPassword());

            if ("".equals(password1) || "".equals(password2)) {
                JOptionPane.showMessageDialog(qqUpdateInfoPanel, "密码不完整");
            } else if (!password1.equals(password2)) {
                JOptionPane.showMessageDialog(qqUpdateInfoPanel, "两次密码不一致");
            } else {
                String password = MD5Utils.encrypByMD5(password1);

                // 验证邮箱格式是否正确
                // mailRegex是整体邮箱正则表达式，mailName是@前面的名称部分，mailDomain是后面的域名部分
                String mailName = "^[0-9a-z]+\\w*"; // ^表明一行以什么开头；^[0-9a-z]表明要以数字或小写字母开头；\\w*表明匹配任意个大写小写字母或数字或下划线
                String mailDomain = "([0-9a-z]+\\.)+[0-9a-z]+$"; // ***.***.***格式的域名，其中*为小写字母或数字;第一个括号代表有至少一个***.匹配单元，而[0-9a-z]$表明以小写字母或数字结尾
                String mailRegex = mailName + "@" + mailDomain; // 邮箱正则表达式 ^[0-9a-z]+\w*@([0-9a-z]+\.)+[0-9a-z]+$
                Pattern pattern = Pattern.compile(mailRegex);
                Matcher matcher = pattern.matcher(emailField.getText());
                String email = null;
                if (matcher.matches() || "".equals(emailField.getText())) {
                    email = emailField.getText();

                    String photo = String.valueOf(profilesBox.getSelectedItem());
                    String nickname = nicknameText.getText();
                    String gender = String.valueOf(genderBox.getSelectedItem());
                    String birthday = String.valueOf(String.valueOf(yearsBox.getSelectedItem()) + "-"
                            + String.valueOf(monthsBox.getSelectedItem()) + "-"
                            + String.valueOf(daysBox.getSelectedItem()));
                    String country = countryField.getText();
                    String province = provinceField.getText();
                    String city = cityField.getText();
                    String nation = String.valueOf(nationsBox.getSelectedItem());

                    boolean updateInfo = QQMainController.getInstance().updateInfo(loginQq, nickname, password, photo,
                            gender, birthday, country, province, city, email, nation);
                    if (updateInfo) {
                        JOptionPane.showMessageDialog(qqUpdateInfoPanel, "修改信息成功, 请重启QQ后生效");
                        QQUpdateInfoFrame.this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(qqUpdateInfoPanel, "修改失败, 系统错误");
                        System.exit(0);
                    }
                } else {
                    JOptionPane.showMessageDialog(qqUpdateInfoPanel, "邮箱格式不正确");
                }
            }
        }
    }

    /**
     * 重置我们的注册界面, 实现原理就是, 将当前页面释放资源, 然后在重新生成一个
     *
     * @author Lutong99
     */
    private class ResetMouse extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            resetPanel();
        }

        // 重置我们的所有东西
        private void resetPanel() {

            // TODO 这里有一个BUG, 需要在数组内才可以使用
            nicknameText.setText(loginQq.getNickname());
            password.setText("");
            passwordRepeat.setText("");
            genderBox.setSelectedItem(loginQq.getGender());

            nationsBox.setSelectedItem(loginQq.getNation());
            yearsBox.setSelectedItem(loginQq.getBirthday().substring(0, 4));
            monthsBox.setSelectedItem(loginQq.getBirthday().substring(5, 7));
            daysBox.setSelectedItem(loginQq.getBirthday().substring(8, 10));

            countryField.setText(loginQq.getCountry());
            provinceField.setText(loginQq.getProvince());
            cityField.setText(loginQq.getCity());
            emailField.setText(loginQq.getEmail());
        }
    }

    /**
     * 主要是提示和选择置空
     *
     * @author Lutong99
     */
    private class FocusEvent extends FocusAdapter {
        @Override
        public void focusGained(java.awt.event.FocusEvent e) {
            if (e.getSource() == nicknameText) {
                if (loginQq.getNickname().equals(nicknameText.getText())) {
                    nicknameText.setText("");
                }
            } else if (e.getSource() == countryField) {
                if (loginQq.getCountry().equals(countryField.getText())) {
                    countryField.setText("");
                }
            } else if (e.getSource() == provinceField) {
                if (loginQq.getProvince().equals(provinceField.getText())) {
                    provinceField.setText("");
                }
            } else if (e.getSource() == cityField) {
                if (loginQq.getCity().equals(cityField.getText())) {
                    cityField.setText("");
                }
            } else if (e.getSource() == emailField) {
                if (loginQq.getEmail().equals(emailField.getText())) {
                    emailField.setText("");
                }
            } else {

            }
        }

        @Override
        public void focusLost(java.awt.event.FocusEvent e) {
            if (e.getSource() == nicknameText) {
                if ("".equals(nicknameText.getText())) {
                    nicknameText.setText(loginQq.getNickname());
                }
            } else if (e.getSource() == countryField) {
                if ("".equals(countryField.getText())) {
                    countryField.setText(loginQq.getCountry());
                }
            } else if (e.getSource() == provinceField) {
                if ("".equals(provinceField.getText())) {
                    provinceField.setText(loginQq.getProvince());
                }
            } else if (e.getSource() == cityField) {
                if ("".equals(cityField.getText())) {
                    cityField.setText(loginQq.getCity());
                }
            } else if (e.getSource() == emailField) {
                if ("".equals(emailField.getText())) {
                    emailField.setText(loginQq.getEmail());
                }
            } else {

            }
        }
    }
}
