package org.probit.voicefishing.screen;

import org.probit.voicefishing.framework.Game;
import org.probit.voicefishing.framework.Graphics;
import org.probit.voicefishing.framework.Pixmap;
import org.probit.voicefishing.framework.Screen;
import org.probit.voicefishing.framework.Graphics.PixmapFormat;
import org.probit.voicefishing.model.Item;
import org.probit.voicefishing.util.Assets;
import org.probit.voicefishing.util.Settings;

import android.util.Log;

public class LoadingScreen extends Screen {
	Graphics g;

	public LoadingScreen(Game game) {
		super(game);
		g = game.getGraphics();

		// loading
		Assets.bg_loading = g.newPixmap("img/bg/loading/bgLoading.jpg",
				PixmapFormat.ARGB4444);

		// main button
		Assets.bg_main = g.newPixmap("img/bg/bgmain.jpg", PixmapFormat.ARGB4444);

		Assets.btn_start = g.newPixmap("img/main/btn_start.png",
				PixmapFormat.ARGB4444, (int) (Settings.screenWidth * 0.6),
				(int) (Settings.screenHeight * 0.25),
				(int) (Settings.screenWidth * 0.3),
				(int) (Settings.screenHeight * 0.15));
		Assets.btn_setting = g.newPixmap("img/main/btn_setting.png",
				PixmapFormat.ARGB4444, (int) (Settings.screenWidth * 0.6),
				(int) (Settings.screenHeight * 0.5),
				(int) (Settings.screenWidth * 0.3),
				(int) (Settings.screenHeight * 0.15));
		Assets.btn_exit = g.newPixmap("img/main/btn_exit.png",
				PixmapFormat.ARGB4444, (int) (Settings.screenWidth * 0.6),
				(int) (Settings.screenHeight * 0.75),
				(int) (Settings.screenWidth * 0.3),
				(int) (Settings.screenHeight * 0.15));

		Assets.btn_shop = g.newPixmap("img/main/btn_shop.png",
				PixmapFormat.ARGB4444, (int) (Settings.screenWidth * 0.78),
				(int) (Settings.screenHeight * 0.07),
				(int) (Settings.screenWidth * 0.15),
				(int) (Settings.screenHeight * 0.1));

		Assets.img_logo = g.newPixmap("img/main/logo.png",
				PixmapFormat.ARGB4444, (int) (Settings.screenWidth * 0.03),
				(int) (Settings.screenHeight * 0.01),
				(int) (Settings.screenWidth * 0.2),
				(int) (Settings.screenHeight * 0.2));
		// end button

		// main
		Assets.bnd_main = g.newPixmap("img/main/main.png",
				PixmapFormat.ARGB4444, (int) (Settings.screenWidth * 0.1),
				(int) (Settings.screenHeight * 0.1),
				(int) (Settings.screenWidth * 0.4),
				(int) (Settings.screenHeight * 0.7));

		Assets.bnd_exp = g.newPixmap("img/main/exp.png", PixmapFormat.ARGB4444,
				(int) (Settings.screenWidth * 0.23),
				(int) (Settings.screenHeight * 0.23),
				(int) (Settings.screenWidth * 0.25),
				(int) (Settings.screenHeight * 0.08));

		// main

		Assets.img_coin = g.newPixmap("img/main/coin.png",
				PixmapFormat.ARGB4444);

		Assets.expGauge = g.newPixmap("img/main/exp_progress.png",
				PixmapFormat.ARGB4444);
		Assets.percent = g.newPixmap("img/main/percent.png",
				PixmapFormat.ARGB4444);
		// common
		Assets.numberWhite = new Pixmap[10];
		Assets.numberWhite[0] = g.newPixmap("img/number/c0.png",
				PixmapFormat.ARGB4444);
		Assets.numberWhite[1] = g.newPixmap("img/number/c1.png",
				PixmapFormat.ARGB4444);
		Assets.numberWhite[2] = g.newPixmap("img/number/c2.png",
				PixmapFormat.ARGB4444);
		Assets.numberWhite[3] = g.newPixmap("img/number/c3.png",
				PixmapFormat.ARGB4444);
		Assets.numberWhite[4] = g.newPixmap("img/number/c4.png",
				PixmapFormat.ARGB4444);
		Assets.numberWhite[5] = g.newPixmap("img/number/c5.png",
				PixmapFormat.ARGB4444);
		Assets.numberWhite[6] = g.newPixmap("img/number/c6.png",
				PixmapFormat.ARGB4444);
		Assets.numberWhite[7] = g.newPixmap("img/number/c7.png",
				PixmapFormat.ARGB4444);
		Assets.numberWhite[8] = g.newPixmap("img/number/c8.png",
				PixmapFormat.ARGB4444);
		Assets.numberWhite[9] = g.newPixmap("img/number/c9.png",
				PixmapFormat.ARGB4444);

		Assets.numberBlue = new Pixmap[10];
		Assets.numberBlue[0] = g.newPixmap("img/number/snd0.png",
				PixmapFormat.ARGB4444);
		Assets.numberBlue[1] = g.newPixmap("img/number/snd1.png",
				PixmapFormat.ARGB4444);
		Assets.numberBlue[2] = g.newPixmap("img/number/snd2.png",
				PixmapFormat.ARGB4444);
		Assets.numberBlue[3] = g.newPixmap("img/number/snd3.png",
				PixmapFormat.ARGB4444);
		Assets.numberBlue[4] = g.newPixmap("img/number/snd4.png",
				PixmapFormat.ARGB4444);
		Assets.numberBlue[5] = g.newPixmap("img/number/snd5.png",
				PixmapFormat.ARGB4444);
		Assets.numberBlue[6] = g.newPixmap("img/number/snd6.png",
				PixmapFormat.ARGB4444);
		Assets.numberBlue[7] = g.newPixmap("img/number/snd7.png",
				PixmapFormat.ARGB4444);
		Assets.numberBlue[8] = g.newPixmap("img/number/snd8.png",
				PixmapFormat.ARGB4444);
		Assets.numberBlue[9] = g.newPixmap("img/number/snd9.png",
				PixmapFormat.ARGB4444);

		Assets.numberBlack = new Pixmap[10];
		Assets.numberBlack[0] = g.newPixmap("img/number/score_0.png",
				PixmapFormat.ARGB4444);
		Assets.numberBlack[1] = g.newPixmap("img/number/score_1.png",
				PixmapFormat.ARGB4444);
		Assets.numberBlack[2] = g.newPixmap("img/number/score_2.png",
				PixmapFormat.ARGB4444);
		Assets.numberBlack[3] = g.newPixmap("img/number/score_3.png",
				PixmapFormat.ARGB4444);
		Assets.numberBlack[4] = g.newPixmap("img/number/score_4.png",
				PixmapFormat.ARGB4444);
		Assets.numberBlack[5] = g.newPixmap("img/number/score_5.png",
				PixmapFormat.ARGB4444);
		Assets.numberBlack[6] = g.newPixmap("img/number/score_6.png",
				PixmapFormat.ARGB4444);
		Assets.numberBlack[7] = g.newPixmap("img/number/score_7.png",
				PixmapFormat.ARGB4444);
		Assets.numberBlack[8] = g.newPixmap("img/number/score_8.png",
				PixmapFormat.ARGB4444);
		Assets.numberBlack[9] = g.newPixmap("img/number/score_9.png",
				PixmapFormat.ARGB4444);

		Assets.numberSmallBlack = new Pixmap[10];
		Assets.numberSmallBlack[0] = g.newPixmap("img/number/xp_0.png",
				PixmapFormat.ARGB4444);
		Assets.numberSmallBlack[1] = g.newPixmap("img/number/xp_1.png",
				PixmapFormat.ARGB4444);
		Assets.numberSmallBlack[2] = g.newPixmap("img/number/xp_2.png",
				PixmapFormat.ARGB4444);
		Assets.numberSmallBlack[3] = g.newPixmap("img/number/xp_3.png",
				PixmapFormat.ARGB4444);
		Assets.numberSmallBlack[4] = g.newPixmap("img/number/xp_4.png",
				PixmapFormat.ARGB4444);
		Assets.numberSmallBlack[5] = g.newPixmap("img/number/xp_5.png",
				PixmapFormat.ARGB4444);
		Assets.numberSmallBlack[6] = g.newPixmap("img/number/xp_6.png",
				PixmapFormat.ARGB4444);
		Assets.numberSmallBlack[7] = g.newPixmap("img/number/xp_7.png",
				PixmapFormat.ARGB4444);
		Assets.numberSmallBlack[8] = g.newPixmap("img/number/xp_8.png",
				PixmapFormat.ARGB4444);
		Assets.numberSmallBlack[9] = g.newPixmap("img/number/xp_9.png",
				PixmapFormat.ARGB4444);

		// select
		Assets.btn_SelectLeft = g.newPixmap("img/main/select/left.png",
				PixmapFormat.ARGB4444, (int) (Settings.screenWidth * 0.32),
				(int) (Settings.screenHeight * 0.43),
				(int) (Settings.screenWidth * 0.05),
				(int) (Settings.screenHeight * 0.1));
		Assets.btn_SelectRight = g.newPixmap("img/main/select/right.png",
				PixmapFormat.ARGB4444, (int) (Settings.screenWidth * 0.63),
				(int) (Settings.screenHeight * 0.43),
				(int) (Settings.screenWidth * 0.05),
				(int) (Settings.screenHeight * 0.1));

		Assets.btn_SelectConfirm = g.newPixmap("img/main/select/confirm.png",
				PixmapFormat.ARGB4444, (int) (Settings.screenWidth * 0.34),
				(int) (Settings.screenHeight * 0.58),
				(int) (Settings.screenWidth * 0.15),
				(int) (Settings.screenHeight * 0.15));

		Assets.btn_SelectBack = g.newPixmap("img/main/select/back.png",
				PixmapFormat.ARGB4444, (int) (Settings.screenWidth * 0.51),
				(int) (Settings.screenHeight * 0.58),
				(int) (Settings.screenWidth * 0.15),
				(int) (Settings.screenHeight * 0.15));

		Assets.img_SelectTable = g.newPixmap("img/main/select/bigtable.png",
				PixmapFormat.ARGB4444);

		Assets.img_arirang = g.newPixmap("img/main/select/arirang.png",
				PixmapFormat.ARGB4444);
		Assets.img_school = g.newPixmap("img/main/select/school.png",
				PixmapFormat.ARGB4444);

		// shop
		Assets.btn_left = g.newPixmap("img/shop/left.png",
				PixmapFormat.ARGB4444, (int) (Settings.screenWidth * 0.6),
				(int) (Settings.screenHeight * 0.2),
				(int) (Settings.screenWidth * 0.05),
				(int) (Settings.screenWidth * 0.05));
		Assets.btn_right = g.newPixmap("img/shop/right.png",
				PixmapFormat.ARGB4444, (int) (Settings.screenWidth * 0.85),
				(int) (Settings.screenHeight * 0.2),
				(int) (Settings.screenWidth * 0.05),
				(int) (Settings.screenWidth * 0.05));
		Assets.btn_back = g.newPixmap("img/shop/back.png",
				PixmapFormat.ARGB4444, (int) (Settings.screenWidth * 0.85),
				(int) (Settings.screenHeight * 0.82),
				(int) (Settings.screenWidth * 0.08),
				(int) (Settings.screenWidth * 0.08));
		Assets.img_category = g.newPixmap("img/shop/category.png",
				PixmapFormat.ARGB4444);
		Assets.bnd_itemBox = g.newPixmap("img/shop/itembox.png",
				PixmapFormat.ARGB4444);
		Assets.bnd_itemTable = g.newPixmap("img/shop/shoptable.png",
				PixmapFormat.ARGB4444);

		// setting
		// Assets.bg_setting = g.newPixmap("img/bg/bgSetting.png",
		// PixmapFormat.ARGB4444);
		// Assets.bg_note = g.newPixmap("img/bg/bgNote.png",
		// PixmapFormat.ARGB4444);
		// Assets.bg_noise = g.newPixmap("img/bg/bgNoise.png",
		// PixmapFormat.ARGB4444);

		Assets.btn_settingGender = g.newPixmap("img/setting/setGender.png",
				PixmapFormat.ARGB4444, (int) (Settings.screenWidth * 0.58),
				(int) (Settings.screenHeight * 0.23),
				(int) (Settings.screenWidth * 0.15),
				(int) (Settings.screenHeight * 0.13));

		Assets.btn_settingGenderMale = g.newPixmap("img/setting/men.png",
				PixmapFormat.ARGB4444, (int) (Settings.screenWidth * 0.6),
				(int) (Settings.screenHeight * 0.38),
				(int) (Settings.screenWidth * 0.1),
				(int) (Settings.screenHeight * 0.15));

		Assets.btn_settingGenderFemale = g.newPixmap("img/setting/women.png",
				PixmapFormat.ARGB4444, (int) (Settings.screenWidth * 0.8),
				(int) (Settings.screenHeight * 0.38),
				(int) (Settings.screenWidth * 0.1),
				(int) (Settings.screenHeight * 0.15));

		Assets.btn_settingGenderMalec = g.newPixmap("img/setting/c_men.png",
				PixmapFormat.ARGB4444, (int) (Settings.screenWidth * 0.6),
				(int) (Settings.screenHeight * 0.38),
				(int) (Settings.screenWidth * 0.1),
				(int) (Settings.screenHeight * 0.15));

		Assets.btn_settingGenderFemalec = g.newPixmap(
				"img/setting/c_women.png", PixmapFormat.ARGB4444,
				(int) (Settings.screenWidth * 0.8),
				(int) (Settings.screenHeight * 0.38),
				(int) (Settings.screenWidth * 0.1),
				(int) (Settings.screenHeight * 0.15));

		Assets.btn_settingNoise = g.newPixmap("img/setting/setNoise.png",
				PixmapFormat.ARGB4444, (int) (Settings.screenWidth * 0.58),
				(int) (Settings.screenHeight * 0.6),
				(int) (Settings.screenWidth * 0.34),
				(int) (Settings.screenHeight * 0.1));

		Assets.bnd_fish = g.newPixmap("img/setting/popup.png",
				PixmapFormat.ARGB4444);

		Assets.bnd_settingTable = g.newPixmap("img/setting/settingtable.png",
				PixmapFormat.ARGB4444);
		Assets.img_versionBox = g.newPixmap("img/setting/versionbox.png",
				PixmapFormat.ARGB4444);

		Assets.db = g.newPixmap("img/number/dB.png", PixmapFormat.ARGB4444);
		Assets.sharp = g.newPixmap("img/number/sharp.png",
				PixmapFormat.ARGB4444);

		Assets.notes = new Pixmap[12];
		Assets.notes[0] = g.newPixmap("img/number/do.png",
				PixmapFormat.ARGB4444);
		Assets.notes[1] = g.newPixmap("img/number/do.png",
				PixmapFormat.ARGB4444);
		Assets.notes[2] = g.newPixmap("img/number/re.png",
				PixmapFormat.ARGB4444);
		Assets.notes[3] = g.newPixmap("img/number/re.png",
				PixmapFormat.ARGB4444);
		Assets.notes[4] = g.newPixmap("img/number/mi.png",
				PixmapFormat.ARGB4444);
		Assets.notes[5] = g.newPixmap("img/number/pa.png",
				PixmapFormat.ARGB4444);
		Assets.notes[6] = g.newPixmap("img/number/pa.png",
				PixmapFormat.ARGB4444);
		Assets.notes[7] = g.newPixmap("img/number/sol.png",
				PixmapFormat.ARGB4444);
		Assets.notes[8] = g.newPixmap("img/number/sol.png",
				PixmapFormat.ARGB4444);
		Assets.notes[9] = g.newPixmap("img/number/ra.png",
				PixmapFormat.ARGB4444);
		Assets.notes[10] = g.newPixmap("img/number/ra.png",
				PixmapFormat.ARGB4444);
		Assets.notes[11] = g.newPixmap("img/number/si.png",
				PixmapFormat.ARGB4444);

		// game
		Assets.bg_game1 = g.newPixmap("img/bg/11.jpg", PixmapFormat.ARGB4444);
		Assets.bg_game2 = g.newPixmap("img/bg/22.jpg", PixmapFormat.ARGB4444);

		Assets.line = g.newPixmap("img/game/line.png", PixmapFormat.ARGB4444);
		Assets.scoreIcon = g.newPixmap("img/game/scoreIcon.png",
				PixmapFormat.ARGB4444);
		Assets.coinIcon = g.newPixmap("img/game/coinIcon.png",
				PixmapFormat.ARGB4444);

		Assets.hpIcon = g.newPixmap("img/game/him_line.png",
				PixmapFormat.ARGB4444, (int) (Settings.screenWidth * 0.55),
				(int) (Settings.screenHeight * 0.05),
				(int) (Settings.screenWidth * 0.32),
				(int) (Settings.screenHeight * 0.15));

		Assets.hpGauge = g.newPixmap("img/game/him_gauge.png",
				PixmapFormat.ARGB4444);
		Assets.bonus_line = g.newPixmap("img/game/bonus_line.png",
				PixmapFormat.ARGB4444, (int) (Settings.screenWidth * 0.3),
				(int) (Settings.screenHeight * 0.85),
				(int) (Settings.screenWidth * 0.4),
				(int) (Settings.screenHeight * 0.15));
		Assets.bonus_line_full = g.newPixmap("img/game/bonus_line_full.png",
				PixmapFormat.ARGB4444, (int) (Settings.screenWidth * 0.3),
				(int) (Settings.screenHeight * 0.85),
				(int) (Settings.screenWidth * 0.4),
				(int) (Settings.screenHeight * 0.15));
		Assets.bonus_gauge = g.newPixmap("img/game/bonus_gauge.png",
				PixmapFormat.ARGB4444);

		Assets.fishing_net = new Pixmap[4];
		Assets.fishing_net[0] = g.newPixmap("img/game/mop/net/net0.png",
				PixmapFormat.ARGB4444);
		Assets.fishing_net[1] = g.newPixmap("img/game/mop/net/net1.png",
				PixmapFormat.ARGB4444);
		Assets.fishing_net[2] = g.newPixmap("img/game/mop/net/net2.png",
				PixmapFormat.ARGB4444);
		Assets.fishing_net[3] = g.newPixmap("img/game/mop/net/net3.png",
				PixmapFormat.ARGB4444);

		Assets.pause = g.newPixmap("img/game/pause.png", PixmapFormat.ARGB4444,
				(int) (Settings.screenWidth * 0.9),
				(int) (Settings.screenHeight * 0.05),
				(int) (Settings.screenWidth * 0.08),
				(int) (Settings.screenWidth * 0.08));

		Assets.ink = new Pixmap[11];
		Assets.ink[0] = g.newPixmap("img/effect/ink1.png",
				PixmapFormat.ARGB4444);
		Assets.ink[1] = g.newPixmap("img/effect/ink2.png",
				PixmapFormat.ARGB4444);
		Assets.ink[2] = g.newPixmap("img/effect/ink3.png",
				PixmapFormat.ARGB4444);
		Assets.ink[3] = g.newPixmap("img/effect/ink4.png",
				PixmapFormat.ARGB4444);
		Assets.ink[4] = g.newPixmap("img/effect/ink5.png",
				PixmapFormat.ARGB4444);
		Assets.ink[5] = g.newPixmap("img/effect/ink6.png",
				PixmapFormat.ARGB4444);
		Assets.ink[6] = g.newPixmap("img/effect/ink7.png",
				PixmapFormat.ARGB4444);
		Assets.ink[7] = g.newPixmap("img/effect/ink8.png",
				PixmapFormat.ARGB4444);
		Assets.ink[8] = g.newPixmap("img/effect/ink9.png",
				PixmapFormat.ARGB4444);
		Assets.ink[9] = g.newPixmap("img/effect/ink10.png",
				PixmapFormat.ARGB4444);
		Assets.ink[10] = g.newPixmap("img/effect/ink11.png",
				PixmapFormat.ARGB4444);

		Assets.item_TxtRope = new Pixmap[5];
		Assets.item_TxtRope[0] = g.newPixmap("img/shop/txt/line0.png",
				PixmapFormat.ARGB4444);
		Assets.item_TxtRope[1] = g.newPixmap("img/shop/txt/line1.png",
				PixmapFormat.ARGB4444);
		Assets.item_TxtRope[2] = g.newPixmap("img/shop/txt/line2.png",
				PixmapFormat.ARGB4444);
		Assets.item_TxtRope[3] = g.newPixmap("img/shop/txt/line3.png",
				PixmapFormat.ARGB4444);
		Assets.item_TxtRope[4] = g.newPixmap("img/shop/txt/line4.png",
				PixmapFormat.ARGB4444);

		Assets.item_TxtMiki = new Pixmap[3];
		Assets.item_TxtMiki[0] = g.newPixmap("img/shop/txt/miki0.png",
				PixmapFormat.ARGB4444);
		Assets.item_TxtMiki[1] = g.newPixmap("img/shop/txt/miki1.png",
				PixmapFormat.ARGB4444);
		Assets.item_TxtMiki[2] = g.newPixmap("img/shop/txt/miki2.png",
				PixmapFormat.ARGB4444);

		Assets.item_TxtDoubleRope = new Pixmap[5];
		Assets.item_TxtDoubleRope[0] = g.newPixmap("img/shop/txt/tline0.png",
				PixmapFormat.ARGB4444);
		Assets.item_TxtDoubleRope[1] = g.newPixmap("img/shop/txt/tline1.png",
				PixmapFormat.ARGB4444);
		Assets.item_TxtDoubleRope[2] = g.newPixmap("img/shop/txt/tline2.png",
				PixmapFormat.ARGB4444);
		Assets.item_TxtDoubleRope[3] = g.newPixmap("img/shop/txt/tline3.png",
				PixmapFormat.ARGB4444);
		Assets.item_TxtDoubleRope[4] = g.newPixmap("img/shop/txt/tline4.png",
				PixmapFormat.ARGB4444);

		Assets.item_TxtDoubleMiki = new Pixmap[5];
		Assets.item_TxtDoubleMiki[0] = g.newPixmap("img/shop/txt/tmiki0.png",
				PixmapFormat.ARGB4444);
		Assets.item_TxtDoubleMiki[1] = g.newPixmap("img/shop/txt/tmiki1.png",
				PixmapFormat.ARGB4444);
		Assets.item_TxtDoubleMiki[2] = g.newPixmap("img/shop/txt/tmiki2.png",
				PixmapFormat.ARGB4444);

		// pause
		Assets.btn_PauseBack = g.newPixmap("img/game/pause/back.png",
				PixmapFormat.ARGB4444, (int) (Settings.screenWidth * 0.35),
				(int) (Settings.screenHeight * 0.38),
				(int) (Settings.screenWidth * 0.3),
				(int) (Settings.screenHeight * 0.15));

		Assets.btn_PauseStop = g.newPixmap("img/game/pause/stop.png",
				PixmapFormat.ARGB4444, (int) (Settings.screenWidth * 0.35),
				(int) (Settings.screenHeight * 0.55),
				(int) (Settings.screenWidth * 0.3),
				(int) (Settings.screenHeight * 0.15));

		Assets.bnd_PauseTable = g.newPixmap("img/game/pause/table.png",
				PixmapFormat.ARGB4444, (int) (Settings.screenWidth * 0.25),
				(int) (Settings.screenHeight * 0.2),
				(int) (Settings.screenWidth * 0.5),
				(int) (Settings.screenHeight * 0.6));

		// result
		Assets.result = g.newPixmap("img/result/result.png",
				PixmapFormat.ARGB4444, (int) (Settings.screenWidth * 0.25),
				(int) (Settings.screenHeight * 0.15),
				(int) (Settings.screenWidth * 0.5),	
				(int) (Settings.screenHeight * 0.7));
		
		Assets.confirm = g.newPixmap("img/result/confirm.png",
				PixmapFormat.ARGB4444, (int) (Settings.screenWidth * 0.42),
				(int) (Settings.screenHeight * 0.7),
				(int) (Settings.screenWidth * 0.16),
				(int) (Settings.screenHeight * 0.1));
		// mop
		Assets.mop = new Pixmap[12];
		Assets.mop[0] = g.newPixmap("img/game/mop/effect.png",
				PixmapFormat.ARGB4444);
		Assets.mop[1] = g.newPixmap("img/game/mop/mop1.png",
				PixmapFormat.ARGB4444);
		Assets.mop[2] = g.newPixmap("img/game/mop/mop2.png",
				PixmapFormat.ARGB4444);
		Assets.mop[3] = g.newPixmap("img/game/mop/mop3.png",
				PixmapFormat.ARGB4444);
		Assets.mop[4] = g.newPixmap("img/game/mop/mop4.png",
				PixmapFormat.ARGB4444);
		Assets.mop[5] = g.newPixmap("img/game/mop/coin5.png",
				PixmapFormat.ARGB4444);
		Assets.mop[6] = g.newPixmap("img/game/mop/coin6.png",
				PixmapFormat.ARGB4444);
		Assets.mop[7] = g.newPixmap("img/game/mop/mop7.png",
				PixmapFormat.ARGB4444);
		Assets.mop[8] = g.newPixmap("img/game/mop/mop8.png",
				PixmapFormat.ARGB4444);
		Assets.mop[9] = g.newPixmap("img/game/mop/mop9.png",
				PixmapFormat.ARGB4444);
		Assets.mop[10] = g.newPixmap("img/game/mop/mop_bonus.png",
				PixmapFormat.ARGB4444);
		Assets.mop[11] = g.newPixmap("img/game/mop/mop_speed.png",
				PixmapFormat.ARGB4444);

		// end of mop

		Assets.twinkle = g.newPixmap("img/game/mop/effect.png",
				PixmapFormat.ARGB4444);

		// item set
		Assets.item_bundle = new Pixmap[5];
		Assets.item_bundle[0] = g.newPixmap("img/game/item/bundle0.png",
				PixmapFormat.ARGB4444);
		Assets.item_bundle[1] = g.newPixmap("img/game/item/bundle1.png",
				PixmapFormat.ARGB4444);
		Assets.item_bundle[2] = g.newPixmap("img/game/item/bundle2.png",
				PixmapFormat.ARGB4444);
		Assets.item_bundle[3] = g.newPixmap("img/game/item/bundle3.png",
				PixmapFormat.ARGB4444);
		Assets.item_bundle[4] = g.newPixmap("img/game/item/bundle4.png",
				PixmapFormat.ARGB4444);

		Assets.item_reverse = g.newPixmap("img/game/item/r_bundle.png",
				PixmapFormat.ARGB4444);

		Assets.item_rope = new Pixmap[5];
		Assets.item_rope[0] = g.newPixmap("img/game/item/rope0.png",
				PixmapFormat.ARGB4444);
		Assets.item_rope[1] = g.newPixmap("img/game/item/rope1.png",
				PixmapFormat.ARGB4444);
		Assets.item_rope[2] = g.newPixmap("img/game/item/rope2.png",
				PixmapFormat.ARGB4444);
		Assets.item_rope[3] = g.newPixmap("img/game/item/rope3.png",
				PixmapFormat.ARGB4444);
		Assets.item_rope[4] = g.newPixmap("img/game/item/rope4.png",
				PixmapFormat.ARGB4444);

		Assets.item_hook = new Pixmap[2];
		Assets.item_hook[0] = g.newPixmap("img/game/item/hook0.png",
				PixmapFormat.ARGB4444);
		Assets.item_hook[1] = g.newPixmap("img/game/item/hook1.png",
				PixmapFormat.ARGB4444);

		Assets.item_miki = new Pixmap[3];
		Assets.item_miki[0] = g.newPixmap("img/game/item/itemMiki0.png",
				PixmapFormat.ARGB4444);
		Assets.item_miki[1] = g.newPixmap("img/game/item/itemMiki1.png",
				PixmapFormat.ARGB4444);
		Assets.item_miki[2] = g.newPixmap("img/game/item/itemMiki2.png",
				PixmapFormat.ARGB4444);

		Assets.item_miki_reverse = new Pixmap[3];
		Assets.item_miki_reverse[0] = g.newPixmap(
				"img/game/item/r_itemMiki0.png", PixmapFormat.ARGB4444);
		Assets.item_miki_reverse[1] = g.newPixmap(
				"img/game/item/r_itemMiki1.png", PixmapFormat.ARGB4444);
		Assets.item_miki_reverse[2] = g.newPixmap(
				"img/game/item/r_itemMiki2.png", PixmapFormat.ARGB4444);

		if (!Settings.load(game.getFileIO())) {
			Settings.save(game.getFileIO());
		}

		// icons
		Settings.setNoteLocation();

	}

	// �ҷ� �°� ����
	@Override
	public void update(float deltaTime) {
		game.setScreen(new LogoScreen(game));
	}

	@Override
	public void present(float deltaTime) {

		/*
		 * for test //------------------------ tickTime += deltaTime;
		 * 
		 * // Change State of Ready Thing if (tickTime < TICK_INITIAL) return;
		 * 
		 * tickTime -= TICK_INITIAL;
		 * 
		 * g.drawPixmap(Assets.background, 0, 0, Settings.screenWidth,
		 * Settings.screenHeight);
		 * 
		 * if (count == 12) { game.setScreen(new LogoScreen(game)); return; }
		 * 
		 * Pixmap pixmap = null;
		 * 
		 * pixmap = Assets.loading[count];
		 * 
		 * if (pixmap != null) g.drawPixmap(pixmap, (Settings.screenWidth / 2) -
		 * (pixmap.getWidth() / 2), (Settings.screenHeight / 2) -
		 * (pixmap.getHeight() / 2)); count++;
		 */// ------------------------
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
