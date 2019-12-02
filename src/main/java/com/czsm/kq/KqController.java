package com.czsm.kq;

import com.czsm.controller.hr.DataJson;
import com.czsm.util.ExcelUtil;
import com.czsm.util.StreamUtils;
import net.sf.ehcache.hibernate.management.impl.BeanUtils;
import net.sf.json.JSONArray;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class KqController {

    /**
     * 人员信息导入接口
     *
     * @param file
     * @param req
     * @return
     */
    @RequestMapping("uploadKqUserInfoFile")
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

        List<UserInfo> list = parserUserInfoExcel(tempFile);
        System.out.println(list.size());
        for (UserInfo userInfo : list) {
            System.out.println(userInfo.getDep() + " " + userInfo.getPosition() + " " + userInfo.getName());
        }
        File file1 = new File(Contants.userInfoFile);
        StreamUtils.writeObject(list, file1);
        tempFile.delete();
        return null;
    }

    /**
     * 人员信息查询接口
     *
     * @return
     */
    @RequestMapping("kqUserList")
    @ResponseBody
    public DataJson userList() {
        // 分页
        DataJson json = new DataJson();
        File file = new File(Contants.userInfoFile);
        if (file.exists()) {
            List<UserInfo> list = StreamUtils.readObjectForList(file);
            json.setData(list);
            json.setCount(list.size());
        } else {
            json.setMsg("请先导入数据！");
        }
        return json;
    }

    /**
     * 钉钉信息导入接口
     *
     * @param file
     * @param req
     * @return
     */
    @RequestMapping("uploadKqDingDingInfoFile")
    @ResponseBody
    public List<UserInfo> uploadKqDingDingInfoFile(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
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

        List<DingDingInfo> list = parserDingDingExcel(tempFile);
        System.out.println(list.size());
        for (DingDingInfo userInfo : list) {
            System.out.println(userInfo.getName() + " " + userInfo.getDate() + " " + userInfo.getTime() + " "
                    + userInfo.getAddr());
        }
        File file1 = new File(Contants.dingDingInfoFile);
        StreamUtils.writeObject(list, file1);
        tempFile.delete();
        return null;
    }

    /**
     * 钉钉信息查询接口
     *
     * @return
     */
    @RequestMapping("kqDingDingList")
    @ResponseBody
    public DataJson dingDingList() {
        // 分页
        DataJson json = new DataJson();
        File file = new File(Contants.dingDingInfoFile);
        if (file.exists()) {
            List<DingDingInfo> list = StreamUtils.readObjectForList(file);
            json.setData(list);
            json.setCount(list.size());
        } else {
            json.setMsg("请先导入数据！");
        }
        return json;
    }

    /**
     * 请假信息导入接口
     *
     * @param file
     * @param req
     * @return
     */
    @RequestMapping("uploadKqLeaveInfoFile")
    @ResponseBody
    public List<UserInfo> uploadKqLeaveInfoFile(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
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

        List<LeaveInfo> list = parserLeaveExcel(tempFile);
        System.out.println(list.size());
        File file1 = new File(Contants.leaveInfoFile);
        StreamUtils.writeObject(list, file1);
        tempFile.delete();
        return null;
    }

    /**
     * 请假信息查询接口
     *
     * @return
     */
    @RequestMapping("kqLeaveList")
    @ResponseBody
    public DataJson kqLeaveList() {
        // 分页
        DataJson json = new DataJson();
        File file = new File(Contants.leaveInfoFile);
        if (file.exists()) {
            List<LeaveInfo> list = StreamUtils.readObjectForList(file);
            json.setData(list);
            json.setCount(list.size());
        } else {
            json.setMsg("请先导入数据！");
        }
        return json;
    }

    /**
     * 考勤排班信息导入接口
     *
     * @param file
     * @param req
     * @return
     */
    @RequestMapping("uploadKqWorkDateInfoFile")
    @ResponseBody
    public List<WorkDate> uploadKqWorkDateInfoFile(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
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

        List<WorkDate> list = parserWorkDateExcel(tempFile);
        System.out.println(list.size());
        for (WorkDate userInfo : list) {
            System.out.println(userInfo.getMonth() + " " + userInfo.getDay() + " " + userInfo.getStartTime() + " "
                    + userInfo.getEndTime());
        }
        File file1 = new File(Contants.workDateInfoFile);
        StreamUtils.writeObject(list, file1);
        tempFile.delete();
        return null;
    }

    /**
     * 考勤排班信息查询接口
     *
     * @return
     */
    @RequestMapping("kqWorkDateList")
    @ResponseBody
    public DataJson kqWorkDateList() {
        // 分页
        DataJson json = new DataJson();
        File file = new File(Contants.workDateInfoFile);
        if (file.exists()) {
            List<LeaveInfo> list = StreamUtils.readObjectForList(file);
            json.setData(list);
            json.setCount(list.size());
        } else {
            json.setMsg("请先导入数据！");
        }
        return json;
    }

    /**
     * 考勤打卡信息导入接口(基础打卡记录版信息)
     *
     * @param file
     * @param req
     * @return
     */
    @RequestMapping("uploadKqWorkTimeInfoFile")
    @ResponseBody
    public List<WorkTime> uploadKqWorkTimeInfoFile(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
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

        List<WorkTime> list = parserWorkTimeBaseExcel(tempFile);
        System.out.println(list.size());
        for (WorkTime userInfo : list) {
            System.out.println(userInfo.getUsername() + " " + userInfo.getDateTime() + "" + userInfo.getDate() + " "
                    + userInfo.getInTime() + " " + userInfo.getGoTime());
        }
        File file1 = new File(Contants.workTimeInfoFile);
        StreamUtils.writeObject(list, file1);
        tempFile.delete();
        return null;
    }

    /**
     * 考勤打卡信息导入接口(统计考勤信息)
     *
     * @param file
     * @param req
     * @return
     */
    @RequestMapping("uploadKqWorkTimeInfoFile1")
    @ResponseBody
    public List<WorkTime> uploadKqWorkTimeInfoFile1(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
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

        List<WorkTime> list = parserWorkTimeExcel(tempFile);
        System.out.println(list.size());
        for (WorkTime userInfo : list) {
            System.out.println(userInfo.getUsername() + " " + userInfo.getDateTime() + "" + userInfo.getDate() + " "
                    + userInfo.getInTime() + " " + userInfo.getGoTime());
        }
        File file1 = new File(Contants.workTimeInfoFile);
        StreamUtils.writeObject(list, file1);
        tempFile.delete();
        return null;
    }

    /**
     * 考勤打卡信息查询接口
     *
     * @return
     */
    @RequestMapping("kqWorkTimeList")
    @ResponseBody
    public DataJson kqWorkTimeList() {
        // 分页
        DataJson json = new DataJson();
        File file = new File(Contants.workTimeInfoFile);
        if (file.exists()) {
            List<LeaveInfo> list = StreamUtils.readObjectForList(file);
            json.setData(list);
            json.setCount(list.size());
        } else {
            json.setMsg("请先导入数据！");
        }
        return json;
    }

    /**
     * 解析钉钉外出打卡数据
     *
     * @return
     */
    private List<DingDingInfo> parserDingDingExcel(File file) {
        // 得到workbook
        List<DingDingInfo> list = new ArrayList<DingDingInfo>();
        try {
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);
            // 直接从第三行开始获取数据
            int rowSize = sheet.getPhysicalNumberOfRows();
            if (rowSize > 2) {
                String username = "";
                for (int i = 2; i < rowSize; i++) {
                    DingDingInfo dd = new DingDingInfo();
                    Row row = sheet.getRow(i);
                    Object userCell = getCellValue(row.getCell(0));
                    if (isNotEmptyCell(userCell)) {
                        username = userCell + "";
                    }
                    dd.setName(username);// 合并单元格的原因
                    Object date = getCellValue(row.getCell(5));
                    if (date instanceof Date) {
                        Date d = (Date) date;
                        dd.setDate(new SimpleDateFormat(Contants.dateFmtStr).format(d));
                    } else {
                        try {
                            dd.setDate(new SimpleDateFormat(Contants.dateFmtStr)
                                    .format(new SimpleDateFormat(Contants.dateFmtStr).parse(date + "")));
                        } catch (Exception e) {
                            try {
                                dd.setDate(new SimpleDateFormat(Contants.dateFmtStr)
                                        .format(new SimpleDateFormat(Contants.dateFmtStr1).parse(date + "")));
                            } catch (Exception e1) {
                                dd.setRemark("日期有误！此记录无效！重新导入");
                            }
                        }
                    }

                    Object time = getCellValue(row.getCell(6));
                    if (time instanceof Date) {
                        Date d = (Date) time;
                        dd.setTime(new SimpleDateFormat(Contants.timeFmtStr).format(d));
                    } else {
                        try {
                            dd.setTime(new SimpleDateFormat(Contants.timeFmtStr)
                                    .format(new SimpleDateFormat(Contants.timeFmtStr).parse(time + "")));
                        } catch (Exception e) {
                            dd.setRemark("时间有误！此记录无效！重新导入");
                        }
                    }

                    Object addr = getCellValue(row.getCell(7));
                    if (isNotEmptyCell(addr)) {
                        dd.setAddr(addr + "");
                    }
                    System.out.println(dd.getName() + "===" + dd.getDate() + "====" + dd.getTime());
                    if (isNotEmpty(dd.getName()) && isNotEmpty(dd.getDate()) && isNotEmpty(dd.getTime())) {
                        dd.setId(i);
                        list.add(dd);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    /**
     * 解析工作日时间数据
     *
     * @return
     */
    private List<WorkDate> parserWorkDateExcel(File file) {
        // 得到workbook
        List<WorkDate> list = new ArrayList<WorkDate>();
        try {
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);
            // 直接从第三行开始获取数据
            int rowSize = sheet.getPhysicalNumberOfRows();
            if (rowSize > 2) {
                for (int i = 2; i < rowSize; i++) {
                    WorkDate dd = new WorkDate();
                    Row row = sheet.getRow(i);
                    dd.setId(getCellValue(row.getCell(0)) + "");
                    dd.setMonth(getCellValue(row.getCell(1)) + "");
                    dd.setDay(getCellValue(row.getCell(2)) + "");
                    if (getCellValue(row.getCell(3)) instanceof Date) {
                        Date d = (Date) getCellValue(row.getCell(3));
                        dd.setStartTime(new SimpleDateFormat(Contants.timeFmtStr).format(d));
                    } else {
                        try {
                            dd.setStartTime(new SimpleDateFormat(Contants.timeFmtStr)
                                    .format(new SimpleDateFormat(Contants.timeFmtStr)
                                            .parse(getCellValue(row.getCell(4)) + "")));
                        } catch (Exception e) {
                            dd.setRemark("上班时间有误!此记录无效！宝宝重新导入！");
                        }
                    }
                    if (getCellValue(row.getCell(4)) instanceof Date) {
                        Date d = (Date) getCellValue(row.getCell(4));
                        dd.setEndTime(new SimpleDateFormat(Contants.timeFmtStr).format(d));
                    } else {
                        try {
                            dd.setEndTime(new SimpleDateFormat(Contants.timeFmtStr)
                                    .format(new SimpleDateFormat(Contants.timeFmtStr)
                                            .parse(getCellValue(row.getCell(4)) + "")));
                        } catch (Exception e) {
                            dd.setRemark("下班时间有误!此记录无效！宝宝重新导入！");
                        }
                    }

                    if (isNotEmpty(dd.getDay())) {
                        list.add(dd);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    /**
     * 解析用户数据
     */
    private List<UserInfo> parserUserInfoExcel(File file) {
        // 得到workbook
        List<UserInfo> list = new ArrayList<UserInfo>();
        try {
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);
            // 直接从第三行开始获取数据
            int rowSize = sheet.getPhysicalNumberOfRows();
            if (rowSize > 2) {
                for (int i = 2; i < rowSize; i++) {
                    Row row = sheet.getRow(i);
                    if (isEmpty(getCellValue(row.getCell(4)) + "")) {
                        continue;
                    }
                    UserInfo dd = new UserInfo();
                    dd.setId(getCellValue(row.getCell(0)) + "");
                    dd.setName(getCellValue(row.getCell(4)) + "");// 合并单元格的原因
                    dd.setDep(getCellValue(row.getCell(1)) + "");
                    dd.setPosition(getCellValue(row.getCell(2)) + "");
                    dd.setJobId(getCellValue(row.getCell(3)) + "");
                    Object date = getCellValue(row.getCell(5));
                    Object date1 = getCellValue(row.getCell(6));
                    if (isNotEmptyCell(date)) {
                        if (date instanceof Date) {
                            Date d = (Date) date;
                            dd.setInDate(new SimpleDateFormat(Contants.dateFmtStr).format(d));
                        } else {
                            try {
                                dd.setInDate(new SimpleDateFormat(Contants.dateFmtStr)
                                        .format(new SimpleDateFormat(Contants.dateFmtStr).parse(date + "")));
                            } catch (Exception e) {
                                try {
                                    dd.setInDate(new SimpleDateFormat(Contants.dateFmtStr)
                                            .format(new SimpleDateFormat(Contants.dateFmtStr1).parse(date + "")));
                                } catch (Exception e1) {
                                    dd.setRemark("入职日期有误！此记录无效！重新导入");
                                }
                            }
                        }
                    }
                    if (isNotEmptyCell(date1)) {
                        if (date1 instanceof Date) {
                            Date d = (Date) date1;
                            dd.setGoDate(new SimpleDateFormat(Contants.dateFmtStr).format(d));
                        } else {
                            try {
                                dd.setGoDate(new SimpleDateFormat(Contants.dateFmtStr)
                                        .format(new SimpleDateFormat(Contants.dateFmtStr).parse(date1 + "")));
                            } catch (Exception e) {
                                try {
                                    dd.setGoDate(new SimpleDateFormat(Contants.dateFmtStr)
                                            .format(new SimpleDateFormat(Contants.dateFmtStr1).parse(date1 + "")));
                                } catch (Exception e1) {
                                    dd.setRemark("离职日期有误！此记录无效！重新导入");
                                }
                            }
                        }
                    }
                    list.add(dd);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    /**
     * 解析请假数据
     *
     * @return
     */
    private List<LeaveInfo> parserLeaveExcel(File file) {
        // 得到workbook
        List<LeaveInfo> list = new ArrayList<LeaveInfo>();
        try {
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);
            // 直接从第三行开始获取数据
            int rowSize = sheet.getPhysicalNumberOfRows();

            if (rowSize > 1) {
                for (int i = 1; i < rowSize; i++) {
                    Row row = sheet.getRow(i);
                    if (row == null) {
                        continue;
                    }


                    String isOk = (getCellValue(row.getCell(3)) + "").trim();//审批
                    String isCheXiao = (getCellValue(row.getCell(2)) + "").trim();//状态
                    if (!"同意".equals(isOk) || "已撤销".equals(isCheXiao)) {
                        //未同意的则不处理  或者  已撤销的不处理
                        continue;
                    }
                    LeaveInfo dd = new LeaveInfo();
                    dd.setDep(getCellValue(row.getCell(10)) + "");//部门
                    dd.setName(getCellValue(row.getCell(9)) + "");//姓名
                    dd.setType(getCellValue(row.getCell(14)) + "");//假期类型
                    String start = getCellValue(row.getCell(15)) + "";//开始时间
                    String end = getCellValue(row.getCell(16)) + "";//结束时间
                    String longTime = getCellValue(row.getCell(17)) + "";//请假时长
                    dd.setDay(start + " 至 " + end);
                    double time = Double.parseDouble(longTime.replace("小时", ""));
                    dd.setLongTime(time);
                    list.add(dd);


                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
//		System.out.println(JSONArray.fromObject(list));
        return list;

    }

    /**
     * 解析基本考勤数据（考勤记录版）
     *
     * @return
     */
    private List<WorkTime> parserWorkTimeBaseExcel(File file) {
        SimpleDateFormat dft0 = new SimpleDateFormat(Contants.dateTimeFmtStr0);
        SimpleDateFormat dft1 = new SimpleDateFormat(Contants.dateTimeFmtStr1);
        SimpleDateFormat dft = new SimpleDateFormat(Contants.dateTimeFmtStr);
        List<WorkTime> list = new ArrayList<WorkTime>();
        // 得到workbook
        try {
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);
            // 直接从第二行开始获取数据
            int rowSize = sheet.getPhysicalNumberOfRows();
            if (rowSize > 1) {
                for (int i = 1; i < rowSize; i++) {
                    WorkTime dd = new WorkTime();
                    Row row = sheet.getRow(i);
                    dd.setId(i + "");
                    dd.setUsername(getCellValue(row.getCell(1)) + "");
                    Object time = getCellValue(row.getCell(3));
                    if (isEmptyCell(time)) {
                        continue;
                    }
                    try {
                        dd.setDateTime(dft.format(dft0.parse(time + "")));
                    } catch (Exception e) {
                        try {
                            dd.setDateTime(dft.format(dft1.parse(time + "")));
                        } catch (Exception e1) {
                            dd.setRemark("日期有误！此记录无效！重新导入");
                        }
                    }
                    dd.setJobId(getCellValue(row.getCell(2)) + "");
                    list.add(dd);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    public static void main(String[] args) throws ParseException {
//		Date d = new Date();
//		String date = "2019/5/30 8:54:04";
//		System.out.println(new SimpleDateFormat(Contants.dateTimeFmtStr).format(d));
//		System.out.println(new SimpleDateFormat(Contants.dateTimeFmtStr1).parse(date));
        String s = "4/7.5";
        System.out.println(Double.parseDouble(s));
    }

    /**
     * 解析基本考勤数据(原始数据版)
     *
     * @return
     */
    private List<WorkTime> parserWorkTimeExcel(File file) {
        // 得到workbook
        List<WorkTime> list = new ArrayList<WorkTime>();
        try {
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);
            // 直接从第三行开始获取数据
            int rowSize = sheet.getPhysicalNumberOfRows();
            if (rowSize > 2) {
                for (int i = 2; i < rowSize; i++) {
                    WorkTime dd = new WorkTime();
                    Row row = sheet.getRow(i);
                    dd.setId(getCellValue(row.getCell(0)) + "");
                    dd.setUsername(getCellValue(row.getCell(1)) + "");
                    Object date = getCellValue(row.getCell(2));
                    if (date instanceof Date) {
                        Date d = (Date) date;
                        dd.setDate(new SimpleDateFormat(Contants.dateFmtStr).format(d));
                    } else {
                        try {
                            dd.setDate(new SimpleDateFormat(Contants.dateFmtStr)
                                    .format(new SimpleDateFormat(Contants.dateFmtStr).parse(date + "")));
                        } catch (Exception e) {
                            try {
                                dd.setDate(new SimpleDateFormat(Contants.dateFmtStr)
                                        .format(new SimpleDateFormat(Contants.dateFmtStr1).parse(date + "")));
                            } catch (Exception e1) {
                                dd.setRemark("日期有误！此记录无效！重新导入");
                            }
                        }
                    }
                    Object time = getCellValue(row.getCell(4));
                    if (isNotEmptyCell(time)) {
                        if (time instanceof Date) {
                            Date d = (Date) time;
                            dd.setInTime(new SimpleDateFormat(Contants.timeFmtStr).format(d));
                        } else {
                            try {
                                dd.setInTime(new SimpleDateFormat(Contants.timeFmtStr)
                                        .format(new SimpleDateFormat(Contants.timeFmtStr)
                                                .parse(getCellValue(row.getCell(4)) + "")));
                            } catch (Exception e) {
                                dd.setRemark("上班时间有误!此记录无效！重新导入！");
                            }
                        }
                    }
                    Object time1 = getCellValue(row.getCell(5));
                    if (isNotEmptyCell(time1)) {
                        if (time1 instanceof Date) {
                            Date d = (Date) time1;
                            dd.setGoTime(new SimpleDateFormat(Contants.timeFmtStr).format(d));
                        } else {
                            try {
                                dd.setGoTime(new SimpleDateFormat(Contants.timeFmtStr)
                                        .format(new SimpleDateFormat(Contants.timeFmtStr).parse(time1 + "")));
                            } catch (Exception e) {
                                dd.setRemark("下班时间有误!此记录无效！重新导入！");
                            }
                        }
                    }
                    list.add(dd);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    private boolean isEmpty(String obj) {
        return obj == null || obj.trim().length() == 0;
    }

    private boolean isNotEmpty(String obj) {
        return obj != null && obj.trim().length() > 0;
    }

    private boolean isNotEmptyCell(Object obj) {
        return obj != null && isNotEmpty(obj + "");
    }

    private boolean isEmptyCell(Object obj) {
        return obj == null || (obj + "").trim().equals("");
    }

    /**
     * 通用的读取excel单元格的处理方法
     *
     * @param cell
     * @return
     */
    private static Object getCellValue(Cell cell) {
        Object result = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    result = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    // 对日期进行判断和解析
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        double cellValue = cell.getNumericCellValue();

                        result = HSSFDateUtil.getJavaDate(cellValue);
                    } else {
                        double d = cell.getNumericCellValue();
                        if (d == (int) d) {
                            result = (int) d;
                        } else {
                            result = d;
                        }
                    }
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    result = cell.getBooleanCellValue();
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    result = cell.getCellFormula();
                    break;
                case Cell.CELL_TYPE_ERROR:
                    result = cell.getErrorCellValue();
                    break;
                case Cell.CELL_TYPE_BLANK:
                    result = "";
                    break;
                default:
                    result = null;
                    break;
            }
        }
        return result;
    }

    /**
     * 最新考勤汇总接口
     *
     * @return
     */
    @RequestMapping("countDateData")
    @ResponseBody
    public String countDateData(boolean isDingDingC, boolean isLeaveC, boolean isGoC) {
        List<UserInfo> userList = StreamUtils.readObjectForList(new File(Contants.userInfoFile));// 用户列表
        List<WorkDate> workDateList = StreamUtils.readObjectForList(new File(Contants.workDateInfoFile));// 排班列表
        List<LeaveInfo> leaveList = StreamUtils.readObjectForList(new File(Contants.leaveInfoFile));// 请假列表
        List<DingDingInfo> dingDingList = StreamUtils.readObjectForList(new File(Contants.dingDingInfoFile));// 钉钉外出打卡列表
        List<WorkTime> workTimeList = StreamUtils.readObjectForList(new File(Contants.workTimeInfoFile));// 考勤打卡列表
        DataJson json = new DataJson();
        if (userList == null || userList.isEmpty()) {
            json.setCode(500);
            json.setMsg("人员信息列表未正确导入");
        } else if (workDateList == null || workDateList.isEmpty()) {
            json.setCode(500);
            json.setMsg("排班信息列表未正确导入");
        } else if (leaveList == null || leaveList.isEmpty()) {
            json.setCode(500);
            json.setMsg("请假信息列表未正确导入");
        } else if (dingDingList == null || dingDingList.isEmpty()) {
            json.setCode(500);
            json.setMsg("钉钉打卡信息列表未正确导入");
        } else if (workTimeList == null || workTimeList.isEmpty() || workDateList.size() <= 3) {
            json.setCode(500);
            json.setMsg("考勤打卡信息列表未正确导入");
        } else {
            int year = Calendar.getInstance().get(Calendar.YEAR);
            SimpleDateFormat sft = new SimpleDateFormat(Contants.dateFmtStr);
            SimpleDateFormat sft1 = new SimpleDateFormat(Contants.dateTimeFmtStr);
            /**
             * 处理考勤打卡数据
             */
            WorkTime wt1 = workTimeList.get(2);
            if (wt1 != null) {
                if (isNotEmpty(wt1.getDateTime())) {
                    Collections.sort(workTimeList);// 先进行排序 名字 打卡时间
                    System.out.println(JSONArray.fromObject(workTimeList));
                    ;
                    List<WorkTime> wtList = new ArrayList<WorkTime>();
                    String username = "";
                    String date = "";
                    WorkTime w = new WorkTime();
                    for (WorkTime wt : workTimeList) {
                        if (isEmpty(wt.getDateTime())) {
                            continue;
                        }
                        String monthDay = wt.getDateTime().trim().split(" ")[0];
                        String time = wt.getDateTime().trim().split(" ")[1];
                        if (!username.equals(wt.getUsername().trim()) || !date.equals(monthDay)) {
                            username = wt.getUsername();
                            date = monthDay;
                            w = new WorkTime();
                            w.setUsername(username);
                            w.setDate(monthDay);
                            wtList.add(w);
                        }
                        System.out.println(
                                wt.getUsername() + "****" + wt.getDateTime() + "*****" + monthDay + "-------" + time);

                        if (isEmpty(w.getInTime())) {// 上班只记录第一条数据
                            w.setInTime(time);
                        } else {// 下班时间不停覆盖
                            w.setGoTime(time);
                        }
                    }
                    System.out.println(JSONArray.fromObject(wtList));

                    workTimeList = wtList;
                }
            }
//			for (WorkTime workTime : workTimeList) {
//				System.out.println(workTime.getUsername() + " -- " + workTime.getDate() + " -- " + workTime.getInTime()
//						+ " -- " + workTime.getGoTime());
//			}

            List<CountDate> countDateList = new ArrayList<CountDate>();// 统计列表
            for (UserInfo user : userList) {// 遍历每个用户
                CountDate ct = new CountDate();
                String name = user.getName().trim();
                ct.setName(name);
                String state = user.getState();
                ct.setState(state);
                ct.setDep(user.getDep());
                ct.setPosition(user.getPosition());
                ct.setJobId(user.getJobId());
                int t10Mcount = 0;// 迟到10分钟内
                int t15Mcount = 0;// 迟到15分钟内
                int t30Mcount = 0;// 迟到30分钟内
                int t30OutMcount = 0;// 迟到30分钟外
                int t60OutMcount = 0;
                String jobScoreDesc = "";// 绩效分说明
                int noCard = 0;// 未打卡次数
                int zaoTui = 0;// 早退次数
                int noWork = 0;// 旷工
                double leave1 = 0;
                double leave2 = 0;
                double leave3 = 0;
                double leave4 = 0;
                String remarkAll = "";
                String excp = "";
                String leaveInfoDesc = "";
                if (isGoC && isEmpty(user.getGoDate())) {// 如果只统计离职人员信息
                    continue;
                }
                for (WorkDate wd : workDateList) {// 每个用户每个排班
                    String date = year + "-"
                            + (wd.getMonth().trim().length() == 1 ? "0" + wd.getMonth() : wd.getMonth()) + "-"
                            + (wd.getDay().trim().length() == 1 ? "0" + wd.getDay() : wd.getDay());
                    if (isNotEmpty(user.getInDate()) && user.getInDate().compareTo(date) > 0) {// 有入职日期 并且入职比当前晚
                        continue;
                    }
                    if (isNotEmpty(user.getGoDate()) && user.getGoDate().compareTo(date) < 0) {// 有离职日期 并且离职比当前早
                        continue;
                    }
                    String monthDay = (wd.getMonth().trim().length() == 1 ? "0" + wd.getMonth() : wd.getMonth()) + "-"
                            + (wd.getDay().trim().length() == 1 ? "0" + wd.getDay() : wd.getDay());
                    String dateStr = wd.getMonth() + "月" + wd.getDay() + "日";
                    String start = isEmpty(wd.getStartTime()) ? "9:00" : wd.getStartTime();
                    String end = isEmpty(wd.getEndTime()) ? "18:00" : wd.getEndTime();

                    // 首先匹配请假数据
                    boolean isLeave = false;// 是否是半天假
                    boolean isDingDing = false;// 是否有钉钉打卡
                    double leaveTime = 0;// 请假时长
                    if (isLeaveC) {// 页面参数如果需要统计请假信息
                        for (LeaveInfo leaveInfo : leaveList) {
                            String day = leaveInfo.getDay();//请假日期
                            if (leaveInfo.getName().trim().indexOf(name)>=0 && day.indexOf(monthDay) >= 0) {// 匹配到当前排班中请假信息
                                String type= leaveInfo.getType();
                                remarkAll += day + " 请" +type + leaveInfo.getLongTime() + "小时     ";
                                leaveTime += leaveInfo.getLongTime();
                                leaveInfoDesc += monthDay + " 请" + leaveInfo.getType() + leaveInfo.getLongTime() + "小时     ";
                                isLeave=true;
                                if (type.indexOf("事")>=0){
                                    leave1+=leaveTime;
                                }else  if (type.indexOf("病")>=0){
                                    leave2+=leaveTime;
                                }else  if (type.indexOf("年")>=0){
                                    leave3+=leaveTime;
                                }else {
                                    leave4+=leaveTime;
                                }
                            }
                        }
                    }



                    if (isDingDingC) {// 页面参数需要统计钉钉打卡信息
                        // 如果没有匹配到请假 再匹配钉钉打卡数据
                        for (DingDingInfo dd : dingDingList) {//
                            if (dd.getName().trim().indexOf(name)>=0 && dd.getDate().indexOf(date) >= 0) {// 匹配到当前排班中钉钉打卡信息
                                isDingDing = true;
                                break;
                            }
                        }
                    }

                    String inTime = "";// 实际上班打卡
                    String goTime = "";// 实际下班打卡时间
                    for (WorkTime wt : workTimeList) {// 最后每个排班下面匹配考勤打卡数据
                        // System.out.println( "正在统计【" + wt.getUsername() + "】--【" + wt.getDate() +
                        // "】考勤........................");
                        if (wt.getUsername().trim().indexOf(name)>=0 && wt.getDate().indexOf(date) >= 0) {// 匹配到当前排班日期下的打卡数据
                            inTime = wt.getInTime();
                            goTime = wt.getGoTime();
                            break;// 找到记录则跳出
                        }
                    }

                    if (isEmpty(inTime) && isEmpty(goTime) && !isLeave && !isDingDing) {// 如果没有请假 没有钉钉外出 也没有打卡记录 则是旷工
                        noWork++;
                        remarkAll += dateStr + "【旷工1天】       ";
                        continue;// 不继续统计
                    }
                    // 此处用于修复考勤打卡记录统计时出现的异常 如： 下班时间为空
                    if (isNotEmpty(inTime)) {
                        if (inTime.trim().compareTo("12:00") > 0 && isEmpty(goTime)) {
                            goTime = inTime;
                            inTime = null;
                        } else if (inTime.trim().compareTo("17:00") > 0 && isNotEmpty(goTime)) {
                            goTime = inTime;
                            inTime = null;

                        }
                    }

                    if (isLeave) {
                        remarkAll += "考勤打卡上班时间：" + (isEmpty(inTime) ? "未打卡" : inTime)
                                + "  下班时间：" + (isEmpty(goTime) ? "未打卡" : goTime) + "]【需人工匹配】       ";
                        excp += dateStr + "请假" + leaveTime + "天[考勤打卡上班时间：" + (isEmpty(inTime) ? "未打卡" : inTime)
                                + "  下班时间：" + (isEmpty(goTime) ? "未打卡" : goTime) + "]【需人工匹配】       ";
                        continue;// 不继续统计
                    }
                    if (isDingDing) {
                        remarkAll += dateStr + "存在钉钉外出卡记录 [考勤打卡上班时间：" + (isEmpty(inTime) ? "未打卡" : inTime) + "  下班时间："
                                + (isEmpty(goTime) ? "未打卡" : goTime) + "]【需人工匹配】       ";
                        excp += dateStr + "存在钉钉外出卡记录 [考勤打卡上班时间：" + (isEmpty(inTime) ? "未打卡" : inTime) + "  下班时间："
                                + (isEmpty(goTime) ? "未打卡" : goTime) + "]【需人工匹配】       ";
                        continue;// 不继续统计
                    }

                    if (isEmpty(inTime)) {// 没有上午打卡时间 也没有外出 没有请假
                        noCard++;
                        remarkAll += dateStr + "上班未打卡       ";
                    }

                    if (isEmpty(goTime)) {// 没有下午打卡时间 也没有外出 没有请假
                        noCard++;
                        remarkAll += dateStr + "下班未打卡       ";
                    }

                    if (isNotEmpty(inTime)) {// 有打上班卡
                        if (inTime.compareTo(start) > 0) {// 迟到
                            // 计算迟到时间
                            try {
                                long time = sft1.parse(date + " " + inTime).getTime()
                                        - sft1.parse(date + " " + start).getTime();
                                int min = Math.round(time / (60 * 1000));
                                if (min <= 10) {// 10分钟内
                                    if (!isLeave && !isDingDing) {
                                        t10Mcount++;
                                    }
                                    remarkAll += dateStr + "上班打卡迟到" + min + "分钟【十分钟内】      ";
                                } else if (min <= 15) {// 15分钟内
                                    if (!isLeave && !isDingDing) {
                                        t15Mcount++;
                                    }
                                    remarkAll += dateStr + "上班打卡迟到" + min + "分钟【十五分钟内】      ";
                                } else if (min <= 30) {// 30分钟内
                                    if (!isLeave && !isDingDing) {
                                        t30Mcount++;
                                    }
                                    remarkAll += dateStr + "上班打卡迟到" + min + "分钟【三十分钟内】      ";
                                } else if (min <= 60) {// 30分钟内
                                    if (!isLeave && !isDingDing) {
                                        t30OutMcount++;
                                    }
                                    remarkAll += dateStr + "上班打卡迟到" + min + "分钟【六十分钟内】      ";
                                } else if (min >= 60) {// 60分钟外
                                    if (!isLeave && !isDingDing) {
                                        t60OutMcount++;
                                    }
                                    remarkAll += dateStr + "上班打卡迟到" + min + "分钟【六十分钟外】      ";
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                    if (isNotEmpty(goTime)) {// 有打下班卡
                        if (goTime.compareTo(end) < 0) {// 早退
                            long time;
                            try {
                                time = sft1.parse(date + " " + end).getTime()
                                        - sft1.parse(date + " " + goTime).getTime();

                                int min = Math.round(time / (60 * 1000));
                                // 计算早退情况
                                if (!isLeave && !isDingDing) {
                                    zaoTui++;
                                }
                                remarkAll += dateStr + "下班早退【" + min + "】分钟      ";
                            } catch (ParseException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }

                }
                ct.setIn10MTime(t10Mcount > 0 ? (t10Mcount + " ") : "");
                ct.setIn15MTime(t15Mcount > 0 ? (t15Mcount + " ") : "");
                ct.setNoWork(noWork > 0 ? (noWork + "") : "");
                ct.setNoCard(noCard > 0 ? (noCard + "") : "");
                ct.setIn30MTime(t30Mcount > 0 ? (t30Mcount + "") : "");
                ct.setIn30OutTime(t30OutMcount > 0 ? (t30OutMcount + "") : "");
                ct.setIn60OutTime(t60OutMcount > 0 ? (t60OutMcount + "") : "");
                ct.setZaoTui(zaoTui > 0 ? (zaoTui + "") : "");
                ct.setLeave1(leave1 > 0 ? (leave1 + "") : "");
                ct.setLeave2(leave2 > 0 ? (leave2 + "") : "");
                ct.setLeave3(leave3 > 0 ? (leave3 + "") : "");
                ct.setLeave4(leave4 > 0 ? (leave4 + "") : "");
                ct.setHasException(excp);

                double score = 0;// ((t10Mcount - 2) + t15Mcount) * 1 + t30Mcount * 1.5 + t30OutMcount * 3 +
                // zaoTui * 10 + noWork * 20;
                if (t10Mcount > 2) {
                    jobScoreDesc += "迟到10分内" + t10Mcount + "次【扣" + (t10Mcount - 2) + "分】    ";
                    score += t10Mcount - 2;

                } else if (t10Mcount > 0) {
                    jobScoreDesc += "迟到10分内" + t10Mcount + "次【不扣分】    ";
                    //score += t10Mcount;

                }
                if (t15Mcount > 0) {
                    jobScoreDesc += "迟到15分内" + t15Mcount + "次【扣" + t15Mcount + "分】    ";
                    score += t15Mcount;
                }
                if (t30Mcount > 0) {
                    jobScoreDesc += "迟到30分内" + t30Mcount + "次【扣" + t30Mcount * 1.5 + "分】   ";
                    score += t30Mcount * 1.5;
                }
                if (t30OutMcount > 0) {
                    jobScoreDesc += "迟到超30分钟" + t30OutMcount + "次【扣" + t30OutMcount * 3 + "分】    ";
                    score += t30OutMcount * 3;
                }
                if (t60OutMcount > 0) {
                    jobScoreDesc += "迟到超60分钟" + t60OutMcount + "次【扣" + t30OutMcount * 3 + "分】   ";
                    score += t60OutMcount * 6;
                }
                if (zaoTui > 0) {
                    jobScoreDesc += "早退" + zaoTui + "次【扣" + zaoTui * 10 + "分】    ";
                    score += zaoTui * 10;
                }
                if (noWork > 0) {
                    jobScoreDesc += "旷工" + noWork + "次【扣" + noWork * 20 + "分】    ";
                    score += noWork * 20;
                }
                if (noCard > 0) {
                    if (noCard > 2) {
                        jobScoreDesc += "未打卡" + noCard + "次【扣" + (noCard - 2) + "分】    ";
                    } else {
                        jobScoreDesc += "未打卡" + noCard + "次【不扣分】    ";

                    }
                    score += noCard > 2 ? noCard - 2 : 0;
                }
                // 是否全勤
                if (isEmpty(jobScoreDesc) && isEmpty(leaveInfoDesc) && noCard == 0 && t10Mcount == 0) {// 没有请假 没有扣绩效
                    ct.setIsAllTime("是");// 全勤
                }
                ct.setJobScore(score > 0 ? (score + "分") : "");
                ct.setJobScoreDesc(jobScoreDesc);
                ct.setRemarkAll(remarkAll);
                ct.setLeaveRemark(leaveInfoDesc);
                countDateList.add(ct);
            }
            Collections.sort(countDateList);// 先进行排序 部门 姓名时间
            // 分页
            File file = new File(Contants.countDataHeadTitle);
            StreamUtils.writeObject(countDateList, file);

            try {
                File file1 = new File("D:\\data\\" + Contants.getFileName("考勤汇总"));
                file1.createNewFile();
                HSSFWorkbook workbook1 = ExcelUtil.makeExcelHead(Contants.countDataHeadTitle,
                        Contants.countDataTitles.length - 1);
                HSSFWorkbook workbook2 = ExcelUtil.makeSecondHead(workbook1, Contants.countDataTitles);
                HSSFWorkbook workbook = exportExcelData(workbook2, countDateList, Contants.countDataBeanPropertys);
                FileOutputStream outputStream = new FileOutputStream(file1);
                workbook.write(outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (
                    Exception e) {
                e.printStackTrace();
            } finally {
            }
        }
        return "生成成功！请重新查询! 报表数据在D:/data文件夹中";

    }

    /**
     * 上次考勤汇总接口
     *
     * @return
     */
    @RequestMapping("countDateDataH")
    @ResponseBody
    public DataJson countDateDataH() {
        // 分页
        DataJson json = new DataJson();
        File file = new File(Contants.countDataHeadTitle);
        if (file.exists()) {
            List<CountDate> list = StreamUtils.readObjectForList(file);
            json.setData(list);
            json.setCount(list.size());
        } else {
            json.setMsg("请先导入数据！");
        }
        return json;
    }

    /**
     * 插入数据
     *
     * @param workbook
     * @param dataList
     * @param beanPropertys
     * @return
     */
    public <T> HSSFWorkbook exportExcelData(HSSFWorkbook workbook, List<CountDate> dataList, String[] beanPropertys) {
        HSSFSheet sheet = workbook.getSheetAt(0);
        sheet.autoSizeColumn(1);
        // 填充数据
        HSSFCellStyle styleData = workbook.createCellStyle();
        styleData.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        styleData.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        styleData.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleData.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
        styleData.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
        styleData.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框


        // 填充数据
        HSSFCellStyle styleData1 = workbook.createCellStyle();
        styleData1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        styleData1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        styleData1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleData1.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
        styleData1.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
        styleData1.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
        styleData1.setFillForegroundColor(HSSFColor.DARK_RED.index);
        styleData1.setWrapText(true);
        styleData.setWrapText(true);
        for (int j = 0; j < dataList.size(); j++) {
            HSSFRow rowData = sheet.createRow(j + 2);

            CountDate t = dataList.get(j);
            if (isNotEmpty(t.getRemarkAll())) {
                t.setRemarkAll(t.getRemarkAll().replace("    ", "\r\n"));
            }
            if (isNotEmpty(t.getJobScoreDesc())) {
                t.setJobScoreDesc(t.getJobScoreDesc().replace("    ", "\r\n"));
            }
            if (isNotEmpty(t.getLeaveRemark())) {
                t.setLeaveRemark(t.getLeaveRemark().replace("    ", "\r\n"));
            }
            if (isNotEmpty(t.getHasException())) {
                t.setHasException(t.getHasException().replace("    ", "\r\n"));
            }
            if (isNotEmpty(t.getLeaveRemark())) {
                t.setLeaveRemark(t.getLeaveRemark().replace("天", "天\r\n"));
            }
            if (isNotEmpty(t.getHasException())) {
                rowData.setRowStyle(styleData1);
            }
            for (int k = 0; k < beanPropertys.length; k++) {
                Object value = BeanUtils.getBeanProperty(t, beanPropertys[k]);
                HSSFCell cellData = rowData.createCell(k);
                if (k == 0) {
                    value = j + 1;
                }
                cellData.setCellValue(value == null ? "" : value + "");
                cellData.setCellStyle(styleData);
            }
        }
        return workbook;
    }
}
