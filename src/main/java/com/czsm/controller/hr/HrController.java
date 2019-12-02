package com.czsm.controller.hr;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.czsm.util.DateUtil;
import com.czsm.util.ExcelUtil;
import com.czsm.util.StringUtil;

@Controller
public class HrController {
	private String tempSessionID = "zpTempData";
	private String loginSessionId = "loginSessionId";
	private String allDataFileName = "all.xls";
	private String headTitle = "招聘人员信息表";
	private String[] titles = { "序号", "姓名", "	性别", "电话", "邮箱", "面试职位", "来源渠道", "	邀约日期", "邀约成功", "面试日期", "初试通过",
			"初试评价", "复试部门", "复试结果", "Offer日期", "备注" };
	private String[] beanPropertys = { "id", "username", "sex", "phone", "mail", "position", "from", "yaoyueDate",
			"isPass0", "testDate", "isPass1", "reason", "department", "isPass2", "offerDate", "remark" };

	/**
	 * 手动单行录入招聘信息
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public String toIndex() {
		return "login2";
	}

	@RequestMapping("/hr")
	public String toIndex1(HttpServletRequest req) {
		if (req.getSession().getAttribute(loginSessionId) == null) {
			return "login2";
		}
		return "index1";
	}

	/**
	 * 手动单行录入招聘信息
	 * 
	 * @return
	 */
	@RequestMapping("userLogin")
	@ResponseBody
	public String userLogin(UserInfo info, HttpServletRequest req) {
		String username = info.getUsername();
		String pwd = info.getSex();
		String code = info.getPhone();
		String msg = "";
		if (code.equals("平哥")) {
			if (username.equals("Tina") || username.equals("Mac")) {
				if (pwd.equals("5200")) {
					req.getSession().setAttribute(loginSessionId, username);
					msg = "1";
				} else {
					msg = "密码不正确!";
				}
			} else {
				msg = "用户名不正确！";
			}
		} else {
			msg = "验证码不正确";
		}
		return msg;
	}

