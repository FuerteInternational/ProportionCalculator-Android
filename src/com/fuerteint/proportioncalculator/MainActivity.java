package com.fuerteint.proportioncalculator;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GestureDetectorCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

import com.fuerteint.proportioncalculator.data.AppSettings;
import com.fuerteint.proportioncalculator.data.Constants;
import com.fuerteint.proportioncalculator.data.Numbers;
import com.fuerteint.proportioncalculator.keyboard.CustomKeyboard;
import com.fuerteint.proportioncalculator.keyboard.CustomKeyboard.KeyboardListener;
import com.fuerteint.proportioncalculator.util.DisplayUtil;
import com.fuerteint.proportioncalculator.util.Logg;
import com.fuerteint.proportioncalculator.views.TextViewPlus;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * @author Vojtech Hrdina
 *
 */
public class MainActivity extends MasterActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		RelativeLayout mainView = (RelativeLayout)findViewById(R.id.mainView);
		mainView.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				hideKeyboard();
				return false;
			}
		});

		initActiveViews();
	}

	@Override
	public void onResume() {
		super.onResume();
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
				WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
	}


	private void initActiveViews() {
		final EditText boxA = (EditText)findViewById(R.id.boxA);
		final EditText boxB = (EditText)findViewById(R.id.boxB);
		final EditText boxC = (EditText)findViewById(R.id.boxC);
		final EditText boxD = (EditText)findViewById(R.id.boxD);


		final Numbers mNum = new Numbers();
		final TextViewPlus textHintA = (TextViewPlus)findViewById(R.id.textHintA);
		final TextViewPlus textHintB = (TextViewPlus)findViewById(R.id.textHintB);
		final TextViewPlus textHintC = (TextViewPlus)findViewById(R.id.textHintC);
		final TextViewPlus textHintD = (TextViewPlus)findViewById(R.id.textHintD);


		TextViewPlus buttonA = (TextViewPlus)findViewById(R.id.buttonA);
		TextViewPlus buttonB = (TextViewPlus)findViewById(R.id.buttonB);
		TextViewPlus buttonC = (TextViewPlus)findViewById(R.id.buttonC);
		TextViewPlus buttonD = (TextViewPlus)findViewById(R.id.buttonD);

		final int paddingDpHint  = DisplayUtil.toPixels(this, Constants.PADDING_HINT);
		final int paddingDpNoHint  = DisplayUtil.toPixels(this, Constants.PADDING_NO_HINT);

		boxA.setPadding(0,0,paddingDpHint,0);
		boxB.setPadding(0,0,paddingDpHint,0);
		boxC.setPadding(0,0,paddingDpHint,0);
		boxD.setPadding(0,0,paddingDpHint,0);

		//add view text watchers
		boxA.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(count > 0) {
					if(textHintA.getVisibility() != View.GONE) {
						boxA.setPadding(paddingDpNoHint,0,paddingDpNoHint,0);
						textHintA.setVisibility(View.GONE);
					}
				} else {
					boxA.setPadding(0,0,paddingDpHint,0);
					textHintA.setVisibility(View.VISIBLE);
				}
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
				if(count > 0) {
					if(textHintB.getVisibility() != View.GONE) {
						boxB.setPadding(paddingDpNoHint,0,paddingDpNoHint,0);
						textHintB.setVisibility(View.GONE);
					}
				} else {
					boxB.setPadding(0,0,paddingDpHint,0);
					textHintB.setVisibility(View.VISIBLE);
				}
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
				if(count > 0) {
					if(textHintC.getVisibility() != View.GONE) {
						boxC.setPadding(paddingDpNoHint,0,paddingDpNoHint,0);
						textHintC.setVisibility(View.GONE);
					}
				} else {
					boxC.setPadding(0,0,paddingDpHint,0);
					textHintC.setVisibility(View.VISIBLE);
				}
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

		boxD.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(count > 0) {
					if(textHintD.getVisibility() != View.GONE) {
						boxC.setPadding(paddingDpNoHint,0,paddingDpNoHint,0);
						textHintD.setVisibility(View.GONE);
					}
				} else {
					boxC.setPadding(0,0,paddingDpHint,0);
					textHintD.setVisibility(View.VISIBLE);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				mNum.textD = s.toString();
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
				case R.id.boxA:
				case R.id.boxB:
				case R.id.boxC:
				case R.id.boxD:
					showKeyboard(v.getId());
					break;
				}

			}
		};
		
		final GestureDetectorCompat gd = new GestureDetectorCompat(this, new OnGestureListener() {

			@Override
			public boolean onSingleTapUp(MotionEvent e) {
				//setX(findViewById(e.getSource()));
				Logg.v("GESTURE DETECTOR", "onSingleTapUp");
				return false;
			}

			@Override
			public void onShowPress(MotionEvent e) {
			}

			@Override
			public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
					float distanceY) {
				return false;
			}

			@Override
			public void onLongPress(MotionEvent e) {
			}

			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
					float velocityY) {
				return false;
			}

			@Override
			public boolean onDown(MotionEvent e) {
				return true;
			}
		});

		gd.setOnDoubleTapListener(new OnDoubleTapListener() {

			@Override
			public boolean onSingleTapConfirmed(MotionEvent e) {
				return false;
			}

			@Override
			public boolean onDoubleTapEvent(MotionEvent e) {
				Logg.v("GESTURE DETECTOR", "onDoubleTapEvent");
				return false;
			}

			@Override
			public boolean onDoubleTap(MotionEvent e) {
				Logg.v("GESTURE DETECTOR", "onDoubleTap");
				return true;
			}
		});

		CompoundButton.OnCheckedChangeListener checkedHandler = new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton v, boolean isChecked) {
				hideKeyboard();
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

		View.OnFocusChangeListener focusHandler = new View.OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				switch(v.getId()) {
				case R.id.boxA:
					showKeyboard(v.getId());
					break;
				case R.id.boxB:
					showKeyboard(v.getId());
					break;
				case R.id.boxC:
					showKeyboard(v.getId());
					break;
				case R.id.boxD:
					showKeyboard(v.getId());
					break;
				}

			}
		};

		View.OnTouchListener touchHandler = new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch(v.getId()) {
				case R.id.boxA:
					Logg.v("TOUCH", "selection start: "+((EditText)v).getSelectionStart());
					Logg.v("TOUCH", "selection end: "+((EditText)v).getSelectionEnd());
					break;
				case R.id.boxB:
					showKeyboard(v.getId());
					Logg.v("TOUCH", "selection start: "+((EditText)v).getSelectionStart());
					Logg.v("TOUCH", "selection end: "+((EditText)v).getSelectionEnd());
					break;
				case R.id.boxC:
					showKeyboard(v.getId());
					Logg.v("TOUCH", "selection start: "+((EditText)v).getSelectionStart());
					Logg.v("TOUCH", "selection end: "+((EditText)v).getSelectionEnd());
					break;
				case R.id.boxD:
					showKeyboard(v.getId());
					Logg.v("TOUCH", "selection start: "+((EditText)v).getSelectionStart());
					Logg.v("TOUCH", "selection end: "+((EditText)v).getSelectionEnd());
					break;
				case R.id.buttonA:
					return gd.onTouchEvent(event);
				case R.id.buttonB:
					return gd.onTouchEvent(event);
				case R.id.buttonC:
					return gd.onTouchEvent(event);
				case R.id.buttonD:
					return gd.onTouchEvent(event);
				}
				return false;
			}

		};

		ToggleButton button_prima = (ToggleButton)findViewById(R.id.button_prima);
		ToggleButton button_neprima = (ToggleButton)findViewById(R.id.button_neprima);

		button_prima.setOnClickListener(buttonHandler);
		button_neprima.setOnClickListener(buttonHandler);
		boxA.setOnClickListener(buttonHandler);
		boxB.setOnClickListener(buttonHandler);
		boxC.setOnClickListener(buttonHandler);
		boxD.setOnClickListener(buttonHandler);

		button_prima.setOnCheckedChangeListener(checkedHandler);
		button_neprima.setOnCheckedChangeListener(checkedHandler);

		boxA.setOnFocusChangeListener(focusHandler);
		boxB.setOnFocusChangeListener(focusHandler);
		boxC.setOnFocusChangeListener(focusHandler);
		boxD.setOnFocusChangeListener(focusHandler);

		boxA.setOnTouchListener(touchHandler);
		boxB.setOnTouchListener(touchHandler);
		boxC.setOnTouchListener(touchHandler);
		boxD.setOnTouchListener(touchHandler);
		
		buttonA.setOnTouchListener(touchHandler);
		buttonB.setOnTouchListener(touchHandler);
		buttonC.setOnTouchListener(touchHandler);
		buttonD.setOnTouchListener(touchHandler);

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
					double x = ((c*b)/a);
					if(x > Double.MAX_VALUE) {
						result.setText("inf.");
					} else {
						if(x > 1e4 || x < 1e-4) {
							result.setText(new DecimalFormat("#.#####E0").format(x));
						} else {
							result.setText(new DecimalFormat("#.#####").format(x));
						}

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
					double x = ((a*b)/c);
					if(x > Double.MAX_VALUE) {
						result.setText("inf.");
					} else {
						if(x > 1e4 || x < 1e-4) {
							result.setText(new DecimalFormat("#.#####E0").format(x));
						} else {
							result.setText(new DecimalFormat("#.#####").format(x));
						}

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

	public void showKeyboard(int resId) {

		final EditText editText = (EditText)findViewById(resId);

		CustomKeyboard f = new CustomKeyboard();
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		//ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
		ft.replace(R.id.keyboardContainer, f, Constants.FRAGMENT_KEYBOARD);
		f.setKeyboardButtonListener(new KeyboardListener() {

			@Override
			public void onKeyPressed(String key) {
				String text = editText.getText().toString();
				int selectionStart = editText.getSelectionStart();

				if(key.equalsIgnoreCase(getResources().getString(R.string.key_dot))) {
					if(compareText(text, "\\"+getResources().getString(R.string.key_dot))) {

					} else if(text.length() > 0 && selectionStart != 0) {
						if(selectionStart != text.length()) {
							editText.setText(editText.getText().insert(selectionStart, key));
							editText.setSelection(selectionStart+1);
						} else {
							editText.setText(editText.getText().append(key));
							editText.setSelection(selectionStart+1);
						}
					}
				} else {
					if(selectionStart != text.length()) {
						editText.setText(editText.getText().insert(selectionStart, key));
						editText.setSelection(selectionStart+1);
					} else {
						editText.setText(editText.getText().append(key));
						editText.setSelection(selectionStart+1);
					}
				}
			}

			@Override
			public void onKeyDeleteLastLetter() {
				String text = editText.getText().toString();
				int selectionStart = editText.getSelectionStart();
				int selectionEnd = editText.getSelectionEnd();
				if(selectionStart > 0) {
					if(text.length() > selectionEnd) {
						text = text.substring(0, selectionStart-1) + text.substring(selectionEnd);
					} else {
						text = text.substring(0, text.length()-1);
					}
					editText.setText(text);
					editText.setSelection(selectionStart-1);
				}
			}

			@Override
			public void onKeyClearAll() {
				editText.setText("");
			}

			@Override
			public void onKeyChangePolarity() {
				String text = editText.getText().toString();
				int selectionStart = editText.getSelectionStart();
				if(text.length() > 0) {
					if(text.startsWith("-")) {
						text = text.substring(1, text.length());
						editText.setText(text);
						editText.setSelection(selectionStart-1);
					} else {
						text = editText.getText().insert(0, "-").toString();
						editText.setText(text);
						editText.setSelection(selectionStart+1);
					}
				}
			}

		});
		ft.commitAllowingStateLoss();
	}

	public void hideKeyboard() {
		CustomKeyboard f = (CustomKeyboard)getSupportFragmentManager().findFragmentByTag(Constants.FRAGMENT_KEYBOARD);
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		if(f != null) {
			ft.remove(f);
		}
		ft.commitAllowingStateLoss();
	}

	@Override
	public void onBackPressed() {
		final CustomKeyboard fragment = (CustomKeyboard) getSupportFragmentManager().findFragmentByTag(Constants.FRAGMENT_KEYBOARD);
		if (fragment != null) { 
			hideKeyboard();
		} else {
			super.onBackPressed();
		}
	}

	public static boolean compareText(String text, String pattern) {
		Pattern patternText = Pattern.compile(pattern);
		Matcher findText = patternText.matcher(text);
		if(findText.find()) {
			return true;
		} else {
			return false;
		}
	}

	public void setX(View v) {
		restoreButtonsBoxs();
		((TextViewPlus)v).setText(getResources().getString(R.string.x));

		switch(v.getId()) {
		case R.id.buttonA:
			((TextViewPlus)findViewById(R.id.textHintA)).setTextAppearance(this, R.style.calculate_text_numbers_X);
			break;
		case R.id.buttonB:
			((TextViewPlus)findViewById(R.id.textHintB)).setTextAppearance(this, R.style.calculate_text_numbers_X);
			break;
		case R.id.buttonC:
			((TextViewPlus)findViewById(R.id.textHintC)).setTextAppearance(this, R.style.calculate_text_numbers_X);
			break;
		case R.id.buttonD:
			((TextViewPlus)findViewById(R.id.textHintD)).setTextAppearance(this, R.style.calculate_text_numbers_X);
			break;
		}
	}

	private void restoreButtonsBoxs() {
		TextViewPlus buttonA = (TextViewPlus)findViewById(R.id.buttonA);
		TextViewPlus buttonB = (TextViewPlus)findViewById(R.id.buttonB);
		TextViewPlus buttonC = (TextViewPlus)findViewById(R.id.buttonC);
		TextViewPlus buttonD = (TextViewPlus)findViewById(R.id.buttonD);

		TextViewPlus textHintA = (TextViewPlus)findViewById(R.id.textHintA);
		TextViewPlus textHintB = (TextViewPlus)findViewById(R.id.textHintB);
		TextViewPlus textHintC = (TextViewPlus)findViewById(R.id.textHintC);
		TextViewPlus textHintD = (TextViewPlus)findViewById(R.id.textHintD);

		EditText boxA = (EditText)findViewById(R.id.boxA);
		EditText boxB = (EditText)findViewById(R.id.boxB);
		EditText boxC = (EditText)findViewById(R.id.boxC);
		EditText boxD = (EditText)findViewById(R.id.boxD);

		String letterA = getResources().getString(R.string.a);
		String letterB = getResources().getString(R.string.b);
		String letterC = getResources().getString(R.string.c);
		String letterD = getResources().getString(R.string.d);

		boxA.setText("");
		boxB.setText("");
		boxC.setText("");
		boxD.setText("");

		buttonA.setText(letterA);
		buttonB.setText(letterB);
		buttonC.setText(letterC);
		buttonD.setText(letterD);

		textHintA.setText(letterA);
		textHintB.setText(letterB);
		textHintC.setText(letterC);
		textHintD.setText(letterD);

		textHintA.setTextAppearance(this, R.style.calculate_text_numbers_hint);
		textHintB.setTextAppearance(this, R.style.calculate_text_numbers_hint);
		textHintC.setTextAppearance(this, R.style.calculate_text_numbers_hint);
		textHintD.setTextAppearance(this, R.style.calculate_text_numbers_hint);
	}


	private String fromula;

	public void setFormula(String formula) {
		this.fromula = formula;
	}

	public String getFormula() {
		return this.fromula;
	}

}
