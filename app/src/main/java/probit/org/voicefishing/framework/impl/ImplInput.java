package org.probit.voicefishing.framework.impl;

import java.util.List;

import org.probit.voicefishing.framework.Input;
import org.probit.voicefishing.framework.PitchHandler;
import org.probit.voicefishing.framework.TouchHandler;

import android.content.Context;
import android.util.Log;
import android.view.View;

public class ImplInput implements Input{

	TouchHandler touchHandler;
	PitchHandler pitchHandler;
	
	public ImplInput(Context context, View view, float scaleX, float scaleY) {
		touchHandler = new ImplTouchHandler(view, scaleX, scaleY);
		pitchHandler = new ImplPitchHandler();
	}
	
	@Override
	public boolean isKeyPressed(int keyCode) {
		return false;
	}

	@Override
	public boolean isTouchDown(int pointer) {
		return touchHandler.isTouchDown(pointer);
	}

	@Override
	public int getTouchX(int pointer) {
		return touchHandler.getTouchX(pointer);
	}

	@Override
	public int getTouchY(int pointer) {
		return touchHandler.getTouchY(pointer);
	}

	@Override
	public List<KeyEvent> getKeyEvent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TouchEvent> getTouchEvent() {
		return touchHandler.getTouchEvents();
	}

	@Override
	public double getPitch(int pointer) {
		return pitchHandler.getFrequency(pointer);
	}

	@Override
	public List<PitchEvent> getPitchEvent() {
		return pitchHandler.getPitchEvents();
	}

	@Override
	public void disposePitch() {
		pitchHandler.dispose();
	}

	@Override
	public void checkPitchOn() {
		pitchHandler.checkOn();
	}

	@Override
	public void checkPitchOff() {
		pitchHandler.checkOff();
	}
	
}
