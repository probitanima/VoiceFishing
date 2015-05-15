package org.probit.voicefishing.framework.impl;

import java.io.IOException;
import java.io.InputStream;

import org.probit.voicefishing.framework.Graphics;
import org.probit.voicefishing.framework.Pixmap;
import org.probit.voicefishing.util.Settings;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Paint.Style;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class ImplGraphics implements Graphics {
	AssetManager assets;
	Bitmap frameBuffer;
	Canvas canvas;
	Paint paint;
	Rect srcRect = new Rect();
	Rect dstRect = new Rect();

	public ImplGraphics(AssetManager assets, Bitmap frameBuffer) {
		this.assets = assets;
		this.frameBuffer = frameBuffer;
		this.canvas = new Canvas(frameBuffer);
		this.paint = new Paint();
	}
	
	@Override
	public Pixmap newPixmap(String fileName, PixmapFormat format, int x, int y, int width, int height) {

		Config config = null;

		if (format == PixmapFormat.RGB565)
			config = Config.RGB_565;
		else if (format == PixmapFormat.ARGB4444)
			config = Config.ARGB_4444;
		else if (format == PixmapFormat.ARGB8888)
			config = Config.ARGB_8888;

		// 원하는 색상의 형식을 설정
		Options option = new Options();
		option.inPreferredConfig = config;

		InputStream in = null;
		Bitmap bitmap = null;

		try {
			in = assets.open(fileName);
			bitmap = BitmapFactory.decodeStream(in); // 자원으로 통해 bitmap을 로드
			if (bitmap == null)
				throw new RuntimeException("no bitmap from asset");
		} catch (IOException e) {
			throw new RuntimeException("no bitmap from asset");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {

				}
			}
		}

		if (bitmap.getConfig() == Config.RGB_565)
			config = Config.RGB_565;
		else if (bitmap.getConfig() == Config.ARGB_4444)
			config = Config.ARGB_4444;
		else if (bitmap.getConfig() == Config.ARGB_8888)
			config = Config.ARGB_8888;

		return new ImplPixmap(bitmap, format, x, y, width, height);
	}

	@Override
	public Pixmap newPixmap(String fileName, PixmapFormat format) {

		Config config = null;

		if (format == PixmapFormat.RGB565)
			config = Config.RGB_565;
		else if (format == PixmapFormat.ARGB4444)
			config = Config.ARGB_4444;
		else if (format == PixmapFormat.ARGB8888)
			config = Config.ARGB_8888;

		// 원하는 색상의 형식을 설정
		Options option = new Options();
		option.inPreferredConfig = config;

		InputStream in = null;
		Bitmap bitmap = null;

		try {
			in = assets.open(fileName);
			bitmap = BitmapFactory.decodeStream(in); // 자원으로 통해 bitmap을 로드
			if (bitmap == null)
				throw new RuntimeException("no bitmap from asset");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("no bitmap from asset");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {

				}
			}
		}

		if (bitmap.getConfig() == Config.RGB_565)
			config = Config.RGB_565;
		else if (bitmap.getConfig() == Config.ARGB_4444)
			config = Config.ARGB_4444;
		else if (bitmap.getConfig() == Config.ARGB_8888)
			config = Config.ARGB_8888;

		return new ImplPixmap(bitmap, format);
	}

	// 지정된 32비트 ARGB 색상 매개변수 중 적, 녹, 청 색 요소를 추출
	// 인공버퍼를 이 색상으로 초기화하는 일을 함
	@Override
	public void clear(int color) {
		canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8,
				(color & 0xff));
	}

	// canvas.drawPoint을 통해 인공 버퍼에 픽셀을 그림
	@Override
	public void drawPixel(int x, int y, int color) {
		paint.setColor(color);
		canvas.drawPoint(x, y, paint);
	}

	@Override
	public void drawLine(int x, int y, int x2, int y2, int color) {
		paint.setColor(color);
		canvas.drawLine(x, y, x2, y2, paint);
	}

	@Override
	public void drawRect(int x, int y, int width, int height, int color) {
		paint.setColor(color);
		paint.setStyle(Style.FILL);
		canvas.drawRect(x, y, x + width - 1, y + width - 1, paint);
	}

	@Override
	public void drawPixmap(Pixmap pixmap, int x, int y, int srcWidth,
			int srcHeight) {
		// srcRect.left = srcX;
		// srcRect.top = srcY;
		// srcRect.right = srcX + srcWidth -1;
		// srcRect.bottom = srcY + srcHeight -1;
		dstRect.left = x;
		dstRect.top = y;
		dstRect.right = x + srcWidth-1;
		dstRect.bottom = y + srcHeight-1;

		canvas.drawBitmap(((ImplPixmap) pixmap).bitmap, null, dstRect, null);
	}

	public void drawText(String text, int x, int y) {
		canvas.drawText(text, x, y, new Paint());
	}

	@Override
	public void drawPixmap(Pixmap pixmap) {
		// srcRect.left = srcX;
		// srcRect.top = srcY;
		// srcRect.right = srcX + srcWidth -1;
		// srcRect.bottom = srcY + srcHeight -1;
		dstRect.left = pixmap.getX();
		dstRect.top = pixmap.getY();
		dstRect.right = pixmap.getX() + pixmap.getWidth();
		dstRect.bottom = pixmap.getY() + pixmap.getHeight();

		canvas.drawBitmap(((ImplPixmap) pixmap).bitmap, null, dstRect, null);
	}

	@Override
	public void drawPixmap(Pixmap pixmap, int x, int y) {
		canvas.drawBitmap(((ImplPixmap) pixmap).bitmap, x, y, null);
	}

	@Override
	public int getWidth() {
		return frameBuffer.getWidth();
	}

	@Override
	public int getHeight() {
		return frameBuffer.getHeight();
	}

	@Override
	public void drawPixmap(Pixmap pixmap, int srcX, int srcY, int srcWidth,
			int srcHeight, int dstX, int dstY, int dstWidth, int dstHeight) {

		srcRect.set(srcX, srcY, srcX + srcWidth - 1, srcY + srcHeight - 1);
//		Log.i("ImplGraphics",""+srcRect.left+" "+srcRect.top+" "+srcRect.right+" "+srcRect.bottom);

		dstRect.set(dstX, dstY, dstX + dstWidth - 1, dstY + dstHeight - 1);
//		Log.i("ImplGraphics",""+dstRect.left+" "+dstRect.top+" "+dstRect.right+" "+dstRect.bottom);

		canvas.drawBitmap(((ImplPixmap) pixmap).bitmap, srcRect, dstRect, null);

	}


}
