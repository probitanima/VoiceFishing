package org.probit.voicefishing.framework.impl;

import java.util.ArrayList;
import java.util.List;

import org.probit.voicefishing.framework.Input.PitchEvent;
import org.probit.voicefishing.framework.PitchHandler;
import org.probit.voicefishing.pitch.PitchDetector;
import org.probit.voicefishing.util.Pool;
import org.probit.voicefishing.util.Pool.PoolObjectFactory;

import android.util.Log;

public class ImplPitchHandler implements PitchHandler {

	double frequency;
	double amp;
	
	double threshold = 10000;

	Pool<PitchEvent> pitchEventPool;
	List<PitchEvent> pitchEvents = new ArrayList<PitchEvent>();
	List<PitchEvent> pitchEventsBuffer = new ArrayList<PitchEvent>();

	PitchDetector pd;

	public ImplPitchHandler() {
		
		PoolObjectFactory<PitchEvent> factory = new PoolObjectFactory<PitchEvent>() {
			@Override
			public PitchEvent createObject() {
				return new PitchEvent();
			}
		};

		pitchEventPool = new Pool<PitchEvent>(factory, 100);

		// Sound PitchDetector-----------

		pd = new PitchDetector(this);
		pd.start();
	}

	@Override
	public void onPitchEvent(double frequency, double amp) {
		synchronized (this) {
			PitchEvent pitchEvent = pitchEventPool.newObject();
			
			pitchEvent.frequency = frequency;
			pitchEvent.amp = amp;
			
			pitchEventsBuffer.add(pitchEvent);
		}
	}

	@Override
	public boolean isSoundOn(int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getFrequency(int pointer) {
		synchronized (this) {
			return frequency;
		}
	}

	@Override
	public double getAmp(int pointer) {
		synchronized (this) {
			return amp;
		}
	}
	
	@Override
	public List<PitchEvent> getPitchEvents() {
		synchronized (this) {

			int len = pitchEvents.size();

			for (int i = 0; i < len; i++)
				pitchEventPool.free(pitchEvents.get(i));
			
			pitchEvents.clear();
			pitchEvents.addAll(pitchEventsBuffer);
			pitchEventsBuffer.clear();

			return pitchEvents;
		}
	}

	@Override
	public void dispose() {
		pd.dispose();
	}

	@Override
	public void checkOn() {
		pd.checkOn();
	}

	@Override
	public void checkOff() {
		pd.checkOff();		
	}
}
