package com.e59.view;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import com.e59.beans.QQ;
import com.e59.controller.QQLoginController;

/**
 * 
 * 这个框继承自我们的{@link CenterFrame}, 我们可以在这里输入qq的用户名和密码后, 进行登陆,
 * 从而来到了{@link QQMainFrame}
 * 
 * @see {@link QQMainFrame}
 * @author  Lutong99
 *
 */
public class QQLoginFrame extends CenterFrame {

	/**
	 * 没有什么实际意义, 完全是为了避免警告
	 */
	private static final long serialVersionUID = 1L;

	/** 登陆板 这个是一个内部类的实例, 因为我们只在这一个类中使用 */
	private LoginPanel loginPanel;

	/** 关闭, 主要是为了美化界面 */
	private JLabel close;

	/** 最小化, 也是为了美化界面 */
	private JLabel minimize;

	/** 弹出的框, 和最小化, 关闭在一起的 */
	private JLabel pop;

	/** 我们的背景 */
	private static BufferedImage backgroundImage;

	/** 登陆面板的固定宽度 */
	public static final int LOGIN_WIDTH = 430;

	/** 登陆面板的固定高度 */
	public static final int LOGIN_HEIGHT = 310;

	/** 记住密码 */
	private JCheckBox rememberPassword;

	/** 自动登录 */
	private JCheckBox autoLogin;

	/**
	 * 输入qq号码的文本框
	 */
	private JTextField userName;

	/**
	 * 密码框
	 */
	private JPasswordField passwordField;

//	private JComboBox<String> selectQQNumber;

	/**
	 * 忘记密码的标签
	 */
	private JLabel forgetPassword;

	/**
	 * 注册账号的标签
	 */
	private JLabel register;

	/**
	 * 登陆的头像
	 */
	private JLabel loginProfile;

	/**
	 * 登陆的按钮
	 */
	private JLabel loginButton;

	/**
	 * 登陆状态的弹出菜单
	 */
	private JPopupMenu states;

	/**
	 * 在线状态
	 */
	private JMenuItem onlineState;

	/**
	 * 隐身状态
	 */
	private JMenuItem hideState;

	/**
	 * 忙碌状态
	 */
	private JMenuItem busyState;

	/**
	 * 离开状态
	 */
	private JMenuItem leaveState;

	/**
	 * 状态选择的标签
	 */
	private JLabel stateSelect;

	/**
	 * 显示状态的事件监听
	 */
	private MouseListener showState = new ShowStates();

	/**
	 * 获取系统图标
	 */
	private SystemTray systemTray = SystemTray.getSystemTray();

	/**
	 * 托盘图标的图像
	 */
	private ImageIcon imageIcon;

	/**
	 * 托盘图标
	 */
	private TrayIcon trayIcon;

	/**
	 * 在托盘区的右键菜单
	 */
	private PopupMenu popupdownMenu;

	/**
	 * 托盘右键菜单的显示
	 */
	private MenuItem showItem;

	/**
	 * 托盘右键菜单的退出选项
	 */
	private MenuItem exitItem;

	/**
	 * 对于我们的托盘菜单的事件监听
	 */
	private ActionListener popupMenuAction = new PopupMenuAction();

