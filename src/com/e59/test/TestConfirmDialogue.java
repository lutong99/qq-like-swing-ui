package com.e59.test;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 测试确认窗口的返回值
 *
 * @author  Lutong99
 *
 */
public class TestConfirmDialogue extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new TestConfirmDialogue();
		
	}

	JButton button = new JButton("Test");

	JPanel panel = new JPanel();

	public TestConfirmDialogue() {
		this.setBounds(300, 300, 600, 600);

		button.setBounds(30, 30, 40, 40);

		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int showConfirmDialog = JOptionPane.showConfirmDialog(panel, "确认吗", "确认框", JOptionPane.OK_CANCEL_OPTION);
				System.out.println(" 这个码是什么 "  + showConfirmDialog);
				
				/**
				 * 确认的返回值是0
				 * 取消的返回值是1
				 * 
				 */

			}
		});

		panel.setPreferredSize(new Dimension(600, 600));

		panel.add(button);
		this.add(panel);
		this.setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
