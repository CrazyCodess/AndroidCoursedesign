package com.hhu.xst.function;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.hhu.xst.main.MainActivity;
import com.hhu.xst.ui.NewsFragment;
import com.jereh.slidingdemo.R;
/**
 * 实现新闻浏览，仍需改进
 * @author lenovo
 *
 */
public class NewsActivity extends Activity {

	private String url="www.baidu.com";//= "http://news.qq.com/a/20161227/036497.htm";//"http://121.42.203.75:8080/xst/news/view/12";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		url=bundle.getString("url");
		WebView show = (WebView) findViewById(R.id.show);
		WebSettings settings = show.getSettings(); 
		 settings.setUseWideViewPort(true);  
	     settings.setLoadWithOverviewMode(true);
	     // 设置WebView属性，能够执行JavaScript脚本  
	     //settings.setJavaScriptEnabled(true);  
	        // 设置可以支持缩放  
	     settings.setSupportZoom(true);  
	        // 设置出现缩放工具  
	     settings.setBuiltInZoomControls(true);  
	        // 为图片添加放大缩小功能  
	     settings.setUseWideViewPort(true);  
	  
	        show.setInitialScale(70);   //100代表不缩放  
	     
		show.loadUrl(url );
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if(keyCode == KeyEvent.KEYCODE_BACK){
			Intent myIntent = new Intent();
            myIntent = new Intent(NewsActivity.this, MainActivity.class);
            startActivity(myIntent);
            this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
