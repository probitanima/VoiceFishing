package org.probit.voicefishing.model.impl.mop;

import org.probit.voicefishing.framework.Pixmap;
import org.probit.voicefishing.model.Mop;
import org.probit.voicefishing.util.Assets;

public class MopFishRain extends Mop{

	public MopFishRain(int y, int order) {
		super(y, order);
	}
	@Override
	public Pixmap getImgAsset() {
		return Assets.mop[3];
	}
	@Override
	public int setScore() {
		// TODO Auto-generated method stub
		return 15;
	}
	@Override
	public int setCoin() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int setBonusGauge() {
		// TODO Auto-generated method stub
		return 1;
	}
	@Override
	public int setHp() {
		// TODO Auto-generated method stub
		return 0;
	}
}
