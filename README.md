# AndroidCoursedesign

移动互联网课程设计

### OSNews是抓取开源中国网页的一个实例。

### 使用终端模拟器通过wifi连接androidstdio

打开手机wifi连接到网络。使用已经拥有root权限的手机，下载终端模拟器，打开。
输入以下命令

* su
* setprop service.adb.tcp.port 5555
* stop adbd
* start adbd

* 点开wifi连接详情查看ip地址

* 在电脑cmd输入如下命令
* adb connect ip:port
* 例如我的是 adb connect 192.169.191.2:5555

### 五天的任务说明

* 第一天的项目是在day1文件夹中，主要是搭建环境并编写一个简单的HelloWorld
* 第二天项目在UIdesign文件夹中，简单的UI设计
* 第三天项目在test1文件夹中，主要是熟悉Activity的跳转和Intent的消息传递机制
* 第四天项目在dayfour中，主要是音乐播放器的功能，可以以后作为音乐播放器的样例使用
* 第五天项目在dayfive中，主要是数据库的操作，数据库的操作在DBHelper中实现，这是一个数据库操作的模板类，可以以后当做数据库操作的模板使用。

### 校视通项目

* 咨询页面实现了从开源中国网站http://www.oschina.net/news抓取了咨询列表里面的资讯然后填充到ListView中
* 主要的类和方法是Jsoup里面提供的，网上有帮助文档http://www.open-open.com/jsoup/
* 主要代码如下：

```
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
```