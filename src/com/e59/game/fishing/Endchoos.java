package com.e59.game.fishing;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 当游戏时间结束或者分数为0时,是该窗口可见
 * 
 * @author 浩哥
 */
public class Endchoos extends CenterJframe {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel pan = new JPanel();
	JButton exit = new JButton("返回菜单");// 返回菜单按钮

	public Endchoos(int width, int height, String title) {
		super(width, height, title);
		this.init();// 初始化
		this.addpan();
		this.addLis();// 添加事件
		this.pack();
	}

	void addpan() {
		pan.setPreferredSize(new Dimension(width, height));
		pan.setLayout(null);
		this.add(pan);
		exit.setBounds(150, 25, 100, 50);
		pan.add(exit);
	}

	void addLis() {
		/**
		 * 为exit按钮添加实现,点击exit按钮使游戏界面和结束界面不可见,菜单界面可见,并将分数设置为初始值
		 */
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Helper.mf.setVisible(false);
				Helper.sf.setVisible(true);
				Helper.ec.setVisible(false);
				Helper.isover = false;// isover = false 表示重新初始化游戏
				try {
					// 将分数记录到文件中
					BufferedOutputStream bos = new BufferedOutputStream(
							new FileOutputStream(new File("resources/Fishing/Ranking.txt"), true));
					bos.write(Helper.mf.coinNum.getText().getBytes());
					bos.write("\r\n".getBytes());// 换行
					bos.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Helper.score = 100;// 游戏结束后,设置初试分数
				Helper.mf.coinNum.setText(Helper.score + "");// 游戏结束后设置分数为初始值
				Helper.times = 60;// 游戏结束后,设置初试时间
			}
		});
	}
}
