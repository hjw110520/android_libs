package com.busunit.widget;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Scroller;

import com.busunit.R;
import com.busunit.ui.fragments.BaseCenterFlipFragment;
import com.hlib.utils.Toolkit;

public class SlideMenuLayout extends ViewGroup {

	public static final String Tag = "SlideMenuLayout";

	protected static final int TOUCH_STATE_NORMAL = 0x0;
	protected static final int TOUCH_STATE_SCROLLING = 0x1;
	protected static final float SCROLL_COEFFICIENT = 1.0F;

	private FrameLayout mLeftMenuContainer;
	private FrameLayout mCenterMenuContainer;
	private FragmentManager mFmManager;

	private float mLeftMenuWidth = 0.8F;
	private int mLeftMenuWidthPixels = 0;

	private float mDownMotionX;
	private float mDownMotionY;
	
	private int mTouchState = TOUCH_STATE_NORMAL;

	private Scroller mScroller;
	private GestureDetector mGestureDetector;

	public SlideMenuLayout(Context context) {
		this(context, null);
		this.init();
	}

	public SlideMenuLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		this.init();
	}

	public SlideMenuLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.init();
	}

	private void init() {
		mScroller = new Scroller(getContext());
		mGestureDetector = new GestureDetector(getContext(),
				new GestureAdapter());
	}
	
	@Override
	public boolean isFocused() {
		return true;
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		for (int i = 0; i < getChildCount(); i++) {
			View view = getChildAt(i);
			view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
		}
		this.layoutSlideMenu(getScrollX());
	}

	public void setContainer(int leftContainerId, int centerContainerId,
			int RightContainerId) {
		mLeftMenuContainer = (FrameLayout) findViewById(leftContainerId);
		mCenterMenuContainer = (FrameLayout) findViewById(centerContainerId);
		centerRootId = mCenterMenuContainer.getId();
	}

	private void layoutSlideMenu(int offset) {
		if (null != mLeftMenuContainer
				&& mLeftMenuContainer.getVisibility() != View.GONE) {
			mLeftMenuContainer.setLayoutParams(new LayoutParams(
					(int) getLeftMenuWidthF(), getHeight()));
			mLeftMenuContainer.layout(offset, 0,
					offset + mLeftMenuContainer.getWidth(), getHeight());
		}
		if (null != mLeftMenuContainer && offset < 0) {
			mLeftMenuContainer.bringToFront();
		}
		
		if (null != mCenterMenuContainer) {
			mCenterMenuContainer.bringToFront();
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		for (int i = 0; i < getChildCount(); i++) {
			View view = getChildAt(i);
			LayoutParams layoutParams = view.getLayoutParams();
			int k = getWidth();
			int m = getHeight();
			if (layoutParams != null) {
				if (layoutParams.width > 0)
					k = layoutParams.width;
				if (layoutParams.height > 0)
					m = layoutParams.height;
			}
			view.measure(View.MeasureSpec.makeMeasureSpec(k,
					View.MeasureSpec.EXACTLY), View.MeasureSpec
					.makeMeasureSpec(m, View.MeasureSpec.EXACTLY));
		}
	}

	public void setFragmentManager(FragmentManager manager) {
		mFmManager = manager;
	}

	private float getLeftMenuWidthF() {
		if (mLeftMenuWidthPixels == 0)
			return getWidth() * mLeftMenuWidth;
		else
			return mLeftMenuWidthPixels;
	}

	public void setLeftMenuWidth(float width) {
		mLeftMenuWidth = width;
		mLeftMenuWidthPixels = 0;
	}

	public void setLeftMenuWidthPixels(int pixels) {
		mLeftMenuWidthPixels = pixels;
		mLeftMenuWidth = 0.0F;
	}

	int centerRootId = -1;

	private void addMenuFragment(Fragment fragment, FrameLayout container,
			boolean isRoot) {
		if (null != mFmManager && null != fragment) {
			FragmentTransaction ft = mFmManager.beginTransaction();
			String name = String.format("fragment:%s",fragment.getClass().getSimpleName() + fragment.hashCode());
			Log.i(Tag, name);
			if (!isRoot) {
				if (fragment instanceof BaseCenterFlipFragment) {
					ft.setCustomAnimations(R.anim.rotate_push_enter, R.anim.rotate_push_exit,
							R.anim.rotate_pop_enter, R.anim.rotate_pop_exit);
				} else {
					ft.setCustomAnimations(R.anim.enter, R.anim.exit,
							R.anim.pop_enter, R.anim.pop_exit);
				}
				
				ft.addToBackStack(name);
			} else if (-1 != centerRootId) {
				if (centerRootId == container.getId()) {
					name += "root";
					ft.addToBackStack(name);
					mFmManager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
				}
			}
			ft.replace(container.getId(), fragment, name);
			ft.commit();
			postInvalidate();
		}
	}

	private void addMenuFragment(Fragment fragment, FrameLayout container,
			boolean isRoot, int enterAnim, int exitAnim, int popEnterAnim,
			int popExitAnim) {
		if (null != mFmManager && null != fragment) {
			FragmentTransaction ft = mFmManager.beginTransaction();
			String name = String.format("fragment:%s",fragment.getClass().getSimpleName() + fragment.hashCode());
			Log.i(Tag, name);
			if (!isRoot) {
				ft.setCustomAnimations(enterAnim, exitAnim, popEnterAnim,
						popExitAnim);
				ft.addToBackStack(name);
			} else if (-1 != centerRootId) {
				if (centerRootId == container.getId()) {
					name += "root";
					ft.addToBackStack(name);
					int count = mFmManager.getBackStackEntryCount();
					Log.i(Tag, "BackStackEntryCount:" + count);
					for (int i = 0; i < count; i++) {
						// mFmManager.popBackStack();
					}
					mFmManager.popBackStack(null,
							FragmentManager.POP_BACK_STACK_INCLUSIVE);
				}
			}
			ft.replace(container.getId(), fragment, name);
			// container.setTag(fragment.getClass());
			ft.commit();
			postInvalidate();
		}
	}

	private void addMenuFragment(Fragment fragment, FrameLayout container,
			boolean isRoot, boolean pushStack, boolean useAnim) {
		if (null != mFmManager && null != fragment) {
			FragmentTransaction ft = mFmManager.beginTransaction();
			String name = String.format("fragment:%s",fragment.getClass().getSimpleName() + fragment.hashCode());
			Log.i(Tag, name);
			if (!isRoot) {
				if (useAnim) {
					ft.setCustomAnimations(R.anim.enter, R.anim.exit,
							R.anim.pop_enter, R.anim.pop_exit);
				}
				if(pushStack){
					ft.addToBackStack(name);
				}
			} else if (-1 != centerRootId) {
				if (centerRootId == container.getId()) {
					name += "root";
					ft.addToBackStack(name);
					int count = mFmManager.getBackStackEntryCount();
					Log.i(Tag, "BackStackEntryCount:" + count);
					for (int i = 0; i < count; i++) {
						// mFmManager.popBackStack();
					}
					mFmManager.popBackStack(null,
							FragmentManager.POP_BACK_STACK_INCLUSIVE);
				}
			}
			ft.replace(container.getId(), fragment, name);
			ft.commit();
			postInvalidate();
		}
	}

	public void setLeftSlideFragment(Fragment fragment) {
		addMenuFragment(fragment, mLeftMenuContainer, true);
	}
	
	public void setCenterSlideFragment(Fragment fragment) {
		addMenuFragment(fragment, mCenterMenuContainer, true);
	}

	public void setCenterSlideFragment(Fragment fragment, boolean isRoot,
			boolean pushStack, boolean useAnim) {
		addMenuFragment(fragment, mCenterMenuContainer, isRoot, pushStack,
				useAnim);
	}

	private void smoothHorizontalScrollTo(int fx) {
		if (fx == 0) {
			if (null != onIsCenterListener) {
				onIsCenterListener.isCenter();
			}
		}
		int dx = fx - mScroller.getFinalX();
		mScroller.startScroll(mScroller.getFinalX(), 0, dx, 0);
		postInvalidate();
	}

	public boolean isCenter() {
		return 0 == mScroller.getCurrX();
	}

	public boolean isLeft() {
		return mScroller.getCurrX() < 0;
	}

	public boolean isRight() {
		return mScroller.getCurrX() > 0;
	}

	public boolean needPopup() {
		return mFmManager.getBackStackEntryCount() > 1;
	}

	public void pushFragmentController(Fragment fragment) {
		addMenuFragment(fragment, mCenterMenuContainer, false);
	}

	public void pushFragmentController(Fragment fragment, int enterAnim,
			int exitAnim, int popEnterAnim, int popExitAnim) {
		addMenuFragment(fragment, mCenterMenuContainer, false, enterAnim,
				exitAnim, popEnterAnim, popExitAnim);
	}
	
	// 后退
	public void popupTopFragmentController() {
		// mFmManager.popBackStackImmediate()若弹出某些状态则返回true，若弹不出某些状态则返回false
		if (!mFmManager.popBackStackImmediate()) {
			((Activity) getContext()).onBackPressed();
		}
	}

	// 将fragmentId的Fragment弹出
	public void popup2FragmentController(int fragmentId) {
		String tagName = String.format("container:%d-fragment:%d",
				mCenterMenuContainer.getId(), fragmentId);
		// 从管理理的Fragment回退堆栈中把最后放入的由name参数所指定的Fragment对象弹出
		mFmManager.popBackStack(tagName, 0);
	}

	// 将fragmentId的Fragment弹出
	public void popup2RootFragmentController() {
		List<Fragment> fragments = mFmManager.getFragments();
		if (null != fragments && fragments.size()>0) {
			Fragment fragment = fragments.get(0); 
			String name = String.format("fragment:%s",fragment.getClass().getSimpleName() + fragment.hashCode())+"root";
			mFmManager.popBackStackImmediate(name, 0);
		}
	}

	public void openLeftSlideMenu() {
		if (null != onIsCenterListener) {
			onIsCenterListener.isLeft();
		}
		smoothHorizontalScrollTo((int) -getLeftMenuWidthF());
	}

	public void openCenterSlideMenu() {
		if (null != onIsCenterListener) {
			onIsCenterListener.isCenter();
		}
		smoothHorizontalScrollTo(0);
	}

	public void reset() {
		smoothHorizontalScrollTo(0);
	}

	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {

			int prevX = getScrollX();
			int currX = mScroller.getCurrX();
			if (prevX != currX) {
				scrollTo(currX, 0);
				layoutSlideMenu(currX);
			}
			requestLayout();
		}

		if (null != mCenterMenuContainer && null != mScroller) {
			// Log.d("----scroll----",
			// String.format("currX=%d,startX=%d,finalX=%d",
			// mScroller.getCurrX(), mScroller.getStartX(),
			// mScroller.getFinalX()));
			mCenterMenuContainer.setEnabled(0 == mScroller.getFinalX());
		}

		super.computeScroll();
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		final int action = ev.getAction();

		if (action == MotionEvent.ACTION_MOVE
				&& mTouchState == TOUCH_STATE_SCROLLING) {
			return false;
		}

		if (action == MotionEvent.ACTION_UP
				&& mTouchState != TOUCH_STATE_SCROLLING) {
			if (isLeft()) {
				if (getLeftMenuWidthF() < mDownMotionX) {
					openCenterSlideMenu();
					return true;
				}
			}
		}
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			mDownMotionX = ev.getX();
			mDownMotionY = ev.getY();
			mGestureDetector.onTouchEvent(ev);
			mTouchState = TOUCH_STATE_NORMAL;
			break;
		case MotionEvent.ACTION_MOVE:
			if (Math.abs(ev.getX() - mDownMotionX) > 2 * Math.abs(ev.getY()
					- mDownMotionY)) {
				Log.i("return", mDownMotionX + "  " + mScroller.getCurrX());
				mTouchState = TOUCH_STATE_SCROLLING;
				if (isRight()) {
					if (mDownMotionX < Toolkit.getScreenWidth()
							- mScroller.getCurrX()) {

						return true;
					}
					return false;
				}
				if (isLeft()) {
					if (mDownMotionX > -mScroller.getCurrX()) {
						return true;
					}
					return false;
				}
				if (isCenter()&&mDownMotionX<Toolkit.dip2px(30)) {
					return true;
				}
			} else {
				mTouchState = TOUCH_STATE_NORMAL;
			}
			break;
		}
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_UP:
			mTouchState = TOUCH_STATE_NORMAL;
			final int pos = getScrollX();
			if (-pos > getLeftMenuWidthF() / 2)
				openLeftSlideMenu();
			else
				reset();
			break;
		}
		return mGestureDetector.onTouchEvent(ev);
	}

	class GestureAdapter extends SimpleOnGestureListener {

		@Override
		public boolean onDown(MotionEvent e) {
			return true;
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			final int dis = (int) (distanceX / SCROLL_COEFFICIENT)
					+ mScroller.getFinalX();
			if (-dis >= getLeftMenuWidthF())
				openLeftSlideMenu();
			else
				smoothHorizontalScrollTo(dis);
			return false;
		}
	}

	private OnIsCenterListener onIsCenterListener;

	public void setOnIsCenterListener(OnIsCenterListener onIsCenterListener) {
		this.onIsCenterListener = onIsCenterListener;
	}

	public interface OnIsCenterListener {
		public void isCenter();

		public void isLeft();

		public void isRight();
	}

	public FrameLayout getCenterMenuContainer() {
		return mCenterMenuContainer;
	}
}
