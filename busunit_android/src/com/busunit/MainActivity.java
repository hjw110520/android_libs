package com.busunit;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.busunit.ui.activities.BaseActivity;
import com.busunit.widget.SlideMenuLayout;
import com.hlib.base.model.HMessage;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class MainActivity extends BaseActivity {
	
	@ViewInject(R.id.slide_menu)
	private SlideMenuLayout mSlideMenuLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_slide_layout);
		ViewUtils.inject(this);
	}

	@Override
	public void onReceiveMessage(HMessage msg) {
	}
}
