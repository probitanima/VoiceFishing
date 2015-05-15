package org.probit.voicefishing.framework.impl;

import java.io.IOException;

import org.probit.voicefishing.framework.Music;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

public class ImplMusic implements Music, OnCompletionListener{

	MediaPlayer mediaPlayer;			// 
	boolean isPrepared = false;			// mediaPlayer가 준비된 이후에 사용 가능하기 때문에 사용하는 변수
	
	public ImplMusic(AssetFileDescriptor assetDescriptor) {
		mediaPlayer = new MediaPlayer();
		try {
			mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(), assetDescriptor.getStartOffset(), assetDescriptor.getLength());
			mediaPlayer.prepare();
			isPrepared = true;
			mediaPlayer.setOnCompletionListener(this);		// 재생을 중단할 때는 다른 메서드를 호출하기전에 MediaPlayer를 다시 준비시켜야 한다.
		} catch (Exception e) {
			throw new RuntimeException("Couldn't load music");
		}
	}
	
	@Override
	public void play() {
		
		// 실행중이면 빠져 나가기
		if(mediaPlayer.isPlaying())
			return;
		
		try {
			synchronized (this) {
				if(!isPrepared)
					mediaPlayer.prepare();
				mediaPlayer.start();
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop() {
		mediaPlayer.stop();
		synchronized (this) {
			isPrepared = false;
		}
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLooping(boolean isLooping) {
		mediaPlayer.setLooping(isLooping);
	}

	@Override
	public void setVolume(float volume) {
		mediaPlayer.setVolume(volume, volume);
		
	}

	@Override
	public boolean isPlaying() {
		return mediaPlayer.isPlaying();
	}

	@Override
	public boolean isStopped() {
		return !isPrepared;
	}

	@Override
	public boolean isLooping() {
		return mediaPlayer.isLooping();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		if(mediaPlayer.isPlaying())
			mediaPlayer.stop();
		mediaPlayer.release();
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		synchronized (this) {
			isPrepared = false;
		}
	}

}
