package org.example.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 测试托盘区的图标问题
 *
 * @author Lutong99
 */
public class TestSystemTray extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private TrayIcon trayIcon;
    private SystemTray systemTray;
    private PopupMenu rightKeyJPopupMenu;
    private MenuItem menuItem1;

    public TestSystemTray() {
        super("System tray test");
        systemTray = SystemTray.getSystemTray();
        setSize(150, 150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        try {
            String filePath = "static/RegisterImages/Profiles/0_online.png";
            Image image = Toolkit.getDefaultToolkit().getImage(filePath);
            trayIcon = new TrayIcon(image);
            systemTray.add(trayIcon);
            this.dispose();
        } catch (Exception e) {
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


        rightKeyJPopupMenu = new PopupMenu();
        menuItem1 = new MenuItem("Exit");
        rightKeyJPopupMenu.add(menuItem1);

        trayIcon.setPopupMenu(rightKeyJPopupMenu);

//		trayIcon.addMouseListener(new MouseAdapter() {
//			
//			@Override
//			public void mousePressed(MouseEvent e) {
//				rightKeyJPopupMenu.setVisible(true);
//			}
//		});

    }

    public static void main(String[] args) {
        new TestSystemTray();
    }

}
