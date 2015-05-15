package org.probit.voicefishing.screen;

import java.util.List;

import org.probit.voicefishing.framework.Game;
import org.probit.voicefishing.framework.Graphics;
import org.probit.voicefishing.framework.Pixmap;
import org.probit.voicefishing.framework.Screen;
import org.probit.voicefishing.framework.Graphics.PixmapFormat;
import org.probit.voicefishing.framework.Input.PitchEvent;
import org.probit.voicefishing.framework.Input.TouchEvent;
import org.probit.voicefishing.model.Item;
import org.probit.voicefishing.pitch.CalCul;
import org.probit.voicefishing.util.Assets;
import org.probit.voicefishing.util.Settings;

import android.util.Log;
import android.widget.SeekBar;

public class SettingScreen extends Screen {
	Graphics g;
	int state;
	int genderMode=0;

	// sound
	int mainNote;
	double amp = 0;

	// noise
	int highestDecibel = 0;
	int decibelArray[] = new int[3];
	float startTime = 0;

	public SettingScreen(Game game) {
		super(game);
		g = game.getGraphics();
		Settings.getArray();
		state = 0;
		
		genderMode = Settings.savefile.getGender();
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvent();
		List<PitchEvent> pitchEvents = game.getInput().getPitchEvent();

		if (state == 0)
			updateSetting(touchEvents);
		if (state == 2)
			updateSettingNoise(touchEvents, pitchEvents, deltaTime);

	}

