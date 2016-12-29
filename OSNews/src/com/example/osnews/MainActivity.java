package com.example.osnews;


import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private ListView listview; 
	private String path = "http://www.oschina.net/news";
	private ListAdapter adapter;
	
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler(){
		public void handleMessage(Message msg){
			switch(msg.what){
			case 1 :
				listview.setAdapter(adapter);
				break; 
			default:
				break;
			} 
		} 
	}; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		listview = (ListView) this.findViewById(R.id.listview);
		new GetListData().execute(path);
	}
	
	class GetListData extends AsyncTask<String, Void, ArrayList<Article>> {
		@Override 
		protected ArrayList<Article> doInBackground(String... arg0) {
			System.out.println("====================================");
			ArrayList<Article> articleList =new ArrayList<Article>();
			try {
				System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
				Document doc = Jsoup.connect(path).timeout(5000).get(); 
				Element masthead = doc.getElementById("all-news");
				System.out.println(masthead.text());
			    Elements articleElements =  masthead.select("div.item");
			    //System.out.println("555555555555555555"+articleElements.text());
				if (doc != null) { 
					for(int i = 0; i < articleElements.size(); i++) {
					    
					    Element articleElement = articleElements.get(i);
					    Elements itembox=articleElement.select("div.main-info");
					    
					    Elements imgitem=articleElement.select("div.thumb");
					    
					    String imgurl=imgitem.select("img").attr("src");
					    if(imgurl.length()>0){
					    	if(imgurl.charAt(0)!='h')
					    	imgurl="http://www.oschina.net"+imgurl;
					    }else imgurl="";
					    
					    Log.e("77777777777",imgurl);
					    String url=		itembox.select("a.title").attr("href");
					    url="http://www.oschina.net"+url;
					    
					    Elements titleElement = itembox.select("a.title span");
					    Elements summaryElement = itembox.select("div.sc");
					    Elements timeElement = itembox.select("div.from");
					    
					    String title = titleElement.text();
					    String summary = summaryElement.text();
					    //if(summary.length() > 70)
					    //	summary = summary.substring(0, 70);
					    String postTime = timeElement.text();
					    
					    System.out.print(title+"------------"+summary+"----------"+postTime);
					    Log.i("title", title);
					    Log.i("summary", summary); 
					    Log.i("postTime", postTime);
					    
					    Article article = new Article(title,summary,postTime,imgurl);
					    imgurl="";
					    articleList.add(article);
					 
					}
				} 
			} catch (IOException e) {
				
				System.out.println(".............****************************");
				e.printStackTrace(); 
				
			}return articleList; 
		} 
		@Override 
		protected void onPostExecute(ArrayList<Article> articleList) { 
		 
			super.onPostExecute(articleList); 
			adapter = new ListAdapter(MainActivity.this,R.layout.item_article_list,articleList); 
			Log.i("adapter", "----------"+adapter.isEmpty()); 
			Message msg = Message.obtain(); 
			msg.what=1; 
			handler.sendMessage(msg);
		} 
	} 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
