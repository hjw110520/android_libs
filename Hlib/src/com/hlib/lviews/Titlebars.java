package com.hlib.lviews;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hlib.R;

public class Titlebars extends FrameLayout{
	private Context context;
	
	private ImageButton leftBtn;
	private ImageButton rightBtn;
	private TextView title;
	
	public Titlebars(Context context) {
		super(context);
		this.context = context;
		init();
	}

	public Titlebars(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		init();
	}

	public Titlebars(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}
	
	private void init(){
		View view = LayoutInflater.from(context).inflate(R.layout.lviews_titlebars, this);
		leftBtn = (ImageButton) view.findViewById(R.id.titlebar_leftbtn);
		rightBtn = (ImageButton) view.findViewById(R.id.titlebar_rightbtn);
		title = (TextView) view.findViewById(R.id.titlebar_title);
	}
	
	/**
	 * 设置左侧图标
	 * @param drawable
	 */
	public void setLDrawable(Drawable drawable) {
		leftBtn.setImageDrawable(drawable);
	}
	
	/**
	 * 设置右侧图标
	 * @param drawable
	 */
	public void setRDrawable(Drawable drawable) {
		rightBtn.setImageDrawable(drawable);
	}
	
	/**
	 * 设置标题
	 * @param c
	 */
	public void setTitleText(CharSequence c){
		title.setText(c);
	}

}
