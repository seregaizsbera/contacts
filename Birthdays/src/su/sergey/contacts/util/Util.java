package su.sergey.contacts.util;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * Title:        Viewer
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:      Sberbank
 * @author Sergey Bogdanov
 * @version 1.0
 */

/**
 * Class util provides some useful general-purpose methods
 */
public final class Util {
	static private long lastTime;
	static private final boolean DEBUG = false;

	private Util() {}

	static void printException(Exception e, PrintStream out) {
		if (e instanceof SQLException) {
			for (SQLException e1 = (SQLException) e; e1 != null; e1 = e1.getNextException()) {
				e1.printStackTrace(out);
				out.println("SQLState=" + e1.getSQLState() + "; ErrorCode=" + e1.getErrorCode() + "; ExceptionClass=" + e1.getClass().getName());
			}
	    } else {
			e.printStackTrace(out);
			out.println("ExceptionClass = " + e.getClass().getName());
		}
	}

	/**
	 * Create a rectangle of specified width and height centered on the screen
	 *
	 * @param width the width
	 * @param height the height
	 * @return rectangle centered on the screen
	 */
	static public Rectangle centerOnScreen(int width, int height) {
		int sw = Toolkit.getDefaultToolkit().getScreenSize().width;
		int sh = Toolkit.getDefaultToolkit().getScreenSize().height;
		if (width > sw || width <= 0) {
			width = sw;
		}
		if (height > sh || height <= 0) {
			height = sh;
		}
		int l = (sw - width) / 2;
		int t = (sh - height) / 2;
		return new Rectangle(l, t, width, height);
	}

	/**
	 * Show dialog with information about specifed excpetion
	 *
	 * @param e the exception
	 */
	static public void showException(Exception e) {
		Calendar calendar = Calendar.getInstance();
		long time = calendar.getTime().getTime();
		if (time - lastTime < 2000) {
			System.err.println(e);
			System.err.println(Util.class.getName() + ".showException(): too often called.");
			lastTime = calendar.getTime().getTime();
			return;
		}
		printException(e, System.err);
		new ExceptionDialog(e).setVisible(true);
		lastTime = calendar.getTime().getTime();
	}

	/**
	 *  Prints object to standard error output, if in the debug mode
	 *
	 * @param o object to be written
	 */
	static public void debug(Object o) {
		if (DEBUG) {
			System.err.println(o);
		}
	}
}
