package com.example.osnews;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

	private ArrayList<Article> mArticleList;
	private int resourceId;
	private Context ctx;
	
	public ListAdapter(Context context, int textViewResourceId, ArrayList<Article> objects) {
		resourceId = textViewResourceId;
		this.mArticleList = objects;
		this.ctx = context;
	}
	@Override
	public int getCount() {
		
		return mArticleList.size();
	}

	@Override
	public Article getItem(int position) {
		return mArticleList.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Article article = getItem(position);
		View view;
		ViewHolder viewHolder;
		
		if (convertView == null) {
			view= LayoutInflater.from(ctx).inflate(resourceId, null);
			
			viewHolder = new ViewHolder();			
 			viewHolder.title = (TextView) view.findViewById(R.id.newstitle);
			//viewHolder.summary = (TextView) view.findViewById(R.id.summary);
			viewHolder.postTime = (TextView) view.findViewById(R.id.newspostTime);
			viewHolder.imge=(ImageView)view.findViewById(R.id.newsimage);
			view.setTag(viewHolder);
		} else {
			view=convertView;
        	viewHolder = (ViewHolder) view.getTag();
		}
		
		if(article.getImgurl().length()>0&&article.getImgurl().charAt(4)!='s'){
			Bitmap 	bitmap =getHttpBitmap(article.getImgurl());
			 viewHolder.imge.setImageBitmap(bitmap);	
		}
		viewHolder.title.setText(article.getTitle());
		viewHolder.postTime.setText(article.getPostTime());
		
		
		return view;
	}

	static class ViewHolder {
		public TextView title;
		//public TextView summary;
		public TextView postTime;
		public ImageView imge;
	}
	
	
    public static Bitmap getHttpBitmap(String url){  
        URL imgUrl = null;  
        Bitmap bitmap = null;  
        try {  
        	StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);
            imgUrl = new URL(url);  
            HttpURLConnection conn = (HttpURLConnection)imgUrl.openConnection();  
            conn.setConnectTimeout(3000);
            
           // int code = conn.getResponseCode();
            
            conn.setDoInput(true);  
            conn.connect(); 
            //Log.e("code", code+"");
            InputStream is = conn.getInputStream();  
            bitmap = BitmapFactory.decodeStream(is);  
            is.close();  
        } catch (MalformedURLException e) {  
            // TODO Auto-generated catch block 
        	 Log.e("“Ï≥£", e.getMessage());
            e.printStackTrace();  
           
        }catch(IOException e){ 
        	Log.e("“Ï≥£", e.getMessage());
            e.printStackTrace(); 
            
        }  
        return bitmap;  
    }
    
    
    public static Bitmap getLoacalBitmap(String url) {
        try {
             FileInputStream fis = new FileInputStream(url);
             return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
             e.printStackTrace();
             return null;
        }
   }
}
