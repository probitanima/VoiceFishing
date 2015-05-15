package probit.org.voicefishing.framework;

import java.util.List;

import org.probit.voicefishing.framework.Input.PitchEvent;
import org.probit.voicefishing.pitch.PitchDetectorListener;


public interface PitchHandler extends PitchDetectorListener {
	public boolean isSoundOn(int pointer);

	public double getFrequency(int pointer);
	public double getAmp(int pointer);

	public List<PitchEvent> getPitchEvents();
	
	public void onPitchEvent(double frequency, double amp);
	
	public void checkOn();
	public void checkOff();
	public void dispose();
}
