package org.probit.voicefishing.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.probit.voicefishing.framework.FileIO;
import org.probit.voicefishing.pitch.CalCul;

import android.util.Log;

public class Settings {

	// from setting file //
	public static SaveFile savefile;

	public static int coinArray_SaveFile[] = new int[5];
	public static int scoreArray_SaveFile[] = new int[5];
	public static int levelArray_SaveFile[] = new int[5];
	public static int expArray_SaveFile[] = new int[5];

	public static int MIN_FREQUENCY = (int) CalCul.returnPitch(0); // HZ
	public static int MAX_FREQUENCY = (int) CalCul.returnPitch(59); // HZ
	public static int LIMIT_AMP = 4000; // amp

	// all //------
	public static int NUM_OF_NOTES = 30;

	public static int screenWidth;
	public static int screenHeight;

	// fisherman //------
	public static int highetNote;
	public static int lowestNote;

	public static ArrayList<Double> NoteInterval = new ArrayList<Double>();
	public static ArrayList<Double> NoteBoundary = new ArrayList<Double>();
	public static ArrayList<Integer> noteCritPosition = new ArrayList<Integer>();

	// about note
	public static void setNoteCrit() {
		NoteInterval.removeAll(NoteInterval);
		NoteBoundary.removeAll(NoteBoundary);
		int i=0;
		
		for (i = 0; i <= 36; i++) {
			
			NoteInterval.add(CalCul.returnPitch(i));
			NoteBoundary.add((CalCul.returnPitch(i) + CalCul.returnPitch(i+1)) / 2);
		}
		
		NoteBoundary.add((CalCul.returnPitch(i) + CalCul.returnPitch(i + 1 )) / 2);	

	}

	public static double getNoteCrit(int i) {
		// return noteCrit[i];
		return NoteInterval.get(i);
	}

	public static void setNoteLocation() {

		for (int j = 2; j >= 0; j--) {
			for (int i = 15; i >= 4; i--) {
				
				noteCritPosition.add((((int)(screenHeight * i * 0.05))+((int)(screenHeight * j))));
				//Log.i("Settings","j "+j+" i "+i+"------>"+(((int)(screenHeight * i * 0.05))+((int)(screenHeight * j))));
			}
		}
	}

	public static void getArray() {
		
		int _coin = savefile.coin, _level = savefile.level, index = 0;
		int _exp = (int) savefile.experience / savefile.level;

		for (int i = 10000; i > 0; i = i / 10, index++) {

			coinArray_SaveFile[index] = (_coin / i);
			_coin = _coin - (coinArray_SaveFile[index] * i);

			levelArray_SaveFile[index] = (_level / i);
			_level = _level - (levelArray_SaveFile[index] * i);

			expArray_SaveFile[index] = (_exp / i);
			_exp = _exp - (expArray_SaveFile[index] * i);
		}
	}

	// about saveFile
	public static void buyItem(int kind) {
		savefile.coin -= savefile.item.get(kind).buyItem();
		checkIO();
	}

	public static void setCoin(int i) {
		savefile.coin += i;
		getArray();
	}

	public static void resultGame(int coin, int score) {

		savefile.experience += (coin + score);
		savefile.coin += coin;
		setLevel(coin + score);

	}

	public static void setLevel(int exp) {
		// savefile.experience += exp;
		while (savefile.experience >= (savefile.level * 100)) {
			savefile.experience = savefile.experience - (savefile.level * 100);
			savefile.level++;
		}
	}

	// IO
	public static boolean load(FileIO files) {
		ObjectInputStream in = null;

		boolean check = true;

		try {
			in = new ObjectInputStream(files.readFile("fish"));

			savefile = (SaveFile) in.readObject();
			
			checkIO();
			
			Log.i("Settings", "load success");
		} catch (Exception e) {
			e.printStackTrace();
			
			savefile = new SaveFile();
			check = false;
			
		} finally {
			try {
				if (in != null) {
					in.close();
					checkIO();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		getArray();

		return check;
	}

	public static void save(FileIO files) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(files.writeFile("fish"));
			
			out.writeObject(savefile);
			checkIO();
			Log.i("Settings", "save success");

		} catch (IOException e) {
			e.printStackTrace();
			Log.i("Settings", "save error");

		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
				Log.i("Settings", "save error222222");
			}
		}

		getArray();
	}

	private static void checkIO() {
//		Log.i("Settings", "level " + savefile.level);
//		Log.i("Settings", "experience " + savefile.experience);
//		Log.i("Settings", "coin " + savefile.coin);
//		Log.i("Settings", "highscore " + savefile.highscore);
//
//		Log.i("Settings", "mainNote " + savefile.getMainNote());
//		Log.i("Settings", "soundEnable " + savefile.getSoundEnable());

		// for (int i = 0; i < 8; i++) {
		// Log.i("Settings", "item " + savefile.item.get(i).last + "using" +
		// savefile.item.get(i).getUsing());
		// }
	}

}
