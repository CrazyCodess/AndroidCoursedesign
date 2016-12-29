package com.hhu.xst.connectutil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.hhu.xst.function.NewsActivity;
import com.jereh.slidingdemo.R;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

	private ArrayList<News_List> newsList;
	private int resourceId;
	private Context ctx;
	
	public ListAdapter(Context context, int textViewResourceId, ArrayList<News_List> objects) {
		resourceId = textViewResourceId;
		this.newsList = objects;
		this.ctx = context;
		
	}
	
	
	@Override
	public int getCount() {
		
		return newsList.size();
	}

	@Override
	public News_List getItem(int position) {
		return newsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		News_List newsList = getItem(position);
		View view;
		ViewHolder viewHolder;
		
		if (convertView == null) {
			view= LayoutInflater.from(ctx).inflate(resourceId, null);
			
			viewHolder = new ViewHolder();			
 			viewHolder.title = (TextView) view.findViewById(R.id.newsss_title);
			viewHolder.postTime = (TextView) view.findViewById(R.id.newsss_postTime);
			viewHolder.imge=(ImageView)view.findViewById(R.id.newsss_image);
			view.setTag(viewHolder);
		} else {
			view=convertView;
        	viewHolder = (ViewHolder) view.getTag();
		}
		
		if(newsList.getImgurl().length()>0&&newsList.getImgurl().charAt(4)!='s'){
			Bitmap 	bitmap =getHttpBitmap(newsList.getImgurl());
			 viewHolder.imge.setImageBitmap(bitmap);	
		}
		viewHolder.title.setText(newsList.getTitle());
		viewHolder.postTime.setText(newsList.getPostTime());
		LinearLayout layout=(LinearLayout)view.findViewById(R.id.newsss_Linear);
		layout.setOnClickListener(new MyClick(newsList));
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
    
    class MyClick implements View.OnClickListener{

    	News_List newslist;
    	public MyClick(News_List newslist){
    		
    	}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
				case 0x7f060068:
					Bundle bundle =new Bundle();
					bundle.putString("url", newslist.getUrl());
					Intent intent=new Intent(ctx,NewsActivity.class);
					intent.putExtras(bundle);
					ctx.startActivity(intent);
					break;
				default:break;
			}
		}
    	
    }
    
}
