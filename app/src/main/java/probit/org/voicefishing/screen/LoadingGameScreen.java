package org.probit.voicefishing.screen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.probit.voicefishing.framework.FileIO;
import org.probit.voicefishing.framework.Game;
import org.probit.voicefishing.framework.Graphics;
import org.probit.voicefishing.framework.Pixmap;
import org.probit.voicefishing.framework.Screen;
import org.probit.voicefishing.framework.Graphics.PixmapFormat;
import org.probit.voicefishing.model.GameSea;
import org.probit.voicefishing.model.Sea;
import org.probit.voicefishing.model.SongSea;
import org.probit.voicefishing.pitch.CalCul;
import org.probit.voicefishing.util.Assets;
import org.probit.voicefishing.util.Settings;

import android.util.Log;

public class LoadingGameScreen extends Screen {
	//Graphics g;
	Sea sea;
	public LoadingGameScreen(Game game, int songNum) {
		super(game);

		sea = new Sea();
		
		if(songNum==0) 
			sea.loadNotes(game.getFileIO(), "note/school.csv");
		
		else if(songNum == 1)
			sea.loadNotes(game.getFileIO(), "note/arirang.csv");
		
		Log.i("LoadingGameScreen", "LoadingGameScreen");
		
		Settings.setNoteCrit();
	}

	// �ҷ� �°� ����
	@Override
	public void update(float deltaTime) {
	}

	@Override
	public void present(float deltaTime) {

		/*
		 * //test//--------- tickTime += deltaTime;
		 * 
		 * // Change State of Ready Thing if (tickTime < TICK_INITIAL) return;
		 * 
		 * tickTime -= TICK_INITIAL;
		 * 
		 * g.drawPixmap(Assets.background, 0, 0, Settings.screenWidth,
		 * Settings.screenHeight);
		 * 
		 * if (count == 12) { game.setScreen(new GameScreen(game)); return; }
		 * 
		 * Pixmap pixmap = null;
		 * 
		 * pixmap = Assets.loading[count];
		 * 
		 * if (pixmap != null) g.drawPixmap(pixmap, (Settings.screenWidth / 2) -
		 * (pixmap.getWidth()/2), (Settings.screenHeight / 2) -
		 * (pixmap.getHeight()/2)); count++;
		 */
		game.setScreen(new GameScreen(game, sea));
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

	}

}
