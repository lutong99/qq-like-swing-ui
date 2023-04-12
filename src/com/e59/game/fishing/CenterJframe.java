package com.e59.game.fishing;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.JFrame;

/**
 * 中央的框架
 * 
 * @author 浩哥
 *
 */
public class CenterJframe extends JFrame {
	private static final long serialVersionUID = 7714415698865264540L;
	protected int width;
	protected int height;
	protected Random ran = new Random();

	public void init() {
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension screenSize = tool.getScreenSize();
		double w = screenSize.getWidth();
		double h = screenSize.getHeight();
		this.setBounds((int) (w - width) / 2, (int) (h - height) / 2, width, height);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);

	}

	public CenterJframe(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.setTitle(title);
	}

}
