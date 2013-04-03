package com.fuerteint.proportioncalculator;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class MainActivity extends MasterActivity {
	
	private static final int UP = 0;
	private static final int DOWN = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		View.OnClickListener buttonHandler = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				switch(v.getId()) {
				case R.id.button_prima:
					break;
				case R.id.button_neprima:
					break;
				}

			}
		};

		CompoundButton.OnCheckedChangeListener checkedHandler = new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton v, boolean isChecked) {
				switch(v.getId()) {
				case R.id.button_prima:
					if(isChecked) {
						setArrow(UP);
					} else {

					}
					break;
				case R.id.button_neprima:
					if(isChecked) {
						setArrow(DOWN);
					} else {

					}
					break;
				}
			}
		};

		ToggleButton button_prima = (ToggleButton)findViewById(R.id.button_prima);
		ToggleButton button_neprima = (ToggleButton)findViewById(R.id.button_neprima);
		
		button_prima.setChecked(true);

		button_prima.setOnClickListener(buttonHandler);
		button_neprima.setOnClickListener(buttonHandler);
		button_prima.setOnCheckedChangeListener(checkedHandler);
		button_neprima.setOnCheckedChangeListener(checkedHandler);
	}
	
	private void setArrow(int state) {
		ImageView arrow_left = (ImageView)findViewById(R.id.arrow_left);
		ImageView arrow_right = (ImageView)findViewById(R.id.arrow_right);
		switch(state) {
		case UP:
			arrow_left.setImageResource(R.drawable.pc_arrow_up);
			arrow_right.setImageResource(R.drawable.pc_arrow_up);
			break;
		case DOWN:
			arrow_left.setImageResource(R.drawable.pc_arrow_down);
			arrow_right.setImageResource(R.drawable.pc_arrow_up);
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