	private void updateSetting(List<TouchEvent> touchEvents) {

		int len = touchEvents.size();

		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(0);
			if (event.type == TouchEvent.TOUCH_UP) {
				
				// gender select
				if(genderMode==0) {
					if (inBounds(event, Assets.btn_settingGenderFemale))
						genderMode = 1;
				}
				else if(genderMode==1) {
					if (inBounds(event, Assets.btn_settingGenderMale))
						genderMode = 0;
				}

				if (inBounds(event, Assets.btn_settingNoise))
					setState(2);
				
				else if (inBounds(event, Assets.btn_back))
					game.setScreen(new MainMenuScreen(game));
				return;
			}
		}
	}

	private void updateSettingNoise(List<TouchEvent> touchEvents,
			List<PitchEvent> pitchEvents, float deltaTime) {
		
		if (startTime == 0) {
			startTime = deltaTime;
			
		} else if (startTime > 5) {
			setBackState();
			return;
			
		} else {
			startTime += deltaTime;

			int len = touchEvents.size();

			for (int i = 0; i < len; i++) {
				TouchEvent event = touchEvents.get(0);
				
				if (event.type == TouchEvent.TOUCH_UP) {
					if (inBounds(event, Assets.btn_back))
						setBackState();
					return;
				}
			}

			len = pitchEvents.size();
			for (int i = 0; i < len; i++) {
				PitchEvent event = pitchEvents.get(i);

				int amp = (int) (90.3087f + (10 * Math
						.log10(event.amp / 32767f)));

				Log.i("NoiseSettingScreen", "amp " + amp);
				if (highestDecibel < amp) {
					highestDecibel = amp;
					getArray();
				}
			}
		}

	}

	private void getArray() {
		int decibel = highestDecibel, index = 0;

		for (int i = 100; i > 0; i = i / 10, index++) {
			decibelArray[index] = (decibel / i);
			decibel = decibel - (decibelArray[index] * i);
		}
	}

	private void setState(int i) {
		state = i;

		game.getInput().checkPitchOn();

		if (i == 2)
			Settings.LIMIT_AMP = 0;
		
		highestDecibel=0;

	}

	private void setBackState() {

		Settings.savefile.setGender(genderMode);
		Settings.save(game.getFileIO());
		
		if (state == 2) {
			if (highestDecibel < 65) {
				Settings.LIMIT_AMP = 6000;
			} else {
				Settings.LIMIT_AMP = 10000;
			}
			startTime = 0;
		}

		state = 0;
		game.getInput().checkPitchOff();
	}

	// private boolean inBounds(TouchEvent event, int x, int y, int width, int
	// height) {
	// if (event.x > x && event.x < x + width - 1 && event.y > y && event.y < y
	// + height - 1)
	// return true;
	// else
	// return false;
	// }

	private boolean inBounds(TouchEvent event, Pixmap button) {
		if (event.x > button.getX()
				&& event.x < button.getX() + button.getWidth() - 1
				&& event.y > button.getY()
				&& event.y < button.getY() + button.getHeight() - 1)
			return true;
		else
			return false;
	}

	@Override
	public void present(float deltaTime) {
		// bg
		g.drawPixmap(Assets.bg_main, 0, 0, Settings.screenWidth,
				Settings.screenHeight);

		g.drawPixmap(Assets.mop[2], (int) (Settings.screenWidth * 0.15),
				(int) (Settings.screenHeight * 0.8),
				(int) (Settings.screenWidth * 0.12),
				(int) (Settings.screenWidth * 0.12));
		g.drawPixmap(Assets.mop[4], (int) (Settings.screenWidth * 0.3),
				(int) (Settings.screenHeight * 0.78),
				(int) (Settings.screenWidth * 0.15),
				(int) (Settings.screenWidth * 0.15));

		g.drawPixmap(Assets.btn_back);

		if (state == 0)
			drawSettingUI();
		if (state == 1)
			drawSettingGenderUI();
		if (state == 2)
			drawSettingNoiseUI();
	}

	private void drawSettingUI() {
		g.drawPixmap(Assets.bnd_settingTable,
				(int) (Settings.screenWidth * 0.55),
				(int) (Settings.screenHeight * 0.1),
				(int) (Settings.screenWidth * 0.4),
				(int) (Settings.screenHeight * 0.7));

		g.drawPixmap(Assets.btn_settingGender);
		
		if(genderMode==0) {
			g.drawPixmap(Assets.btn_settingGenderMalec);
			g.drawPixmap(Assets.btn_settingGenderFemale);
		}
		else if(genderMode==1) {
			g.drawPixmap(Assets.btn_settingGenderMale);
			g.drawPixmap(Assets.btn_settingGenderFemalec);
		}
		
		g.drawPixmap(Assets.btn_settingNoise);

		
		g.drawPixmap(Assets.bnd_main); // main_board

		// coin
		g.drawPixmap(Assets.numberBlack[Settings.coinArray_SaveFile[0]],
				(int) (Settings.screenWidth * 0.35),
				(int) (Settings.screenHeight * 0.11),
				(int) (Settings.screenWidth * 0.03),
				(int) (Settings.screenHeight * 0.08));
		g.drawPixmap(Assets.numberBlack[Settings.coinArray_SaveFile[1]],
				(int) (Settings.screenWidth * 0.37),
				(int) (Settings.screenHeight * 0.11),
				(int) (Settings.screenWidth * 0.03),
				(int) (Settings.screenHeight * 0.08));
		g.drawPixmap(Assets.numberBlack[Settings.coinArray_SaveFile[2]],
				(int) (Settings.screenWidth * 0.39),
				(int) (Settings.screenHeight * 0.11),
				(int) (Settings.screenWidth * 0.03),
				(int) (Settings.screenHeight * 0.08));
		g.drawPixmap(Assets.numberBlack[Settings.coinArray_SaveFile[3]],
				(int) (Settings.screenWidth * 0.41),
				(int) (Settings.screenHeight * 0.11),
				(int) (Settings.screenWidth * 0.03),
				(int) (Settings.screenHeight * 0.08));
		g.drawPixmap(Assets.numberBlack[Settings.coinArray_SaveFile[4]],
				(int) (Settings.screenWidth * 0.43),
				(int) (Settings.screenHeight * 0.11),
				(int) (Settings.screenWidth * 0.03),
				(int) (Settings.screenHeight * 0.08));

		// // level

		g.drawPixmap(Assets.numberBlack[Settings.levelArray_SaveFile[3]],
				(int) (Settings.screenWidth * 0.17),
				(int) (Settings.screenHeight * 0.225),
				(int) (Settings.screenWidth * 0.03),
				(int) (Settings.screenHeight * 0.08));
		g.drawPixmap(Assets.numberBlack[Settings.levelArray_SaveFile[4]],
				(int) (Settings.screenWidth * 0.19),
				(int) (Settings.screenHeight * 0.225),
				(int) (Settings.screenWidth * 0.03),
				(int) (Settings.screenHeight * 0.08));

		// // exp gauge
		g.drawPixmap(
				Assets.expGauge,
				0,
				0,
				Assets.expGauge.getBitmapWidth() * Settings.savefile.getExp()
						/ 100,
				Assets.expGauge.getBitmapHeight(),
				(int) (Settings.screenWidth * 0.23),
				(int) (Settings.screenHeight * 0.23),
				(int) (Settings.screenWidth * 0.25)
						* Settings.savefile.getExp() / 100,
				(int) (Settings.screenHeight * 0.08));

		g.drawPixmap(Assets.bnd_exp);

		// // exp %
		g.drawPixmap(Assets.numberWhite[Settings.expArray_SaveFile[3]],
				(int) (Settings.screenWidth * 0.33),
				(int) (Settings.screenHeight * 0.24),
				(int) (Settings.screenWidth * 0.03),
				(int) (Settings.screenHeight * 0.07));
		g.drawPixmap(Assets.numberWhite[Settings.expArray_SaveFile[4]],
				(int) (Settings.screenWidth * 0.35),
				(int) (Settings.screenHeight * 0.24),
				(int) (Settings.screenWidth * 0.03),
				(int) (Settings.screenHeight * 0.07));
		g.drawPixmap(Assets.percent, (int) (Settings.screenWidth * 0.38),
				(int) (Settings.screenHeight * 0.25),
				(int) (Settings.screenWidth * 0.02),
				(int) (Settings.screenHeight * 0.05));

		// // item

		for (Item item : Settings.savefile.item) {
			if (item.getUsing()) {
				if (item.type == 0) {
					g.drawPixmap(Assets.item_bundle[item.id],
							(int) (Settings.screenWidth * 0.13),
							(int) (Settings.screenHeight * 0.51),
							(int) (Settings.screenWidth * 0.15),
							(int) (Settings.screenHeight * 0.25));
					g.drawPixmap(Assets.item_TxtRope[item.id],
							(int) (Settings.screenWidth * 0.16),
							(int) (Settings.screenHeight * 0.48),
							(int) (Settings.screenWidth * 0.1),
							(int) (Settings.screenHeight * 0.05));
				} else if (item.type == 1) {
					g.drawPixmap(Assets.item_miki[item.id],
							(int) (Settings.screenWidth * 0.32),
							(int) (Settings.screenHeight * 0.53),
							(int) (Settings.screenWidth * 0.15),
							(int) (Settings.screenHeight * 0.21));
					g.drawPixmap(Assets.item_TxtMiki[item.id],
							(int) (Settings.screenWidth * 0.345),
							(int) (Settings.screenHeight * 0.48),
							(int) (Settings.screenWidth * 0.1),
							(int) (Settings.screenHeight * 0.05));
				}
			}
		}
	}

	private void drawSettingGenderUI() {
		g.drawPixmap(Assets.bnd_fish, (int) (Settings.screenWidth * 0.25),
				(int) (Settings.screenHeight * 0.25),
				(int) (Settings.screenWidth * 0.5),
				(int) (Settings.screenHeight * 0.5));

		g.drawPixmap(Assets.btn_settingGender,
				(int) (Settings.screenWidth * 0.5),
				(int) (Settings.screenHeight * 0.2),
				(int) (Settings.screenWidth * 0.3),
				(int) (Settings.screenHeight * 0.1));

		int j = mainNote % 12;
		int index = mainNote / 12;

		switch (j) {
		case 1:
		case 3:
		case 6:
		case 7:
		case 10:
			g.drawPixmap(Assets.sharp, (int) (Settings.screenWidth * 0.45),
					(int) (Settings.screenHeight * 0.4),
					(int) (Settings.screenWidth * 0.1),
					(int) (Settings.screenWidth * 0.1));
		}
		g.drawPixmap(Assets.notes[j], (int) (Settings.screenWidth * 0.35),
				(int) (Settings.screenHeight * 0.4),
				(int) (Settings.screenWidth * 0.1),
				(int) (Settings.screenWidth * 0.1));

		g.drawPixmap(Assets.numberBlue[index + 2],
				(int) (Settings.screenWidth * 0.55),
				(int) (Settings.screenHeight * 0.4),
				(int) (Settings.screenWidth * 0.1),
				(int) (Settings.screenWidth * 0.1));

	}

	private void drawSettingNoiseUI() {

		g.drawPixmap(Assets.bnd_fish, (int) (Settings.screenWidth * 0.25),
				(int) (Settings.screenHeight * 0.25),
				(int) (Settings.screenWidth * 0.5),
				(int) (Settings.screenHeight * 0.5));

		g.drawPixmap(Assets.btn_settingNoise,
				(int) (Settings.screenWidth * 0.5),
				(int) (Settings.screenHeight * 0.2),
				(int) (Settings.screenWidth * 0.3),
				(int) (Settings.screenHeight * 0.1));

		g.drawPixmap(Assets.numberBlue[decibelArray[0]],
				(int) (Settings.screenWidth * 0.35),
				(int) (Settings.screenHeight * 0.42),
				(int) (Settings.screenWidth * 0.1),
				(int) (Settings.screenWidth * 0.1));
		g.drawPixmap(Assets.numberBlue[decibelArray[1]],
				(int) (Settings.screenWidth * 0.4),
				(int) (Settings.screenHeight * 0.42),
				(int) (Settings.screenWidth * 0.1),
				(int) (Settings.screenWidth * 0.1));
		g.drawPixmap(Assets.numberBlue[decibelArray[2]],
				(int) (Settings.screenWidth * 0.45),
				(int) (Settings.screenHeight * 0.42),
				(int) (Settings.screenWidth * 0.1),
				(int) (Settings.screenWidth * 0.1));

		g.drawPixmap(Assets.db, (int) (Settings.screenWidth * 0.52),
				(int) (Settings.screenHeight * 0.4),
				(int) (Settings.screenWidth * 0.1),
				(int) (Settings.screenWidth * 0.1));
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispase() {
		// TODO Auto-generated method stub
		setBackState();
	}

}
