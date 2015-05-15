package org.probit.voicefishing.model;

import org.probit.voicefishing.util.Assets;

public class BackGround {
	public float bgTime1; // 배경 움직이기 위한 변수
	public float bgTime2; // 배경 움직이기 위한 변수
	
	public int seenIndex;
	public int seenVarious;

	public double seenRate;
	public double bgDesHeight;
	
	public BackGround() {
		bgTime1 = Assets.bg_game1.getBitmapWidth();
		bgTime2 = 0;

		bgDesHeight = 100 * 2 / 3;
	}

	public void checkSeen() {
		if (-1 < seenVarious && seenVarious <= 11)
			if (seenIndex != 0)
				setSeen(0);
		if (12 < seenVarious && seenVarious <= 23)
			if (seenIndex != 1)
				setSeen(1);
		if (24 < seenVarious && seenVarious <= 35)
			if (seenIndex != 2)
				setSeen(2);
	}

	private void setSeen(int i) {
		seenIndex = i;
		bgDesHeight = 100 * (2 - seenIndex) / 3;
	}

	public void setSeenRate() {
		
		bgTime1 += 5;
		bgTime2 += 5;
		
		seenRate = seenRate + 0.03 * (bgDesHeight - seenRate);

		if (bgTime1 > Assets.bg_game1.getBitmapWidth())
			bgTime1 = -Assets.bg_game1.getBitmapWidth();
		if (bgTime2 > Assets.bg_game2.getBitmapWidth())
			bgTime2 = -Assets.bg_game2.getBitmapWidth();
	}
}
