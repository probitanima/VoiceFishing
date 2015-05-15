package probit.org.voicefishing.framework;

public interface Game {
	public Input getInput();
	
	public FileIO getFileIO();
	
	public Audio getAudio();
	
	public Graphics getGraphics();
	
	public void setScreen(Screen screen);		// Game�� ���� Screen�� ����
	
	public Screen getCurrentScreen();			// ���� Ȱ��ȭ�� Screen�� ��ȯ
	
	public Screen getStartScreen();				// ���� �ȵ�
	
	public void quit();
	
}
