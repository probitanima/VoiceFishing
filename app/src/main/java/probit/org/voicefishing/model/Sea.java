package org.probit.voicefishing.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.probit.voicefishing.framework.FileIO;
import org.probit.voicefishing.model.Mop.MopState;
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

public class Sea {

	static final float TICK_INITIAL = 0.8f;

	public int score = 0;
	public int coin = 0;
	public int maxHp = 0;
	public int hp = 0;
	public int bonusGauge = 0;

	public int scoreArray[] = new int[5];
	public int coinArray[] = new int[5];

	// bonus
	public int bonus = 0;
	public float bonusTime = 8f;
	public float bonusCheck;

	// ink
	public float inkLeftTime;
	public float inkTime = 8f;
	
	// setting

	static float tick = TICK_INITIAL;

	// delta Data
	public int scoreDelta = 1;
	public int coinDelta = 1;
	public int gaugeDelta = 1;

	// Item Information
	public int itemsOrder = 1;
	public int itemsShown = 0;
	public int itemsDone = 0;
	public int itemsLast = 0;
	
	public int usingItem[] = new int[2];

	// public int pointer = 0;
	public boolean gameOver = false;

	float tickTime = 0;

	public FisherMan fisherMan;
	public EffectInk effectInk;
	public ArrayList<Mop> mops = new ArrayList<Mop>();
	

	public GotTimer gt;

	// doInk
	// checkInkTime

	public Sea() {
		fisherMan = new FisherMan();
		effectInk = new EffectInk();
		usingItem[0] = -1;
		usingItem[1] = -1;
		// item ���ο� �� delta�� ���
		getItemEffect();
	}

	public void getItemEffect() {
		for (Item item : Settings.savefile.item) {
			if (item.getUsing() == true) {
				switch (item.type) {
				case 0:
					maxHp = item.doEffect(); 
					hp = item.doEffect();
					usingItem[0] = item.id;
					break;
				case 1:
					scoreDelta = item.doEffect();
					usingItem[1] = item.id;
					break;
				}
			}

		}
	}

	// ���� �ð� ���� �� ���� �ν��Ͻ����� ���º���
	public void update(float deltaTime) {
		if (gameOver)
			return;

		// GameOver Cases //-------------------------------
		// 1. End of Notes
		// 2. Out of HP
		// 3. Cancel Key

		// End of Notes
		if (itemsDone >= Settings.NUM_OF_NOTES) {
			gameOver = true;
			return;
		}

		// End of HP
		if (hp == 0) {
			gameOver = true;
			return;
		}
		// end of GameOver Cases //-------------------------------

		// get frequency

		//
		tickTime += deltaTime;
		fisherMan.setLocation();

		// check Bonus & Ink Time
		checkBonusTime(deltaTime);
		checkInkTime(deltaTime);

		// Change State of Ready Thing
		if (tickTime > tick && itemsOrder < Settings.NUM_OF_NOTES) {

			while (itemsShown < Settings.NUM_OF_NOTES) {

				if (mops.get(itemsShown).order == itemsOrder) {
					// Log.i("Sea","mop "+mops.get(itemsShown).y);
					mops.get(itemsShown).setState(MopState.Running);
					itemsShown++;
				} else
					break;
			}
			itemsOrder++;
			tickTime -= tick;
		}

	}

	public void getCatch() {
		// setNext all && check isGet all
		for (int i = itemsDone; i < itemsShown; i++) {

			mops.get(i).setNext();

			if (mops.get(i).x < fisherMan.location_x && mops.get(i).state == MopState.Running) {
				
				itemsLast = i+1;		// diff
				
				// missed
				if (mops.get(i).x < 0) {
					mops.get(i).setState(MopState.Done);
					itemsDone++;
				}

				// Get Items
				else if (checkGetItem(i)) {
					if(gt != null) {
						gt = null;
					}
					
					gt = new GotTimer(mops.get(i));
					
					mops.get(i).setState(MopState.Got);
					setCodition(mops.get(i));

					getEachPoint();
					checkGauge();
					itemsDone++;
				}
				
			}
		}
	}

