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
import android.widget.AbsListView;
import android.widget.ListView;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hhu.xst.connectutil.News_List;
import com.jereh.slidingdemo.R;

/**
 * @描述 在Fragment中要使用ListView，就要用ListFragment
 * */
@SuppressLint("SimpleDateFormat")
public class NewsFragment extends Fragment {
	PullToRefreshListView mPull;
	List<News_List>  newsList=new ArrayList<News_List>();
	private ILoadingLayout loadingLayout;
	ListView listView;
	private String path = "http://www.oschina.net/news";
	private com.hhu.xst.connectutil.ListAdapter adapter;	
		//@SuppressLint("HandlerLeak")
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
        initListView(view);
        return view;  
    }
	
	private void initListView(View view)
    {


        mPull=(PullToRefreshListView)view.findViewById(R.id.newslist);
        //mPull.setRefreshing(true);
        loadingLayout = mPull.getLoadingLayoutProxy();
        loadingLayout.setLastUpdatedLabel("");
        loadingLayout
                .setPullLabel(getString(R.string.pull_to_refresh_bottom_pull));
        loadingLayout
                .setRefreshingLabel(getString(R.string.pull_to_refresh_bottom_refreshing));
        loadingLayout
                .setReleaseLabel(getString(R.string.pull_to_refresh_bottom_release));
        // //滑动监听
        mPull.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                if (firstVisibleItem == 0) {
                    loadingLayout.setLastUpdatedLabel("");
                    loadingLayout
                            .setPullLabel(getString(R.string.pull_to_refresh_top_pull));
                    loadingLayout
                            .setRefreshingLabel(getString(R.string.pull_to_refresh_top_refreshing));
                    loadingLayout
                            .setReleaseLabel(getString(R.string.pull_to_refresh_top_release));
                } else if (firstVisibleItem + visibleItemCount + 1 == totalItemCount) {
                    loadingLayout.setLastUpdatedLabel("");
                    loadingLayout
                            .setPullLabel(getString(R.string.pull_to_refresh_bottom_pull));
                    loadingLayout
                            .setRefreshingLabel(getString(R.string.pull_to_refresh_bottom_refreshing));
                    loadingLayout
                            .setReleaseLabel(getString(R.string.pull_to_refresh_bottom_release));
                }
            }
        });

        // 下拉刷新监听
        mPull
                .setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

                    @Override
                    public void onPullDownToRefresh(
                            PullToRefreshBase<ListView> refreshView) {
                        // 下拉刷新(从第一页开始装载数据)
                        new GetListData().execute(path);
                        //queryData(0,STATE_REFRESH,0,what);
                    }

                    @Override
                    public void onPullUpToRefresh(
                            PullToRefreshBase<ListView> refreshView) {
                        // 上拉加载更多(加载下一页数据)
                        new GetListData().execute(path);
                        //queryData(curPage, STATE_MORE,0,what);
                    }
                });


        listView=mPull.getRefreshableView();
        
    }
	
	class GetListData extends AsyncTask<String, Void, ArrayList<News_List>> {
		@Override 
		protected ArrayList<News_List> doInBackground(String... arg0) {
			
			ArrayList<News_List> articleList =new ArrayList<News_List>();
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
					    articleList.add(news);
					 
					}
				} 
			} catch (IOException e) {
				
				System.out.println(".............****************************");
				e.printStackTrace(); 
				
			}return articleList; 
		} 
		@Override 
		protected void onPostExecute(ArrayList<News_List> newsList) { 
		 
			super.onPostExecute(newsList); 
			adapter = new com.hhu.xst.connectutil.ListAdapter(getActivity(),R.layout.news_listviewadapter,newsList); 
			Log.i("adapter", "----------"+adapter.isEmpty()); 
			Message msg = Message.obtain(); 
			msg.what=1; 
			handler.sendMessage(msg);
		} 
	} 
}