	/**
	 * 读取背景的图片
	 */
	static {
		try {
			// 读取我们的背景
			backgroundImage = ImageIO.read(new File("resources/LoginImages/login.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 无参构造, java bean 的反射必须, 保持优良习惯
	 * 
	 * 并且在我们的托盘设置一些东西
	 */
	public QQLoginFrame() {
		this.setIconImage(new ImageIcon("resources/LoginImages/tray.png").getImage());
		imageIcon = new ImageIcon("resources/LoginImages/tray.png");
		trayIcon = new TrayIcon(imageIcon.getImage());
		trayIcon.setImageAutoSize(true);
		try {
			systemTray.add(trayIcon);
		} catch (AWTException e) {
			e.printStackTrace();
		}

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
		showItem = new MenuItem("Show Main Frame");
		showItem.addActionListener(popupMenuAction);
		exitItem = new MenuItem("Exit");
		exitItem.addActionListener(popupMenuAction);

		popupdownMenu = new PopupMenu();
		popupdownMenu.add(showItem);
		popupdownMenu.add(exitItem);

		trayIcon.setPopupMenu(popupdownMenu);
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

	/** 右上角的三个按钮的鼠标监听器 */
	private RightUp rightUp = new RightUp();

	/**
	 * 设置登陆状态的事件监听
	 */
	private ActionListener setState = new SetState();

	/**
	 * 输入的状态监听, 移开会怎么样, 不移开会怎么样
	 */
	private FocusListener inputFoucs = new InputFocus();

	/**
	 * 对于点击事件的所有监听
	 */
	private MouseListener mouseClick = new MouseClick();

	/**
	 * 鼠标的手的形状
	 */
	private Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

	@Override
	protected void addComponent() {
		loginPanel = new LoginPanel();
		loginPanel.setPreferredSize(new Dimension(QQLoginFrame.LOGIN_WIDTH, QQLoginFrame.LOGIN_HEIGHT));

		// 关闭
		close = new JLabel();
		close.setBounds(400, 0, 29, 30);
		loginPanel.add(close);
		close.setCursor(cursor);// 把鼠标弄成手的形状
		close.addMouseListener(rightUp);
		close.setToolTipText("关闭");

		// 最小化
		minimize = new JLabel();
		minimize.setBounds(370, 0, 29, 30);
		loginPanel.add(minimize);
		minimize.setCursor(cursor);// 把鼠标弄成手的形状
		minimize.addMouseListener(rightUp);
		minimize.setToolTipText("最小化");

		// 弹出框, 虽然这里没有写
		pop = new JLabel();
		pop.setBounds(340, 0, 29, 30);
		loginPanel.add(pop);
		pop.setCursor(cursor);// 把鼠标弄成手的形状
		pop.addMouseListener(rightUp);

		// qq登陆的时候
		userName = new JTextField("QQ/手机/邮箱");
		userName.setBounds(135, 190, 190, 25);
		loginPanel.add(userName);
		userName.setFont(new Font("", Font.PLAIN, 14));
		userName.setOpaque(false);
		userName.addFocusListener(inputFoucs);

//		selectQQNumber = new JComboBox<String>(vlist);
//		selectQQNumber.setBounds(135, 190, 190, 25);
//		loginPanel.add(selectQQNumber);
//		selectQQNumber.setEditable(true);
//		selectQQNumber.setFont(new Font("", Font.PLAIN, 14));
//		selectQQNumber.addFocusListener(inputFoucs);

		// qq密码框
		passwordField = new JPasswordField();
		passwordField.setBounds(135, 225, 190, 25);
		loginPanel.add(passwordField);
		passwordField.setFont(new Font("", Font.PLAIN, 14));
		passwordField.setOpaque(false);

		// 设置颜色, 记住密码
		Color myc = new Color(166, 166, 166);
		rememberPassword = new JCheckBox("记住密码");
		rememberPassword.setBounds(130, 250, 80, 20);
		rememberPassword.setForeground(myc);
		loginPanel.add(rememberPassword);

		// 自动登陆
		autoLogin = new JCheckBox("自动登录");
		autoLogin.setBounds(230, 250, 80, 20);
		autoLogin.setForeground(myc);
		loginPanel.add(autoLogin);

		// 注册
		Color mycolor = new Color(15, 177, 255);// 设置颜色
		register = new JLabel("注册账号");
		register.setBounds(340, 190, 60, 25);
		loginPanel.add(register);
		register.setForeground(mycolor);
		register.setCursor(cursor);// 手
		register.addMouseListener(mouseClick);

		// 忘记
		forgetPassword = new JLabel("忘记密码");
		forgetPassword.setBounds(340, 225, 60, 25);
		loginPanel.add(forgetPassword);
		forgetPassword.setForeground(mycolor);
		forgetPassword.setCursor(cursor);// 手

		// 登陆按钮
		ImageIcon buttomIcon = new ImageIcon("resources/LoginImages/loginButton.png");
		loginButton = new JLabel();
		loginButton.setIcon(buttomIcon);
		loginButton.setBounds(130, 270, 232, 40);
		loginPanel.add(loginButton);
		loginButton.setCursor(cursor);
		loginButton.addMouseListener(new LoginClick());

		// 状态设置
		stateSelect = new JLabel(new ImageIcon("resources/LoginImages/s_online.png"));
		stateSelect.setBounds(97, 235, 15, 15);
		loginPanel.add(stateSelect);
		stateSelect.addMouseListener(showState);

		// 登陆头像
		loginProfile = new JLabel(new ImageIcon("resources/LoginImages/loginProfile.png"));
		loginProfile.setBounds(50, 190, 60, 60);
		loginPanel.add(loginProfile);

		// 状态弹框
		states = new JPopupMenu();
		onlineState = new JMenuItem("我在线上", new ImageIcon("resources/LoginImages/s_online.png"));
		onlineState.addActionListener(setState);
		leaveState = new JMenuItem("离开", new ImageIcon("resources/LoginImages/s_leave.png"));
		leaveState.addActionListener(setState);
		busyState = new JMenuItem("忙碌", new ImageIcon("resources/LoginImages/s_busy.png"));
		busyState.addActionListener(setState);
		hideState = new JMenuItem("隐身", new ImageIcon("resources/LoginImages/s_hide.png"));
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

	/** 是否可以拖拽 */
	protected boolean isDragged;
	/** 临时变量 */
	private Point tempPoint = null;
	/** 位置 */
	private Point location = null;

	/**
	 * 设置我们的面板可以移动,
	 */
	private void setDragable() {
		this.addMouseListener(new java.awt.event.MouseAdapter() {

			public void mouseReleased(java.awt.event.MouseEvent e) {
				isDragged = false;
				QQLoginFrame.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 默认鼠标的箭头
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
				tempPoint = new Point(e.getX(), e.getY());

				isDragged = true;
				QQLoginFrame.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent e) {
				if (isDragged) {
					location = new Point(QQLoginFrame.this.getLocation().x + e.getX() - tempPoint.x,
							QQLoginFrame.this.getLocation().y + e.getY() - tempPoint.y);
					QQLoginFrame.this.setLocation(location);
				}
			}
		});
	}

	/**
	 * 内部类, 主要是为了让我们的图能够完整的在我们的面板上显示
	 *
	 * @author  Lutong99
	 *
	 */
	private class LoginPanel extends JPanel {
		/**
		 * 只是为了不显示警告, 其实没有什么意义
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
	 * @author  Lutong99
	 *
	 */
	private class RightUp extends MouseAdapter {
		@Override
		public void mouseEntered(MouseEvent e) {
			if (e.getSource() == close) {
				close.setIcon(new ImageIcon("resources/LoginImages/loginclose.png"));
			} else if (e.getSource() == minimize) {
				minimize.setIcon(new ImageIcon("resources/LoginImages/loginMin.png"));
			} else if (e.getSource() == pop) {
				pop.setIcon(new ImageIcon("resources/LoginImages/loginpop.png"));
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
				QQLoginFrame.this.setExtendedState(JFrame.ICONIFIED); // 最小化, 说实话我连源码写的注释翻译完成之后都不认识, 但是这个可以最小化, 还是很厉害的
				minimize.setIcon(new ImageIcon(""));
			} else if (e.getSource() == pop) {
				// TODO 写一点点弹框出来的东西
			}
		}
	}

	/**
	 * 为选择状态设置的内部类
	 *
	 * @author  Lutong99
	 *
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
	 * @author  Lutong99
	 *
	 */
	class SetState implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == onlineState) {
				// 点击菜单项改变这个用户的状态
				stateSelect.setIcon(new ImageIcon("resources/LoginImages/s_online.png"));
			} else if (e.getSource() == hideState) {
				stateSelect.setIcon(new ImageIcon("resources/LoginImages/s_hide.png"));

			} else if (e.getSource() == busyState) {
				stateSelect.setIcon(new ImageIcon("resources/LoginImages/s_busy.png"));

			} else if (e.getSource() == leaveState) {
				stateSelect.setIcon(new ImageIcon("resources/LoginImages/s_leave.png"));
			}
		}
	}

	/**
	 * 主要是为了验证登陆使用
	 *
	 * @author  Lutong99
	 *
	 */
	private class LoginClick extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == loginButton) {
				loginCheck();
			}
		};
	}

	/**
	 * 鼠标进入时候的事件触发
	 *
	 * @author  Lutong99
	 *
	 */
	private class InputFocus extends FocusAdapter {
		@Override
		public void focusGained(FocusEvent e) {
			if (e.getSource() == userName) {
				if ("QQ/手机/邮箱".equals(String.valueOf(userName.getText()))) {
					userName.setText("");
				}
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			if (e.getSource() == userName) {
				if ("".equals(userName.getText())) {
					userName.setText("QQ/手机/邮箱");
				}
			}
		}
	}

	/**
	 * 登陆检测, 主要是验证密码和用户名是否正确
	 */
	private void loginCheck() {
		if ("".equals(userName.getText()) || userName.getText() == null || "QQ/手机/邮箱".equals(userName.getText())) {
			JOptionPane.showMessageDialog(loginPanel, "qq号码为空");
		}
		if ("".equals(String.valueOf(passwordField.getPassword())) || passwordField.getPassword() == null) {
			JOptionPane.showMessageDialog(loginPanel, "请输入密码");
		} else {
			String qqNumber = userName.getText();
			String qqPassword = String.valueOf(passwordField.getPassword());
			QQ loginCheck = QQLoginController.getQqLoginController().loginCheck(qqNumber, qqPassword);
			if (loginCheck == null) {
				JOptionPane.showMessageDialog(loginPanel, "用户名或密码错误, 请重新输入");
				passwordField.setText("");
				userName.grabFocus();
			} else {
				JOptionPane.showMessageDialog(loginPanel, "登陆成功");
				// TODO 登陆成功
				QQLoginFrame.this.dispose();
				systemTray.remove(trayIcon);
				new QQMainFrame(loginCheck);
			}
		}
	}

	/**
	 * 
	 *
	 * @author  Lutong99
	 *
	 */
	private class MouseClick extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == register) {
				new QQRegisterFrame();
			}
		}
	}

	/**
	 * 托盘区显示右键菜单 的所有动作
	 *
	 * @author  Lutong99
	 *
	 */
	private class PopupMenuAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == showItem) {
				QQLoginFrame.this.setExtendedState(NORMAL);
			} else if (e.getSource() == exitItem) {
				System.exit(0); // 点击推出之后就退出
			}
		}
	}
}
