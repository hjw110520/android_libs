package com.busunit.widget.slidescrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class SlideScrollView extends ScrollView{
	
	private float mDownMotionX;
	private float mDownMotionY;
	
	public SlideScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public SlideScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SlideScrollView(Context context) {
		super(context);
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return super.onInterceptTouchEvent(ev);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		super.onTouchEvent(ev);
		if(getScrollY() != 0 ){
			requestDisallowInterceptTouchEvent(true);
		}
		return true;
	}
}
