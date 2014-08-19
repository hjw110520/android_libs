package com.busunit.ui.fragments;

import com.hlib.base.model.HMessage;
import com.lidroid.xutils.util.LogUtils;

public class EmptyFragment extends BaseFragment{

	@Override
	public void onReceiveMessage(HMessage msg) {
		LogUtils.d("HMessage : "+msg.toString());
	}
	
}
