package org.example.test;

import javax.swing.*;

public class TestJOptionPane {
    public static void main(String[] args) {

        Object[] options = {"最小化", "关闭"};
        int showOptionDialog = JOptionPane.showOptionDialog(null, "确认退出QQ吗?", null, JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        System.out.println(showOptionDialog);

//		int showConfirmDialog = JOptionPane.showConfirmDialog(qqMainPanel, "确认退出QQ吗", null,
//				JOptionPane.OK_CANCEL_OPTION);
//		if (showConfirmDialog == 0) {
//			System.exit(0);
//		} else if (showConfirmDialog == 2) {
//
//		}

    }

    /**
     * Type meaning Look and Feel should not supply any options -- only use the
     * options from the <code>JOptionPane</code>.
     */
    public static final int DEFAULT_OPTION = -1;
    /**
     * Type used for <code>showConfirmDialog</code>.
     */
    public static final int YES_NO_OPTION = 0;
    /**
     * Type used for <code>showConfirmDialog</code>.
     */
    public static final int YES_NO_CANCEL_OPTION = 1;
    /**
     * Type used for <code>showConfirmDialog</code>.
     */
    public static final int OK_CANCEL_OPTION = 2;

    //
    // Return values.
    //
    /**
     * Return value from class method if YES is chosen.
     */
    public static final int YES_OPTION = 0;
    /**
     * Return value from class method if NO is chosen.
     */
    public static final int NO_OPTION = 1;
    /**
     * Return value from class method if CANCEL is chosen.
     */
    public static final int CANCEL_OPTION = 2;
    /**
     * Return value form class method if OK is chosen.
     */
    public static final int OK_OPTION = 0;
    /**
     * Return value from class method if user closes window without selecting
     * anything, more than likely this should be treated as either a
     * <code>CANCEL_OPTION</code> or <code>NO_OPTION</code>.
     */
    public static final int CLOSED_OPTION = -1;

    //
    // Message types. Used by the UI to determine what icon to display,
    // and possibly what behavior to give based on the type.
    //
    /**
     * Used for error messages.
     */
    public static final int ERROR_MESSAGE = 0;
    /**
     * Used for information messages.
     */
    public static final int INFORMATION_MESSAGE = 1;
    /**
     * Used for warning messages.
     */
    public static final int WARNING_MESSAGE = 2;
    /**
     * Used for questions.
     */
    public static final int QUESTION_MESSAGE = 3;
    /**
     * No icon is used.
     */
    public static final int PLAIN_MESSAGE = -1;
}
