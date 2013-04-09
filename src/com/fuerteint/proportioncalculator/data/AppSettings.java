package com.fuerteint.proportioncalculator.data;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * @author Vojtech Hrdina
 *
 */
public class AppSettings {
	
	private int type;

	private static final String PREFERENCES_MAIN_KEY = "settings_main_key";
	private static final String KEY_TYPE = "type";
	
	private SharedPreferences mPreferences;
	private SharedPreferences.Editor mPreferencesEditor;

	private static final Object sLock = new Object();
	private static AppSettings sInstance;

	private Context mContext;

	/**
	 * @param applicationContext
	 */
	private AppSettings(Context applicationContext) {
		mContext = applicationContext;
		mPreferences = mContext.getSharedPreferences(PREFERENCES_MAIN_KEY, 0);
		initVariables();
	}

	/**
	 * @param context
	 * @return
	 */
	public static AppSettings getInstance(Context context) {
		synchronized (sLock) {
			if (sInstance == null) {
				sInstance = new AppSettings(context.getApplicationContext());
			}
			return sInstance;
		}
	}
	
	
	private void initVariables() {
		type = mPreferences.getInt(KEY_TYPE, Constants.TYPE_NORMAL);
	}
	
	public synchronized void setType(int type) {
		mPreferencesEditor = mPreferences.edit();
		mPreferencesEditor.putInt(KEY_TYPE, type);
		mPreferencesEditor.commit();
		this.type = type;
	}
	
	public int getType() {
		return type;
	}

}
