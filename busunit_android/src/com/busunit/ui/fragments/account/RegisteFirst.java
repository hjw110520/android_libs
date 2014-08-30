package com.busunit.ui.fragments.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.busunit.R;
import com.busunit.ui.fragments.BaseCenterFragment;
import com.busunit.widget.Titlebars;
import com.busunit.widget.commonview.CommonViewNomal;
import com.busunit.widget.commonview.CommonViewTextNomal;
import com.busunit.widget.commonview.CommonViewVerifyCode;
import com.hlib.base.model.HMessage;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class RegisteFirst extends BaseCenterFragment{
	
	private View fgView;
	
	@ViewInject(R.id.commonview1)
	private CommonViewNomal phoneNum;
	
	@ViewInject(R.id.commonview2)
	private CommonViewVerifyCode VerifyCode;
	
	@ViewInject(R.id.commonview3)
	private CommonViewTextNomal registeType;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		fgView = inflater.inflate(R.layout.account_registe_frist_fm,null,false);
		ViewUtils.inject(this,fgView);
		titlebars = (Titlebars) fgView.findViewById(R.id.titlebars);
		return fgView;
	}
	
	@OnClick(R.id.commonview3)
	public void onRegisteTypeClick(View view){
		pushFragmentController(initFragmentWithKey("RegisteType", new RegisteType()));
	}

	@Override
	public void dealReceiveMessage(HMessage msg) {
		if(null != msg){
			if(msg.getMsgKey().equalsIgnoreCase("RegisteType")){
				registeType.setContextViewText((String)msg.getMsg());
			}
		}
		
	}
}
