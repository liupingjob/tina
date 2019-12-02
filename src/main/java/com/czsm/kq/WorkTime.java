package com.czsm.kq;

import java.io.Serializable;

/**
 * 基本考勤信息
 * @author mac
 *
 */
public class WorkTime implements Serializable , Comparable<WorkTime>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String username;
	private String time;
	private String inTime;
	private String goTime;
	private String date;
	private String dateTime;
	private String day;
	private String jobId;
	private String moth;
	private String state;
	private String type;
	private String remark;
	
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDateTime() {
		return dateTime==null?"":dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username==null?"":username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getGoTime() {
		return goTime;
	}
	public void setGoTime(String goTime) {
		this.goTime = goTime;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getMoth() {
		return moth;
	}
	public void setMoth(String moth) {
		this.moth = moth;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public int compareTo(WorkTime o) {
		if(o==null||o.getUsername()==null||o.getDateTime()==null) {
			return 0;
		}
		int i= this.getUsername().compareTo(o.getUsername());
		if(i==0) {
			i=this.getDateTime().compareTo(o.getDateTime());
		}
		return i;
	}
	
	
	
}
