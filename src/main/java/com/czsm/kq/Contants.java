package com.czsm.kq;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 常量类
 * 
 * @author mac
 *
 */
public class Contants {

	public final static String dateFmtStr = "yyyy-MM-dd";
	public final static String dateFmtStr1 = "yyyy/MM/dd";
	public final static String timeFmtStr = "HH:mm";
	public final static String dateTimeFmtStr = "yyyy-MM-dd HH:mm";
	public final static String dateTimeFmtStr0 = "yyyy-MM-dd HH:mm:ss";
	public final static String dateTimeFmtStr1 = "yyyy/MM/dd HH:mm:ss";

	public final static String inTime = "9:00";
	public final static String outTime = "18:00";

	public static String getFileName(String fileName) {
		return fileName + (new SimpleDateFormat("yyyy-MM-dd HH mm ss").format(new Date())) + ".xls";
	}

	/**
	 * 人员信息常量部分
	 */
	public final static String userInfoFile = "userInfo.xls";
	public final static String userInfoHeadTitle = "人员信息";
	public final static String[] userInfoTitles = { "序号", "部门", "职务", "工号", "姓名", "状态", "入职日期", "离职日期", "备注" };
	public final static String[] userInfoBeanPropertys = { "id", "dep", "position", "jobId", "name", "state", "inDate",
			"goDate", "remark" };

	/**
	 * 统计信息常量部分
	 */
	public final static String countDataInfoFile = "countDataInfo.xls";
	public final static String countDataHeadTitle = "考勤汇总统计报表信息";
	public final static String[] countDataTitles = { "序号", "部门", "职务", "姓名", "10分钟内", "15分钟内", "15到30分钟", "30分钟及以上",
			"事假","病假","年休假","其他","未打卡", "旷工", "早退", "请假情况", "备注", "是否全勤", "绩效分", "绩效说明", "人工匹配" };
	public final static String[] countDataBeanPropertys = { "id", "dep", "position", "name", "in10MTime", "in15MTime",
			"in30MTime", "in30OutTime",  "leave1", "leave2", "leave3","leave4","noCard", "noWork", "zaoTui", "leaveRemark", "remarkAll", "isAllTime",
			"jobScore", "jobScoreDesc", "hasException" };

	/**
	 * 外出钉钉信息常量部分
	 */

	public final static String dingDingInfoFile = "dingDingInfo.xls";
	public final static String dingDingHeadTitle = "外出钉钉打卡信息";
	public final static String[] dingDingTitles = { "序号", "姓名", "日期", "时间", "地点", "备注" };
	public final static String[] dingDingBeanPropertys = { "id", "name", "date", "time", "addr", "remark" };

	/**
	 * 请假信息常量部分
	 */

	public final static String leaveInfoFile = "leaveInfo.xls";
	public final static String leaveHeadTitle = "请假信息";
	public final static String[] leaveTitles = { "序号", "姓名", "请假信息", "备注" };
	public final static String[] leaveBeanPropertys = { "id", "name", "leaveInfo", "remark" };

	/**
	 * 工作日信息常量部分
	 */

	public final static String workDateInfoFile = "workDateInfo.xls";
	public final static String workDateHeadTitle = "工作日信息";
	public final static String[] workDateTitles = { "序号", "月份", "日", "上班时间", "下班时间" };
	public final static String[] workDateBeanPropertys = { "id", "month", "day", "startTime", "endTime" };

	/**
	 * 基本考勤信息常量部分
	 */

	public final static String workTimeInfoFile = "workTimeInfo.xls";
	public final static String workTimeHeadTitle = "基本考勤信息";
	public final static String[] workTimeTitles = { "考勤号码", "姓名", "日期", "签到时间", "签退时间" };
	public final static String[] workTimeBeanPropertys = { "id", "username", "dateTime", "inTime", "goTime" };

}
