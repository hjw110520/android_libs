package com.busunit.widget.commonview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.busunit.R;

public  class CommonViewTextNomal extends CommonViewNomal{
	
	public CommonViewTextNomal(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CommonViewTextNomal(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CommonViewTextNomal(Context context) {
		super(context);
	}
	
	protected  View createLoadingView(Context context, AttributeSet attrs){
		View view = LayoutInflater.from(context).inflate(R.layout.cp_views_commonview_textnormal, null);
		leftView = (ImageView) view.findViewById(R.id.commonview_leftview);
		titleView = (TextView) view.findViewById(R.id.commonview_titleview);
		contextView = (TextView) view.findViewById(R.id.commonview_contextview);
		rightView = (ImageView) view.findViewById(R.id.commonview_rightview);
		bootomSplitView =  view.findViewById(R.id.commonview_bootomsplitview);
		return view;
	}
}
