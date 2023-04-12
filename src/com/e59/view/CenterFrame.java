package com.e59.view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * 
 * 居中窗口
 *
 * @author  Lutong99
 * 
 */
public abstract class CenterFrame extends JFrame {
	/**
	 * 为了避免出现警告, 没有什么实际意义
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * 初始化一个在屏幕中央的窗口框
	 * 
	 * @param title     窗口的标题
	 * @param imagePath 窗口的图标的路径名字
	 * @param width     窗口的宽度
	 * @param height    窗口的宽度
	 */
	protected void init(String title, String imagePath, int width, int height) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();

		int x = (screenWidth - width) / 2;
		int y = (screenHeight - height) / 2;

		this.setBounds(x, y, width, height);

		if (title != null) {
			this.setTitle(title);
		}

		if (imagePath != null) {
			Image image;
			try {
				image = ImageIO.read(new File(imagePath));
				this.setIconImage(image);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.specilAction();
		this.addComponent();
		this.addEvents();
		// 设置关闭, 但事实证明了没有什么用
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * 初始化窗口, 我们已经写完了, 在这里主要调用的是另一个
	 * 
	 * @see {@link CenterFrame#init(String, String, int, int)}
	 * @param title
	 * @param imagePath
	 */
	public void init(String title, String imagePath) {
		this.init(title, imagePath, QQLoginFrame.LOGIN_WIDTH, QQLoginFrame.LOGIN_HEIGHT);
	}

	/**
	 * 添加一些事件
	 */
	protected abstract void addEvents();

	/**
	 * 添加一些我们需要的组件
	 */
	protected abstract void addComponent();

	/**
	 * 特殊的动作, 可能我会忘记是用来做什么的, 就想起来就用一下
	 */
	protected abstract void specilAction();

}
