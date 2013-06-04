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

	private static final int MODE_1 = 0;
	private static final int MODE_2 = 1;
	private static final int MODE_3 = 2;
	private static final int MODE_4 = 3;

	private int MODE = MODE_1;

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
		setX(buttonD);

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
				case R.id.boxB:
				case R.id.boxC:
				case R.id.boxD:
					showKeyboard(v.getId());
					break;
				}

			}
		};

		View.OnTouchListener touchHandler = new View.OnTouchListener() {

			@Override
			public boolean onTouch(final View v, MotionEvent event) {
				switch(v.getId()) {
				case R.id.boxA:
				case R.id.boxB:
				case R.id.boxC:
				case R.id.boxD:
					showKeyboard(v.getId());
					break;
				case R.id.buttonA:
				case R.id.buttonB:
				case R.id.buttonC:
				case R.id.buttonD:
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
							setX(v);
							return true;
						}
					});
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
		if(checkNull(mNum)) {
				double x = calculate(mNum);
				if(x > Double.MAX_VALUE) {
					result.setText("inf.");
				} else {
					if(x > 1e4 || x < 1e-4) {
						result.setText(new DecimalFormat("#.#####E0").format(x));
					} else {
						result.setText(new DecimalFormat("#.#####").format(x));
					}
				}
			
		} else {
			result.setText("");
		}

	}
	
	private boolean checkNull(Numbers mNum) {
		switch(MODE) {
		case MODE_1:
			return mNum.textB != null && !mNum.textB.equalsIgnoreCase("") && mNum.textC != null && !mNum.textC.equalsIgnoreCase("") && mNum.textD != null && !mNum.textD.equalsIgnoreCase("") ? true : false;
		case MODE_2:
			return mNum.textA != null && !mNum.textA.equalsIgnoreCase("") && mNum.textC != null && !mNum.textC.equalsIgnoreCase("") && mNum.textD != null && !mNum.textD.equalsIgnoreCase("") ? true : false;
		case MODE_3:
			return mNum.textA != null && !mNum.textA.equalsIgnoreCase("") && mNum.textB != null && !mNum.textB.equalsIgnoreCase("") && mNum.textD != null && !mNum.textD.equalsIgnoreCase("") ? true : false;
		case MODE_4:
			return mNum.textA != null && !mNum.textA.equalsIgnoreCase("") && mNum.textB != null && !mNum.textB.equalsIgnoreCase("") && mNum.textC != null && !mNum.textC.equalsIgnoreCase("") ? true : false;
		default: return false;
		}
	}


	private double calculate(Numbers mNum) {
		double a = mNum.textA != null && !mNum.textA.equalsIgnoreCase("") ? Double.valueOf(mNum.textA) : 0;
		double b = mNum.textB != null && !mNum.textB.equalsIgnoreCase("") ? Double.valueOf(mNum.textB) : 0;
		double c = mNum.textC != null && !mNum.textC.equalsIgnoreCase("") ? Double.valueOf(mNum.textC) : 0;
		double d = mNum.textD != null && !mNum.textD.equalsIgnoreCase("") ? Double.valueOf(mNum.textD) : 0;
		double x = 0;
		int calculateType = AppSettings.getInstance(this).getType();
		switch(MODE) {
		case MODE_1:
			x = calculateType == Constants.TYPE_NORMAL ? ((b*c)/d) : ((c*d)/b);
			break;
		case MODE_2:
			x = calculateType == Constants.TYPE_NORMAL ? ((a*d)/c) : ((c*d)/a);
			break;
		case MODE_3:
			x = calculateType == Constants.TYPE_NORMAL ? ((a*d)/b) : ((a*b)/d);
			break;
		case MODE_4:
			x = calculateType == Constants.TYPE_NORMAL ? ((b*c)/a) : ((a*b)/c);
			break;
		}

		return x;
	}


	public void animateView(final int state, final float start, final float end, final int duration) {
		final ImageView arrow_left = (ImageView)findViewById(R.id.arrow_left);
		final ImageView arrow_right = (ImageView)findViewById(R.id.arrow_right);
		final TextViewPlus formula = (TextViewPlus)findViewById(R.id.formula);
		final TextViewPlus formulaTitle = (TextViewPlus)findViewById(R.id.formula_title);
		AnimatorSet set = new AnimatorSet();
		set.playTogether(ObjectAnimator.ofFloat(arrow_left, "alpha", start, end), ObjectAnimator.ofFloat(arrow_right, "alpha", start, end), ObjectAnimator.ofFloat(formula, "alpha", start, end), ObjectAnimator.ofFloat(formulaTitle, "alpha", start, end));
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
					formula.setText(getFormula());
					formulaTitle.setText(getFormulaTitle());
					arrow_left.setImageResource(R.drawable.pc_arrow_down);
					arrow_right.setImageResource(R.drawable.pc_arrow_down);
					break;
				case Constants.TYPE_INVERT:
					formula.setText(getFormula());
					formulaTitle.setText(getFormulaTitle());
					arrow_left.setImageResource(R.drawable.pc_arrow_down);
					arrow_right.setImageResource(R.drawable.pc_arrow_up);
					break;
				}
				ObjectAnimator.ofFloat(arrow_left, "alpha", end, start).setDuration(duration).start();
				ObjectAnimator.ofFloat(arrow_right, "alpha", end, start).setDuration(duration).start();
				ObjectAnimator.ofFloat(formula, "alpha", end, start).setDuration(duration).start();
				ObjectAnimator.ofFloat(formulaTitle, "alpha", end, start).setDuration(duration).start();
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

		switch(v.getId()) {
		case R.id.buttonA:
			((TextViewPlus)findViewById(R.id.textHintA)).setTextAppearance(this, R.style.calculate_text_numbers_X);
			((TextViewPlus)findViewById(R.id.textHintA)).setText(getResources().getString(R.string.x));
			((EditText)findViewById(R.id.boxA)).setEnabled(false);
			MODE = MODE_1;
			break;
		case R.id.buttonB:
			((TextViewPlus)findViewById(R.id.textHintB)).setTextAppearance(this, R.style.calculate_text_numbers_X);
			((TextViewPlus)findViewById(R.id.textHintB)).setText(getResources().getString(R.string.x));
			((EditText)findViewById(R.id.boxB)).setEnabled(false);
			MODE = MODE_2;
			break;
		case R.id.buttonC:
			((TextViewPlus)findViewById(R.id.textHintC)).setTextAppearance(this, R.style.calculate_text_numbers_X);
			((TextViewPlus)findViewById(R.id.textHintC)).setText(getResources().getString(R.string.x));
			((EditText)findViewById(R.id.boxC)).setEnabled(false);
			MODE = MODE_3;
			break;
		case R.id.buttonD:
			((TextViewPlus)findViewById(R.id.textHintD)).setTextAppearance(this, R.style.calculate_text_numbers_X);
			((TextViewPlus)findViewById(R.id.textHintD)).setText(getResources().getString(R.string.x));
			((EditText)findViewById(R.id.boxD)).setEnabled(false);
			MODE = MODE_4;
			break;
		}

		((TextViewPlus)v).setText(getResources().getString(R.string.x));
		((TextViewPlus)findViewById(R.id.formula)).setText(getFormula());
		((TextViewPlus)findViewById(R.id.formula_title)).setText(getFormulaTitle());

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

		boxA.setEnabled(true);
		boxB.setEnabled(true);
		boxC.setEnabled(true);
		boxD.setEnabled(true);

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


	
	public String getFormula() {
		int calculateType = AppSettings.getInstance(this).getType();
		switch(MODE) {
		case MODE_1:
		return calculateType == Constants.TYPE_NORMAL ? getResources().getString(R.string.formula_normal_mode_1) : getResources().getString(R.string.formula_invert_mode_1);
		case MODE_2:
		return calculateType == Constants.TYPE_NORMAL ? getResources().getString(R.string.formula_normal_mode_2) : getResources().getString(R.string.formula_invert_mode_2);
		case MODE_3:
		return calculateType == Constants.TYPE_NORMAL ? getResources().getString(R.string.formula_normal_mode_3) : getResources().getString(R.string.formula_invert_mode_3);
		case MODE_4:
		return calculateType == Constants.TYPE_NORMAL ? getResources().getString(R.string.formula_normal_mode_4) : getResources().getString(R.string.formula_invert_mode_4);
		default: return "";
		}
	}
	
	public String getFormulaTitle() {
		int calculateType = AppSettings.getInstance(this).getType();
		switch(MODE) {
		case MODE_1:
		return calculateType == Constants.TYPE_NORMAL ? getResources().getString(R.string.formula_title_normal_mode_1) : getResources().getString(R.string.formula_title_invert_mode_1);
		case MODE_2:
		return calculateType == Constants.TYPE_NORMAL ? getResources().getString(R.string.formula_title_normal_mode_2) : getResources().getString(R.string.formula_title_invert_mode_2);
		case MODE_3:
		return calculateType == Constants.TYPE_NORMAL ? getResources().getString(R.string.formula_title_normal_mode_3) : getResources().getString(R.string.formula_title_invert_mode_3);
		case MODE_4:
		return calculateType == Constants.TYPE_NORMAL ? getResources().getString(R.string.formula_title_normal_mode_4) : getResources().getString(R.string.formula_title_invert_mode_4);
		default: return "";
		}
	}

}
