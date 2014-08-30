package com.busunit.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.busunit.R;
import com.busunit.widget.Titlebars;
import com.hlib.base.model.HMessage;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 首页
 * @author haojinwei
 *
 */
public class HomeFragment extends BaseFragment{
	@ViewInject(R.id.titlebars)
	private Titlebars titlebars;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fm_test, null);
		ViewUtils.inject(this,view);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
	}
	
	@Override
	public void dealReceiveMessage(HMessage msg) {
		
	}

}
