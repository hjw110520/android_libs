package com.hlib.base;

import com.hlib.utils.StringUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public abstract class HBaseActivity extends FragmentActivity implements OnReceiveMessageListener{
	protected String msgKey = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		if(null != intent && null != intent.getExtras()){
			Bundle args = getIntent().getExtras();
			this.msgKey = args.getString("msgKey");
			if(StringUtils.isNotBlank(msgKey)){
				observeMessage(msgKey);
			}
		}
	}
	
	protected void observeMessage(String msgKey) {
		HMessageHandler.getInstance().addMsgHandler(msgKey, this);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		HMessageHandler.getInstance().removeMsgHandler(this);
	}
	
	public void startActivity (String msgKey, Class<?> cls) {
		Intent intent = new Intent(this, cls);
		intent.putExtra("msgKey", msgKey);
		startActivity(intent);
	}
}
