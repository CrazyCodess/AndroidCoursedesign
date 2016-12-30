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
 * ʵ���������������Ľ�
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
	                            { //  ��д�˷������������ҳ��������ӻ����ڵ�ǰ��webview����ת��������������Ǳ�
	                                    view.loadUrl(url);
	                                    return true;
	                            }
	    });
		WebSettings settings = show.getSettings(); 
		 settings.setUseWideViewPort(true);  
	     settings.setLoadWithOverviewMode(true);
	     // ����WebView���ԣ��ܹ�ִ��JavaScript�ű�  
	     //settings.setJavaScriptEnabled(true);  
	        // ���ÿ���֧������  
	     settings.setSupportZoom(true);  
	        // ���ó������Ź���  
	     settings.setBuiltInZoomControls(true);  
	        // ΪͼƬ��ӷŴ���С����  
	     settings.setUseWideViewPort(true);  
	  
	        show.setInitialScale(70);   //100��������
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
