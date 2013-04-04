package com.fuerteint.proportioncalculator.views;

import com.fuerteint.proportioncalculator.R;
import com.fuerteint.proportioncalculator.util.FontCache;
import com.fuerteint.proportioncalculator.util.Logg;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.EditText;

public class EditTextViewPlus extends EditText {

	private float textSize;
	
	private static final float MAX_SIZE = 40;
	private static final float MIN_SIZE = 18;
	

	public EditTextViewPlus(Context context) {
		super(context);
	}

	public EditTextViewPlus(Context context, AttributeSet attrs) {
		super(context, attrs);
		setCustomFont(context, attrs);
	}

	public EditTextViewPlus(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setCustomFont(context, attrs);
	}

	private void setCustomFont(Context ctx, AttributeSet attrs) {
		TypedArray a = ctx.obtainStyledAttributes(attrs,
				R.styleable.TextViewPlus);
		String customFont = a.getString(R.styleable.TextViewPlus_customFont);
		this.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
		textSize = this.getTextSize();
		FontCache.setCustomFont(this, ctx, customFont);
		a.recycle();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		Logg.e("edittetx", "test");

		Logg.e("EditText", "EditText: "+(this.getText().length()*textSize)+" box size: "+this.getWidth());

		if ((this.getText().length()*textSize) > this.getWidth() && textSize > MIN_SIZE) {
			textSize = textSize * 0.90f;
			this.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
		} 

		if((this.getText().length()*textSize) < this.getWidth() && textSize < MAX_SIZE) {
			textSize = textSize / 0.90f;
			this.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

}
