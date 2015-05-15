package org.probit.voicefishing.model;

public class MyTimer {
	public int mode;
	public float time;
	public float tickTime;
	
	public void setMode(int mode) {
		this.mode = mode;
	}
	
	public void getTime(float deltaTime) {
		time += deltaTime;
		
		if(time>=tickTime) {
			doSomething();
			time=0;
		}
	}
	
	public void doSomething(){}

}
