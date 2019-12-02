package com.czsm.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

import net.sf.ehcache.hibernate.management.impl.BeanUtils;

public class ExcelUtil {
	private ExcelUtil() {

	}

	/**
	 * 导出excel头部标题
	 * 
	 * @param title
	 * @param cellRangeAddressLength
	 * @return
	 */
	public static HSSFWorkbook makeExcelHead(String title, int cellRangeAddressLength) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFCellStyle styleTitle = createStyle(workbook, (short) 16);
		HSSFSheet sheet = workbook.createSheet(title);
		//sheet.setDefaultColumnWidth(20);
		CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, cellRangeAddressLength);
		sheet.addMergedRegion(cellRangeAddress);
		HSSFRow rowTitle = sheet.createRow(0);
		rowTitle.setHeight((short) 600);
		HSSFCell cellTitle = rowTitle.createCell(0);
		// 为标题设置背景颜色
		styleTitle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		styleTitle.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);

		cellTitle.setCellValue(title);
		cellTitle.setCellStyle(styleTitle);
		//设置最外面四周边框
		CellRangeAddress brodercell = new CellRangeAddress(0,0, 0, cellRangeAddressLength);
		setBorderForMergeCell(CellStyle.BORDER_MEDIUM, brodercell, sheet, workbook);
		return workbook;
	}

	/**
	 *      * 合并单元格设置边框      * @param i      * @param cellRangeTitle      * @param
	 * sheet      * @param workBook     
	 */
	private static void setBorderForMergeCell(int i, CellRangeAddress cellRangeTitle, Sheet sheet,
			HSSFWorkbook workBook) {
		RegionUtil.setBorderBottom(i, cellRangeTitle, sheet, workBook);
		RegionUtil.setBorderLeft(i, cellRangeTitle, sheet, workBook);
		RegionUtil.setBorderRight(i, cellRangeTitle, sheet, workBook);
		RegionUtil.setBorderTop(i, cellRangeTitle, sheet, workBook);
	}

	/**
	 * 设定二级标题
	 * 
	 * @param workbook
	 * @param secondTitles
	 * @return
	 */
	public static HSSFWorkbook makeSecondHead(HSSFWorkbook workbook, String[] secondTitles) {
		// 创建用户属性栏
		HSSFSheet sheet = workbook.getSheetAt(0);
		HSSFRow rowField = sheet.createRow(1);
		HSSFCellStyle styleField = createStyle(workbook, (short) 8);
		styleField.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleField.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		styleField.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		styleField.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		// 为标题设置背景颜色
		styleField.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		styleField.setFillForegroundColor(HSSFColor.LIME.index);
		for (int i = 0; i < secondTitles.length; i++) {
			HSSFCell cell = rowField.createCell(i);
			cell.setCellValue(secondTitles[i]);
			cell.setCellStyle(styleField);
		}
		return workbook;
	}

	/**
	 * 插入数据
	 * 
	 * @param workbook
	 * @param dataList
	 * @param beanPropertys
	 * @return
	 */
	public static <T> HSSFWorkbook exportExcelData(HSSFWorkbook workbook, List<T> dataList, String[] beanPropertys) {
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
		for (int j = 0; j < dataList.size(); j++) {
			HSSFRow rowData = sheet.createRow(j + 2);
			T t = dataList.get(j);
			for (int k = 0; k < beanPropertys.length; k++) {
				Object value = BeanUtils.getBeanProperty(t, beanPropertys[k]);
				HSSFCell cellData = rowData.createCell(k);
				if(k==0) {
					value=j+1;
				}
				cellData.setCellValue(value == null ? "" : value + "");

				cellData.setCellStyle(styleData);
			}
		}
		return workbook;
	}

	/**
	 * 使用批量导入方法时，请注意需要导入的Bean的字段和excel的列一一对应
	 * 
	 * @param clazz
	 * @param file
	 * @param beanPropertys
	 * @return
	 */
	public static <T> List<T> parserExcel(Class<T> clazz, File file, String[] beanPropertys) {
		// 得到workbook
		List<T> list = new ArrayList<T>();
		try {
			Workbook workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheetAt(0);
			// 直接从第三行开始获取数据
			int rowSize = sheet.getPhysicalNumberOfRows();
			System.out.println("rowsize "+rowSize);
			if (rowSize > 2) {
				for (int i = 2; i < rowSize; i++) {
					T t = clazz.newInstance();
					Row row = sheet.getRow(i);
					int cellSize = row.getPhysicalNumberOfCells();
					Object cell1 = getCellValue(row.getCell(0));
					if(cell1==null||StringUtil.isBlank(cell1+"")) {
						continue;
					}
					for (int j = 0; j < cellSize; j++) {

						Object cellValue = getCellValue(row.getCell(j));
						org.apache.commons.beanutils.BeanUtils.copyProperty(t, beanPropertys[j], cellValue);
					}

					list.add(t);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

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
				}else {
					result = (int)cell.getNumericCellValue();
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
				break;
			default:
				break;
			}
		}
		return result;
	}

	/**
	 * 提取公共的样式
	 * 
	 * @param workbook
	 * @param fontSize
	 * @return
	 */
	private static HSSFCellStyle createStyle(HSSFWorkbook workbook, short fontSize) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 创建一个字体样式
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints(fontSize);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		return style;
	}

	/**
	 * 导出excel上传文件到OSS
	 * 
	 * @param fileName     文件名称 只需要输入名称 不用后缀
	 * @param HeadTitle    文件头标题
	 * @param titles       列头
	 * @param dataList     数据列表<实体类>
	 * @param beanProperty 类属性按列头顺序对应
	 * @param ossPath      OSS中所放路径 以/结束
	 * @return 最终阿里云OSS的存储路径
	 */
	public static String uploadOSSExcel(String fileName, String HeadTitle, String[] titles, List<?> dataList,
			String[] beanProperty, String ossPath) {
		String filePath = "";
		try {
			File file = new File(fileName + DateUtil.dateToStr(new Date(), DateUtil.YYYYMMDDHHMMSS) + ".xls");
			HSSFWorkbook workbook1 = ExcelUtil.makeExcelHead(HeadTitle, titles.length - 1);
			HSSFWorkbook workbook2 = ExcelUtil.makeSecondHead(workbook1, titles);
			HSSFWorkbook workbook = ExcelUtil.exportExcelData(workbook2, dataList, beanProperty);
			FileOutputStream outputStream = new FileOutputStream(file);
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
			filePath = AliyunOSSUtil.upload(file, ossPath);
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return filePath;
	}

	public static void main(String[] args) {
//		try {
//			HSSFWorkbook workbook1 = ExcelUtil.makeExcelHead("用户列表", 4);
//			String[] secondTitles = { "用户名", "账号", "所属部门", "性别", "电子邮箱" };
//			HSSFWorkbook workbook2 = ExcelUtil.makeSecondHead(workbook1, secondTitles);
//
//			String[] beanProperty = { "name", "account", "dept", "gender", "email" };
//
////              HSSFWorkbook workbook = ExcelUtil.exportExcelData(workbook2, userList, beanProperty);
////              workbook.write(outputStream);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

//    	  String[] beanProperty = {"name","account","dept", "gender", "mobile", "email", "birthday"};
//          List<User> list = ExcelUtil.parserExcel(User.class, file, beanProperty);
//          if(list.size() > 0){
//              for(User user : list){
//                  user.setPassword("123456");
//                  user.setState(User.USER_STATE_VALID);
//                  add(user);
//              }
//          }        
	}
}
