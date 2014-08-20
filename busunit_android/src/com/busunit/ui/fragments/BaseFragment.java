package com.busunit.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.busunit.MainActivity;
import com.busunit.widget.SlideMenuLayout;
import com.hlib.base.HBaseFragment;
import com.hlib.base.model.HMessage;
import com.hlib.utils.StringUtils;

public abstract class BaseFragment extends HBaseFragment{
	
	protected SlideMenuLayout mSlideMenuLayout;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mSlideMenuLayout = ((MainActivity)getActivity()).getSlideMenuLayout();
	}
	
	public void statFragment(String msgKey,Fragment target){
		if(null == target){
			throw new NullPointerException("target fragment is null");
		}
		
		if(StringUtils.isNotBlank(msgKey)){
			Bundle bundle = target.getArguments();
			if(null == bundle){
				bundle = new Bundle();
			}
			bundle.putString("msgKey", msgKey);
			target.setArguments(bundle);
		}
		
		mSlideMenuLayout.pushFragmentController(target);
	}
	
	public void startFragment(Fragment target){
		statFragment(null, target);
	}
}
