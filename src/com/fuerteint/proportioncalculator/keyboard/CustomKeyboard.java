package com.fuerteint.proportioncalculator.keyboard;

import com.fuerteint.proportioncalculator.R;
import com.fuerteint.proportioncalculator.util.Logg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

public class CustomKeyboard extends Fragment {

	private static final String TAG = "CustomKeyboard";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Logg.v(TAG, "onCreate");
		super.onCreate(savedInstanceState);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
	 * android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Logg.v(TAG, "onCreateView");
		View v = inflater.inflate(R.layout.keyboard, container, false);
		
		View.OnClickListener buttonHandler = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				switch(v.getId()) {
				case R.id.key_0:
					mKeyboardControl.onKeyPressed(getResources().getString(R.string.key_0));
					break;
				case R.id.key_1:
					mKeyboardControl.onKeyPressed(getResources().getString(R.string.key_1));
					break;
				case R.id.key_2:
					mKeyboardControl.onKeyPressed(getResources().getString(R.string.key_2));
					break;
				case R.id.key_3:
					mKeyboardControl.onKeyPressed(getResources().getString(R.string.key_3));
					break;
				case R.id.key_4:
					mKeyboardControl.onKeyPressed(getResources().getString(R.string.key_4));
					break;
				case R.id.key_5:
					mKeyboardControl.onKeyPressed(getResources().getString(R.string.key_5));
					break;
				case R.id.key_6:
					mKeyboardControl.onKeyPressed(getResources().getString(R.string.key_6));
					break;
				case R.id.key_7:
					mKeyboardControl.onKeyPressed(getResources().getString(R.string.key_7));
					break;
				case R.id.key_8:
					mKeyboardControl.onKeyPressed(getResources().getString(R.string.key_8));
					break;
				case R.id.key_9:
					mKeyboardControl.onKeyPressed(getResources().getString(R.string.key_9));
					break;
				case R.id.key_c:
					mKeyboardControl.onKeyClearAll();
					break;
				case R.id.key_back:
					mKeyboardControl.onKeyDeleteLastLetter();
					break;
				case R.id.key_plusminus:
					mKeyboardControl.onKeyChangePolarity();
					break;
				case R.id.key_dot:
					mKeyboardControl.onKeyPressed(getResources().getString(R.string.key_dot));
					break;
				}
				
			}
		};
		
		View.OnTouchListener touchHandler = new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch(v.getId()) {
				case R.id.keyboardBox:
					break;
				}
				return true;
			}
		};
		
		Button key_1 = (Button)v.findViewById(R.id.key_1);
		Button key_2 = (Button)v.findViewById(R.id.key_2);
		Button key_3 = (Button)v.findViewById(R.id.key_3);
		Button key_4 = (Button)v.findViewById(R.id.key_4);
		Button key_5 = (Button)v.findViewById(R.id.key_5);
		Button key_6 = (Button)v.findViewById(R.id.key_6);
		Button key_7 = (Button)v.findViewById(R.id.key_7);
		Button key_8 = (Button)v.findViewById(R.id.key_8);
		Button key_9 = (Button)v.findViewById(R.id.key_9);
		Button key_0 = (Button)v.findViewById(R.id.key_0);
		
		RelativeLayout key_C = (RelativeLayout)v.findViewById(R.id.key_c);
		RelativeLayout key_Back = (RelativeLayout)v.findViewById(R.id.key_back);
		RelativeLayout key_PlusMinus = (RelativeLayout)v.findViewById(R.id.key_plusminus);
		RelativeLayout key_Dot = (RelativeLayout)v.findViewById(R.id.key_dot);
		RelativeLayout keyboardBox = (RelativeLayout)v.findViewById(R.id.keyboardBox);
		
		key_1.setOnClickListener(buttonHandler);
		key_2.setOnClickListener(buttonHandler);
		key_3.setOnClickListener(buttonHandler);
		key_4.setOnClickListener(buttonHandler);
		key_5.setOnClickListener(buttonHandler);
		key_6.setOnClickListener(buttonHandler);
		key_7.setOnClickListener(buttonHandler);
		key_8.setOnClickListener(buttonHandler);
		key_9.setOnClickListener(buttonHandler);
		key_0.setOnClickListener(buttonHandler);
		key_C.setOnClickListener(buttonHandler);
		key_Back.setOnClickListener(buttonHandler);
		key_PlusMinus.setOnClickListener(buttonHandler);
		key_Dot.setOnClickListener(buttonHandler);
		
		keyboardBox.setOnTouchListener(touchHandler);

		return v;
	}

	private KeyboardListener mKeyboardControl;

	public void setKeyboardButtonListener(
			KeyboardListener listener) {
		this.mKeyboardControl = listener;
	}


	public interface KeyboardListener {

		public void onKeyPressed(String key);
		public void onKeyDeleteLastLetter();
		public void onKeyClearAll();
		public void onKeyChangePolarity();

	}
	

}
