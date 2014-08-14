package com.hlib.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.hlib.utils.StringUtils;

public abstract class HBaseFragment extends Fragment implements OnReceiveMessageListener{
	
	/*public static XcBaseFragment getInstance(String msgKey){
		XcBaseFragment xbf = new XcBaseFragment();
		Bundle bundle = new Bundle();
		bundle.putString("msgKey", msgKey);
		xbf.setArguments(bundle);
		return xbf;
	}*/
	
	private String msgKey;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle args = getArguments();
		if(null != args){
			msgKey = args.getString("msgKey");
			if(StringUtils.isNotBlank(msgKey)){
				HMessageHandler.getInstance().addMsgHandler(msgKey, this);
			}
		}
	}
	
}
