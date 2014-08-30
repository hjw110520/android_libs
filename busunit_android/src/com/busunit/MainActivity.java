package com.busunit;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;

import com.busunit.ui.activities.BaseActivity;
import com.busunit.ui.fragments.BaseFragment;
import com.busunit.ui.fragments.EmptyFragment;
import com.busunit.ui.fragments.account.RegisteFirst;
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
		mSlideMenuLayout.setLeftMenuWidth(0.7f);
		mSlideMenuLayout.setLeftSlideFragment(BaseFragment.initFragmentWithKey("EmptyFragment",new EmptyFragment()));
		mSlideMenuLayout.setCenterSlideFragment(BaseFragment.initFragmentWithKey("RegisteFirst",new RegisteFirst()));
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			onBackPressed();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public void onBackPressed() {
		if (!mSlideMenuLayout.isCenter()) {
			mSlideMenuLayout.openCenterSlideMenu();
			return;
		} else if (mSlideMenuLayout.needPopup()) {
			mSlideMenuLayout.popupTopFragmentController();
			return;
		} else {
			MainActivity.this.finish();
		}
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
	
	@Override
	public void onReceiveMessage(HMessage msg) {
		
	}
}
