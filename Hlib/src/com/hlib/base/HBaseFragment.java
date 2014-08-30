package com.hlib.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.hlib.base.model.HMessage;
import com.hlib.utils.StringUtils;

public abstract class HBaseFragment extends Fragment implements OnReceiveMessageListener{
	
	protected String msgKey;
	private HMessage msg = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle args = getArguments();
		if(null != args){
			msgKey = args.getString("msgKey");
			if(StringUtils.isNotBlank(msgKey)){
				observeMessage(msgKey);
			}
		}
	}
	
	protected void observeMessage(String msgKey) {
		HMessageHandler.getInstance().addMsgHandler(msgKey, this);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		HMessageHandler.getInstance().removeMsgHandler(this);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if(null != msg){
			dealReceiveMessage(msg);
		}
		msg = null;
	}
	
	public abstract void dealReceiveMessage(HMessage msg);
	
	@Override
	public void onReceiveMessage(HMessage msg) {
		this.msg = msg;
	}
}
