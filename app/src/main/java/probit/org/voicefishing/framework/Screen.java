package org.probit.voicefishing.framework;

public abstract class Screen {
	protected final Game game;
	
	public Screen(Game game) {
		this.game = game;
	}
	
	// Game �ν��Ͻ��� ���� ������ �� �ݺ����� �� �� �̵� �޼��带 ȣ��
	public abstract void update(float deltaTime);		// ȭ�� ���� ������Ʈ
	public abstract void present(float deltaTime);		// ȭ�� ���� ������
	
	// Game �ν��Ͻ��� ���� ȣ��Ǹ� ���� Ȱ��ȭ�� Screen�� ���� ���� 
	public abstract void pause();						// ������ �Ͻ� ����
	public abstract void resume();						// ���� �簳
	
	// Game.setScreen()�� ȣ��� �� Game �ν��Ͻ��� ȣ���Ѵ�.
	// Game �ν��Ͻ��� �� �޼��带 ���� ���� Screen�� ����, 
	// Screen.dispose() �޼���� �� ȭ���� ���� �����ؾ� �� ������ ���������� ������ �� �ִ� ����
	// Screen�� �� �����带 ���� ��� �ý��� �ڿ��� ��ȯ�ϰ� �����ν� �޸𸮻� �� ȭ���� �ڿ� ���� �����Ѵ�.
	public abstract void dispase();
}
