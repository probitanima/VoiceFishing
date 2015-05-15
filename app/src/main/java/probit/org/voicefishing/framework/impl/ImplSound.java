package org.probit.voicefishing.framework.impl;

import org.probit.voicefishing.framework.Sound;

import android.media.SoundPool;

public class ImplSound implements Sound{
	int soundId;
	SoundPool soundPool;
	
	public ImplSound(SoundPool soundPool, int soundId) {
		this.soundId = soundId;
		this.soundPool = soundPool;
	}

	@Override
	public void play(float volume) {
		soundPool.play(soundId, volume, volume, 0, 0, 1);
	}

	@Override
	public void dispose() {
		soundPool.unload(soundId);
	}
}
