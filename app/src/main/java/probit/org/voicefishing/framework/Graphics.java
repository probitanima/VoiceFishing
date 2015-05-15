package probit.org.voicefishing.framework;

import java.io.Serializable;

public interface Graphics extends Serializable {
	// ���� �ٸ� �ȼ� ������ ���ڵ�
	public static enum PixmapFormat {
		ARGB8888, ARGB4444, RGB565
	}
	
	// JPEG �Ǵ� PNG �������� �� �̹��� �ε�
	public Pixmap newPixmap(String fileName, PixmapFormat format, int x, int y, int srcWidth, int srcHeight);
//	public Pixmap newPixmap(String fileName, PixmapFormat format, int x, int y);
	public Pixmap newPixmap(String fileName, PixmapFormat format);
	public void clear(int color);
	
	public void drawPixel(int x, int y, int color);
	public void drawLine(int x, int y, int x2, int y2, int color);
	public void drawRect(int x, int y, int width, int height, int color);
	public void drawPixmap(Pixmap pixmap, int x, int y, int srcWidth, int srcHeight);
	public void drawPixmap(Pixmap pixmap, int x, int y);
	public void drawPixmap(Pixmap pixmap);
	public void drawPixmap(Pixmap pixmap, int srcX, int srcY, int srcWidth,
			int srcHeight, int dstX, int dstY, int dstWidth, int dstHeight);
	public void drawText(String text, int x, int y);
	
	public int getWidth();
	public int getHeight();
}
