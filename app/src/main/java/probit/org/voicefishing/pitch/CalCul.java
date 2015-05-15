package org.probit.voicefishing.pitch;

import android.util.Log;

public class CalCul {
	
	public static String[] scale = new String[] {"도", "도#", "레", "레#", "미",
		"파", "파#", "솔", "솔#", "라", "라#", "시" };
	
	public static double[] range = new double[] { 65.4064, 69.2957, 73.4162,
			77.7817, 82.4069, 87.3071, 92.4986, 97.9989, 103.8262, 110.0000,
			116.5409, 123.4708, 130.8128, 138.5913, 146.8324, 155.5635,
			164.8138, 174.6141, 184.9972, 195.9977, 207.6523, 220.0000,
			233.0819, 246.9417, 261.6256, 277.1826, 293.6648, 311.1270,
			329.6276, 349.2282, 369.9944, 391.9954, 415.3047, 440.0000,
			466.1638, 493.8833, 523.2511, 554.3653, 587.3295, 622.2540,
			659.2551, 698.4565, 739.9888, 783.9909, 830.6094, 880.0000,
			932.3275, 987.7666, 1046.502, 1108.731, 1174.659, 1244.508,
			1318.510, 1396.913, 1479.978, 1567.982, 1661.219, 1760.000,
			1867.655, 1975.533 };
	
	// Calcul{
	// scale[0] = "도2";
	// scale[12] = "도3";
	// scale[24] = "도4";
	// scale[36] = "도5";
	// scale[48] = "도6";
	//
	// scale[1] = "도#2";
	// scale[13] = "도#3";
	// scale[25] = "도#4";
	// scale[37] = "도#5";
	// scale[49] = "도#6";
	// scale[2] = "레2";
	// scale[14] = "레3";
	// scale[26] = "레4";
	// scale[38] = "레5";
	// scale[50] = "레6";
	//
	// scale[3] = "레#2";
	// scale[15] = "레#3";
	// scale[27] = "레#4";
	// scale[39] = "레#5";
	// scale[51] = "레#6";
	//
	// scale[4] = "미2";
	// scale[16] = "미3";
	// scale[28] = "미4";
	// scale[40] = "미5";
	// scale[52] = "미6";
	//
	// scale[5] = "파2";
	// scale[17] = "파3";
	// scale[29] = "파4";
	// scale[41] = "파5";
	// scale[53] = "파6";
	//
	// scale[6] = "파#2";
	// scale[18] = "파#3";
	// scale[30] = "파#4";
	// scale[42] = "파#5";
	// scale[54] = "파#6";
	//
	// scale[7] = "솔2";
	// scale[19] = "솔3";
	// scale[31] = "솔4";
	// scale[43] = "솔5";
	// scale[55] = "솔6";
	//
	// scale[8] = "솔#2";
	// scale[20] = "솔#3";
	// scale[32] = "솔#4";
	// scale[44] = "솔#5";
	// scale[56] = "솔#6";
	//
	// scale[9] = "라2";
	// scale[21] = "라3";
	// scale[33] = "라4";
	// scale[45] = "라5";
	// scale[57] = "라6";
	//
	// scale[10] = "라#2";
	// scale[22] = "라#3";
	// scale[34] = "라#4";
	// scale[46] = "라#5";
	// scale[58] = "라#6";
	//
	// scale[11] = "시2";
	// scale[23] = "시3";
	// scale[35] = "시4";
	// scale[47] = "시5";
	// scale[59] = "시6";
	//
	//
	//
	// }

	// // 주파수 -> 음계
	// public static String returnNote(double _pitch)
	// {
	// for(int i = 0; i<60; i++)
	// {
	// if(range[i]>_pitch)
	// {
	// if((range[i]-_pitch) > (_pitch-range[i-1]))
	// return scale[i-1];
	// else if ((range[i]-_pitch) < (_pitch-range[i-1]))
	// return scale[i];
	// }
	// }
	// return "ERROR";
	// }

	// 주파수 -> 음계
	public static String returnNote(double _pitch) {

		for (int j = 1; j < 60; j++) {
			if (range[j] > _pitch) {
				
				if ((range[j] - _pitch) > (_pitch - range[j - 1])) {
					return scale[(j-1)%12]+((j/12)+2);
				}
				
				else if ((range[j] - _pitch) <= (_pitch - range[j - 1]))
					return scale[(j)%12]+((j/12)+2);
			}
		}
		return "ERROR";
	}

	// 주파수 -> 음 순서
	public static int returnID(double _pitch) {
		for (int i = 1; i < 60; i++) {
			if (range[i] > _pitch) {
				if ((range[i] - _pitch) > (_pitch - range[i - 1]))
					return i - 1;
				else if ((range[i] - _pitch) < (_pitch - range[i - 1]))
					return i;
			}
		}
		return 0;
	}

	// 음 순서 -> 음계
	public static String returnScale(int i) {
		int j = i%12;
		int index = i/12;
		return scale[j]+(index+2);
	}


	// 음 순서 -> 주파수
	public static double returnPitch(int i) {
		return range[i];
	}

}
