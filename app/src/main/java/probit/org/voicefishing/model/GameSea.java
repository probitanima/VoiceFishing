package org.probit.voicefishing.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.probit.voicefishing.framework.FileIO;
import org.probit.voicefishing.model.impl.mop.MopGoldCoin;
import org.probit.voicefishing.model.impl.mop.MopSilverCoin;
import org.probit.voicefishing.model.impl.mop.MopFishNorm;
import org.probit.voicefishing.model.impl.mop.MopFishColor;
import org.probit.voicefishing.model.impl.mop.MopFishRain;
import org.probit.voicefishing.model.impl.mop.MopInkFish;
import org.probit.voicefishing.model.impl.mop.TrashCan;
import org.probit.voicefishing.model.impl.mop.MopTrashShoes;
import org.probit.voicefishing.model.impl.mop.MopWhale;
import org.probit.voicefishing.util.Settings;

import android.util.Log;

public class GameSea extends Sea {

	@Override
	public int setFisherManLocation(double frequency) {
//		if (frequency == 0) {
//			fisherMan.setLocation_des(Settings.noteCritPosition.get(24));
//			return 0;
//			// Log.i("sea", "NONE" + Settings.noteCritPosition.get(0));
//		} else if (frequency > Settings.NoteBoundary.get(0)) {
//			// Log.i("sea", "HIGH" + Settings.noteCritPosition.get(4));
//			fisherMan.setLocation_des(Settings.noteCritPosition.get(35));
//		} else if (frequency <= Settings.NoteBoundary.get(0)) {
//			fisherMan.setLocation_des(Settings.noteCritPosition.get(29));
//			// Log.i("sea", "LOW" + Settings.noteCritPosition.get(2));
//		}
		return bonus;
	}

	@Override
	public void loadNotes(FileIO files, String fileName) {
		BufferedReader in = null;

		try {
			in = new BufferedReader(new InputStreamReader(
					files.readAsset(fileName)));

			String notes;
			String[] arr;

			int orderCount = 0;
			int mopsCount = 0;

			int y_location=0;

			while ((notes = in.readLine()) != null) {

				orderCount++;

				arr = notes.split(",");

				for (int i = 0; i < arr.length; i += 2) {
					switch (Integer.parseInt(arr[i + 1])) {
					case 0:
						y_location = 24;
						break;
					case 1:
						y_location = 25;
						break;
					case 2:
						y_location = 27;
						break;
					case 3:
						y_location = 29;
						break;
					case 4:
						y_location = 31;
						break;
					case 5:
						y_location = 33;
						break;
					case 6:
						y_location = 35;
						break;

					}
					//y_location = Integer.parseInt(arr[i + 1]);
					// Log.i("sea", "y_location " + y_location);

					mopsCount++;
					switch (Integer.parseInt(arr[i])) {
					case 1:
						// type = ItemsType.fish1;
						mops.add(new MopFishNorm(y_location, orderCount));
						break;
					case 2:
						mops.add(new MopFishColor(y_location, orderCount));
						break;
					case 3:
						mops.add(new MopFishRain(y_location, orderCount));
						break;
					case 4:
						mops.add(new MopWhale(y_location, orderCount));
						break;
					case 5:
						mops.add(new MopGoldCoin(y_location, orderCount));
						break;
					case 6:
						mops.add(new MopSilverCoin(y_location, orderCount));
						break;
					case 7:
						mops.add(new MopInkFish(y_location, orderCount));
						break;
					case 8:
						mops.add(new TrashCan(y_location, orderCount));
						break;
					case 9:
						mops.add(new MopTrashShoes(y_location, orderCount));
						break;
					}
				}
			}

			Settings.NUM_OF_NOTES = mops.size();
			Log.i("GameSea", "Settings.NUM_OF_NOTES "+Settings.NUM_OF_NOTES);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
