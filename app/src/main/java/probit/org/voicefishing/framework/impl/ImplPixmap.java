package org.probit.voicefishing.framework.impl;

import java.io.Serializable;

import org.probit.voicefishing.framework.Graphics.PixmapFormat;
import org.probit.voicefishing.framework.Pixmap;

import android.graphics.Bitmap;

public class ImplPixmap implements Pixmap, Serializable{
	Bitmap bitmap;
	PixmapFormat format;
	public int x;
	public int y;
	public int width;
	public int height;
	
	public ImplPixmap(Bitmap bitmap, PixmapFormat format) {
		this.bitmap = bitmap;
		this.format = format;
	}
	public ImplPixmap(Bitmap bitmap, PixmapFormat format, int x, int y, int width, int height) {
		this.bitmap = bitmap;
		this.format = format;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}
	
	@Override
	public int getBitmapWidth() {
		return bitmap.getWidth();
	}
	
	@Override
	public int getBitmapHeight() {
		return bitmap.getHeight();
	}
	
	
	@Override
	public int getX() {
		return x;
	}
	@Override
	public int getY() {
		return y;
	}
	@Override
	public PixmapFormat getFormat() {
		return format;
	}

	@Override
	public void dispose() {
		bitmap.recycle();
	}

}