	/**
	 * 按部门统计数据
	 * 
	 * @return
	 */
	@RequestMapping("countDataBm")
	@ResponseBody
	public DataJson countDataBm(String time) {
		List<UserInfo> list = readDataListFromFile();
		// 1、查询数据
		Iterator<UserInfo> it = list.iterator();
		String[] date = time.split(" - ");
		while (it.hasNext()) {
			UserInfo x = it.next();
			if (x.getYaoyueDate().compareTo(date[0]) < 0 || x.getYaoyueDate().compareTo(date[1]) > 0) {
				it.remove();
			}
		}
		// 2、分析数据
		List<DepInfo> depList = new ArrayList<DepInfo>();
		for (UserInfo i : list) {
			boolean hasDep = false;
			DepInfo d = null;
			for (DepInfo dep : depList) {
				if (i.getDepartment().trim().indexOf(dep.getDepName().trim()) >= 0) {
					hasDep = true;
					d = dep;
					break;
				}
			}
			if (!hasDep) {// 如果没有找到部门就要新建一个并加入到depList
				d=new DepInfo();
				d.setDepName(i.getDepartment().trim());
				d.setFrom(i.getFrom().trim());
				d.setPosition(i.getPosition().trim());
				d.setYaoyueAll(1);
				d.setYaoyueSucc(i.getIsPass0().indexOf("成功") >= 0 ? 1 : 0);
				d.setTest(StringUtil.isNotBlank(i.getTestDate())?1:0);
				d.setTestSucc(i.getIsPass1().indexOf("通过")>=0?1:0);
				d.setTest1(StringUtil.isNotBlank(i.getDepartment())?1:0);
				d.setTest1Succ(i.getIsPass2().indexOf("通过")>=0?1:0);
				d.setOffer(StringUtil.isNotBlank(i.getOfferDate())?1:0);
				depList.add(d);
			} else {
				d.setFrom(d.getFrom().indexOf(i.getFrom().trim())>=0?d.getFrom():d.getFrom()+" "+i.getFrom());
				d.setPosition(d.getPosition().indexOf(i.getPosition().trim())>=0?d.getPosition():d.getPosition()+" "+i.getPosition());
				d.setYaoyueAll(d.getYaoyueAll()+1);
				d.setYaoyueSucc(i.getIsPass0().indexOf("成功") >= 0 ? d.getYaoyueSucc()+1 : d.getYaoyueSucc());
				d.setTest(StringUtil.isNotBlank(i.getTestDate())?d.getTest()+1:d.getTest());
				d.setTestSucc(i.getIsPass1().indexOf("通过")>=0?d.getTest1Succ()+1:d.getTestSucc());
				d.setTest1(StringUtil.isNotBlank(i.getDepartment())?d.getTest1()+1:d.getTest1());
				d.setTest1Succ(i.getIsPass2().indexOf("通过")>=0?d.getTest1Succ()+1:d.getTest1Succ());
				d.setOffer(StringUtil.isNotBlank(i.getOfferDate())?1:0);
			}
			d.setUsernames(d.getUsernames()+" "+i.getUsername());
		}
		 
		DataJson json=new DataJson();
		json.setData(depList);
		return json;
	}

	
	/**
	 * 按部门统计数据
	 * 
	 * @return
	 */
	@RequestMapping("countDataZw")
	@ResponseBody
	public DataJson countDataZw(String time) {
		List<UserInfo> list = readDataListFromFile();
		// 1、查询数据
		Iterator<UserInfo> it = list.iterator();
		String[] date = time.split(" - ");
		while (it.hasNext()) {
			UserInfo x = it.next();
			if (x.getYaoyueDate().compareTo(date[0]) < 0 || x.getYaoyueDate().compareTo(date[1]) > 0) {
				it.remove();
			}
		}
		// 2、分析数据
		List<PositionInfo> depList = new ArrayList<PositionInfo>();
		for (UserInfo i : list) {
			boolean hasDep = false;
			PositionInfo d = null;
			for (PositionInfo dep : depList) {
				if (i.getPosition().trim().indexOf(dep.getPosition().trim()) >= 0) {
					hasDep = true;
					d = dep;
					break;
				}
			}
			if (!hasDep) {// 如果没有找到部门就要新建一个并加入到depList
				d=new PositionInfo();
				d.setPosition(i.getPosition().trim());
				d.setFrom(i.getFrom().trim());
				d.setYaoyueAll(1);
				d.setYaoyueSucc(i.getIsPass0().indexOf("成功") >= 0 ? 1 : 0);
				d.setTest(StringUtil.isNotBlank(i.getTestDate())?1:0);
				d.setTestSucc(i.getIsPass1().indexOf("通过")>=0?1:0);
				d.setTest1(StringUtil.isNotBlank(i.getDepartment())?1:0);
				d.setTest1Succ(i.getIsPass2().indexOf("通过")>=0?1:0);
				d.setOffer(StringUtil.isNotBlank(i.getOfferDate())?1:0);
				depList.add(d);
			} else {
				d.setFrom(d.getFrom().indexOf(i.getFrom().trim())>=0?d.getFrom():d.getFrom()+" "+i.getFrom());
				d.setYaoyueAll(d.getYaoyueAll()+1);
				d.setYaoyueSucc(i.getIsPass0().indexOf("成功") >= 0 ? d.getYaoyueSucc()+1 : d.getYaoyueSucc());
				d.setTest(StringUtil.isNotBlank(i.getTestDate())?d.getTest()+1:d.getTest());
				d.setTestSucc(i.getIsPass1().indexOf("通过")>=0?d.getTest1Succ()+1:d.getTestSucc());
				d.setTest1(StringUtil.isNotBlank(i.getDepartment())?d.getTest1()+1:d.getTest1());
				d.setTest1Succ(i.getIsPass2().indexOf("通过")>=0?d.getTest1Succ()+1:d.getTest1Succ());
				d.setOffer(StringUtil.isNotBlank(i.getOfferDate())?1:0);
			}
			d.setUsernames(d.getUsernames()+" "+i.getUsername());
		}
		 
		DataJson json=new DataJson();
		json.setData(depList);
		return json;
	}
	/**
	 * 手动单行录入招聘信息
	 * 
	 * @return
	 */
	@RequestMapping("addUserInfo")
	@ResponseBody
	public String addUserInfo(UserInfo info, HttpServletRequest req) {
		List<UserInfo> list = readDataListFromFile();
		list.add(info);
		saveDataListFromSession(list);
		return "姓名：" + info.getUsername();
	}

