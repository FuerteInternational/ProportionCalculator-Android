package com.fuerteint.proportioncalculator.views;

import com.fuerteint.proportioncalculator.R;
import com.fuerteint.proportioncalculator.util.FontCache;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewPlus extends TextView {

	public TextViewPlus(Context context) {
		super(context);
	}

	public TextViewPlus(Context context, AttributeSet attrs) {
		super(context, attrs);
		setCustomFont(context, attrs);
	}

	public TextViewPlus(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setCustomFont(context, attrs);
	}

    private void setCustomFont(Context ctx, AttributeSet attrs) {
    	TypedArray a = ctx.obtainStyledAttributes(attrs,
				R.styleable.TextViewPlus);
		//String customFont = Constants.DEFAULT_FONT;
    	String customFont = a.getString(R.styleable.TextViewPlus_customFont);
    	//Logg.e("customfont", "font: "+customFont);
        /*if(customFont == null) {
        	customFont = Constants.DEFAULT_FONT;
        }*/
		FontCache.setCustomFont(this, ctx, customFont);
		a.recycle();
    }

}
