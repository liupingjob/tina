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
	private int in10MTime;//10分钟内两次不属于迟到
	private int in15MTime;
	private int in30MTime;
	private int in30OutTime;
	private int in60OutTime;
	private Double leave1;//事假
	private Double leave2;//病假
	private Double leave3;//年假
	private Double leave4;//其它假
	private String leaveRemark;//请假情况
	private int noCard;
	private int noWork;//旷工
	private int zaoTui;//早退
	private String allTime;//总出勤时间
	private String allAddWorkTime;//总加班时间
	private String remark;//备注
	private Double jobScore;//绩效分
	private String jobScoreDesc;//
	private String state;//状态
	private String isAllTime;//是否全勤
	private String remarkAll;
	private String hasException;//需要人工匹配
	private String temp="";//需要人工匹配

	public static long getSerialVersionUID() {
		return serialVersionUID;
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

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
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


	public void setIsAllTime(String isAllTime) {
		this.isAllTime = isAllTime;
	}

	public String getRemarkAll() {
		return remarkAll;
	}

	public void setRemarkAll(String remarkAll) {
		this.remarkAll = remarkAll;
	}

	public int getIn10MTime() {
		return in10MTime;
	}

	public void setIn10MTime(int in10MTime) {
		this.in10MTime = in10MTime;
	}

	public int getIn15MTime() {
		return in15MTime;
	}

	public void setIn15MTime(int in15MTime) {
		this.in15MTime = in15MTime;
	}

	public int getIn30MTime() {
		return in30MTime;
	}

	public void setIn30MTime(int in30MTime) {
		this.in30MTime = in30MTime;
	}

	public int getIn30OutTime() {
		return in30OutTime;
	}

	public void setIn30OutTime(int in30OutTime) {
		this.in30OutTime = in30OutTime;
	}

	public int getIn60OutTime() {
		return in60OutTime;
	}

	public void setIn60OutTime(int in60OutTime) {
		this.in60OutTime = in60OutTime;
	}

	public Double getLeave1() {
		return leave1;
	}

	public void setLeave1(Double leave1) {
		this.leave1 = leave1;
	}

	public Double getLeave2() {
		return leave2;
	}

	public void setLeave2(Double leave2) {
		this.leave2 = leave2;
	}

	public Double getLeave3() {
		return leave3;
	}

	public void setLeave3(Double leave3) {
		this.leave3 = leave3;
	}

	public Double getLeave4() {
		return leave4;
	}

	public void setLeave4(Double leave4) {
		this.leave4 = leave4;
	}

	public String getLeaveRemark() {
		return leaveRemark;
	}

	public void setLeaveRemark(String leaveRemark) {
		this.leaveRemark = leaveRemark;
	}

	public int getNoCard() {
		return noCard;
	}

	public void setNoCard(int noCard) {
		this.noCard = noCard;
	}

	public int getNoWork() {
		return noWork;
	}

	public void setNoWork(int noWork) {
		this.noWork = noWork;
	}

	public int getZaoTui() {
		return zaoTui;
	}

	public void setZaoTui(int zaoTui) {
		this.zaoTui = zaoTui;
	}

	public Double getJobScore() {
		return jobScore;
	}

	public void setJobScore(Double jobScore) {
		this.jobScore = jobScore;
	}

	public String getJobScoreDesc() {
		return jobScoreDesc;
	}

	public void setJobScoreDesc(String jobScoreDesc) {
		this.jobScoreDesc = jobScoreDesc;
	}

	public String getIsAllTime() {
		return isAllTime;
	}

	public String getHasException() {
		return hasException;
	}

	public void setHasException(String hasException) {
		this.hasException = hasException;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
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
