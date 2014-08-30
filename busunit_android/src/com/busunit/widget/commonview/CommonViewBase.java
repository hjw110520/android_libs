package com.busunit.widget.commonview;

import com.busunit.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class CommonViewBase extends FrameLayout{
    
	public CommonViewBase(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs);
		initDefStyle(context,attrs);
	}
	
	public CommonViewBase(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}

	public CommonViewBase(Context context) {
		this(context,null);
	}
	
	protected void init(Context context, AttributeSet attrs) {
		View mContainer = createLoadingView(context, attrs);
        if (null == mContainer) {
            throw new NullPointerException("Loading view can not be null.");
        }
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, 
                LayoutParams.WRAP_CONTENT);
        addView(mContainer, params);
    }
	
	private void initDefStyle(Context context,AttributeSet attrs) {
		TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.uiCommonTextStyle);
		if(null != typedArray){
			int count = typedArray.getIndexCount();
			for(int i=0;i<count;i++){
				int index = typedArray.getIndex(i);
				switch (index) {
				case R.styleable.uiCommonTextStyle_commonText_lefticonRes:
					int lefticonRes = typedArray.getResourceId(index, -1);
					setLefticonRes(lefticonRes);
					break;
				case R.styleable.uiCommonTextStyle_commonText_righticonRes:
					int righticonRes = typedArray.getResourceId(index, -1);
					setRighticonRes(righticonRes);
					break;
				case R.styleable.uiCommonTextStyle_commonText_title:
					String titleViewText = typedArray.getString(index);
					setTitleViewText(titleViewText);
					break;
				case R.styleable.uiCommonTextStyle_commonText_context:
					String contextViewText = typedArray.getString(index);
					setContextViewText(contextViewText);
					break;
				case R.styleable.uiCommonTextStyle_commonText_context_hint:
					String contextViewHint = typedArray.getString(index);
					setContextViewHint(contextViewHint);
					break;
				case R.styleable.uiCommonTextStyle_commonText_bootomSplit:
					boolean isBootomSplitView = typedArray.getBoolean(index, true);
					isBootomSplitView(isBootomSplitView);
					break;
				default:
					break;
				}
			}
		}
		typedArray.recycle();
	}
	
	public void setTitleViewText(CharSequence titleViewText) {}
	public void setContextViewText(CharSequence contextViewText) {}
	public void setContextViewHint(CharSequence contextViewHint) {}
	public void setLefticonRes(int lefticonRes) {}
	public void setRighticonRes(int righticonRes) {}
	public void isBootomSplitView(boolean isBootomSplitView) {}
	public CharSequence getContextViewText() {return null;}

	protected  abstract View createLoadingView(Context context, AttributeSet attrs);

	
}
