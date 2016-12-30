package com.hhu.xst.function;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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

	private String url = "www.baidu.com";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		url=bundle.getString("url");
		WebView show = (WebView) findViewById(R.id.show);

		show.setWebViewClient(new WebViewClient() {
	    public boolean shouldOverrideUrlLoading(WebView view, String url)
	                            { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
	                                    view.loadUrl(url);
	                                    return true;
	                            }
	    });
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
