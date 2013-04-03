package com.fuerteint.proportioncalculator.util;

import java.lang.ref.SoftReference;
import java.util.HashMap;


import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FontCache {

	private static final String TAG = "FontCache";

	public static boolean setCustomFont(View textViewOrButton, Context ctx,
			String asset) {
		if (TextUtils.isEmpty(asset))
			return false;
		Typeface tf = null;
		try {
			tf = getFont(ctx, asset);
			if (textViewOrButton instanceof TextView) {
				((TextView) textViewOrButton).setTypeface(tf);
			} else {
				((Button) textViewOrButton).setTypeface(tf);
			}
		} catch (Exception e) {
			Logg.e(TAG, "Could not get typeface: " + asset);
			return false;
		}

		return true;
	}

	private static final HashMap<String, SoftReference<Typeface>> fontCache = new HashMap<String, SoftReference<Typeface>>();

	public static Typeface getFont(Context c, String name) {
		synchronized (fontCache) {
			if (fontCache.get(name) != null) {
				SoftReference<Typeface> ref = fontCache.get(name);
				if (ref.get() != null) {
					return ref.get();
				}
			}

			Typeface typeface = Typeface.createFromAsset(c.getAssets(), name);
			fontCache.put(name, new SoftReference<Typeface>(typeface));

			return typeface;
		}
	}

}
