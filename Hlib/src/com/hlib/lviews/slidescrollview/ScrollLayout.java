package com.hlib.lviews.slidescrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.Scroller;

import com.hlib.utils.Toolkit;

public class ScrollLayout extends FrameLayout{
	private Context context;
	private Scroller mScroller;
	
	private float mDownMotionX;
	private float mDownMotionY;
	
	private int splitPos = -1 ;
	private MScrollState mScrollState = MScrollState.Scroll_State_Zero;
	
	public ScrollLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		init();
	}

	public ScrollLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}

	public ScrollLayout(Context context) {
		super(context);
		this.context = context;
		init();
	}
	
	public void setContainer(int topContainerId, int belowContainerId) {
	}

	
	private void init(){
		splitPos = Toolkit.getScreenHeight(context)/6;
		mScroller = new Scroller(context);
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			mDownMotionX = ev.getX();
			mDownMotionY = ev.getY();
			//LogUtils.d("mDownMotionY : "+mDownMotionY);
			break;
		case MotionEvent.ACTION_MOVE:
			float disY = ev.getY() - mDownMotionY;
			float disX = ev.getX() - mDownMotionX;
			//LogUtils.d("disY : "+disY);
			//LogUtils.d("mScrollState : "+mScrollState);
			if (Math.abs(disY) > 2 * Math.abs(disX)) {
				if(mScrollState == MScrollState.Scroll_State_Zero && disY > 0){
					smoothScrollTo(-splitPos*2);
					mScrollState = MScrollState.Scroll_State_one;
					return true;
				}else if(mScrollState == MScrollState.Scroll_State_one){
					if(disY > 0){
						smoothScrollTo(-splitPos*4);
						mScrollState = MScrollState.Scroll_State_two;
					}else{
						smoothScrollTo(0);
						mScrollState = MScrollState.Scroll_State_Zero;
					}
					return true;
				}else if(mScrollState == MScrollState.Scroll_State_two && disY < 0){
					smoothScrollTo(-splitPos*2);
					mScrollState = MScrollState.Scroll_State_one;
					return true;
				}
			}
			
			break;
		default:
			break;
		}
		return super.onInterceptTouchEvent(ev);
	}
	
	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			int prevY = getScrollY();
			int currY = mScroller.getCurrY();
			//LogUtils.d("prevY " +prevY);
			//LogUtils.d("currY " +currY);
			if (prevY != currY) {
				scrollTo(0,currY);
				layoutScrollLayout(currY);
			}
			requestLayout();
		}
		super.computeScroll();
	}
	
	private void layoutScrollLayout(int offset) {
		
	}
	
	private void smoothScrollTo(int fy) {
		int dy = fy - mScroller.getFinalY();
		//LogUtils.d("fy :"+fy);
		//LogUtils.d("dy :"+dy);
		//LogUtils.d("mScroller.getFinalY() : "+mScroller.getFinalY());
		mScroller.startScroll(0, mScroller.getFinalY(), 0, dy,200);
		postInvalidate();
	}
	
	public enum MScrollState{
		Scroll_State_Zero,Scroll_State_one,Scroll_State_two
	}

}
