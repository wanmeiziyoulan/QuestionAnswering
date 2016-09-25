package com.example.quizeanswer;

import com.quizeanser.fragment.Chinesefragment;
import com.quizeanser.fragment.Englishfragment;
import com.quizeanser.fragment.Mathfragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;//主要用于在Activity中操作Fragment
import android.app.FragmentTransaction;//保证一系列Fragment操作的原子性，熟悉事务这个词，一定能明白~
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends FragmentActivity implements OnClickListener
{
	private Chinesefragment mFriend;
	private ImageButton tabchinese;
	private ImageButton tabmath;
	private ImageButton tabenglish;
	private ImageButton tabmine;
	private Chinesefragment chinese;
	private Mathfragment math;
	private Englishfragment eglish;
	private Chinesefragment mine;

	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		// 初始化控件和声明事件
		tabchinese = (ImageButton)findViewById(R.id.ib_chinese);
		tabmath = (ImageButton) findViewById(R.id.ib_math);
		tabenglish = (ImageButton) findViewById(R.id.ib_eglish);
		tabmine = (ImageButton) findViewById(R.id.ib_mine);
		tabchinese.setOnClickListener(this);
		tabmath.setOnClickListener(this);
		tabenglish.setOnClickListener(this);
		tabmine.setOnClickListener(this);

		// 设置默认的Fragment
		//setDefaultFragment();
		FragmentManager fm = getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();//开启一个事务
		chinese = new Chinesefragment();
		transaction.replace(R.id.vp_content, chinese);
		transaction.commit();
	}
	/**
	private void setDefaultFragment()
	{
		FragmentManager fm = getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		mWeixin = new Contentfragment();
		transaction.replace(R.id.id_content, mWeixin);
		transaction.commit();
	}
	 */
	@SuppressLint("NewApi") @Override
	public void onClick(View v)
	{
		FragmentManager fm = getFragmentManager();
		// 开启Fragment事务
		FragmentTransaction transaction = fm.beginTransaction();
		switch (v.getId())
		{
		case R.id.ib_chinese:
			if (chinese == null)
			{
				chinese = new Chinesefragment();
			}
			// 使用当前Fragment的布局替代id_content的控件
			transaction.replace(R.id.vp_content, chinese);
			break;
		case R.id.ib_math:
			if (math == null)
			{
				math = new Mathfragment();
			}
			transaction.replace(R.id.vp_content, math);
			break;
		case R.id.ib_eglish:
			if (eglish == null)
			{
				eglish = new Englishfragment();
			}
			transaction.replace(R.id.vp_content, eglish);
			break;
		case R.id.ib_mine:
			if (mine == null)
			{
				mine = new Chinesefragment();
			}
			transaction.replace(R.id.vp_content, chinese);
			break;

		}
		// transaction.addToBackStack();
		// 事务提交
		transaction.commit();
	}

}
