package org.example.view;

import org.example.beans.FileCustom;
import org.example.beans.QQ;
import org.example.controller.QQCloudController;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Vector;

/**
 * QQ微云的窗口
 *
 * @author Lutong99
 */
public class QQCloudFrame extends CenterFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * QQ微云的面板, 所有组件的容器
     */
    private QQCloundPanel qqCloudPanel;

    /**
     * 绑定的QQ用户
     */
    private QQ loginQQ;

    /**
     * 头的图片,
     */
    private static BufferedImage titleImage;
    /**
     * 微云页面的固定宽度
     */
    public static final int CLOUD_WIDTH = 800;

    /**
     * 微云页面的固定高度
     */
    public static final int CLOUD_HEIGHT = 600;

    /**
     * 关闭的标签
     */
    private JLabel closeJLabel;

    /**
     * 最小化标签
     */
    private JLabel minimizeJLabel;

    /**
     * 鼠标的所有点击事件
     */
    private MouseListener mouseClick = new MouseClick();

    /**
     * 头像的图
     */
    private JLabel profileJLabel;

    /**
     * 昵称标签
     */
    private JLabel nicknameJLabel;

    /**
     * 手型的鼠标
     */
    private Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

    /**
     * 上传文件的标签
     */
    private JLabel upLoadJLabel;

    /**
     * 上部分的panel, 主要放置一些, 头像昵称, 上传等等
     */
    private JPanel upSectorJPanel;

    /**
     * 下面部分的panel 容器
     */
    private JPanel downSectorJPanel;

    /**
     * 文件表格, 所有上传的文件都在这里显示
     */
    private JTable filesJTable;

    /**
     * 文件的Vector, 主要放在表格中
     */
    private Vector<Vector<String>> filesVector = new Vector<>();

    /**
     * 文件表格的列名
     */
    private Vector<String> filesTileVector = new Vector<>();

    /**
     * JTable的容器
     */
    private JScrollPane filesJScrollPane;

    /**
     * 构造代码块, 设置我们的表格的列名
     */ {
        filesTileVector.add("文件名");
        filesTileVector.add("上传日期");
        filesTileVector.add("大小");
    }

    /**
     * 我的资源 显示的标签
     */
    private JLabel myResourcesJLabel;

    /**
     * 属性标签
     */
    private JLabel propertiesJLabel;

    /**
     * 下载标签
     */
    private JLabel downloadJLabel;

    /**
     * 字体, 主要用来作为我们的表头的字体
     */
    private Font font = new Font("楷体", Font.BOLD, 24);

    /**
     * 删除的标签
     */
    private JLabel deleteJLabel;

    /**
     * 加载我们的微云的头
     */
    static {
        try {
            titleImage = ImageIO.read(new File("static/CloudImages/qqCloudTitle.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 构造方法
     *
     * @param loginQQ 微云的用户, 作为参数传过来
     */
    public QQCloudFrame(QQ loginQQ) {
        this.loginQQ = loginQQ;
        init(null, null, CLOUD_WIDTH, CLOUD_HEIGHT);
        this.setIconImage(new ImageIcon("static/CloudImages/cloud.png").getImage());
    }

    @Override
    protected void addEvents() {

    }

    @Override
    protected void addComponent() {
        qqCloudPanel = new QQCloundPanel();
        qqCloudPanel.setPreferredSize(new Dimension(CLOUD_WIDTH, CLOUD_HEIGHT));

        // 关闭的标签的所有操作
        closeJLabel = new JLabel(new ImageIcon(""));
        closeJLabel.setBounds(772, 0, 29, 28);
        qqCloudPanel.add(closeJLabel);
        closeJLabel.addMouseListener(mouseClick);
        closeJLabel.setToolTipText("关闭");
        closeJLabel.setCursor(cursor);

        // 最小化标签的所有操作
        minimizeJLabel = new JLabel(new ImageIcon(""));
        minimizeJLabel.setBounds(744, 0, 29, 28);
        qqCloudPanel.add(minimizeJLabel);
        minimizeJLabel.addMouseListener(mouseClick);
        minimizeJLabel.setToolTipText("最小化");
        minimizeJLabel.setCursor(cursor);

//		upLoadJLabel=new JLabel(new ImageIcon("static/CloudImages/upload.png"));
//		qqCloudPanel.add(upLoadJLabel);
//		upLoadJLabel.setCursor(cursor);

        // 上面的部分的Panel容器
        upSectorJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        profileJLabel = new JLabel(new ImageIcon(loginQQ.getPhoto()));
        upSectorJPanel.add(profileJLabel);
//		

        // 昵称的标签的操作
        nicknameJLabel = new JLabel(loginQQ.getNickname());
        nicknameJLabel.setFont(new Font("宋体", Font.BOLD, 20));
        upSectorJPanel.add(nicknameJLabel);

        // 上传标签的操作
        upLoadJLabel = new JLabel(new ImageIcon("static/CloudImages/upload.png"));
        upSectorJPanel.add(upLoadJLabel);
        upLoadJLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));// 手型
        upLoadJLabel.addMouseListener(mouseClick);

        // 上面的东西的panel容器
        upSectorJPanel.setBounds(10, 30, 800, 70);
        qqCloudPanel.add(upSectorJPanel);

        // 我的资源的标签
        myResourcesJLabel = new JLabel(new ImageIcon("static/CloudImages/myResources.png"));
        myResourcesJLabel.setBounds(10, 100, 164, 57);
        qqCloudPanel.add(myResourcesJLabel);

        // 文件表格的操作
        filesJTable = new JTable(filesVector, filesTileVector);
        filesJScrollPane = new JScrollPane(filesJTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        filesJTable.setFont(new Font("宋体", Font.PLAIN, 20));
        filesJScrollPane.setBounds(10, 170, 780, 380);
        qqCloudPanel.add(filesJScrollPane);
        setProperties();

        // 下面的panel容器的内容操作
        downSectorJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        propertiesJLabel = new JLabel(new ImageIcon("static/CloudImages/properties.png"));
//		propertiesJLabel.setFont(new Font("宋体",Font.PLAIN,23));
        propertiesJLabel.addMouseListener(mouseClick);

        propertiesJLabel.setCursor(cursor);// 手型
        downSectorJPanel.add(propertiesJLabel);

        // 下载的操作
        downloadJLabel = new JLabel(new ImageIcon("static/CloudImages/download.png"));
        downloadJLabel.setCursor(cursor);// 手型
        downSectorJPanel.add(downloadJLabel);
        downloadJLabel.addMouseListener(mouseClick);

        // 删除的操作
        deleteJLabel = new JLabel(new ImageIcon("static/CloudImages/delete.png"));
        deleteJLabel.addMouseListener(mouseClick);
        deleteJLabel.setCursor(cursor);
        downSectorJPanel.add(deleteJLabel);

        downSectorJPanel.setBounds(10, 540, 780, 60);
        qqCloudPanel.add(downSectorJPanel);

        this.add(qqCloudPanel);

    }

    /**
     * 主要是设置表格的属性
     */
    private void setProperties() {
        JTableHeader header = filesJTable.getTableHeader();
        header.setReorderingAllowed(false);

        header.setPreferredSize(new Dimension(780, 30));
        header.setFont(font);

        filesJTable.setRowHeight(30);
    }

    @Override
    protected void specilAction() {
        this.setUndecorated(true);
        setDragable();

        Vector<Vector<String>> datas = QQCloudController.getInstance().getDatas(loginQQ);
        if (datas != null) {
            filesVector = datas;
        } else {
        }
    }

    /**
     * 微云的总的Panel 容器类,
     *
     * @author Lutong99
     */
    private class QQCloundPanel extends JPanel {

        /**
         * 主要是为了不让这哥们出警告
         */
        private static final long serialVersionUID = 1L;

        /**
         * 云的构造方法, 设置我们的页面布局为null
         */
        public QQCloundPanel() {
            this.setLayout(null);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(titleImage, 0, 0, 800, 32, null);
        }

    }

    /**
     * 对所有的鼠标进行事件监控
     *
     * @author Lutong99
     */
    private class MouseClick extends MouseAdapter {
        /**
         * 选择文件的窗口
         */
        private JFileChooser uploadChooser;

        /**
         * 选择的文件上传的文件
         */
        private File fileChoseFile;

        /**
         * 保存文件的窗口
         */
        private JFileChooser saveChooser;

        @SuppressWarnings("resource")
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == closeJLabel) {
                QQCloudFrame.this.dispose();
                closeJLabel.setIcon(new ImageIcon(""));
            } else if (e.getSource() == minimizeJLabel) {
                QQCloudFrame.this.setExtendedState(ICONIFIED);
                minimizeJLabel.setIcon(new ImageIcon(""));
            } else if (e.getSource() == upLoadJLabel) {
                uploadChooser = new JFileChooser();
                uploadChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                uploadChooser.showOpenDialog(qqCloudPanel);

                fileChoseFile = uploadChooser.getSelectedFile();

                if (fileChoseFile != null) {
                    FileCustom uploadFiles = QQCloudController.getInstance().uploadFiles(fileChoseFile, loginQQ);
                    if (uploadFiles != null) {
                        String name = uploadFiles.getFileName();
                        String time = uploadFiles.getFileTime();
                        String size = uploadFiles.getFileSize();

                        Vector<String> fileItem = new Vector<String>();
                        fileItem.add(name);
                        fileItem.add(time);
                        fileItem.add(size);
                        filesVector.add(fileItem);
                        filesJTable.updateUI();
                    } else {
                    }
                } else {
                }

            } else if (e.getSource() == downloadJLabel) {
                if (filesJTable.getRowCount() > 0) {
                    int row = filesJTable.getSelectedRow();
                    if (row > -1) {
                        Vector<String> selectFile = filesVector.get(row);
                        FileCustom downloadFiles = QQCloudController.getInstance().downloadFiles(selectFile, loginQQ);
                        if (downloadFiles != null) {
                            String filePath = downloadFiles.getFilePath();
                            String name = downloadFiles.getFileName();
                            try {
                                FileInputStream fileInputStream = new FileInputStream(filePath);
                                saveChooser = new JFileChooser();
                                JTextField textField = getTextField(saveChooser);
                                textField.setText(name);
                                int saveOption = saveChooser.showSaveDialog(qqCloudPanel);
//							saveChooser.setSelectedFile(new File(name));
                                if (saveOption == JFileChooser.APPROVE_OPTION) {
//								saveChooser.setSelectedFile(new File(name));
                                    FileOutputStream fileOutputStream = new FileOutputStream(
                                            saveChooser.getSelectedFile());
                                    byte[] bytes = new byte[1024];
                                    int len = 0;
                                    while ((len = fileInputStream.read(bytes)) != -1) {
                                        fileOutputStream.write(bytes, 0, len);
                                    }
                                    JOptionPane.showMessageDialog(qqCloudPanel, "保存成功");
                                } else {

                                }

                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }

                        } else {
                        }

                    } else if (row == -1) {
                        JOptionPane.showMessageDialog(downloadJLabel, "还未选中任何文件");
                    }
                } else {
                    JOptionPane.showMessageDialog(qqCloudPanel, "表中没有任何数据, 请确认");
                }
            } else if (e.getSource() == deleteJLabel) {
                if (filesJTable.getRowCount() > 0) {
                    int row = filesJTable.getSelectedRow();
                    Vector<String> deleteVector = filesVector.get(row);
                    if (row > -1) {

                        int showConfirmDialog = JOptionPane.showConfirmDialog(qqCloudPanel,
                                "确定要删除文件" + deleteVector.get(0) + "吗? ", "确认删除吗", JOptionPane.OK_CANCEL_OPTION);

                        if (showConfirmDialog == 0) {
                            boolean deleteFiles = QQCloudController.getInstance().deleteFiles(deleteVector, loginQQ);
                            if (deleteFiles) {

                                filesVector.removeElementAt(row);
                                filesJTable.updateUI();
                            } else {
                                JOptionPane.showMessageDialog(qqCloudPanel, "删除失败");
                            }
                        } else {
                        }
                    } else {
                        JOptionPane.showMessageDialog(qqCloudPanel, "请选择要删除文件");
                    }
                } else {
                    JOptionPane.showMessageDialog(qqCloudPanel, "表中没有任何数据, 请确认");
                }

            }

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == closeJLabel) {
                closeJLabel.setIcon(new ImageIcon("static/CloudImages/loginclose.png"));
            } else if (e.getSource() == minimizeJLabel) {
                minimizeJLabel.setIcon(new ImageIcon("static/CloudImages/loginMin.png"));
            } else {

            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == closeJLabel) {
                closeJLabel.setIcon(new ImageIcon(""));
            } else if (e.getSource() == minimizeJLabel) {
                minimizeJLabel.setIcon(new ImageIcon(""));
            } else {

            }
        }

        /**
         * 获取我们的JTextField, 从我们的容器 {@code c}中
         *
         * @param c 容器
         * @return 返回我们的TextField 框
         */
        public JTextField getTextField(Container c) {
            JTextField textField = null;
            for (int i = 0; i < c.getComponentCount(); i++) {
                Component cnt = c.getComponent(i);
                if (cnt instanceof JTextField) {
                    return (JTextField) cnt;
                }
                if (cnt instanceof Container) {
                    textField = getTextField((Container) cnt);
                    if (textField != null) {
                        return textField;
                    }
                }
            }
            return textField;
        }
    }

    /**
     * 是否可以拖拽
     */
    protected boolean isDragged;
    /**
     * 临时变量
     */
    private Point tempPoint = null;
    /**
     * 位置
     */
    private Point location = null;

    /**
     * 设置我们的面板可以移动,
     */
    private void setDragable() {
        this.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseReleased(java.awt.event.MouseEvent e) {
                isDragged = false;
                QQCloudFrame.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 默认鼠标的箭头
            }

            public void mousePressed(java.awt.event.MouseEvent e) {
                tempPoint = new Point(e.getX(), e.getY());

                isDragged = true;
                QQCloudFrame.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
                if (isDragged) {
                    location = new Point(QQCloudFrame.this.getLocation().x + e.getX() - tempPoint.x,
                            QQCloudFrame.this.getLocation().y + e.getY() - tempPoint.y);
                    QQCloudFrame.this.setLocation(location);
                }
            }
        });
    }

}
