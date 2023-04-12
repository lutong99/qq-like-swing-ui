package com.e59.game.moveBox;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 推箱子的帮助界面
 *
 * @author  Lutong99
 *
 */
public class MoveBoxHelp extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel tipJLabel;
	private JLabel tipjLabel2;
	private JPanel panel;

	public MoveBoxHelp() {
		super("推箱子-帮助");
		setSize(400, 200);
		setVisible(true);

		panel = new JPanel(null);
		panel.setPreferredSize(new Dimension(400, 200));

		int x = (int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 400) / 2);
		int y = (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 400) / 2);

		setBounds(x, y, 400, 200);

		tipJLabel = new JLabel("使用上下左右");
		tipJLabel.setFont(new Font("楷体", Font.BOLD, 28));
		tipJLabel.setBounds(10, 0, 400, 80);

		tipjLabel2 = new JLabel("来控制人物的移动");
		tipjLabel2.setFont(new Font("楷体", Font.BOLD, 28));
		tipjLabel2.setBounds(10, 80, 400, 80);
		panel.add(tipJLabel);
		panel.add(tipjLabel2);

		panel.add(tipJLabel);
		this.add(panel);

		// setLocationRelativeTo(null);不要中心对焦
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 关闭
	}

}