	/**
	 * 上传文件到临时session
	 * 
	 * @param file
	 * @param req
	 * @return
	 */
	@RequestMapping("uploadUserInfoFile")
	@ResponseBody
	public List<UserInfo> uploadUserInfoFile(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
		File tempFile = new File(file.getOriginalFilename());
		try {
			FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file.getOriginalFilename()));
			byte[] bs = new byte[1024];
			int len;
			while ((len = fileInputStream.read(bs)) != -1) {
				bos.write(bs, 0, len);
			}
			bos.flush();
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<UserInfo> list = ExcelUtil.parserExcel(UserInfo.class, tempFile, beanPropertys);
		Iterator<UserInfo> it = list.iterator();
		while (it.hasNext()) {
			UserInfo x = it.next();
			if (StringUtil.isBlank(x.getId())) {
				it.remove();
			}
		}
		req.getSession().setAttribute(tempSessionID, list);
		tempFile.delete();
		return list;
	}

	/**
	 * 从临时session中保存数据到文件
	 * 
	 * @param req
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("saveUploadFileData")
	@ResponseBody
	public String saveUploadFileData(HttpServletRequest req) {
		List<UserInfo> list = readDataListFromFile();
		List<UserInfo> addList = (List<UserInfo>) req.getSession().getAttribute(tempSessionID);
		list.addAll(addList);
		saveDataListFromSession(list);
		return "保存成功";
	}

	/*
	 * 从临时session中保存数据到文件
	 * 
	 * @param req
	 * 
	 * @return
	 */
	@RequestMapping("delZpUserInfo")
	@ResponseBody
	public String delZpUserInfo(String ids) {
		List<UserInfo> list = readDataListFromFile();
		Iterator<UserInfo> it = list.iterator();
		while (it.hasNext()) {
			UserInfo x = it.next();
			if (ids.trim().indexOf(x.getId().trim()) >= 0) {
				it.remove();
				continue;
			}
		}
		saveDataListFromSession(list);
		return "删除成功";
	}

	/**
	 * 数据列表
	 * 
	 * @param req
	 * @param info
	 * @return
	 */
	@RequestMapping("searchList1")
	@ResponseBody
	public DataJson searchList1(HttpServletRequest req, UserInfo info) {
		List<UserInfo> list = readDataListFromFile();
		Iterator<UserInfo> it = list.iterator();
		int j = 1;
		while (it.hasNext()) {
			UserInfo i = it.next();
			i.setId(j++ + "");
			if (StringUtil.isNotBlank(info.getUsername())) {
				if (i.getUsername().indexOf(info.getUsername()) < 0) {
					it.remove();
					continue;
				}
			}
			if (StringUtil.isNotBlank(info.getSex())) {
				if (i.getSex().indexOf(info.getSex()) < 0) {
					it.remove();
					continue;
				}
			}
			if (StringUtil.isNotBlank(info.getPhone())) {
				if (i.getPhone().indexOf(info.getPhone()) < 0) {
					it.remove();
					continue;
				}
			}
			if (StringUtil.isNotBlank(info.getMail())) {
				if (i.getMail().indexOf(info.getMail()) < 0) {
					it.remove();
					continue;
				}
			}
			if (StringUtil.isNotBlank(info.getPosition())) {
				if (i.getPosition().indexOf(info.getPosition()) < 0) {
					it.remove();
					continue;
				}
			}
			if (StringUtil.isNotBlank(info.getFrom())) {
				if (i.getFrom().indexOf(info.getFrom()) < 0) {
					it.remove();
					continue;
				}
			}
			if (StringUtil.isNotBlank(info.getYaoyueDate())) {
				String[] date = info.getYaoyueDate().split(" - ");
				if (i.getYaoyueDate().compareTo(date[0]) < 0 || i.getYaoyueDate().compareTo(date[1]) > 0) {
					it.remove();
					continue;
				}
			}
			if (StringUtil.isNotBlank(info.getIsPass0())) {
				if (i.getIsPass0().indexOf(info.getIsPass0()) < 0) {
					it.remove();
					continue;
				}
			}
			if (StringUtil.isNotBlank(info.getTestDate())) {
				String[] date = info.getTestDate().split(" - ");
				if (i.getTestDate().compareTo(date[0]) < 0 || i.getTestDate().compareTo(date[1]) > 0) {
					it.remove();
					continue;
				}
			}
			if (StringUtil.isNotBlank(info.getIsPass1())) {
				if (i.getIsPass1().indexOf(info.getIsPass1()) < 0) {
					it.remove();
					continue;
				}
			}
			if (StringUtil.isNotBlank(info.getReason())) {
				if (i.getReason().indexOf(info.getReason()) < 0) {
					it.remove();
					continue;
				}
			}
			if (StringUtil.isNotBlank(info.getDepartment())) {
				if (i.getDepartment().indexOf(info.getDepartment()) < 0) {
					it.remove();
					continue;
				}
			}
			if (StringUtil.isNotBlank(info.getIsPass2())) {
				if (i.getIsPass2().indexOf(info.getIsPass2()) < 0) {
					it.remove();
					continue;
				}
			}
			if (StringUtil.isNotBlank(info.getOfferDate())) {
				String[] date = info.getOfferDate().split(" - ");
				if (i.getOfferDate().compareTo(date[0]) < 0 || i.getOfferDate().compareTo(date[1]) > 0) {
					it.remove();
					continue;
				}
			}
			if (StringUtil.isNotBlank(info.getRemark())) {
				if (i.getRemark().indexOf(info.getRemark()) < 0) {
					it.remove();
					continue;
				}
			}

		}
		// 分页
		DataJson json = new DataJson();
		json.setCount(list.size());
		int temp = 0;
		Iterator<UserInfo> i = list.iterator();
		while (i.hasNext()) {
			temp++;
			i.next();
			if (temp > (info.getPage() - 1) * info.getLimit() && temp <= info.getPage() * info.getLimit()) {
				System.out.println(temp);
				continue;
			}
			i.remove();
		}
		json.setData(list);
		return json;
	}

	/**
	 * 读取总数据从文件
	 * 
	 * @return
	 */
	private List<UserInfo> readDataListFromFile() {
		File file = new File(allDataFileName);
		List<UserInfo> list = ExcelUtil.parserExcel(UserInfo.class, file, beanPropertys);
		return list;
	}

	/**
	 * 保存总数据到文件中并及时备份
	 * 
	 * @return
	 */
	private void saveDataListFromSession(List<UserInfo> dataList) {
		try {
			if (dataList.size() >= 2)
				Collections.sort(dataList);
			File file = new File(allDataFileName);
			File bak_file = new File("备份/" + DateUtil.dateToStr(new Date(), DateUtil.YYYY_MM_DD) + "_bak.xls");
			FileUtils.copyFile(file, bak_file);
			HSSFWorkbook workbook1 = ExcelUtil.makeExcelHead(headTitle, titles.length - 1);
			HSSFWorkbook workbook2 = ExcelUtil.makeSecondHead(workbook1, titles);
			HSSFWorkbook workbook = ExcelUtil.exportExcelData(workbook2, dataList, beanPropertys);
			FileOutputStream outputStream = new FileOutputStream(file);
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

}
