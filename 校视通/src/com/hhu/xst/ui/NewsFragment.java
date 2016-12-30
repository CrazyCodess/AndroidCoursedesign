package com.hhu.xst.ui;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Shader.TileMode;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.ListView;
import com.hhu.xst.connectutil.News_List;
import com.jereh.slidingdemo.R;

/**
 * @描述 在Fragment中要使用ListView，就要用ListFragment
 * */
@SuppressLint("SimpleDateFormat")
public class NewsFragment extends Fragment {
	List<News_List>  newsList=new ArrayList<News_List>();
	
	ListView listView;
	private String path = "http://www.oschina.net/news";
	private com.hhu.xst.connectutil.ListAdapter adapter;	
	
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler(){
		public void handleMessage(Message msg){
			switch(msg.what){
			case 1 :
				listView.setAdapter(adapter);
				break; 
			default:
				break;
			} 
		} 
	}; 

	/**
	 * @描述 在onCreateView中加载布局
	 * */
	@Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState) {  
        View view = inflater.inflate(R.layout.fragment_news, container,false);    
        
       // getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);
        listView = (ListView) view.findViewById(R.id.newslist);
        new GetListData().execute(path);
        return view;  
    }
	class GetListData extends AsyncTask<String, Void, ArrayList<News_List>> {
		@Override 
		protected ArrayList<News_List> doInBackground(String... arg0) {
			
			ArrayList<News_List> newsList =new ArrayList<News_List>();
			try {
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
					    if(url.charAt(0)=='/')
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
					    
					    News_List news = new News_List(title,summary,postTime,imgurl,url);
					    imgurl="";
					    newsList.add(news);
					 
					}
				} 
			} catch (IOException e) {
				
				System.out.println(".............****************************");
				e.printStackTrace(); 
				
			}
			return newsList; 
		} 
		@Override 
		protected void onPostExecute(ArrayList<News_List> newsList) { 
		 
			super.onPostExecute(newsList); 
			adapter = new com.hhu.xst.connectutil.ListAdapter(getActivity(),R.layout.news_listviewadapter,newsList); 
			Log.e("adapter", "----------"+adapter.isEmpty()); 
			Message msg = Message.obtain(); 
			msg.what=1; 
			handler.sendMessage(msg);
		} 
	} 
}