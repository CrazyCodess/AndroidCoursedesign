package com.hhu.xst.ui;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Shader.TileMode;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hhu.xst.connectutil.News_List;
import com.hhu.xst.function.LoginActivity;
import com.hhu.xst.function.NewsActivity;
import com.hhu.xst.main.MainActivity;
import com.jereh.slidingdemo.R;

/**
 * @���� ��Fragment��Ҫʹ��ListView����Ҫ��ListFragment
 * */
@SuppressLint("SimpleDateFormat")
public class NewsFragment extends Fragment {
	PullToRefreshListView mPull;
	List<News_List>  newsList=new ArrayList<News_List>();
	private ILoadingLayout loadingLayout;
	ListView listView;
	MyAdapter myListAdapter;
	
	
	private String[] names = new String[] { "RonCheen", "Bless Lee", "Mark" };
	private String[] descs = new String[3];
	private int[] ImagIds = new int[] { R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.ic_launcher, };
	private ListView list;
	public static String title;

	/**
	 * @���� ��onCreateView�м��ز���
	 * */
	@Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState) {  
        View view = inflater.inflate(R.layout.fragment_news, container,false);  
        list = (ListView) view.findViewById(android.R.id.list);  
        initListView(view);
        Tianchong();
       
        return view;  
    }
	
	private void initListView(View view)
    {

    /*    initTopBarForBoth(title, R.drawable.base_action_bar_add_bg_selector, new HeaderLayout.onRightImageButtonClickListener() {
            @Override
            public void onClick() {
                startAnimActivity(AddGoodsActivity.class);
                finish();
            }
        });*/
        //queryData(0,STATE_REFRESH,1,what);
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
        // //��������
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

        // ����ˢ�¼���
        mPull
                .setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

                    @Override
                    public void onPullDownToRefresh(
                            PullToRefreshBase<ListView> refreshView) {
                        // ����ˢ��(�ӵ�һҳ��ʼװ������)
                        //queryData(0,STATE_REFRESH,0,what);
                    }

                    @Override
                    public void onPullUpToRefresh(
                            PullToRefreshBase<ListView> refreshView) {
                        // �������ظ���(������һҳ����)
                        //queryData(curPage, STATE_MORE,0,what);
                    }
                });


        listView=mPull.getRefreshableView();
       myListAdapter=new MyAdapter(getActivity());
        listView.setAdapter(myListAdapter);
        
    }
	
	private void querydata(){
		
	}
	
	private class MyAdapter extends BaseAdapter {
        class ViewHolder{
            ImageView headph,thingph;
            TextView personName,timeText,adressText,thingDetails,phoneNumber;
            TextView oldPrice,newPrice;
            LinearLayout layout;
            LinearLayout userInfo;
            ImageButton delete;
        }
        class myClick implements View.OnClickListener {



           /*
           * ͨ�����췽������������ֵ
           *
           *
           * */

            //Goods_List goods;
            //String avatar;
            //String time;
            String phone;
            public myClick(String phone)
            {
                this.phone=phone;
            }
      /*      public myClick(Goods_List goods){
                this.goods=goods;
                //this.avatar=avatar;
            }*/
            @Override
            public void onClick(View v) {
                Bundle bundle;
                Intent intent;
                /*switch (v.getId()){
                    case R.id.marget_headph:
                        bundle=new Bundle();
                        bundle.putSerializable("goods",goods);
                        //bundle.putString("avatar",avatar);
                        intent=new Intent(getActivity(),userInfo.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case R.id.marget_thinglinear:
                    case R.id.userInfo:
                        bundle=new Bundle();
                        bundle.putSerializable("goods",goods);
                        // bundle.putString("avatar",avatar);
                        intent=new Intent(getActivity(),thingdetailsActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case R.id.phoneNumber:
                        intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                        startActivity(intent);
                        break;
                }*/
            }
        }
        Context context;
        /*        String name,details;
                int resourceId0,resourceId1;
                String adress,time;*/
        public MyAdapter(Context context)   {
            this.context=context;
        }
        /*
        * ͨ�����췽������������ʱ�䣬�ص㣬ͷ����ƷͼƬ����ϸ��Ϣ
        *
        * */
/*        public myAdapter(Context context,String name,String time,String adress,String details,int resourceId0,int resourceId1){
            this.context=context;
            this.name=name;
            this.details=details;
            this.resourceId0=resourceId0;
            this.resourceId1=resourceId1;
            this.adress=adress;
            this.time=time;
        }*/
        @Override
        public int getCount() {
            return newsList.size();
        }

        @Override
        public Object getItem(int position) {
            return newsList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if(convertView==null){
                convertView= LayoutInflater.from(getActivity()).inflate(R.layout.news_listviewadapter,parent,false);
                holder=new ViewHolder();
                /*holder.headph=(ImageView)convertView.findViewById(R.id.marget_headph);
                holder.personName=(TextView)convertView.findViewById(R.id.marget_name);
                holder.timeText=(TextView)convertView.findViewById(R.id.marget_time);
                holder.adressText=(TextView)convertView.findViewById(R.id.marget_adress);
                holder.thingph=(ImageView)convertView.findViewById(R.id.marget_thing);
                holder.thingDetails=(TextView)convertView.findViewById(R.id.marget_thingdetails);
                holder.newPrice=(TextView)convertView.findViewById(R.id.marget_thingNewprice);
                holder.oldPrice=(TextView)convertView.findViewById(R.id.marget_Oldprice);
                holder.layout=(LinearLayout)convertView.findViewById(R.id.marget_thinglinear);
                holder.userInfo=(LinearLayout)convertView.findViewById(R.id.userInfo);
                holder.phoneNumber=(TextView)convertView.findViewById(R.id.phoneNumber);
                holder.delete=(ImageButton)convertView.findViewById(R.id.adapter_delete);*/
                convertView.setTag(holder);
            }
            else{
                holder = (ViewHolder) convertView.getTag();
            }
            /*
            * �����������ı���ʵ��ʵ������
            *
            *
            * */

            //Goods_List goods=(Goods_List)getItem(position);
            /*BmobFile icon=goods.getIcon();
            icon.loadImage(FindActivity.this,holder.headph);*/



      
           
           


            // BmobChatUser bcuser=goods.getGoodsUser();
           

            
            holder.phoneNumber.setOnClickListener(new myClick(holder.phoneNumber.getText().toString().trim()));
            return convertView;
        }





    }
	
	
	
	
