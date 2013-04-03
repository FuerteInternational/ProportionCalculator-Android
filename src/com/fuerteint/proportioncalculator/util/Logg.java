package com.fuerteint.proportioncalculator.util;

import android.util.Log;

/**
 * @author Stefan Kostelny
 * modified by Stefan Kostelny on Feb 20, 2013
 *
 */
public class Logg {

	public static final int VERBOSE = 1;
	public static final int DEBUG = 2;
	public static final int INFO = 3;
	public static final int WARNING = 4;
	public static final int ERROR = 5;

	public static final int ON = 0;
	public static final int OFF = 100;

	public static int level = OFF;

	public static void v(String tag, String string) {
		if (level <= VERBOSE) {
			Log.v(tag, string);
		}
	}

	public static void d(String tag, String string) {
		if (level <= DEBUG) {
			Log.d(tag, string);
		}
	}

	public static void i(String tag, String string) {
		if (level <= INFO) {
			Log.i(tag, string);
		}
	}

	public static void w(String tag, String string) {
		if (level <= WARNING) {
			Log.w(tag, string);
		}
	}

	public static void e(String tag, String string) {
		if (level <= ERROR) {
			Log.e(tag, string);
		}
	}

	public static void e(String tag, String string, Exception e) {
		if (level <= ERROR) {
			Log.e(tag, string, e);
		}
	}

}
