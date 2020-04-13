/*
 * Copyright 2009 Cedric Priscal
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package com.example.serialporttest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import com.example.serialporttest.utils.SerialPort;
import android.util.Log;

public abstract class SerialPortActivity extends Activity {
	private static final String TAG = "SerialPort";

	protected SerialPortApplication mApplication;
	protected SerialPort mSerialPort;
	protected OutputStream mOutputStream;
	private InputStream mInputStream;
	private ReadThread mReadThread;

	private class ReadThread extends Thread {

		@Override
		public void run() {
			super.run();
			
			Log.i(TAG, "SerialPortActivity ---> ReadThread run");
			
			while(!isInterrupted()) {
				int size;
				try {
					byte[] buffer = new byte[32];
					if (mInputStream == null) return;
					size = mInputStream.read(buffer);

					for(int i=0;i<buffer.length;i++){  
						Log.i(TAG, "SerialPortActivity ---> ReadThread run buffer[" + i +  " ] = " + buffer[i]);
					} 
					if (size > 0) {
						onDataReceived(buffer, size);
					}
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
			}
		}
	}

	private void DisplayError(int resourceId) {
		
		Log.i(TAG, "SerialPortActivity ---> DisplayError");
		
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle("Error");
		b.setMessage(resourceId);
		b.setPositiveButton("OK", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				SerialPortActivity.this.finish();
			}
		});
		b.show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		Log.i(TAG, "SerialPortActivity ---> onCreate");
		
		super.onCreate(savedInstanceState);
		mApplication = (SerialPortApplication) getApplication();
		try {
			// get serial port and then open it.
			mSerialPort = mApplication.getSerialPort();  
			mOutputStream = mSerialPort.getOutputStream();
			mInputStream = mSerialPort.getInputStream();

			/* Create a receiving thread */
			mReadThread = new ReadThread();
			mReadThread.start();
		} catch (SecurityException e) {
			DisplayError(R.string.error_security);
		} catch (IOException e) {
			DisplayError(R.string.error_unknown);
		} catch (InvalidParameterException e) {
			DisplayError(R.string.error_configuration);
		}
	}

	protected abstract void onDataReceived(final byte[] buffer, final int size);

	@Override
	protected void onDestroy() {
		
		Log.i(TAG, "SerialPortActivity ---> onDestroy");
		
		if (mReadThread != null)
		mReadThread.interrupt();
		mApplication.closeSerialPort();
		mSerialPort = null;
		super.onDestroy();
	}
}
