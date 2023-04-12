package com.e59.game.fishing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 开始游戏
 *
 * @author 浩哥
 *
 */
public class StartFishing extends CenterJframe {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel Startpan = new MyJPanel();
	BufferedImage StartpanImage;// 开始界面背景
	BufferedImage StartbtnImage;// "开始游戏"按钮背景
	BufferedImage EndbtnImage;// "关闭按钮背景"
	BufferedImage logoImage;// logo
	BufferedImage rangkingimage;
	JButton Stratbtn;// 开始游戏按钮
	JButton rangking;

	public StartFishing(int width, int height, String title) {
		super(width, height, title);
		this.init();
		this.addStartpan();
		this.addLis();
		this.pack();
	}

//	public static void main(String[] args) {
//		StartFishing s = new StartFishing(1200, 628, "捕鱼奇兵");
//	}

	class MyJPanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(StartpanImage, 0, 0, this);// 设置开始界面背景
		}
	}

	void addStartpan() {
		Startpan.setPreferredSize(new Dimension(width, height));
		Startpan.setLayout(null);
		try {
			/*
			 * 将图片加入图片缓冲区
			 */
			StartpanImage = ImageIO.read(new File("resources/Fishing/Start.jpg"));// 背景图片
			StartbtnImage = ImageIO.read(new File("resources/Fishing/Start.png"));// 开始游戏按钮图片
			rangkingimage = ImageIO.read(new File("resources/Fishing/排行.png"));
			this.setIconImage(ImageIO.read(new File("resources/Fishing/章鱼.png")));// 设置图标
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.add(Startpan);
		/*
		 * 加入按钮背景并将按钮加入Stratbtn,设置按钮属性
		 */
		Stratbtn = new JButton(null, new ImageIcon(StartbtnImage));
		rangking = new JButton(null, new ImageIcon(rangkingimage));
		Stratbtn.setBounds(135, 100, 470, 200);
		Stratbtn.setBorder(null);
		Stratbtn.setContentAreaFilled(false);
		rangking.setBounds(220, 300, 300, 300);
		rangking.setBorder(null);
		rangking.setContentAreaFilled(false);
		Startpan.add(Stratbtn);
		Startpan.add(rangking);
	}

	void addLis() {
		/*
		 * 游戏开始事件,点击开始游戏按钮,调用setVisible使初始界面不可见可见
		 */
		Stratbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new TimeThread().start();// 启动时间线程
				Helper.mf.setVisible(true);// 使游戏界面可见
				Helper.sf.setVisible(false);// 使菜单界面不可见
			}
		});
		// 排行榜按钮 ,点击按钮使排行榜窗口可见
		rangking.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Helper.rk.setVisible(true);
			}
		});
	}

}
