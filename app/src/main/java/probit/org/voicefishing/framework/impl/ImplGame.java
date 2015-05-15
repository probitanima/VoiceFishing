package org.probit.voicefishing.framework.impl;

import org.probit.voicefishing.framework.Audio;
import org.probit.voicefishing.framework.FileIO;
import org.probit.voicefishing.framework.Game;
import org.probit.voicefishing.framework.Graphics;
import org.probit.voicefishing.framework.Input;
import org.probit.voicefishing.framework.Pixmap;
import org.probit.voicefishing.framework.Screen;
import org.probit.voicefishing.util.Assets;
import org.probit.voicefishing.util.Settings;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public abstract class ImplGame extends Activity implements Game {

	// keep screen on
	static WakeLock mWakeLock;

	ImplView renderView;
	Graphics graphics;
	Audio audio;
	Input input;
	FileIO fileIO;
	Screen screen;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// ���̾�α� �׸��� ��ܿ� ���̾�α� ����ٰ� ���µ� �̸� ����ִ� ��ɾ� �Դϴ�.
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

		Settings.screenWidth = getWindowManager().getDefaultDisplay()
				.getWidth();
		Settings.screenHeight = getWindowManager().getDefaultDisplay()
				.getHeight();
		//
		int frameBufferWidth = isLandscape ? Settings.screenWidth
				: Settings.screenHeight;
		int frameBufferHeight = isLandscape ? Settings.screenHeight
				: Settings.screenWidth;

		// Log.i("ImplGame","Settings.screenWidth "+Settings.screenWidth);
		// Log.i("ImplGame","Settings.screenWidth "+Settings.screenHeight);
		//
		// Log.i("ImplGame","Settings.frameBufferWidth "+frameBufferWidth);
		// Log.i("ImplGame","Settings.frameBufferHeight "+frameBufferHeight);

		// int frameBufferWidth = Settings.screenWidth;
		// int frameBufferHeight = Settings.screenHeight;

		Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
				frameBufferHeight, Config.RGB_565);

		float scaleX = (float) frameBufferWidth / Settings.screenWidth;
		float scaleY = (float) frameBufferHeight / Settings.screenHeight;

		// Log.i(""+getWindowManager().getDefaultDisplay().getWidth(),""+getWindowManager().getDefaultDisplay().getHeight());
		// Log.i("isLandscape",""+isLandscape);
		// Log.i(""+scaleX,""+""+scaleY);

		renderView = new ImplView(this, frameBuffer);
		graphics = new ImplGraphics(getAssets(), frameBuffer);
		fileIO = new ImplFileIO(getAssets());
		audio = new ImplAudio(this);
		input = new ImplInput(this, renderView, scaleX, scaleY);
		screen = getStartScreen();
		setContentView(renderView);

		// keep screen on
		PowerManager pm = (PowerManager) this
				.getSystemService(Context.POWER_SERVICE);
		mWakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "App");
	}

	@Override
	public void onResume() {
		super.onResume();
		mWakeLock.acquire();
		screen.resume();
		renderView.resume();
	}

	@Override
	public void onPause() {
		super.onPause();
		mWakeLock.release();
		renderView.pause();
		screen.pause();
		input.disposePitch();

		if (isFinishing())
			screen.dispase();
	}

	@Override
	public Input getInput() {
		return input;
	}

	@Override
	public FileIO getFileIO() {
		return fileIO;
	}

	@Override
	public Audio getAudio() {
		return audio;
	}

	@Override
	public Graphics getGraphics() {
		return graphics;
	}

	@Override
	public void setScreen(Screen screen) {
		if (screen == null)
			try {
				throw new IllegalAccessException("Screen must not be null");
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		this.screen.pause();
		this.screen.dispase();
		screen.resume();
		screen.update(0);
		this.screen = screen;
	}

	@Override
	public Screen getCurrentScreen() {
		return screen;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		
		// common
		Assets.bonus_gauge.dispose();

		// loading
		Assets.bg_loading.dispose();

		// main
		Assets.bg_main.dispose();
		
		Assets.img_logo.dispose();
		Assets.img_coin.dispose();
		
		Assets.btn_shop.dispose();
		Assets.btn_setting.dispose();
		Assets.btn_exit.dispose();
		Assets.btn_start.dispose();
		
		Assets.bnd_exp.dispose();
		Assets.bnd_main.dispose();
		
		Assets.expGauge.dispose();	

		Assets.percent.dispose();
		
		
		Assets.btn_SelectBack.dispose();
		Assets.btn_SelectConfirm.dispose();
		Assets.img_SelectTable.dispose();
		Assets.btn_SelectLeft.dispose();
		Assets.btn_SelectRight.dispose();
		
		Assets.img_arirang.dispose();
		Assets.img_school.dispose();
		
		// shop
		Assets.btn_right.dispose();
		Assets.btn_left.dispose();
		Assets.btn_back.dispose();
		Assets.img_category.dispose();
		Assets.bnd_itemBox.dispose();
		Assets.bnd_itemTable.dispose();

		// setting	
		Assets.btn_settingNoise.dispose();
		Assets.btn_settingGenderMale.dispose();
		Assets.btn_settingGenderFemale.dispose();
		Assets.btn_settingGenderMalec.dispose();
		Assets.btn_settingGenderFemalec.dispose();
		Assets.btn_settingGender.dispose();
		Assets.bnd_settingTable.dispose();
		Assets.img_versionBox.dispose();
		
		Assets.bnd_fish.dispose();
		
		// result
		Assets.result.dispose();
		Assets.confirm.dispose();
		
		// game
		Assets.bg_game1.dispose();
		Assets.bg_game2.dispose();
		
		Assets.line.dispose();	
		Assets.scoreIcon.dispose();
		Assets.coinIcon.dispose();
		
		Assets.hpIcon.dispose();
		Assets.hpGauge.dispose();
		
		Assets.bonus_line.dispose();
		Assets.bonus_line_full.dispose();
		
		// pause
		Assets.btn_PauseBack.dispose();
		Assets.btn_PauseStop.dispose();
		Assets.bnd_PauseTable.dispose();

		
		// item
		Assets.item_reverse.dispose();
		
		
		Assets.twinkle.dispose();
		Assets.pause.dispose();

		Assets.db.dispose();
		Assets.sharp.dispose();
	}
}
