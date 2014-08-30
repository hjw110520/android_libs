package com.busunit.widget.commonview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.busunit.R;

public  class CommonViewNomal extends CommonViewBase{
	protected ImageView leftView;
	protected TextView titleView;
	protected TextView contextView;
	protected ImageView rightView;
	protected View bootomSplitView;
	
	public CommonViewNomal(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CommonViewNomal(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CommonViewNomal(Context context) {
		super(context);
	}
	
	protected  View createLoadingView(Context context, AttributeSet attrs){
		View view = LayoutInflater.from(context).inflate(R.layout.cp_views_commonview_normal, null);
		leftView = (ImageView) view.findViewById(R.id.commonview_leftview);
		titleView = (TextView) view.findViewById(R.id.commonview_titleview);
		contextView = (TextView) view.findViewById(R.id.commonview_contextview);
		rightView = (ImageView) view.findViewById(R.id.commonview_rightview);
		bootomSplitView =  view.findViewById(R.id.commonview_bootomsplitview);
		return view;
	}
	
	@Override
	public void setTitleViewText(CharSequence titleText) {
		if(null != titleView)
			((TextView)titleView).setText(titleText);
	}
	
	@Override
	public void setContextViewText(CharSequence contextText) {
		if(null != contextView)
			contextView.setText(contextText);
	}
	
	@Override
	public CharSequence getContextViewText() {
		if(null != contextView)
			return contextView.getText();
		return null;
	}
	
	@Override
	public void setContextViewHint(CharSequence contextHint) {
		if(null != contextView)
			contextView.setHint(contextHint);
	}
	
	@Override
	public void setLefticonRes(int lefticonRes) {
		if(null != leftView && -1 != lefticonRes)
			leftView.setVisibility(View.VISIBLE);
			leftView.setImageResource(lefticonRes);
	}
	
	@Override
	public void setRighticonRes(int righticonRes) {
		if(null != rightView && -1 != righticonRes)
			rightView.setVisibility(View.VISIBLE);
			rightView.setImageResource(righticonRes);
	}
	
	@Override
	public void isBootomSplitView(boolean isBootomSplit) {
		if(null != bootomSplitView){
			if(isBootomSplit){
				bootomSplitView.setVisibility(View.VISIBLE);
			}else{
				bootomSplitView.setVisibility(View.GONE);
			}
		}
	}
}
