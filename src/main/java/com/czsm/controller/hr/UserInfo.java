package com.czsm.controller.hr;

public class UserInfo implements Comparable<UserInfo>{
	private String id="";
	private String username="";
	private String sex="";
	private String phone="";//
	private String mail="";
	private String position="";// 职位
	private String from="";// 来源
	private String yaoyueDate="";// 邀约时间
	private String isPass0="";
	private String testDate="";// 面试时间
	private String isPass1="";// 初试是否通过
	private String reason="";// 初试说明
	private String department="";// 部门
	private String isPass2="";// 复试是否通过
	private String offerDate="";// offer日期
	private String remark="";
	private int  page;
	private int limit;
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public String getIsPass0() {
		return isPass0;
	}

	public void setIsPass0(String isPass0) {
		this.isPass0 = isPass0;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getYaoyueDate() {
		return yaoyueDate;
	}

	public void setYaoyueDate(String yaoyueDate) {
		this.yaoyueDate = yaoyueDate;
	}

	public String getTestDate() {
		return testDate;
	}

	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}

	public String getIsPass1() {
		return isPass1;
	}

	public void setIsPass1(String isPass1) {
		this.isPass1 = isPass1;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getIsPass2() {
		return isPass2;
	}

	public void setIsPass2(String isPass2) {
		this.isPass2 = isPass2;
	}

	public String getOfferDate() {
		return offerDate;
	}

	public void setOfferDate(String offerDate) {
		this.offerDate = offerDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public int compareTo(UserInfo o) {
		if(o==null||o.getYaoyueDate()==null) {
			return 0;
		}
		return this.getYaoyueDate().compareTo(o.getYaoyueDate());
	}

}
