package com.czsm.controller.hr;

public class DepInfo {
	private String depName="";// 部门
	private String position="";// 职位
	private String from="";/// 渠道
	private int yaoyueAll=0;// 邀约总数
	private int yaoyueSucc;// 邀约成功数
	private String yaoyueLv;// 邀约率
	private int test;// 初试面试人数
	private int testSucc;// 初试成功人数
	private String testLv;// 初试成功率
	private int test1;// 复试试面试人数
	private int test1Succ;// 复试成功人数
	private String test1Lv;// 复试成功率
	private int offer;// offer数
	private String usernames="";//名称集

	public String getUsernames() {
		return usernames;
	}

	public void setUsernames(String usernames) {
		this.usernames = usernames;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
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

	public int getYaoyueAll() {
		return yaoyueAll;
	}

	public void setYaoyueAll(int yaoyueAll) {
		this.yaoyueAll = yaoyueAll;
	}

	public int getYaoyueSucc() {
		return yaoyueSucc;
	}

	public void setYaoyueSucc(int yaoyueSucc) {
		this.yaoyueSucc = yaoyueSucc;
	}

	public String getYaoyueLv() {
		return  (double)yaoyueSucc/(double)yaoyueAll+"";
	}

	public void setYaoyueLv(String yaoyueLv) {
		this.yaoyueLv = yaoyueLv;
	}

	public int getTest() {
		return test;
	}

	public void setTest(int test) {
		this.test = test;
	}

	public int getTestSucc() {
		return testSucc;
	}

	public void setTestSucc(int testSucc) {
		this.testSucc = testSucc;
	}

	public String getTestLv() {
		return (double)testSucc/(double)test+"";
	}

	public void setTestLv(String testLv) {
		this.testLv = testLv;
	}

	public int getTest1() {
		return test1;
	}

	public void setTest1(int test1) {
		this.test1 = test1;
	}

	public int getTest1Succ() {
		return test1Succ;
	}

	public void setTest1Succ(int test1Succ) {
		this.test1Succ = test1Succ;
	}

	public String getTest1Lv() {
		return (double)test1Succ/(double)test1+"";
	}

	public void setTest1Lv(String test1Lv) {
		this.test1Lv = test1Lv;
	}

	public int getOffer() {
		return offer;
	}

	public void setOffer(int offer) {
		this.offer = offer;
	}

}
