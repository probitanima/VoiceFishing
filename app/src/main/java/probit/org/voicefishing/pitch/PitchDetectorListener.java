package org.probit.voicefishing.pitch;

public interface PitchDetectorListener {
	
	void onPitchEvent(double frequency, double amp);
}
