package org.probit.voicefishing.screen;

import java.util.List;

import org.probit.voicefishing.framework.Game;
import org.probit.voicefishing.framework.Graphics;
import org.probit.voicefishing.framework.Input.PitchEvent;
import org.probit.voicefishing.framework.Input.TouchEvent;
import org.probit.voicefishing.framework.Pixmap;
import org.probit.voicefishing.framework.Screen;
import org.probit.voicefishing.model.BackGround;
import org.probit.voicefishing.model.Mop.MopState;
import org.probit.voicefishing.model.GotTimer;
import org.probit.voicefishing.model.Sea;
import org.probit.voicefishing.model.SongSea;
import org.probit.voicefishing.pitch.CalCul;
import org.probit.voicefishing.util.Assets;
import org.probit.voicefishing.util.Settings;

import android.util.Log;

public class GameScreen extends Screen {

	enum GameState {
		Ready, Running, Pause, GameOver
	}

	GameState state = GameState.Running;

	private Graphics g;
	private Sea sea;
	private BackGround bg;

	private float waitTime; // �Է��� �ѵ��� �ȹ����� �� ������ hook�� ����

	int diff;

	public int scoreArray[] = new int[5];
	public int coinArray[] = new int[5];
	public int expArray[] = new int[5];

	public GameScreen(Game game, Sea sea) {
		super(game);

		this.sea = sea;
		
		g = game.getGraphics();
		
		bg = new BackGround();

		waitTime = 0;
		
		game.getInput().checkPitchOn();
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvent();
		List<PitchEvent> pitchEvents = game.getInput().getPitchEvent();

		game.getInput().getKeyEvent();

		if (state == GameState.Ready)
			updateReady(touchEvents);
		if (state == GameState.Pause)
			updatePuase(touchEvents);
		if (state == GameState.GameOver)
			updateGameOver(touchEvents);
		if (state == GameState.Running) {
			updateRunning(touchEvents, pitchEvents, deltaTime);
			sea.getCatch();
		}
	}

	private void updateReady(List<TouchEvent> touchEvents) {
		if (touchEvents.size() > 0)
			state = GameState.Running;
	}

	private void updateRunning(List<TouchEvent> touchEvents, List<PitchEvent> pitchEvents, float deltaTime) {
		// �Ͻ� ���� ��ư ���� ���� Ȯ��
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);

			if (event.type == TouchEvent.TOUCH_UP) {

				if (inBounds(event, Assets.pause)) {
					state = GameState.Pause;
					return;
				}
			}
		}

		len = pitchEvents.size();

		for (int i = 0; i < len; i++) {
			PitchEvent event = pitchEvents.get(i);

			//diff = sea.mops.get(sea.itemsLast).y - CalCul.returnID(event.frequency);

			//
//			if (-12 < diff && diff < 12) {
//				bg.seenVarious = sea.setFisherManLocation(event.frequency);
//				Log.i("GameScreen",""+CalCul.returnNote(event.frequency));
//			}
			
			bg.seenVarious = sea.setFisherManLocation(event.frequency);
//			Log.i("GameScreen",""+CalCul.returnNote(event.frequency));
			
			waitTime = 0.01f;
			//

			//
			// bonus
			if (event.amp > 100000) {
				sea.doBonus();
			}
		}

		bg.checkSeen();

		// ���� ������ ������-----------------------------------
		if (waitTime != 0) {
			waitTime += deltaTime;
		}

