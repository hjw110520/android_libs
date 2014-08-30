package com.busunit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.busunit.R;


public class Titlebars extends FrameLayout{
	private Context context;
	
	private ImageButton leftBtn;
	private ImageButton rightBtn;
	private TextView title;
	private TextView rightText;
	
	public Titlebars(Context context) {
		super(context);
		this.context = context;
		init();
	}

	public Titlebars(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		init();
		initDefStyle(attrs);
	}

	public Titlebars(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
		initDefStyle(attrs);
	}
	
	private void initDefStyle(AttributeSet attrs){
		TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.titlebarsStyle);
		if(null != typedArray){
			int count = typedArray.getIndexCount();
			for(int i=0;i<count;i++){
				int index = typedArray.getIndex(i);
				switch (index) {
				case R.styleable.titlebarsStyle_leftBtnRes:
					
					break;
				case R.styleable.titlebarsStyle_rightBtnRes:
					
					break;
				case R.styleable.titlebarsStyle_titleText:
					String titleText = typedArray.getString(index);
					setTitleText(titleText);
					break;
				case R.styleable.titlebarsStyle_rightText:
					String rightText = typedArray.getString(index);
					setRightText(rightText);
					break;
				default:
					break;
				}
			}
		}
		typedArray.recycle();
	}
	
	private void init(){
		View view = LayoutInflater.from(context).inflate(R.layout.cp_views_titlebars, this);
		leftBtn = (ImageButton) view.findViewById(R.id.titlebar_leftbtn);
		rightBtn = (ImageButton) view.findViewById(R.id.titlebar_rightbtn);
		title = (TextView) view.findViewById(R.id.titlebar_title);
		rightText = (TextView) view.findViewById(R.id.titlebar_rightText);
	}
	
	/**
	 * 设置左侧图标
	 * @param drawable
	 */
	public void setLDrawable(Drawable drawable) {
		leftBtn.setImageDrawable(drawable);
	}
	
	public void setLOnClickListener(View.OnClickListener l) {
		leftBtn.setOnClickListener(l);
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
	
	/**
	 * 设置右侧文字
	 * @param c
	 */
	public void setRightText(CharSequence c){
		rightText.setText(c);
	}

}
