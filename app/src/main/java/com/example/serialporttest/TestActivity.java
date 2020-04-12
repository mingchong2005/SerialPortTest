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

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;


public class TestActivity extends SerialPortActivity {

	EditText mReception;
	String Result = "+DMOCONNECT";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);

		mReception = (EditText) findViewById(R.id.EditTextReceive);

		Log.i("chw", "TestActivity ---> onCreate");
		

		Button SendBtn = (Button)findViewById(R.id.TestBtn);
		SendBtn.setOnClickListener(SendBtnBtnListener);

		Button SetGroupBtn = (Button)findViewById(R.id.SetGroup);
		SetGroupBtn.setOnClickListener(SetGroupBtnListener);

		Button VolumeBtn = (Button)findViewById(R.id.Volume);
		VolumeBtn.setOnClickListener(VolumeBtnBtnListener);


		Button TrsmitBtn = (Button)findViewById(R.id.Trsmit);
		TrsmitBtn.setOnClickListener(TrsmitBtnBtnListener);

		Button ReceiveBtn = (Button)findViewById(R.id.Receive);
		ReceiveBtn.setOnClickListener(ReceiveBtnListener);

	}
	

	private View.OnClickListener SendBtnBtnListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			int i;	
			String test = "AT+DMOCONNECT\r";
			
				try {
					mOutputStream.write(new String(test).getBytes());
					//mOutputStream.write('0x0d');
				} catch (IOException e) {
					e.printStackTrace();
				}
			  }
	};


	private View.OnClickListener SetGroupBtnListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			int i;	
			String test = "AT+DMOSETGROUP=0,409.7500,409.7500,01,1,1\r";
			
				try {
					mOutputStream.write(new String(test).getBytes());
					//mOutputStream.write('0x0d');
				} catch (IOException e) {
					e.printStackTrace();
				}
			  }
	};

	private View.OnClickListener VolumeBtnBtnListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			int i;	
			String test = "AT+DMOSETVOLUME=8\r";
			
				try {
					mOutputStream.write(new String(test).getBytes());
					//mOutputStream.write('0x0d');
				} catch (IOException e) {
					e.printStackTrace();
				}
			  }
	};


	private View.OnClickListener TrsmitBtnBtnListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			//mSerialPort.interphone_module_Transmit(1);
			}
	};

	private View.OnClickListener ReceiveBtnListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			//mSerialPort.interphone_module_Transmit(0);
			}
	};


	@Override
	protected void onDataReceived(final byte[] buffer, final int size) {
		Log.i("chw", "TestActivity ---> onDataReceived");
		runOnUiThread(new Runnable() {
			public void run() {
					//String strReceive = convertHexToString(byteToHexString(buffer,size));
					//Log.i("chw", "TestActivity ---> onDataReceived" + strReceive);
					//JudgeResult(strReceive);

					if (mReception != null) {  
                        mReception.append(new String(buffer, 0, size));  
                        }  
			}
		});
	}
}
