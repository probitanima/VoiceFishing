package org.probit.voicefishing.framework.impl;

import java.io.IOException;

import org.probit.voicefishing.framework.Audio;
import org.probit.voicefishing.framework.Music;
import org.probit.voicefishing.framework.Sound;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

public class ImplAudio implements Audio{
	
	AssetManager assets;			// newSound()�� ȣ���� �� �ڿ� ���Ϸκ��� ȿ������ �ε��� SoundPool�� ����ִ� �� �ʿ��ϴ�
	SoundPool soundPool;

	public ImplAudio(Activity activity) {
		// Activity�� �Ѱ��ִ� ����
		// mediaStream�� ����  ������ ������ �� ����
		// AssetManager �ν��Ͻ��� ������ �̸� Ŭ������ ����� ������ �� �ִ�.
		
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.assets = activity.getAssets();
		this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);		// 20���� ȿ������ ����� �� �ְ� ���� ����
	}
	
	@Override
	public Music newMusic(String fileName) {
		try {
			// ���� ��θ� �̿��Ͽ� asset�� ����
			AssetFileDescriptor assetDescriptor = assets.openFd(fileName);
			return new ImplMusic(assetDescriptor);
			
		} catch (IOException e) {
			throw new RuntimeException("Couldn't load music '" +fileName + "'");
		}
	}

	@Override
	public Sound newSound(String fileName) {
		//
		try {
			AssetFileDescriptor assetDescriptor = assets.openFd(fileName);
			// 
			int soundId = soundPool.load(assetDescriptor, 0);
			return new ImplSound(soundPool, soundId);
			
		} catch (IOException e) {
			throw new RuntimeException("Couldn't load sound '" +fileName + "'");
		}
	}

}
