package com.minq.domain;

import java.util.Date;

public class Project {
	
	private int id;
	private String title;
	private String url;
	private String source;
	private String catalogy;
	private String keyword;
	private Date ptime;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getCatalogy() {
		return catalogy;
	}
	public void setCatalogy(String catalogy) {
		this.catalogy = catalogy;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Date getPtime() {
		return ptime;
	}
	public void setPtime(Date ptime) {
		this.ptime = ptime;
	}

	
	public void out(){
		System.out.print("id  :"+ this.id
				+"\ncatalogy  :"+this.catalogy
				+"\nkeyword  :"+this.keyword+"\n"
		);
	}

}
