package com.hhu.xst.connectutil;

public class News_List {

	private String title="";
	private String summary="";
	private String postTime="";
	private String imgurl="";
	private String url;
	
	public News_List(String title,String summary,String postTime,String imgurl,String url){
		this.title=title;
		this.summary=summary;
		this.postTime=postTime;
		this.imgurl=imgurl;
		this.url=url;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getPostTime() {
		return postTime;
	}
	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
