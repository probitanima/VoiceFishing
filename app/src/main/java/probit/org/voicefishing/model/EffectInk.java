package org.probit.voicefishing.model;

public class EffectInk extends MyTimer {
	
	public EffectInk () {
		tickTime = 0.5f;
	}
	
	@Override
	public void setMode(int newMode) {
		super.setMode(newMode);
	}

	@Override
	public void doSomething() {
		if (mode != 0) {

			if (mode <= 8)
				mode++;
			else
				mode = 1;
		}
	}
}
