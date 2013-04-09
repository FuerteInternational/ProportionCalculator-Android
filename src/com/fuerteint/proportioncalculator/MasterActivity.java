package com.fuerteint.proportioncalculator;

import android.support.v4.app.FragmentActivity;

import com.flurry.android.FlurryAgent;

/**
 * @author Vojtech Hrdina
 *
 */
public class MasterActivity extends FragmentActivity {
	
	@Override
	protected void onStart()
	{
		super.onStart();
		FlurryAgent.onStartSession(this, getResources().getString(R.string.flurry_apikey));
	}
	 
	@Override
	protected void onStop()
	{
		super.onStop();		
		FlurryAgent.onEndSession(this);
	}

}