//		if (waitTime > 0.4) {
//			sea.setFisherManLocation(0);
//			bg.seenVarious = 0;
//			bg.checkSeen();
//			waitTime = 0;
//		}
		// ------------------------------------------------
		sea.update(deltaTime);
		// �� ����
		if (sea.gameOver) {
			gameOver();
			state = GameState.GameOver;
		}
	}

	private void gameOver() {
		int coin = sea.coin;
		int score = sea.score;
		int exp = coin + score;

		Settings.resultGame(coin, score);

		// int all_exp = (int) Settings.savefile.experience;

		int index = 0;
		for (int j = 10000; j > 0; j = j / 10, index++) {
			expArray[index] = (exp / j);
			exp = exp - (expArray[index] * j);

			scoreArray[index] = (score / j);
			score = score - (scoreArray[index] * j);

			coinArray[index] = (coin / j);
			coin = coin - (coinArray[index] * j);
		}

		Settings.save(game.getFileIO());
	}

	private void updatePuase(List<TouchEvent> touchEvents) {
		// �Ͻ� ���� ��ư ���� ���� Ȯ��
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);

			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, Assets.btn_PauseBack)) {
					state = GameState.Running;
				}

				else if (inBounds(event, Assets.btn_PauseStop)) {
					gameOver();
					state = GameState.GameOver;
				}
				return;
			}
			// }
		}
	}

	private void updateGameOver(List<TouchEvent> touchEvents) {
		// �Ͻ� ���� ��ư ���� ���� Ȯ��
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);

			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, Assets.confirm))
					game.setScreen(new MainMenuScreen(game));
				return;
			}
		}
	}

	private boolean inBounds(TouchEvent event, Pixmap button) {

		if (event.x > button.getX() && event.x < button.getX() + button.getWidth() - 1 && event.y > button.getY()
				&& event.y < button.getY() + button.getHeight() - 1)
			return true;
		else
			return false;
	}

	@Override
	public void present(float deltaTime) {
		drawSea();

		if (state == GameState.Running)
			drawRunningUI(deltaTime);
		if (state == GameState.Pause)
			drawPausedUI();
		if (state == GameState.GameOver)
			drawResultUI();
	}

	private void drawRunningUI(float deltaTime) {

		// ���
		// ���� ��
		// ���� ����
		// ���� �ٴ� & ������

		// line
		for (int i = 0; i < 36; i = i + 2) {
			g.drawPixmap(Assets.line, 
					(int) (Settings.screenWidth * 0.1), 
					Settings.noteCritPosition.get(i) - (int) (Settings.screenHeight * 3 * bg.seenRate / 100), 
					(int) (Settings.screenWidth * 0.9), (int) (Settings.screenHeight * 0.02));

		}
		drawInfo();
		
		sea.fisherMan.getTime(deltaTime);
		drawMop(sea);

		sea.effectInk.getTime(deltaTime);
		drawInk(sea);
		
		if(sea.gt != null) {
			if(sea.gt.mode <= 10) {
				g.drawPixmap(sea.gt.mop.getImgAsset(),
					(int) sea.fisherMan.location_x, 
					(int) (sea.fisherMan.loc_show) - (int) (Settings.screenHeight * 0.025),
					(int) (Settings.screenWidth * 0.07),
					(int) (Settings.screenWidth * 0.07));
				sea.gt.mode++;
			}
			else
				sea.gt = null;
		}
	}

	private void drawSea() {
		// draw BackGround //

		g.drawPixmap(Assets.bg_game1, (int) bg.bgTime1, (int) (Assets.bg_game1.getBitmapHeight() * bg.seenRate / 100), Assets.bg_game1.getBitmapWidth() / 4,
				Assets.bg_game1.getBitmapHeight() / 3, 0, 0, Settings.screenWidth, Settings.screenHeight);

		g.drawPixmap(Assets.bg_game2, (int) bg.bgTime2, (int) (Assets.bg_game2.getBitmapHeight() * bg.seenRate / 100), Assets.bg_game2.getBitmapWidth() / 4,
				Assets.bg_game2.getBitmapHeight() / 3, 0, 0, Settings.screenWidth, Settings.screenHeight);

		bg.setSeenRate();

	}

	private void drawInfo() {

		// score icon //
		g.drawPixmap(Assets.scoreIcon, (int) (Settings.screenWidth * 0.03), (int) (Settings.screenHeight * 0.05),
				(int) (Settings.screenWidth * 0.12), (int) (Settings.screenHeight * 0.1));

		// score
		g.drawPixmap(Assets.numberWhite[sea.scoreArray[0]], (int) (Settings.screenWidth * 0.15), (int) (Settings.screenHeight * 0.05),
				(int) (Settings.screenWidth * 0.05), (int) (Settings.screenHeight * 0.1));
		g.drawPixmap(Assets.numberWhite[sea.scoreArray[1]], (int) (Settings.screenWidth * 0.18), (int) (Settings.screenHeight * 0.05),
				(int) (Settings.screenWidth * 0.05), (int) (Settings.screenHeight * 0.1));
		g.drawPixmap(Assets.numberWhite[sea.scoreArray[2]], (int) (Settings.screenWidth * 0.21), (int) (Settings.screenHeight * 0.05),
				(int) (Settings.screenWidth * 0.05), (int) (Settings.screenHeight * 0.1));
		g.drawPixmap(Assets.numberWhite[sea.scoreArray[3]], (int) (Settings.screenWidth * 0.24), (int) (Settings.screenHeight * 0.05),
				(int) (Settings.screenWidth * 0.05), (int) (Settings.screenHeight * 0.1));
		g.drawPixmap(Assets.numberWhite[sea.scoreArray[4]], (int) (Settings.screenWidth * 0.27), (int) (Settings.screenHeight * 0.05),
				(int) (Settings.screenWidth * 0.05), (int) (Settings.screenHeight * 0.1));

		
		
		// hp
		
		// him & gauge
		g.drawPixmap(Assets.hpGauge, 0, 0, (int) (Assets.hpGauge.getBitmapWidth() * sea.hp / sea.maxHp),
				Assets.hpGauge.getBitmapHeight(), (int) (Settings.screenWidth * 0.6), (int) (Settings.screenHeight * 0.1),
				(int) (Settings.screenWidth * 0.27) * sea.hp / sea.maxHp , (int) (Settings.screenHeight * 0.08));
		
		// him icon	
		g.drawPixmap(Assets.hpIcon);

		// draw coin icon //
		g.drawPixmap(Assets.coinIcon, (int) (Settings.screenWidth * 0.7), 
				(int) (Settings.screenHeight * 0.01), 
				(int) (Settings.screenWidth * 0.05),
				(int) (Settings.screenWidth * 0.05));

		// coin
		g.drawPixmap(Assets.numberWhite[sea.coinArray[0]], (int) (Settings.screenWidth * 0.75), (int) (Settings.screenHeight * 0.02),
				(int) (Settings.screenWidth * 0.04), (int) (Settings.screenHeight * 0.08));
		g.drawPixmap(Assets.numberWhite[sea.coinArray[1]], (int) (Settings.screenWidth * 0.77), (int) (Settings.screenHeight * 0.02),
				(int) (Settings.screenWidth * 0.04), (int) (Settings.screenHeight * 0.08));
		g.drawPixmap(Assets.numberWhite[sea.coinArray[2]], (int) (Settings.screenWidth * 0.79), (int) (Settings.screenHeight * 0.02),
				(int) (Settings.screenWidth * 0.04), (int) (Settings.screenHeight * 0.08));
		g.drawPixmap(Assets.numberWhite[sea.coinArray[3]], (int) (Settings.screenWidth * 0.81), (int) (Settings.screenHeight * 0.02),
				(int) (Settings.screenWidth * 0.04), (int) (Settings.screenHeight * 0.08));
		g.drawPixmap(Assets.numberWhite[sea.coinArray[4]], (int) (Settings.screenWidth * 0.83), (int) (Settings.screenHeight * 0.02),
				(int) (Settings.screenWidth * 0.04), (int) (Settings.screenHeight * 0.08));

		// pause
		g.drawPixmap(Assets.pause);

		// bonus & gauge
		g.drawPixmap(Assets.bonus_gauge, 0, 0, (int) (Assets.bonus_gauge.getBitmapWidth() * sea.bonusGauge / 15),
				Assets.bonus_gauge.getBitmapHeight(), (int) (Settings.screenWidth * 0.35), (int) (Settings.screenHeight * 0.91),
				(int) (Settings.screenWidth * 0.35) * sea.bonusGauge / 15 , (int) (Settings.screenHeight * 0.07));

		// bonus line //
		if (sea.bonus > 0)
			g.drawPixmap(Assets.bonus_line_full);
		else
			g.drawPixmap(Assets.bonus_line);
	}

	private void drawMop(Sea sea) {

		// Fish
		Pixmap fishPixmap = null;

		for (int i = sea.itemsDone; i < sea.itemsShown; i++) {

			fishPixmap = Assets.mop[0];

			if (sea.mops.get(i).state == MopState.Running) {
				g.drawPixmap(sea.mops.get(i).getImgAsset(), 
						sea.mops.get(i).x,
						(Settings.noteCritPosition.get(sea.mops.get(i).y) - (int) (Settings.screenHeight * 3 * bg.seenRate / 100)) - (int) (Settings.screenHeight*0.025),
						(int) (Settings.screenWidth * 0.07), (int) (Settings.screenWidth * 0.07));

			} else if (sea.mops.get(i).state == MopState.Got) {
				
				g.drawPixmap(Assets.twinkle, sea.mops.get(i).x + 20, (Settings.noteCritPosition.get(sea.mops.get(i).y) - (int) (Settings.screenHeight
						* 3 * bg.seenRate / 100)));
				sea.mops.get(i).setState(MopState.Done);
			}
		}

		// hook
		switch (sea.fisherMan.mode) {
		case 0:
			g.drawPixmap(Assets.item_rope[sea.usingItem[0]], (int) (sea.fisherMan.location_x) - (int) (Settings.screenWidth * 0.01),
					(int) (sea.fisherMan.loc_show) - (int) (Settings.screenHeight * 0.83), 
					(int) (Settings.screenWidth * 0.03),
					(int) (Settings.screenHeight * 0.9));

			if (sea.usingItem[1] != -1)
				g.drawPixmap(Assets.item_hook[1], (int) sea.fisherMan.location_x, (int) (sea.fisherMan.loc_show),
						(int) (Settings.screenHeight * 0.05), (int) (Settings.screenHeight * 0.05));
			else
				g.drawPixmap(Assets.item_hook[0], (int) sea.fisherMan.location_x, (int) (sea.fisherMan.loc_show),
						(int) (Settings.screenHeight * 0.05), (int) (Settings.screenHeight * 0.05));
			break;
			
		case 1:
			g.drawPixmap(Assets.fishing_net[0], (int) (Settings.screenWidth * 0.1), (int) (Settings.screenHeight * 0.1),
					(int) (Settings.screenHeight * 0.6), (int) (Settings.screenHeight * 0.8));
			break;
		case 2:
			g.drawPixmap(Assets.fishing_net[1], (int) (Settings.screenWidth * 0.1), (int) (Settings.screenHeight * 0.1),
					(int) (Settings.screenHeight * 0.6), (int) (Settings.screenHeight * 0.8));
			break;
		case 3:
			g.drawPixmap(Assets.fishing_net[2], (int) (Settings.screenWidth * 0.1), (int) (Settings.screenHeight * 0.1),
					(int) (Settings.screenHeight * 0.6), (int) (Settings.screenHeight * 0.8));
			break;
		case 4:
			g.drawPixmap(Assets.fishing_net[3], (int) (Settings.screenWidth * 0.1), (int) (Settings.screenHeight * 0.1),
					(int) (Settings.screenHeight * 0.6), (int) (Settings.screenHeight * 0.8));
			break;
		}
		// End of Fish
	}

	private void drawInk(Sea sea) {
		// drawInk
		if (sea.inkLeftTime > 0) {
			g.drawPixmap(Assets.ink[sea.effectInk.mode], (int) (Settings.screenWidth * 0.05), (int) (Settings.screenHeight * 0.05),
					(int) (Settings.screenWidth * 0.9), (int) (Settings.screenHeight * 0.9));
		}
	}

	private void drawPausedUI() {
		drawInfo();

		g.drawPixmap(Assets.bnd_PauseTable);

		g.drawPixmap(Assets.btn_PauseBack);
		g.drawPixmap(Assets.btn_PauseStop);
	}

	private void drawResultUI() {
		g.drawPixmap(Assets.result);

		// score
		g.drawPixmap(Assets.numberBlack[scoreArray[0]], (int) (Settings.screenWidth * 0.42), (int) (Settings.screenHeight * 0.38),
				(int) (Settings.screenWidth * 0.04), (int) (Settings.screenHeight * 0.10));
		g.drawPixmap(Assets.numberBlack[scoreArray[1]], (int) (Settings.screenWidth * 0.45), (int) (Settings.screenHeight * 0.38),
				(int) (Settings.screenWidth * 0.04), (int) (Settings.screenHeight * 0.10));
		g.drawPixmap(Assets.numberBlack[scoreArray[2]], (int) (Settings.screenWidth * 0.48), (int) (Settings.screenHeight * 0.38),
				(int) (Settings.screenWidth * 0.04), (int) (Settings.screenHeight * 0.10));
		g.drawPixmap(Assets.numberBlack[scoreArray[3]], (int) (Settings.screenWidth * 0.51), (int) (Settings.screenHeight * 0.38),
				(int) (Settings.screenWidth * 0.04), (int) (Settings.screenHeight * 0.10));
		g.drawPixmap(Assets.numberBlack[scoreArray[4]], (int) (Settings.screenWidth * 0.54), (int) (Settings.screenHeight * 0.38),
				(int) (Settings.screenWidth * 0.04), (int) (Settings.screenHeight * 0.10));
		
		// exp
		g.drawPixmap(Assets.numberSmallBlack[expArray[0]], (int) (Settings.screenWidth * 0.58), (int) (Settings.screenHeight * 0.49),
				(int) (Settings.screenWidth * 0.05), (int) (Settings.screenHeight * 0.1));
		g.drawPixmap(Assets.numberSmallBlack[expArray[1]], (int) (Settings.screenWidth * 0.6), (int) (Settings.screenHeight * 0.49),
				(int) (Settings.screenWidth * 0.05), (int) (Settings.screenHeight * 0.1));
		g.drawPixmap(Assets.numberSmallBlack[expArray[2]], (int) (Settings.screenWidth * 0.62), (int) (Settings.screenHeight * 0.49),
				(int) (Settings.screenWidth * 0.05), (int) (Settings.screenHeight * 0.1));
		g.drawPixmap(Assets.numberSmallBlack[expArray[3]], (int) (Settings.screenWidth * 0.64), (int) (Settings.screenHeight * 0.49),
				(int) (Settings.screenWidth * 0.05), (int) (Settings.screenHeight * 0.1));
		g.drawPixmap(Assets.numberSmallBlack[expArray[4]], (int) (Settings.screenWidth * 0.66), (int) (Settings.screenHeight * 0.49),
				(int) (Settings.screenWidth * 0.05), (int) (Settings.screenHeight * 0.1));

		// coin
		g.drawPixmap(Assets.numberSmallBlack[coinArray[0]], (int) (Settings.screenWidth * 0.58), (int) (Settings.screenHeight * 0.57),
				(int) (Settings.screenWidth * 0.05), (int) (Settings.screenHeight * 0.1));
		g.drawPixmap(Assets.numberSmallBlack[coinArray[1]], (int) (Settings.screenWidth * 0.6), (int) (Settings.screenHeight * 0.57),
				(int) (Settings.screenWidth * 0.05), (int) (Settings.screenHeight * 0.1));
		g.drawPixmap(Assets.numberSmallBlack[coinArray[2]], (int) (Settings.screenWidth * 0.62), (int) (Settings.screenHeight * 0.57),
				(int) (Settings.screenWidth * 0.05), (int) (Settings.screenHeight * 0.1));
		g.drawPixmap(Assets.numberSmallBlack[coinArray[3]], (int) (Settings.screenWidth * 0.64), (int) (Settings.screenHeight * 0.57),
				(int) (Settings.screenWidth * 0.05), (int) (Settings.screenHeight * 0.1));
		g.drawPixmap(Assets.numberSmallBlack[coinArray[4]], (int) (Settings.screenWidth * 0.66), (int) (Settings.screenHeight * 0.57),
				(int) (Settings.screenWidth * 0.05), (int) (Settings.screenHeight * 0.1));
		
		g.drawPixmap(Assets.confirm);

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
		game.getInput().checkPitchOff();
	}

}