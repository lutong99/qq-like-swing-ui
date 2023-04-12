package com.e59.game.fishing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 等级
 *
 * @author 浩哥
 *
 */
public class Rangking extends CenterJframe {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel pan = new JPanel();
	JTextArea jta = new JTextArea();// 多行文本框
	JScrollPane jsp;// 存放JTextArea的有滚动条的容器
	JButton exit = new JButton("关闭");// 退出按钮

	public Rangking(int width, int height, String title) {
		super(width, height, title);
		this.init();
		this.addpan();
		this.addLis();
		this.inputfile();// 加载文件方法
		this.pack();
	}

	// 加入基本组件到pan中
	void addpan() {
		pan.setPreferredSize(new Dimension(width, height));// 设置优选大小
		pan.setLayout(null);// 设置布局为null
		jta.setEditable(false);// 设置文本框不可编辑
		jta.setForeground(new Color(0XFFA500));// 设置文本框字体颜色
		jta.setFont(new Font("楷体", Font.BOLD, 30));// 设置字体格式
		// 设置滚动条按需可见
		jsp = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBounds(0, 0, 400, 600);
		pan.add(jsp);
		this.add(pan);
		exit.setBounds(150, 550, 100, 30);
		jta.add(exit);
	}

	/**
	 * 读取文件中的游戏得分,排序后输入到多行文本框中
	 */
	void inputfile() {
		Path path = Paths.get("resources/Fishing/Ranking.txt");
		try {
			List<String> list = Files.readAllLines(path);
			/*
			 * 使用冒泡排序将list中的成绩排序
			 */
			for (int i = 0; i < list.size() - 1; i++) {
				for (int j = 0; j < list.size() - i - 1; j++) {
					if (Integer.parseInt(list.get(j)) < Integer.parseInt(list.get(j + 1))) {
						String temp = list.get(j);
						list.set(j, list.get(j + 1));
						list.set(j + 1, temp);
					}
				}
			}
			// 将排序好的list输入到多行文本框中
			for (int i = 0; i < list.size(); i++) {
				jta.append(list.get(i) + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭按钮事件,点击按钮,使排行榜窗口不可见
	 */
	void addLis() {
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Helper.rk.setVisible(false);
			}
		});
	}
}
