package com.busunit.ui.fragments.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.busunit.R;
import com.busunit.ui.fragments.BaseCenterFragment;
import com.busunit.widget.Titlebars;
import com.busunit.widget.commonview.CommonViewTextNomal;
import com.hlib.base.HMessageHandler;
import com.hlib.base.model.HMessage;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class RegisteType extends BaseCenterFragment{
	
	@ViewInject(R.id.commonview1)
	private CommonViewTextNomal drivers;
	@ViewInject(R.id.commonview2)
	private CommonViewTextNomal users;
	
	private static final String REGISTETYPE_DRIVERS = "司机";
	private static final String REGISTETYPE_USERS = "乘客";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.account_registe_type_fm, null);
		ViewUtils.inject(this, view);
		titlebars = (Titlebars) view.findViewById(R.id.titlebars);
		return view;
	}
	
	@OnClick(R.id.commonview1)
	public void onDriversClick(View view){
		HMessage msg = new HMessage();
		msg.put("RegisteType", REGISTETYPE_DRIVERS);
		HMessageHandler.getInstance().pushMsg("RegisteFirst", msg);
		getActivity().onBackPressed();
	}
	
	@OnClick(R.id.commonview2)
	public void onUsersClick(View view){
		HMessage msg = new HMessage();
		msg.put("RegisteType", REGISTETYPE_USERS);
		HMessageHandler.getInstance().pushMsg("RegisteFirst", msg);
		getActivity().onBackPressed();
	}
	
	@Override
	public void dealReceiveMessage(HMessage msg) {
	}

}
