package com.czsm.kq;

import java.io.Serializable;

public class CountDate implements Serializable, Comparable<CountDate>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String dep;
	private String jobId;
	private String position;
	private String name;
	private String in10MTime;//10分钟内两次不属于迟到
	private String in15MTime;
	private String in30MTime;
	private String in30OutTime;
	private String in60OutTime;
	private String leave1;//事假
	private String leave2;//病假
	private String leave3;//年假
	private String leave4;//其它假
	private String leaveRemark;//请假情况
	private String noCard;
	private String noWork;//旷工
	private String zaoTui;//早退
	private String allTime;//总出勤时间
	private String allAddWorkTime;//总加班时间
	private String remark;//备注
	private String jobScore;//绩效分
	private String jobScoreDesc;//
	private String state;//状态
	private String isAllTime;//是否全勤
	private String remarkAll;
	private String hasException;//需要人工匹配
	private String temp="";//需要人工匹配
	
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getIn60OutTime() {
		return in60OutTime;
	}
	public void setIn60OutTime(String in60OutTime) {
		this.in60OutTime = in60OutTime;
	}
	public String getHasException() {
		return hasException;
	}
	public void setHasException(String hasException) {
		this.hasException = hasException;
	}
	public String getJobScoreDesc() {
		return jobScoreDesc;
	}
	public void setJobScoreDesc(String jobScoreDesc) {
		this.jobScoreDesc = jobScoreDesc;
	}
	public String getNoCard() {
		return noCard;
	}
	public void setNoCard(String noCard) {
		this.noCard = noCard;
	}
	public String getRemarkAll() {
		return remarkAll;
	}
	public void setRemarkAll(String remarkAll) {
		this.remarkAll = remarkAll;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getLeaveRemark() {
		return leaveRemark;
	}
	public void setLeaveRemark(String leaveRemark) {
		this.leaveRemark = leaveRemark;
	}
	public String getJobScore() {
		return jobScore;
	}
	public void setJobScore(String jobScore) {
		this.jobScore = jobScore;
	}
	public String getIn10MTime() {
		return in10MTime;
	}
	public void setIn10MTime(String in10mTime) {
		in10MTime = in10mTime;
	}
	public String getIsAllTime() {
		return isAllTime;
	}
	public void setIsAllTime(String isAllTime) {
		this.isAllTime = isAllTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDep() {
		return dep;
	}
	public void setDep(String dep) {
		this.dep = dep;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIn15MTime() {
		return in15MTime;
	}
	public void setIn15MTime(String in15mTime) {
		in15MTime = in15mTime;
	}
	public String getIn30MTime() {
		return in30MTime;
	}
	public void setIn30MTime(String in30mTime) {
		in30MTime = in30mTime;
	}
	public String getIn30OutTime() {
		return in30OutTime;
	}
	public void setIn30OutTime(String in30OutTime) {
		this.in30OutTime = in30OutTime;
	}
	public String getLeave1() {
		return leave1;
	}
	public void setLeave1(String leave1) {
		this.leave1 = leave1;
	}
	public String getLeave2() {
		return leave2;
	}
	public void setLeave2(String leave2) {
		this.leave2 = leave2;
	}
	public String getLeave3() {
		return leave3;
	}
	public void setLeave3(String leave3) {
		this.leave3 = leave3;
	}
	public String getLeave4() {
		return leave4;
	}
	public void setLeave4(String leave4) {
		this.leave4 = leave4;
	}
	public String getNoWork() {
		return noWork;
	}
	public void setNoWork(String noWork) {
		this.noWork = noWork;
	}
	public String getZaoTui() {
		return zaoTui;
	}
	public void setZaoTui(String zaoTui) {
		this.zaoTui = zaoTui;
	}
	public String getAllTime() {
		return allTime;
	}
	public void setAllTime(String allTime) {
		this.allTime = allTime;
	}
	public String getAllAddWorkTime() {
		return allAddWorkTime;
	}
	public void setAllAddWorkTime(String allAddWorkTime) {
		this.allAddWorkTime = allAddWorkTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public int compareTo(CountDate o) {
		if(o==null||o.getDep()==null||o.getPosition()==null) {
			return 0;
		}
		int i= this.getDep().compareTo(o.getDep());
		if(i==0) {
			i=this.getPosition().compareTo(o.getPosition());
		}
		return i;
	}
}
