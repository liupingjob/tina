package com.czsm.kq;

import java.io.Serializable;

/**
 * 请假信息表
 * 
 * @author mac
 *
 */
public class LeaveInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String month;
	private String day;
	private String type;
	private String leave1;// 事假
	private String leave2;// 病假
	private String leave3;// 年假
	private String leave4;// 其它假
	private double longTime;// 时长
	private String leaveInfo;// 请假信息
	private String leaveInfo1;// 请假信息
	private String remark;
	private String dep;
	private String position;
	private String temp;

	public String getLeaveInfo1() {
		return leaveInfo1;
	}

	public void setLeaveInfo1(String leaveInfo1) {
		this.leaveInfo1 = leaveInfo1;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
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

	public String getLeaveInfo() {
		return leaveInfo;
	}

	public void setLeaveInfo(String leaveInfo) {
		this.leaveInfo = leaveInfo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getLongTime() {
		return longTime;
	}

	public void setLongTime(double longTime) {
		this.longTime = longTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
