package org.probit.voicefishing.pitch;

import org.probit.voicefishing.util.Settings;

import ca.uol.aig.fftpack.RealDoubleFFT;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

public class PitchDetector extends Thread {

	private PitchDetectorListener mCallback;
	// private float mCenter;

	// ------���� ����----------------------------//
	private final static int RATE = 8000;		// voice(4000Hz) * 2 = 8000Hz
	private final static int CHANNEL_MODE = AudioFormat.CHANNEL_CONFIGURATION_MONO;
	private final static int ENCODING = AudioFormat.ENCODING_PCM_16BIT;
	private final static int CHUNK_SIZE_IN_SAMPLES = 2048; // = 2 ^
	// ------------------------------------------------------------------------------------//

	private int result;

	boolean chk_run;
	boolean chk_pitch;
	private double best_frequency;
	private double best_amplitude;

	public PitchDetector(PitchDetectorListener callback) {
		mCallback = callback;
		chk_run = true;
		chk_pitch=false;
	}
	
	public void checkOn() {
		Log.i("PitchDetector", "checkOn");
		chk_pitch = true;
	}
	
	public void checkOff() {
		Log.i("PitchDetector", "checkOff");
		chk_pitch = false;
	}

	public void dispose() {
		chk_run = false;
		chk_pitch = false;
	}

	// public void setPitchCenter(float center) {
	// //mCenter = center;
	// }

	public void run() {
		super.run();
		//Log.i("PitchDetector", "Run");
		AudioRecord recorder = new AudioRecord(MediaRecorder.AudioSource.MIC,
				RATE, CHANNEL_MODE, ENCODING, CHUNK_SIZE_IN_SAMPLES);

		if (recorder.getState() != AudioRecord.STATE_INITIALIZED) {
			// ShowError("Can't initialize AudioRecord");
			Log.i("PitchDetector", "Can't initialize AudioRecord");
			return;
		}

		// ���� �� ����-----------------------------------------//
		short[] short_buffer = new short[CHUNK_SIZE_IN_SAMPLES];
		double[] double_buffer = new double[CHUNK_SIZE_IN_SAMPLES];

		

		RealDoubleFFT transformer; // FFT ��ü
		transformer = new RealDoubleFFT(CHUNK_SIZE_IN_SAMPLES);

		recorder.startRecording();
		try {
			while (chk_run) {
				if (chk_pitch) {
					int min_frequency_fft = Math.round(Settings.MIN_FREQUENCY
							* CHUNK_SIZE_IN_SAMPLES / RATE);
					int max_frequency_fft = Math.round(Settings.MAX_FREQUENCY
							* CHUNK_SIZE_IN_SAMPLES / RATE);
					
					
					//Log.i("PitchDetector", "chk_pitch");
					// ------���� ����-------------------------------//
					best_frequency = min_frequency_fft;
					best_amplitude = 0;
					// right_checker = false;

					// ------���� �б�------------------------------------//
					result = recorder.read(short_buffer, 0,
							CHUNK_SIZE_IN_SAMPLES);

					for (int i = 0; i < CHUNK_SIZE_IN_SAMPLES; i++) {
						double_buffer[i] = (double) short_buffer[i]
								/ Short.MAX_VALUE; // ��ȣ �ִ� 16��Ʈ
					}
					// ----------------------------------------------------//

					transformer.ft(double_buffer); // FFT

					// ------Pitch ����---------------------------//
					for (int i = min_frequency_fft; i <= max_frequency_fft; i++) {
						
						final double current_frequency = i * 1.0 * RATE / CHUNK_SIZE_IN_SAMPLES;

						final double current_amplitude = Math.pow(double_buffer[i * 2], 2) + Math.pow(double_buffer[i * 2 + 1], 2);

						final double normalized_amplitude = current_amplitude * Math.pow(Settings.MIN_FREQUENCY * Settings.MAX_FREQUENCY, 0.5) / current_frequency;

						if (normalized_amplitude > best_amplitude) {
							best_frequency = current_frequency;
							best_amplitude = normalized_amplitude;
							//
						}
					} // Pitch ���� ��--------------------------------//

					// TODO �̺�Ʈ �߻�
					// ���� ���� �ְ��� amp�� ������.
					if (best_amplitude > Settings.LIMIT_AMP) {
						mCallback.onPitchEvent(best_frequency, best_amplitude);
					}
				}

			}// while ��
				// dos.close(); // ���� �ݱ�
		} catch (Exception e) {
		}
		// ------����, ���
		// ����--------------------------------------------------------------------//

		//Log.i("test", "PitchDetector done");
		recorder.stop();
	}

}
