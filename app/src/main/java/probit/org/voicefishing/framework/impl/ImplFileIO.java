package org.probit.voicefishing.framework.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.probit.voicefishing.framework.FileIO;

import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

public class ImplFileIO implements FileIO{

	AssetManager assets;
	String externalStoragePath;
	
	public ImplFileIO(AssetManager assets) {
		// AssetManager�� ����
		this.assets = assets;
		// �ܺ�������� ��θ� ���� 
		this.externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"VoiceFishing"+File.separator;
	}
	@Override
	public InputStream readAsset(String fileName) throws IOException {

		return assets.open(fileName);
	}
	@Override
	public InputStream readFile(String fileName) throws IOException {

		return new FileInputStream(externalStoragePath + fileName);
	}
	@Override
	public OutputStream writeFile(String fileName) throws IOException {
		File file = new File(externalStoragePath);
		
		if(!file.exists()){
			file.mkdirs();
			
			Log.i("ImplFileIO", "make file");
		}
		
		Log.i("ImplFileIO", externalStoragePath + fileName);

		return new FileOutputStream(externalStoragePath + fileName);
	}
	
}