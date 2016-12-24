package com.minq.domain;

import java.util.Date;

public class Pathrecord {
	private int id;
	private int enterpriseid;
	private String enterprisename;
	private String targetname;
	private String targeturl;
	public String getTargeturl() {
		return targeturl;
	}
	public void setTargeturl(String targeturl) {
		this.targeturl = targeturl;
	}
	private Date accesstime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEnterpriseid() {
		return enterpriseid;
	}
	public void setEnterpriseid(int enterpriseid) {
		this.enterpriseid = enterpriseid;
	}
	public String getEnterprisename() {
		return enterprisename;
	}
	public void setEnterprisename(String enterprisename) {
		this.enterprisename = enterprisename;
	}
	public String getTargetname() {
		return targetname;
	}
	public void setTargetname(String targetname) {
		this.targetname = targetname;
	}
	public Date getAccesstime() {
		return accesstime;
	}
	public void setAccesstime(Date accesstime) {
		this.accesstime = accesstime;
	}
	
	public void out(){
			System.out.println("id  :"+ this.id
					+"\ntargetname  :" + this.targetname
					+"\ntargeturl  :"+ this.targeturl
					+"\nenterpriseid  :" +this.enterpriseid
			);
	}

}
