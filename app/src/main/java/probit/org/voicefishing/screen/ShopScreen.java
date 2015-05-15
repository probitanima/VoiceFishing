package org.probit.voicefishing.screen;

import java.util.List;

import org.probit.voicefishing.framework.Game;
import org.probit.voicefishing.framework.Graphics;
import org.probit.voicefishing.framework.Pixmap;
import org.probit.voicefishing.framework.Screen;
import org.probit.voicefishing.framework.Graphics.PixmapFormat;
import org.probit.voicefishing.framework.Input.TouchEvent;
import org.probit.voicefishing.model.Item;
import org.probit.voicefishing.util.Assets;
import org.probit.voicefishing.util.SaveFile;
import org.probit.voicefishing.util.Settings;

import android.util.Log;

public class ShopScreen extends Screen {
	Graphics g;

	int itemKind;

	public ShopScreen(Game game) {
		super(game);
		g = game.getGraphics();

		// before present
		// <-- �̵��ϸ鼭 ������
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
				// ������ �·� �ѱ��
				if (inBounds(event, Assets.btn_left)) {
					if (itemKind > 0)
						itemKind--;
				}
				// ������ ��� �ѱ��
				else if (inBounds(event, Assets.btn_right)) {
					if (itemKind < Settings.savefile.item.size() - 1)
						itemKind++;
				}
				// ���� �ϱ�
				else if (inBounds(event, (int) (Settings.screenWidth * 0.6),
						(int) (Settings.screenHeight * 0.35),
						(int) (Settings.screenWidth * 0.3),
						(int) (Settings.screenHeight * 0.35)))
					selectItem();

				 else if (inBounds(event, 0,0,(int) (Settings.screenWidth * 0.1),
							(int) (Settings.screenHeight * 0.1)))
				 Settings.setCoin(10);

//				else if (inBounds(event, 520, 400, 120, 40)) {
//					Settings.savefile = new SaveFile();
//					Settings.getArray();
//				}
				// �ڷ� ����
				else if (inBounds(event, Assets.btn_back))
					game.setScreen(new MainMenuScreen(game));

				return;
			}
		}
	}

	public void init() {

	}

	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
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

	// select Item
	private void selectItem() {
		// ���� �������� ������
		if (Settings.savefile.item.get(itemKind).last > 0) {
			// Log.i("ShopScreen", "using!!");
			Settings.savefile.usingItem(itemKind);
			return;

			// lack of money
		} else if (Settings.savefile.coin < Settings.savefile.item
				.get(itemKind).price) {
			Log.i("ShopScreen", "no money");
			return;

			// buy it!
		} else
			Settings.buyItem(itemKind);

		Settings.save(game.getFileIO());
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

		g.drawPixmap(Assets.bnd_main); // main_board
//
//		// logo
//		g.drawPixmap(Assets.img_logo); // shop
		
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
		

		g.drawPixmap(Assets.btn_left);
		g.drawPixmap(Assets.btn_right);
		g.drawPixmap(Assets.btn_back);

		g.drawPixmap(Assets.bnd_itemTable, (int) (Settings.screenWidth * 0.55),
				(int) (Settings.screenHeight * 0.05),
				(int) (Settings.screenWidth * 0.4),
				(int) (Settings.screenHeight * 0.75));

		g.drawPixmap(Assets.img_category, (int) (Settings.screenWidth * 0.65),
				(int) (Settings.screenHeight * 0.2),
				(int) (Settings.screenWidth * 0.2),
				(int) (Settings.screenHeight * 0.1));

		g.drawPixmap(Assets.bnd_itemBox, (int) (Settings.screenWidth * 0.6),
				(int) (Settings.screenHeight * 0.3),
				(int) (Settings.screenWidth * 0.3),
				(int) (Settings.screenHeight * 0.45));

		// view to choose
		if (itemKind < 5) {
			if (Settings.savefile.item.get(itemKind).last > 0)
				g.drawPixmap(Assets.item_bundle[itemKind],
						(int) (Settings.screenWidth * 0.6),
						(int) (Settings.screenHeight * 0.4),
						(int) (Settings.screenWidth * 0.3),
						(int) (Settings.screenHeight * 0.35));// Į��(���ð���)->����
			else
				g.drawPixmap(Assets.item_reverse,
						(int) (Settings.screenWidth * 0.6),
						(int) (Settings.screenHeight * 0.4),
						(int) (Settings.screenWidth * 0.3),
						(int) (Settings.screenHeight * 0.35));// ���(���úҰ�)
		} else {
			if (Settings.savefile.item.get(itemKind).last > 0)
				g.drawPixmap(Assets.item_miki[itemKind - 5],
						(int) (Settings.screenWidth * 0.6),
						(int) (Settings.screenHeight * 0.4),
						(int) (Settings.screenWidth * 0.3),
						(int) (Settings.screenHeight * 0.35));// Į��(���ð���)->����
			else
				g.drawPixmap(Assets.item_miki_reverse[itemKind - 5],
						(int) (Settings.screenWidth * 0.6),
						(int) (Settings.screenHeight * 0.4),
						(int) (Settings.screenWidth * 0.3),
						(int) (Settings.screenHeight * 0.35)); // ���(���úҰ�)
		}

		if (itemKind < 5)
			g.drawPixmap(Assets.item_TxtDoubleRope[itemKind],
					(int) (Settings.screenWidth * 0.65),
					(int) (Settings.screenHeight * 0.33),
					(int) (Settings.screenWidth * 0.2),
					(int) (Settings.screenHeight * 0.1));// Į��(���ð���)->����
		else
			g.drawPixmap(Assets.item_TxtDoubleMiki[itemKind - 5],
					(int) (Settings.screenWidth * 0.65),
					(int) (Settings.screenHeight * 0.33),
					(int) (Settings.screenWidth * 0.2),
					(int) (Settings.screenHeight * 0.1));// Į��(���ð���)->����

	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispase() {
		// TODO Auto-generated method stub
		// before present
		// --> �̵��ϸ鼭 ������
		Settings.save(game.getFileIO());
	}

}