/**
 * �÷�������Ϊ��Ѷ�������
 */
	private void Tianchong() {
		// TODO Auto-generated method stub
		 SimpleDateFormat formatter = new SimpleDateFormat(
	    			"yyyy��MM��dd��    HH:mm:ss     ");
	    	Date curDate = new Date(System.currentTimeMillis());// ��ȡ��ǰʱ��
	    	descs[0] = formatter.format(curDate);
	    	SimpleDateFormat sDateFormat = new SimpleDateFormat(
	    			"yyyy-MM-dd    hh:mm:ss");
	    	descs[1] = sDateFormat.format(new java.util.Date());
	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");    
	    	descs[2] = sdf.format(new java.util.Date());    
	    	List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
	    	for(int i = 0; i<names.length;i++){
	    		Map <String,Object> listItem = new HashMap<String, Object>();
	    		listItem.put("header", ImagIds[i]);
	    		listItem.put("personName", names[i]);
	    		listItem.put("desc",descs[i]);
	    		listItems.add(listItem);
	    	}
	    	/**
	    	 * ע��getActivity��һ��ֵ�ü�ס�ĵط�
	    	 */
	    	SimpleAdapter simple = new SimpleAdapter(getActivity(), listItems, R.layout.item_list, new String[]
					{"header","personName","desc"}, new int[]{R.id.header,R.id.name,R.id.desc});

	    	list.setAdapter(simple);
	    	list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
//					Toast toast=Toast.makeText(getActivity(),"���ǵ�"+(position+1)+"����Ϣ",Toast.LENGTH_SHORT);
//				    toast.show();
					/**
					 * title ������activity������Ϣ
					 */
				    title = "This is the news Page of " + String.valueOf(position) +" !";
				    Intent intent = new Intent(getActivity(),NewsActivity.class);
			//intent.setActivity(getActivity(), NewsActivity.class);
				    startActivity(intent);
				}
			});
	}
}