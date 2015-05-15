package org.probit.voicefishing.framework.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ImplView extends SurfaceView implements Runnable {
	ImplGame game;

	Bitmap framebuffer;
	Thread renderThread = null;
	SurfaceHolder holder;
	volatile boolean running = false;

	public ImplView(ImplGame game, Bitmap framebuffer) {
		super(game);
		this.game = game;
		this.framebuffer = framebuffer;
		this.holder = getHolder();
	}

	public void resume() {
		running = true;
		renderThread = new Thread(this);
		renderThread.start();
	}

	@Override
	public void run() {
		
		Rect dstRect = new Rect();
		long startTime = System.nanoTime();

		while (running) {
			if (!holder.getSurface().isValid())
				continue;

			float deltaTime = (System.nanoTime() - startTime) / 1000000000.0f;
			startTime = System.nanoTime();

			game.getCurrentScreen().update(deltaTime);
			game.getCurrentScreen().present(deltaTime);

			Canvas canvas = holder.lockCanvas();

			// Surfaceview�� �����ϴ� ��� �簢���� ���� ������ ��
			// dstRect�� top�� left��� ���� 0, 0���� ����
			canvas.getClipBounds(dstRect);

			canvas.drawBitmap(framebuffer, null, dstRect, null);
			holder.unlockCanvasAndPost(canvas);
		}
	}

	public void pause() {
		running = false;
		while (true) {
			try {
				renderThread.join();
				break;
			} catch (InterruptedException e) {
				// ��õ�
			}
		}
	}

}