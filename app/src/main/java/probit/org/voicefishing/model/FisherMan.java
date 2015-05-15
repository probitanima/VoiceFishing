package org.probit.voicefishing.model;

import org.probit.voicefishing.util.Settings;

import android.util.Log;

public class FisherMan extends MyTimer {
	private double location;
	private double location_des;
	public double loc_show_unshow;
	public double loc_show;
	public double loc_des_show;
	public double location_x;
	public double frequency;

	// ����
	public FisherMan() {

		this.frequency = 130;
		this.location_x = 50;

		this.tickTime = 0.5f;
		setLocation_des(Settings.noteCritPosition.get(0));

	}
	
	public double getShownLocation() {
		return loc_show_unshow;
	}
	
	public double getLocation() {
		return location;
	}

	public void setLocation() {
		// this.location = this.location + 0.05 * (location_des -
		// this.location);
		this.location = location_des;
		this.loc_show_unshow= this.loc_show_unshow + 0.03 * (location_des - this.loc_show_unshow);
		this.loc_show = this.loc_show + 0.03 * (loc_des_show - this.loc_show);
		
		//Log.i("FisherMan","location: "+location+" loc_show: "+loc_show);
	}

	public double getLocation_des() {
		return location_des;
	}

	public void setLocation_des(double location_des) {
		
		this.location_des = location_des;
		this.loc_des_show = location_des % Settings.screenHeight;
	}

	public void getThings(Mop thing) {
		Log.i("FisherMan", "Get thing");
	}

	@Override
	public void setMode(int newMode) {
		super.setMode(newMode);

		if (mode > 0)
			location_x = 200;
		else
			location_x = 50;
	}

	@Override
	public void doSomething() {
		if (mode != 0) {

			if (mode <= 3)
				mode++;
			else
				mode = 1;
		}
	}

}

// public void setLocationUsingFre(double freq) {
// frequency = freq;
// //
//
// // easy
// if (freq>Settings.noteCritBoundary[5]) {
// Log.i("FisherMan", "TOO HIGH");
// location_des = 0;
// }
// else if (Settings.noteCritBoundary[4] < freq && freq <=
// Settings.noteCritBoundary[5])
// location_des = Settings.noteCritLocation[4];
// else if (Settings.noteCritBoundary[3] < freq && freq <=
// Settings.noteCritBoundary[4])
// location_des = Settings.noteCritLocation[3];
// else if (Settings.noteCritBoundary[2] < freq && freq <=
// Settings.noteCritBoundary[3])
// location_des = Settings.noteCritLocation[2];
// else if (Settings.noteCritBoundary[1] < freq && freq <=
// Settings.noteCritBoundary[2])
// location_des = Settings.noteCritLocation[1];
// else if (Settings.noteCritBoundary[0] < freq && freq <=
// Settings.noteCritBoundary[1])
// location_des = Settings.noteCritLocation[0];
// else if (freq<=Settings.noteCritBoundary[0]) {
// Log.i("FisherMan", "TOO LOW");
// location_des = Settings.screenHeight-Assets.hook.getY();
// }
//
//
// //if (freq < Settings.noteCrit[0]) {
// // location = Settings.screenHeight * 5.5 / 7;
// //} else if (Settings.noteCrit[0] <= freq && freq < Settings.noteCrit[1]) {
// // calculLocation(0, freq);
// //} else if (Settings.noteCrit[1] <= freq && freq < Settings.noteCrit[2]) {
// // calculLocation(1, freq);
// //} else if (Settings.noteCrit[2] <= freq && freq < Settings.noteCrit[3]) {
// // calculLocation(2, freq);
// //} else if (Settings.noteCrit[3] <= freq && freq < Settings.noteCrit[4]) {
// // calculLocation(3, freq);
// //} else if (freq >= Settings.noteCrit[4]) {
// // location = Settings.screenHeight * 0.5 / 7;
// //} else {
// //
// //
// //}
//
// }

// private void calculLocation(int index, double freq) {
//
// double a = 1 - ((freq - Settings.noteCrit[index]) / (Settings.noteCrit[index
// + 1] - Settings.noteCrit[index]));
// double b = (Settings.screenHeight * (5 - index) / 7)
// - (Settings.screenHeight * (5 - index - 1) / 7);
// double c = a * b + (Settings.screenHeight * (5 - index - 1) / 7);
//
// setLocation(c);
// }

