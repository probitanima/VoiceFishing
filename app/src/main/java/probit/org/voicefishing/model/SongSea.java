package org.probit.voicefishing.model;

import org.probit.voicefishing.util.Settings;

import android.util.Log;

public class SongSea extends Sea {

	@Override
	public int setFisherManLocation(double frequency) {
		
		if (frequency < Settings.NoteBoundary.get(0)) {
			fisherMan.setLocation_des(Settings.noteCritPosition.get(0));
			return 0;
		}
		
		for (int i = 0; i < 35; i++) {
			if (Settings.NoteBoundary.get(i) <= frequency
					&& frequency < Settings.NoteBoundary.get(i + 1)) {
				fisherMan.setLocation_des(Settings.noteCritPosition.get(i));
				return i;
			}
		}
		
		return -1;
	}
}
