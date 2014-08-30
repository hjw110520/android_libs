package com.busunit.ui.fragments;

import android.os.Bundle;
import android.view.View;

import com.busunit.widget.Titlebars;
import com.hlib.base.model.HMessage;

/**
 * 
 * @author haojinwei
 *
 */
public  class BaseCenterFragment extends BaseFragment{
	protected Titlebars titlebars;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if(null != titlebars){
			titlebars.setLOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					getActivity().onBackPressed();
				}
			});
		}
	}

	@Override
	public void dealReceiveMessage(HMessage msg) {
	}
}
