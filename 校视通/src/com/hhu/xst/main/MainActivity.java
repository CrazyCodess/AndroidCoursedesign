package com.hhu.xst.main;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hhu.xst.function.AboutXSTActivity;
import com.hhu.xst.function.LoginActivity;
import com.hhu.xst.function.MyNoteActivity;
import com.hhu.xst.function.RegisterActivity;
import com.hhu.xst.function.SearchActivity;
import com.hhu.xst.notetool.ManagerNoteActivity;
import com.hhu.xst.ui.ClassFragment;
import com.hhu.xst.ui.NewsFragment;
import com.hhu.xst.ui.XiaozuFragment;
import com.jereh.slidingdemo.R;
import com.slidingmenu.lib.SlidingMenu;

//import com.hhu.xst.ui.HomeFragment;
/**
 * 为各子界面fragment的容器，以及侧滑菜单栏
 * @author lenovo
 *
 */
public class MainActivity extends FragmentActivity implements OnClickListener {

	public final static int num = 3;

	Fragment homeFragment;
	Fragment personFragment;
	Fragment sorttypeFragment;
	private FragmentManager fragmentManager;
	private FragmentTransaction transaction;
	private RadioGroup radioGroup;
	private Button btLogin,button06;
	private Button	btRegister;
	private TextView TextView06;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 隐藏标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 隐藏状态栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_main);
		fragmentManager = getSupportFragmentManager();
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		
		if(bundle!=null){
			
			
			if(bundle.getInt("status")==1){
				String name=bundle.getString("name");
				initSlidingMenu(1,name);
			}
			else initSlidingMenu(0,null);
		}else initSlidingMenu(0,null);
		
		
		((RadioButton) radioGroup.findViewById(R.id.radio0)).setChecked(true);

		transaction = fragmentManager.beginTransaction();
		Fragment fragment = new ClassFragment();
		transaction.replace(R.id.content, fragment);
		transaction.commit();
		//radioGroup.check(id)
		radioGroup
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {

						if (checkedId == R.id.radio0) {
							transaction = fragmentManager.beginTransaction();
							Fragment homeFragment = new ClassFragment();
							transaction.replace(R.id.content, homeFragment);
							transaction.commit();
						}
						if (checkedId == R.id.radio1) {
							transaction = fragmentManager.beginTransaction();
							Fragment sortFragment = new NewsFragment();
							transaction.replace(R.id.content, sortFragment);
							transaction.commit();
						}
/*						if (checkedId == R.id.radio2) {
							transaction = fragmentManager.beginTransaction();
							Fragment personFragment = new XiaozuFragment();
							transaction.replace(R.id.content, personFragment);
							transaction.commit();
						}*/
						/*if (checkedId == R.id.radio4) {
							transaction = fragmentManager.beginTransaction();
							Fragment classFragment = new DirectFragment();
							transaction.replace(R.id.content, classFragment);
							transaction.commit();
						}*/
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private SlidingMenu slidingMenu;

	private ImageButton slidebutt;
	private ImageButton searchbutt;

	private void initSlidingMenu(int status,String name) {
		slidingMenu = new SlidingMenu(this);

		slidingMenu.setMode(SlidingMenu.LEFT);
		slidingMenu.setSecondaryMenu(R.layout.navigation_layout);
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		slidingMenu.setShadowWidth(10);
		slidingMenu.setBehindOffsetRes(R.dimen.sliding_menu_offset);// 设置偏离距离
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// 全屏模式，全屏滑动都可打开
		
		Dianji(status,name);
	}

	private void Dianji(int status,String name) {
		// TODO Auto-generated method stub
	//btLogin = (Button) findViewById(R.id.login);
		slidebutt = (ImageButton) findViewById(R.id.slidemn);
		searchbutt = (ImageButton) findViewById(R.id.searchbt);
		/**
		 * 打开侧边栏按钮
		 */
		slidebutt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				slidingMenu.toggle();
			}
		});
		/**
		 * 打开搜索页面
		 */
		searchbutt.setOnClickListener(new OnClickListener() {

			private ArrayList<Map<String, String>> moreList;

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						SearchActivity.class);
				startActivity(intent);
				finish();
				// initData();
			}
		});
		/**
		 * 点击获取不同操作
		 */
		/*//退出登陆
		button06=(Button)findViewById(R.id.button06);
		button06.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				Dianji(0,null);
				initSlidingMenu(0,null);


			}
		});*/
		
		
		/**
		 * 退出登陆
		 */
		TextView06=(TextView)findViewById(R.id.TextView06);
		TextView06.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Bundle bundle=new Bundle();
                bundle.putInt("status",0);
                bundle.putString("name",null);
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
				finish();	

			}
		});
		
		
		
		
		
		// 登录
		btLogin=(Button)findViewById(R.id.login0);
		 btLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();

			}
		});
		 
	
		 btRegister=(Button)findViewById(R.id.regist);
		 btRegister.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(MainActivity.this,
							RegisterActivity.class);
					startActivity(intent);
					finish();

				}
			});
		 
		 TextView username =(TextView)findViewById(R.id.username);
		 if(status==1){
			 
			 btLogin.setVisibility(View.INVISIBLE);
			 btRegister.setVisibility(View.INVISIBLE);
			 username.setText(name);
			 TextView hint=(TextView)findViewById(R.id.user_text);
			 username.setVisibility(View.VISIBLE);
			 hint.setVisibility(View.INVISIBLE);
			 
			 
		 }else{
			 ImageView imageview6=(ImageView)findViewById(R.id.img_6);
			 imageview6.setVisibility(View.INVISIBLE);
			 username.setVisibility(View.INVISIBLE);
			 TextView06.setVisibility(View.INVISIBLE);
		 }
		RelativeLayout note = (RelativeLayout) findViewById(R.id.item_1);
		note.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent inte = new Intent(MainActivity.this,
						MyNoteActivity.class);
				startActivity(inte);
				finish();
			}
		});
		RelativeLayout guanyu = (RelativeLayout) findViewById(R.id.item_0);
		guanyu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent inte = new Intent(MainActivity.this,
						AboutXSTActivity.class);
				startActivity(inte);
				finish();
			}
		});
		RelativeLayout shoucang = (RelativeLayout) findViewById(R.id.item_3);
		shoucang.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// Intent inte = new Intent(MainActivity.this,
				// LoginActivity.class);
				// startActivity(inte);
				// finish();
			}
		});
		RelativeLayout geren = (RelativeLayout) findViewById(R.id.item_4);
		geren.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent inte = new Intent(MainActivity.this, LoginActivity.class);
				startActivity(inte);
				finish();
			}
		});
		RelativeLayout tool = (RelativeLayout) findViewById(R.id.item_5);
		tool.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent inte = new Intent(MainActivity.this, LoginActivity.class);
				startActivity(inte);
				finish();
			}
		});

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			slidingMenu.toggle();
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 * 实现overflow菜单项带ICON
	 */
	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
			if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
				try {
					Method m = menu.getClass().getDeclaredMethod(
							"setOptionalIconsVisible", Boolean.TYPE);
					m.setAccessible(true);
					m.invoke(menu, true);
				} catch (Exception e) {
				}
			}
		}
		return super.onMenuOpened(featureId, menu);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

}
