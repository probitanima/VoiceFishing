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
	
	AssetManager assets;			// newSound()를 호출할 때 자원 파일로부터 효과음을 로드해 SoundPool에 집어넣는 데 필요하다
	SoundPool soundPool;

	public ImplAudio(Activity activity) {
		// Activity를 넘겨주는 이유
		// mediaStream에 대한  볼륨을 제어할 수 있음
		// AssetManager 인스턴스에 접근해 이를 클래스의 멤버로 저장할 수 있다.
		
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.assets = activity.getAssets();
		this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);		// 20개의 효과음을 재생할 수 있게 설정 했음
	}
	
	@Override
	public Music newMusic(String fileName) {
		try {
			// 파일 경로를 이용하여 asset에 접근
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
