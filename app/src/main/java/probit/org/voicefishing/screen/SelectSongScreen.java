package org.probit.voicefishing.screen;

import java.util.List;

import org.probit.voicefishing.framework.Game;
import org.probit.voicefishing.framework.Graphics;
import org.probit.voicefishing.framework.Screen;
import org.probit.voicefishing.framework.Input.TouchEvent;
import org.probit.voicefishing.util.Assets;
import org.probit.voicefishing.util.Settings;

public class SelectSongScreen extends Screen {
	Graphics g;
	int songIndex;

	public SelectSongScreen(Game game) {
		super(game);
		g = game.getGraphics();
		songIndex = 0;
	}

	// �ҷ� �°� ����
	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvent();

		// �Ͻ� ���� ��ư ���� ���� Ȯ��
		int len = touchEvents.size();

		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(0);

			if (event.type == TouchEvent.TOUCH_UP) {
				// ����
				if (inBounds(event, Assets.btn_shop.getX(), Assets.btn_shop.getY(), Assets.btn_shop.getWidth(), Assets.btn_shop.getHeight()))
					game.setScreen(new ShopScreen(game));
				
				// �¹�ư
				else if (inBounds(event, Assets.btn_shop.getX(), Assets.btn_shop.getY(), Assets.btn_shop.getWidth(), Assets.btn_shop.getHeight()))
					game.setScreen(new ShopScreen(game));
				
				// ���ư
				else if (inBounds(event, Assets.btn_shop.getX(), Assets.btn_shop.getY(), Assets.btn_shop.getWidth(), Assets.btn_shop.getHeight()))
					game.setScreen(new ShopScreen(game));

				else
					game.setScreen(new MainMenuScreen(game));
				return;
			}
		}
	}

	private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y && event.y < y + height - 1)
			return true;
		else
			return false;
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
