package com.busunit;

import android.os.Bundle;

import com.busunit.ui.activities.BaseActivity;
import com.busunit.ui.fragments.EmptyFragment;
import com.busunit.ui.fragments.HomeFragment;
import com.busunit.widget.SlideMenuLayout;
import com.hlib.base.model.HMessage;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 
 * @author haojinwei
 *
 */
public class MainActivity extends BaseActivity {
	
	@ViewInject(R.id.slide_menu)
	private SlideMenuLayout mSlideMenuLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_slide_layout);
		ViewUtils.inject(this);
		init();
	}
	
	public SlideMenuLayout getSlideMenuLayout() {
		return mSlideMenuLayout;
	}

	private void init() {
		mSlideMenuLayout.setFragmentManager(getSupportFragmentManager());
		mSlideMenuLayout.setContainer(R.id.leftContainer, R.id.centerContainer);
		mSlideMenuLayout.setLeftMenuWidth(0.9f);
		mSlideMenuLayout.setLeftSlideFragment(new EmptyFragment());
		mSlideMenuLayout.setCenterSlideFragment(new HomeFragment());
	}
	
	@Override
	public void onReceiveMessage(HMessage msg) {
		
	}
}
