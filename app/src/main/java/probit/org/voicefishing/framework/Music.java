package probit.org.voicefishing.framework;

// for long sound

public interface Music {
	public void play();
	public void stop();
	public void pause();
	public void setLooping(boolean isLooping);
	public void setVolume(float volume);
	
	public boolean isPlaying();
	public boolean isStopped();
	public boolean isLooping();
	
	public void dispose();
}
