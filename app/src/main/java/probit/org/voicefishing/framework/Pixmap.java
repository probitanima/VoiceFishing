package probit.org.voicefishing.framework;

import org.probit.voicefishing.framework.Graphics.PixmapFormat;

public interface Pixmap {
	public int getBitmapWidth();
	public int getBitmapHeight();
	public int getWidth();
	public int getHeight();
	public int getX();
	public int getY();
	
	public PixmapFormat getFormat();
	public void dispose();
}
