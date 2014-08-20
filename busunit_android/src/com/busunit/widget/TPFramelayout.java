package com.busunit.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class TPFramelayout extends FrameLayout{

	public TPFramelayout(Context context) {
		super(context);
	}

	public TPFramelayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public TPFramelayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
    
	/**
	 * 防止点击事件穿透
	 */
    @Override  
    public boolean onTouchEvent(MotionEvent event) {  
        return true;  
    }  
}
