package com.fuerteint.proportioncalculator.views;

import com.fuerteint.proportioncalculator.R;
import com.fuerteint.proportioncalculator.util.FontCache;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.EditText;

/**
 * @author Vojtech Hrdina
 *
 */
public class EditTextViewPlus extends EditText {

	private float textSize;
	private float MAX_SIZE;
	private float MIN_SIZE;
	private float originaltextSize;


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
				R.styleable.EditTextViewPlus);
		String customFont = a.getString(R.styleable.TextViewPlus_customFont);
		float maxSize = a.getDimension(R.styleable.EditTextViewPlus_maxSize, this.getTextSize());
		float minSize = a.getDimension(R.styleable.EditTextViewPlus_minSize, this.getTextSize());
		MAX_SIZE = maxSize;
		MIN_SIZE = minSize;
		originaltextSize = this.getTextSize();
		textSize = this.getTextSize();
		FontCache.setCustomFont(this, ctx, customFont);
		a.recycle();
	}
	


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//get TextSize
		Paint textPaint = this.getPaint();
		float widthText = textPaint.measureText(this.getText().toString());

		//get EditBoxSize
		float widthEditBox = this.getWidth()-this.getPaddingLeft()-this.getPaddingRight();

		//scale
		if(widthText != 0) {
			float scale = widthEditBox/widthText;


			if(scale < 1 ) {
				//scale down
				if (widthText >= widthEditBox && textSize > MIN_SIZE) {
					textSize = textSize * scale;
					this.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
				} 
			} else {
				//scale up
				if(widthText < widthEditBox && textSize < MAX_SIZE) {
					textSize = textSize * scale;
					if(textSize > originaltextSize) {
						textSize = originaltextSize;
					}
					this.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
				}
			}
		} else {
			this.setTextSize(TypedValue.COMPLEX_UNIT_PX, originaltextSize);
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

}
