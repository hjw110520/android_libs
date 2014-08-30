package com.busunit.widget.commonview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.busunit.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public  class CommonViewVerifyCode extends CommonViewNomal{
	
	private TextView rightView;
	
	public CommonViewVerifyCode(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CommonViewVerifyCode(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CommonViewVerifyCode(Context context) {
		super(context);
	}
	
	protected  View createLoadingView(Context context, AttributeSet attrs){
		View view = LayoutInflater.from(context).inflate(R.layout.cp_views_commonview_verifycode, null);
		titleView = (TextView) view.findViewById(R.id.commonview_titleview);
		contextView = (TextView) view.findViewById(R.id.commonview_contextview);
		rightView = (TextView) view.findViewById(R.id.commonview_rightview);
		bootomSplitView =  view.findViewById(R.id.commonview_bootomsplitview);
		return view;
	}
}
