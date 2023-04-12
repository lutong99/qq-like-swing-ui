package com.e59.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.e59.game.fishing.StartGame;
import com.e59.game.moveBox.MoveBoxMain;

/**
 * 用来选择游戏界面.
 *
 * @author  Lutong99
 *
 */
public class QQGameSelectorFrame extends CenterFrame {

	/**
	 * 只是为了不显示警告, 没别的事
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 背景图片
	 */
	private static BufferedImage backgroundImage;

	/**
	 * 选择界面的宽度
	 */
	public static final int SELECT_WIDTH = 425;

	/**
	 * 选择界面的高度
	 */
	public static final int SELECT_HEIGHT = 282;

	/**
	 * 标题
	 */
	private JLabel titleJLabel;

	/**
	 * 
	 */
	private JLabel closeJLabel;

	/**
	 * 容器
	 */
	private SelectorPanel selectorPanel;

	/**
	 * 推箱子游戏标签
	 */
	private JLabel moveBoxJLabel;

	/**
	 * 捕鱼达人标签
	 */
	private JLabel fishingJLabel;

	/**
	 * 自定义的点击类
	 */
	private MouseListener mouseClick = new MouseClick();

	/**
	 * 手型的鼠标
	 */
	private Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

	static {
		try {
			backgroundImage = ImageIO.read(new File("resources/GameSelectorImages/BackGround.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public QQGameSelectorFrame() {
		init(null, null, SELECT_WIDTH, SELECT_HEIGHT);
	}

	@Override
	protected void addEvents() {

	}

	@Override
	protected void addComponent() {
		selectorPanel = new SelectorPanel();
		selectorPanel.setPreferredSize(new Dimension(SELECT_WIDTH, SELECT_HEIGHT));

		titleJLabel = new JLabel("请选择游戏");
		titleJLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));
		titleJLabel.setForeground(new Color(12, 73, 192));
		titleJLabel.setBounds(150, 25, 200, 30);
		selectorPanel.add(titleJLabel);

		closeJLabel = new JLabel(new ImageIcon("resources/GameSelectorImages/Close.jpg"));
		closeJLabel.setBounds(178, 174, 71, 72);
		closeJLabel.addMouseListener(mouseClick);
		closeJLabel.setCursor(cursor);
		selectorPanel.add(closeJLabel);

		moveBoxJLabel = new JLabel(new ImageIcon("resources/GameSelectorImages/MoveBox.jpg"));
		moveBoxJLabel.setBounds(100, 100, 100, 58);
		moveBoxJLabel.addMouseListener(mouseClick);
		selectorPanel.add(moveBoxJLabel);

		fishingJLabel = new JLabel(new ImageIcon("resources/GameSelectorImages/Fish.jpg"));
		fishingJLabel.setBounds(250, 100, 100, 58);
		fishingJLabel.addMouseListener(mouseClick);
		selectorPanel.add(fishingJLabel);

		this.add(selectorPanel);
	}

	@Override
	protected void specilAction() {
		// 去边框
		this.setUndecorated(true);
		// 设置可以移动
		setDragable();
	}

	/**
	 * 这个页面的容器
	 *
	 * @author  Lutong99
	 *
	 */
	private class SelectorPanel extends JPanel {

		/**
		 * 不弹警告
		 */
		private static final long serialVersionUID = 1L;

		public SelectorPanel() {
			this.setLayout(null);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backgroundImage, 0, 0, null);
		}
	}

	/**
	 * 鼠标点击事件
	 *
	 * @author  Lutong99
	 *
	 */
	private class MouseClick extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == closeJLabel) {
				QQGameSelectorFrame.this.dispose();
			} else if (e.getSource() == moveBoxJLabel) {
				QQGameSelectorFrame.this.dispose();
				new MoveBoxMain();
			} else if (e.getSource() == fishingJLabel) {
				QQGameSelectorFrame.this.dispose();
				new StartGame().PlayGame();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if (e.getSource() == closeJLabel) {
				closeJLabel.setIcon(new ImageIcon("resources/GameSelectorImages/CloseRed.jpg"));
			} else if (e.getSource() == moveBoxJLabel) {

			} else if (e.getSource() == fishingJLabel) {

			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if (e.getSource() == closeJLabel) {
				closeJLabel.setIcon(new ImageIcon("resources/GameSelectorImages/Close.jpg"));
			} else if (e.getSource() == moveBoxJLabel) {

			} else if (e.getSource() == fishingJLabel) {

			}
		}
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
				QQGameSelectorFrame.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 默认鼠标的箭头
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
				tempPoint = new Point(e.getX(), e.getY());

				isDragged = true;
				QQGameSelectorFrame.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent e) {
				if (isDragged) {
					location = new Point(QQGameSelectorFrame.this.getLocation().x + e.getX() - tempPoint.x,
							QQGameSelectorFrame.this.getLocation().y + e.getY() - tempPoint.y);
					QQGameSelectorFrame.this.setLocation(location);
				}
			}
		});
	}

}