	private void checkBonusTime(float deltaTime) {
		if (bonusCheck <= 0)
			fisherMan.setMode(0);
		else {
			bonusCheck -= deltaTime;
			// Log.i("Sea","Bonus Time!");
		}
		// Log.i("Sea",""+fisherMan.bonusMode);

	}

	public void doBonus() {
		// do �ʻ��
		if (bonus > 0) {
			bonus--;
			fisherMan.setMode(1);
			bonusCheck = bonusTime;
			// Log.i("Sea","Bonus!!!!!!!!!!!!!!!!!!!");
		}
	}

	private void checkGauge() {
		if (bonusGauge > 15 && bonus < 4) {
			bonus++;
			bonusGauge = 0;
		}
	}

	private void checkInkTime(float deltaTime) {
		
		if (inkLeftTime <= 0)
			effectInk.setMode(0);
		else
			inkLeftTime -= deltaTime;
	}

	public void doInk() {
		// do �ʻ��
		inkLeftTime = inkTime;
		effectInk.setMode(1);
	}

	private boolean checkGetItem(int i) {
		if (fisherMan.mode > 0)
			return true;

		else {
			//double check = Settings.noteCritPosition.get(mops.get(i).y) - fisherMan.getLocation();

			double check = fisherMan.getShownLocation() - Settings.noteCritPosition.get(mops.get(i).y);
			//Log.i("FisherMan"," ->"+mops.get(i).y+" mop: "+ Settings.noteCritPosition.get(mops.get(i).y) +" hook: "+fisherMan.getShownLocation()+" check  "+ check );
			// Log.i("sea","hook  "+ fisherMan.getLocation() + " fish " +
			// Settings.noteCritPosition.get(mops.get(i).y));
			
			//Log.i("sea","check  "+ check + " aaa "+(Settings.screenHeight * 0.05));
			if (-((int) (Settings.screenHeight * 0.025)) <= check && check <= (int) (Settings.screenHeight * 0.025))
				return true;
			else
				return false;
		}
	}

	private void setCodition(Mop type) {

		score += type.setScore() * scoreDelta;
		if (score < 0)
			score = 0;

		coin += type.setCoin() * coinDelta;
		if (coin < 0)
			coin = 0;

		if (fisherMan.mode == 0) {
			// no bonus
			bonusGauge += type.setBonusGauge() * gaugeDelta;
			if (bonusGauge < 0)
				bonusGauge = 0;

			// no ink effect
			if (type.getClass().getSimpleName().equals("MopInkFish")) {
				// speciall Effect
				doInk();
			}

			// no hp attack
			hp += type.setHp();
		}
	}

	private void getEachPoint() {
		int _score = score, _coin = coin, index = 0;

		for (int i = 10000; i > 0; i = i / 10, index++) {
			scoreArray[index] = (_score / i);
			_score = _score - (scoreArray[index] * i);

			coinArray[index] = (_coin / i);
			_coin = _coin - (coinArray[index] * i);

		}
	}

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

	public void loadNotes(FileIO files, String fileName) {
		BufferedReader in = null;

		try {
			in = new BufferedReader(new InputStreamReader(files.readAsset(fileName)));

			String notes;
			String[] arr;

			int orderCount = 0;
			int mopsCount = 0;

			int y_location;
			
			int genderIndex=0;		// male or female(+0 or +5)
			
			if(Settings.savefile.getGender()==0)
				genderIndex = 0;
			else if(Settings.savefile.getGender()==1)
				genderIndex = 5;

			while ((notes = in.readLine()) != null) {

				orderCount++;

				arr = notes.split(",");

				for (int i = 0; i < arr.length; i += 2) {
					
					y_location = Integer.parseInt(arr[i + 1]) + genderIndex;
					
					if (y_location >= Settings.highetNote)
						Settings.highetNote = y_location;

					if (y_location <= Settings.lowestNote)
						Settings.lowestNote = y_location;

					mopsCount++;
					switch (Integer.parseInt(arr[i])) {
					case 0:
						mops.add(new MopGoldCoin(y_location, orderCount));
						break;
					case 1:
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
			Log.i("Sea", "Settings.NUM_OF_NOTES " + Settings.NUM_OF_NOTES);
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
