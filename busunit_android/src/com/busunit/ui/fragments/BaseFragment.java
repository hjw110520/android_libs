package com.busunit.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.busunit.MainActivity;
import com.busunit.widget.SlideMenuLayout;
import com.hlib.base.HBaseFragment;
import com.hlib.base.model.HMessage;
import com.hlib.utils.StringUtils;
/**
 * 
 * @author haojinwei
 *
 */
public abstract class BaseFragment extends HBaseFragment{
	
	protected SlideMenuLayout mSlideMenuLayout;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mSlideMenuLayout = ((MainActivity)getActivity()).getSlideMenuLayout();
	}
	
	public static Fragment initFragmentWithKey(String msgKey,Fragment target){
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
		return target;
	}
	
	
	/**====================================fragment 处理=====================================*/
	public void pushFragmentController(Fragment fragment) {
		mSlideMenuLayout.pushFragmentController(fragment);
	}
	
	public void pushFragmentController(Fragment fragment,
			int enterAnim,int exitAnim,int popEnterAnim,int popExitAnim) {
		mSlideMenuLayout.pushFragmentController(fragment, enterAnim, exitAnim, popEnterAnim, popExitAnim);;
	}
	
	public void setLeftSlideFragment(Fragment fragment) {
		mSlideMenuLayout.setLeftSlideFragment(fragment);
	}
	
	public void setCenterSlideFragment(Fragment fragment) {
		mSlideMenuLayout.setCenterSlideFragment(fragment);
	}
	
	public void setCenterSlideFragment(Fragment fragment,boolean isRoot,boolean pushStack,boolean useAnim) {
		mSlideMenuLayout.setCenterSlideFragment(fragment, isRoot, pushStack, useAnim);
	}
	
}
