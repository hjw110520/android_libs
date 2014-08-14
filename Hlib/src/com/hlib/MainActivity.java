package com.hlib;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.hlib.lviews.slidescrollview.ScrollLayout;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ScrollLayout ds = (ScrollLayout) findViewById(R.id.main_ScrollLayout);
		ViewUtils.inject(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
