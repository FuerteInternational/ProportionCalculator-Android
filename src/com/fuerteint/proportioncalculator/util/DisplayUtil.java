package com.fuerteint.proportioncalculator.util;

import android.content.Context;

public class DisplayUtil {
	
	public static int toPixels(Context c, int dip) { 
		final float scale = c.getResources().getDisplayMetrics().density;
		return (int) (dip * scale + 0.5f);
	}

}
