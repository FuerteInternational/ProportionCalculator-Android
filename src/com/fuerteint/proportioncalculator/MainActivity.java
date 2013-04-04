package com.fuerteint.proportioncalculator;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

import com.fuerteint.proportioncalculator.data.AppSettings;
import com.fuerteint.proportioncalculator.data.Constants;
import com.fuerteint.proportioncalculator.data.Numbers;
import com.fuerteint.proportioncalculator.util.Logg;
import com.fuerteint.proportioncalculator.views.TextViewPlus;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

public class MainActivity extends MasterActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		RelativeLayout mainView = (RelativeLayout)findViewById(R.id.mainView);
		mainView.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				hideSoftKeyboard(MainActivity.this);
				return false;
			}
		});
		
		initActiveViews();
	}

	float textSize;
	private void initActiveViews() {
		final EditText boxA = (EditText)findViewById(R.id.boxA);
		EditText boxB = (EditText)findViewById(R.id.boxB);
		EditText boxC = (EditText)findViewById(R.id.boxC);
		
		textSize = boxA.getTextSize();

		final Numbers mNum = new Numbers();

		//add view text watchers
		boxA.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				mNum.textA = s.toString();
				calculateResult(mNum);
			}
		});

		boxB.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				mNum.textB = s.toString();
				calculateResult(mNum);
			}
		});

		boxC.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				mNum.textC = s.toString();
				calculateResult(mNum);
			}
		});
		
		
		//add view listeners
		View.OnClickListener buttonHandler = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				switch(v.getId()) {
				case R.id.button_prima:
					if(!((ToggleButton)v).isChecked()) {
						((ToggleButton)v).setChecked(true);
					}
					break;
				case R.id.button_neprima:
					if(!((ToggleButton)v).isChecked()) {
						((ToggleButton)v).setChecked(true);
					}
					break;
				}

			}
		};

		CompoundButton.OnCheckedChangeListener checkedHandler = new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton v, boolean isChecked) {
				hideSoftKeyboard(MainActivity.this);
				switch(v.getId()) {
				case R.id.button_prima:
					if(isChecked) {
						animateView(Constants.TYPE_NORMAL, 1f, 0f, 200);
						((ToggleButton)findViewById(R.id.button_neprima)).setChecked(false);
						AppSettings.getInstance(MainActivity.this).setType(Constants.TYPE_NORMAL);
						calculateResult(mNum);
					} 
					break;
				case R.id.button_neprima:
					if(isChecked) {
						animateView(Constants.TYPE_INVERT, 1f, 0f, 200);
						((ToggleButton)findViewById(R.id.button_prima)).setChecked(false);
						AppSettings.getInstance(MainActivity.this).setType(Constants.TYPE_INVERT);
						calculateResult(mNum);
					} 
					break;
				}
			}
		};
		
		ToggleButton button_prima = (ToggleButton)findViewById(R.id.button_prima);
		ToggleButton button_neprima = (ToggleButton)findViewById(R.id.button_neprima);

		button_prima.setOnClickListener(buttonHandler);
		button_neprima.setOnClickListener(buttonHandler);
		button_prima.setOnCheckedChangeListener(checkedHandler);
		button_neprima.setOnCheckedChangeListener(checkedHandler);
		
		switch(AppSettings.getInstance(this).getType()) {
		case Constants.TYPE_NORMAL:
			button_prima.setChecked(true);
			break;
		case Constants.TYPE_INVERT:
			button_neprima.setChecked(true);
			break;
		}

	}
	

	private void calculateResult(Numbers mNum) {
		TextViewPlus result = (TextViewPlus)findViewById(R.id.result);
		Logg.v("type", "type: "+AppSettings.getInstance(this).getType());
		switch(AppSettings.getInstance(this).getType()) {
		case Constants.TYPE_NORMAL:
			if(mNum.textA != null && mNum.textB != null && mNum.textC != null) {
				if(!mNum.textA.equalsIgnoreCase("") && !mNum.textB.equalsIgnoreCase("") && !mNum.textC.equalsIgnoreCase("")) {
					double a = Double.parseDouble(mNum.textA);
					double b = Double.parseDouble(mNum.textB);
					double c = Double.parseDouble(mNum.textC);
					double x = round(((c*b)/a), 1);
					if(x > Double.MAX_VALUE) {
						result.setText("inf.");
					} else {
						result.setText(String.valueOf(x));
					}
				}else {
					result.setText("");
				}
			} else {
				result.setText("");
			}
			break;
		case Constants.TYPE_INVERT:
			if(mNum.textA != null && mNum.textB != null && mNum.textC != null) {
				if(!mNum.textA.equalsIgnoreCase("") && !mNum.textB.equalsIgnoreCase("") && !mNum.textC.equalsIgnoreCase("")) {
					double a = Double.parseDouble(mNum.textA);
					double b = Double.parseDouble(mNum.textB);
					double c = Double.parseDouble(mNum.textC);
					double x = round(((a*b)/c), 1);
					if(x > Double.MAX_VALUE) {
						result.setText("inf.");
					} else {
						result.setText(String.valueOf(x));
					}
				}else {
					result.setText("");
				}
			}else {
				result.setText("");
			}
			break;
		}

	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	public static void hideSoftKeyboard(FragmentActivity activity) {
	    InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
	    inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	public void animateView(final int state, final float start, final float end, final int duration) {
		final ImageView arrow_left = (ImageView)findViewById(R.id.arrow_left);
		final ImageView arrow_right = (ImageView)findViewById(R.id.arrow_right);
		final TextViewPlus formula = (TextViewPlus)findViewById(R.id.formula);
		AnimatorSet set = new AnimatorSet();
		set.playTogether(ObjectAnimator.ofFloat(arrow_left, "alpha", start, end), ObjectAnimator.ofFloat(arrow_right, "alpha", start, end), ObjectAnimator.ofFloat(formula, "alpha", start, end));
		set.setDuration(duration);
		set.addListener(new AnimatorListener() {

			@Override
			public void onAnimationStart(Animator arg0) {

			}

			@Override
			public void onAnimationRepeat(Animator arg0) {

			}

			@Override
			public void onAnimationEnd(Animator arg0) {
				switch(state) {
				case Constants.TYPE_NORMAL:
					formula.setText(getResources().getString(R.string.vzorec_prima));
					arrow_left.setImageResource(R.drawable.pc_arrow_down);
					arrow_right.setImageResource(R.drawable.pc_arrow_down);
					break;
				case Constants.TYPE_INVERT:
					formula.setText(getResources().getString(R.string.vzorec_neprima));
					arrow_left.setImageResource(R.drawable.pc_arrow_down);
					arrow_right.setImageResource(R.drawable.pc_arrow_up);
					break;
				}
				ObjectAnimator.ofFloat(arrow_left, "alpha", end, start).setDuration(duration).start();
				ObjectAnimator.ofFloat(arrow_right, "alpha", end, start).setDuration(duration).start();
				ObjectAnimator.ofFloat(formula, "alpha", end, start).setDuration(duration).start();
			}

			@Override
			public void onAnimationCancel(Animator arg0) {

			}
		});
		set.start();
	}


}
