package org.probit.voicefishing.screen;

import java.util.List;

import org.probit.voicefishing.framework.Game;
import org.probit.voicefishing.framework.Graphics;
import org.probit.voicefishing.framework.Screen;
import org.probit.voicefishing.framework.Input.TouchEvent;
import org.probit.voicefishing.util.Assets;
import org.probit.voicefishing.util.Settings;

public class LogoScreen extends Screen {
	Graphics g;

	public LogoScreen(Game game) {
		super(game);
		g = game.getGraphics();
		init();
	}
	
	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvent();

		int len = touchEvents.size();
		
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(0);

			if (event.type == TouchEvent.TOUCH_UP) {
				game.setScreen(new MainMenuScreen(game));
				return;
			}
		}
	}
	
	public void init() {
		g.drawPixmap(Assets.bg_loading, 0, 0, Settings.screenWidth,
				Settings.screenHeight);
	}

	@Override
	public void present(float deltaTime) {
		
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispase() {
	}

}
