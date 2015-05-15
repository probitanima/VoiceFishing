package org.probit.voicefishing.screen;

import java.util.List;

import org.probit.voicefishing.framework.Game;
import org.probit.voicefishing.framework.Graphics;
import org.probit.voicefishing.framework.Graphics.PixmapFormat;
import org.probit.voicefishing.framework.Input.TouchEvent;
import org.probit.voicefishing.framework.Pixmap;
import org.probit.voicefishing.framework.Screen;
import org.probit.voicefishing.model.Item;
import org.probit.voicefishing.util.Assets;
import org.probit.voicefishing.util.Settings;

import android.util.Log;

public class MainMenuScreen extends Screen {
	Graphics g;
	int SongNum;
	boolean startGame;

	public MainMenuScreen(Game game) {
		super(game);
		g = game.getGraphics();
		startGame = false;

		Settings.getArray();
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvent();

		if (!startGame)
			updateMenu(touchEvents);
		if (startGame)
			updateSelect(touchEvents);
	}

	private void updateMenu(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);

			if (event.type == TouchEvent.TOUCH_UP) {
				// shop
				if (inBounds(event, Assets.btn_shop))
					game.setScreen(new ShopScreen(game));
				// start
				else if (inBounds(event, Assets.btn_start))
					// game.setScreen(new LoadingGameScreen(game));
					startGame = true;
				// setting
				else if (inBounds(event, Assets.btn_setting))
					game.setScreen(new SettingScreen(game));
				// exit
				else if (inBounds(event, Assets.btn_exit))
					game.quit();
				return;
			}
		}
	}

	private void updateSelect(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);

			if (event.type == TouchEvent.TOUCH_UP) {
				// shop
				if (inBounds(event, Assets.btn_SelectLeft))
					selectSong();
				else if (inBounds(event, Assets.btn_SelectRight))
					selectSong();
				else if (inBounds(event, Assets.btn_SelectConfirm))
					game.setScreen(new LoadingGameScreen(game, SongNum));
				else if (inBounds(event, Assets.btn_SelectBack))
					startGame = false;
			}
		}
	}

	private void selectSong() {
		if (SongNum == 0)
			SongNum = 1;
		else if (SongNum == 1)
			SongNum = 0;
	}

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
	public void present(float detlaTiem) {
		// bg
		g.drawPixmap(Assets.bg_main, 0, 0, Settings.screenWidth,
				Settings.screenHeight);
		
		g.drawPixmap(Assets.mop[1], (int) (Settings.screenWidth * 0.65),
				(int) (Settings.screenHeight * 0.01),
				(int) (Settings.screenWidth * 0.12),
				(int) (Settings.screenWidth * 0.12));
		g.drawPixmap(Assets.mop[2], (int) (Settings.screenWidth * 0.15),
				(int) (Settings.screenHeight * 0.8),
				(int) (Settings.screenWidth * 0.12),
				(int) (Settings.screenWidth * 0.12));
		g.drawPixmap(Assets.mop[4], (int) (Settings.screenWidth * 0.3),
				(int) (Settings.screenHeight * 0.78),
				(int) (Settings.screenWidth * 0.15),
				(int) (Settings.screenWidth * 0.15));

		if (!startGame)
			drawMenuUI();
		if (startGame)
			drawSelectUI();
	}

	private void drawMenuUI() {

		// button
		g.drawPixmap(Assets.btn_shop); // shop
		g.drawPixmap(Assets.btn_start); // start
		g.drawPixmap(Assets.btn_setting);// setting
		g.drawPixmap(Assets.btn_exit); // exit

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

	private void drawSelectUI() {
		g.drawPixmap(Assets.img_SelectTable,
				(int) (Settings.screenWidth * 0.25),
				(int) (Settings.screenHeight * 0.19),
				(int) (Settings.screenWidth * 0.5),
				(int) (Settings.screenHeight * 0.6));

		if (SongNum == 0) {
			g.drawPixmap(Assets.img_school,
					(int) (Settings.screenWidth * 0.4),
					(int) (Settings.screenHeight * 0.44),
					(int) (Settings.screenWidth * 0.2),
					(int) (Settings.screenHeight * 0.1));
		} else if (SongNum == 1) {
			g.drawPixmap(Assets.img_arirang,
					(int) (Settings.screenWidth * 0.4),
					(int) (Settings.screenHeight * 0.44),
					(int) (Settings.screenWidth * 0.2),
					(int) (Settings.screenHeight * 0.1));
		}

		g.drawPixmap(Assets.btn_SelectLeft);
		g.drawPixmap(Assets.btn_SelectRight);
		g.drawPixmap(Assets.btn_SelectConfirm);
		g.drawPixmap(Assets.btn_SelectBack);
	}

	@Override
	public void pause() {
		// Settings.save(game.getFileIO());
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispase() {
		// TODO Auto-generated method stub
	}

}
