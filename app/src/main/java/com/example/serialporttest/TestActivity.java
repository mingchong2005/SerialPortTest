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
	private static final String TAG = "SerialPort";
	EditText mReception;
	String Result = "+DMOCONNECT";
	static EditText mInputText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);

		mReception = (EditText) findViewById(R.id.EditTextReceive);

		Log.i(TAG, "TestActivity ---> onCreate");
		
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

		mInputText = (EditText) findViewById(R.id.EditTextInput);
		//mInputText.requestFocus();
	}

	private View.OnClickListener SendBtnBtnListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			int i;	
			String test = "sip get_ver";
			//String test = {68, 25, 01, 01, 95, C8, 00, 01, 01, 10 }; //"sip get_ver";
			//int[] buffer = {0x68, 0x25, 0x01, 0x01, 0x95, 0xC8, 0x00, 0x01, 0x01, 0x10};
			//int[] buffer = {0x68, 0x24, 0x01, 0x01, 0x95, 0xC9, 0x00, 0x01, 0x01, 0x10};
			try {
				mOutputStream.write(new String(test).getBytes());
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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
			String test = "sip get_hw_model";
			//String ConvertString = null;
			//CharSequence editText = mInputText.getText();

			try {
				mOutputStream.write(new String(test).getBytes());
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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
			String[] test = {
					"mac set_ch_freq 0 867100000",
					"mac set_ch_freq 1 867300000",
					"mac set_ch_freq 2 867500000",
					"mac set_ch_freq 3 867700000",
					"mac set_ch_freq 4 867900000",
					"mac set_ch_freq 5 868100000",
					"mac set_ch_freq 6 868300000",
					"mac set_ch_freq 7 868500000",
					"mac set_rx2 0 868000000",
					"mac set_class C",
					"mac set_rx1_freq 867100000 200000 8",
					"mac set_deveui 4736549f00311111",
					"mac set_appeui 526973696e671111",
					"mac set_appkey 2b7e151628aed2a6abf7158809cf1111",
					"mac join otaa",
					"mac tx ucnf 15 98ba34fd",
			};

			for (i = 0; i < test.length; i++) {
				try {
					mOutputStream.write(new String(test[i]).getBytes());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//mOutputStream.write('0x0d');
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	};


	private View.OnClickListener TrsmitBtnBtnListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			int i;
			String[] test = {
					"mac set_ch_freq 0 867100000",
					"mac set_ch_freq 1 867300000",
					"mac set_ch_freq 2 867500000",
					"mac set_ch_freq 3 867700000",
					"mac set_ch_freq 4 867900000",
					"mac set_ch_freq 5 868100000",
					"mac set_ch_freq 6 868300000",
					"mac set_ch_freq 7 868500000",
					"mac set_rx2 0 868000000",
					"mac set_class C",
					"mac set_rx1_freq 867100000 200000 8",
					"mac set_deveui 4736549f00312222",
					"mac set_appeui 526973696e672222",
					"mac set_appkey 2b7e151628aed2a6abf7158809cf2222",
					"mac join otaa",
					"mac tx ucnf 15 98ba34fd",
			};

			for (i = 0; i < test.length; i++) {
				try {
					mOutputStream.write(new String(test[i]).getBytes());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//mOutputStream.write('0x0d');
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	};

	private View.OnClickListener ReceiveBtnListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			int i;
			String[] test = {
					"mac set_ch_freq 0 867100000",
					"mac set_ch_freq 1 867300000",
					"mac set_ch_freq 2 867500000",
					"mac set_ch_freq 3 867700000",
					"mac set_ch_freq 4 867900000",
					"mac set_ch_freq 5 868100000",
					"mac set_ch_freq 6 868300000",
					"mac set_ch_freq 7 868500000",
					"mac set_rx2 0 868000000",
					"mac set_class C",
					"mac set_rx1_freq 867100000 200000 8",
					"mac set_deveui 4736549f00313333",
					"mac set_appeui 526973696e673333",
					"mac set_appkey 2b7e151628aed2a6abf7158809cf3333",
					"mac join otaa",
					"mac tx ucnf 15 98ba34fd",
			};

			for (i = 0; i < test.length; i++) {
				try {
					mOutputStream.write(new String(test[i]).getBytes());
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//mOutputStream.write('0x0d');
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	};

	public static String hexStringToString(String hexStr) {
		String str = "0123456789ABCDEF";
		char[] hexs = hexStr.toCharArray();
		byte[] bytes = new byte[hexStr.length() / 2];
		int n;
		for (int i = 0; i < bytes.length; i++) {
			n = str.indexOf(hexs[2 * i]) * 16;
			n += str.indexOf(hexs[2 * i + 1]);
			bytes[i] = (byte) (n & 0xff);
		}
		return new String(bytes);
	}

	private static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		char[] buffer = new char[2];
		for (int i = 0; i < src.length; i++) {
			buffer[0] = Character.forDigit((src[i] >>> 4) & 0x0F, 16);
			buffer[1] = Character.forDigit(src[i] & 0x0F, 16);
			System.out.println(buffer);
			stringBuilder.append(buffer);
		}
		return stringBuilder.toString();
	}


	@Override
	protected void onDataReceived(final byte[] buffer, final int size) {
		Log.i(TAG, "TestActivity ---> onDataReceived");
		runOnUiThread(new Runnable() {
			public void run() {
					//String strReceive = convertHexToString(byteToHexString(buffer,size));
					//Log.i(TAG, "TestActivity ---> onDataReceived" + strReceive);
					//JudgeResult(strReceive);
				Log.i(TAG, "TestActivity ---> onDataReceived" + buffer);
				String HexString = bytesToHexString(buffer);
				Log.e(TAG, "HexString.getBytes() = " + HexString);
				Log.e(TAG, "HexString length =" + HexString.length());

				if (mReception != null) {
					mReception.append(new String(buffer, 0, size));
				}

				//if (mReception != null) {
                //        mReception.append(HexString);
                //        }
			}
		}
		);
	}
}
