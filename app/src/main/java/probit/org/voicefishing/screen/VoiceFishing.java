package org.probit.voicefishing.screen;

import org.probit.voicefishing.framework.Screen;
import org.probit.voicefishing.framework.impl.ImplGame;

public class VoiceFishing extends ImplGame{
	@Override
	public Screen getStartScreen() {
		return new LoadingScreen(this);
	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub
		finish();
	}
}
