package org.probit.voicefishing.model;


import org.probit.voicefishing.framework.Pixmap;
import org.probit.voicefishing.util.Settings;


public abstract class Mop {
	public enum MopState {
		Ready, Running, Done, Got
	}
	
	public int x;
	public int y;
	
	public int width=100;
	public int height=100;
	
	public int order;
	public int type;
	
	public int mode;
	
	
	public MopState state;
	
	public Mop(int y, int order) {
		this.x = Settings.screenWidth;
		this.y = y;
		this.order = order;
		this.state = MopState.Ready;
		
	}
	
	public void setNext() {
		if (state == MopState.Running)
			x -= 5;
	}
	
	public void setState(MopState state) {
		if (state == MopState.Running) {
			this.state = MopState.Running;
		} else if (state == MopState.Done) {
			this.state = MopState.Done;
		} else if (state == MopState.Got) {
			this.state = MopState.Got;
		}
	}
	
	public abstract int setScore();
	public abstract int setCoin();
	public abstract int setBonusGauge();
	public abstract int setHp();
	public abstract Pixmap getImgAsset();
	
}
