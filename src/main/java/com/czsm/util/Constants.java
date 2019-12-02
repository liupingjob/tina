package com.czsm.util;


/**
 * 常量列表 所有字段定义为常量 \n 所有静态字段定义此处规范管理
 * 
 * @author Mac(刘平) 20180806
 *
 */
public class Constants {
	public static final String KAFKA_TOPIC = "czsm"; // Kafka服务器 tipic标识
	public static final String CZ_LOG_TOPIC = "czlog"; // Kafka服务器 tipic标识 日志模块
	public static final String LOG_BUYER = "buyer_userLog"; // Kafka服务器 tipic标识 日志模块
	public static final String LOG_SELLER = "czlog"; // Kafka服务器 tipic标识 日志模块
	

	public static final String ACCOUNT_ERROR = "accountError"; // 账号错误
	public static final String ACCOUNT_NO_EXIST = "accountNoExist"; // 账号不存在
	public static final String ACCOUNT_EXIST = "accountExist"; // 账号存在

	public static final String INPUT_VCODE = "inputVcode";// 请输入验证码
	public static final String VCODE_ERROR = "vcodeError";// 验证码错误
	public static final String VCODE_CORRECT = "vcodeCorrect";// 验证码正确
	public static final String VCODE_SENT = "sent"; // 验证码发送成功

	public static final String SUCCESS = "success"; // 登录成功、注册成功
	public static final String FAIL = "fail"; // 登录失败、注册失败

	public static final String RESET_SUCCESS = "resetPwdSuccess"; // 重置密码成功
	public static final String RESET_FAIL = "resetPwdFail"; // 重置密码失败
	
	public static final String BIND_SUCCESS = "bindSuccess";   //绑定成功
	public static final String BIND_FAIL = "bindFail";   //绑定失败
	
	public static final String insert_SUCCESS = "insertSuccess";   //添加成功
	public static final String insert_FAIL = "insertFail";   //添加失败
	
	public static final String UPDATE_SUCCESS = "updateSuccess";   //修改成功
	public static final String UPDATE_FAIL = "updateFail";   //修改失败
	
	public static final String USERNAME_EXISE = "usernameExise"; // 用户名已存在
	public static final String USERNAME_NO_EXISE = "usernameNoExise"; // 用户名不存在

	public static final String NO_EXISE = "noExise"; // 不存在
	
	public static final String PWD_ERROR = "passwordError"; // 密码错误
	public static final String PWD_SAME = "passwordSame"; // 原密码与新密码相同
	public static final String PWD_NO_SAME = "passwordNoSame"; // 原密码与新密码不相同

	public static final String UNKNOWN_ERROR = "系统忙，请稍后重试，或联系管理员！"; // 未知错误
	public static final String NULL_VALUE_EXCEPTION = "nullValueException"; // 空值异常

	public static final String PIC_VERIFY_CODE = "picVerifyCode"; // 图形验证码SessionID
	public static final String SMS_VERIFY_CODE = "smsVerifyCode"; // 短信验证码SessionID
	public static final String PHONE_CODE = "phoneNumberCode"; // 短信验证码SessionID
	public static final String MAIL_VERIFY_CODE = "mailVerifyCode"; // 邮箱验证码SessionID
	public static final String MAIL_CODE = "mailNumberCode"; // 邮箱验证码SessionID

	

	public static final String PHONE_EXISE = "phoneExise";// 手机号已存在
	public static final String CAN_REGISTER = "canRegister";// 可注册
	public static final String EMAIL_EXISE = "emailExise"; // 邮箱已存在
	public static final String EMAIL_NO_EXISE = "emailNoExise"; // 邮箱不存在
	public static final String PHONE_NO_EXISE = "phoneNoExise";// 手机号不存在
	public static final String EMAIL_ERROR = "inputEmailError";// 您输入的邮箱有误
	public static final String IDENTITY_EXISE = "identityExise";// 证件号存在
	public static final String IDENTITY_NO_EXISE = "identityNoExise";// 证件号不存在
	public static final String REALNAME_CERTIFICATION = "realNameCertification";// 已实名认证
	public static final String REALNAME_NO_CERTIFICATION = "realNameNoCertification";// 未实名认证
	public static final String USERNAME_NO_UPDATE = "usernameNoUpdate";// 用户名不可修改
	public static final String USERNAME_UPDATE = "usernameUpdate";// 用户名可修改
	

	public static final int VCODE_LENGTH = 6; // 设置短信验证码和邮箱验证码均为6位

	public static final String COMPANY_EXISE = "companyExise"; // 公司已存在
	public static final String COMPANY_NO_EXISE = "companyNoExise"; // 公司不存在
	public static final String LICENSE_NUMBER_EXISE = "LicenseNumberExise"; // 营业执照编号已存在
	public static final String LICENSE_NUMBER_NO_EXISE = "LicenseNumberNOExise"; // 营业执照编号不存在
	public static final String ALLOW_UPDATE = "allowUpdate"; // 可修改
	public static final String IMMEDIATE_VERIFY = "immediateVerification"; // 立即验证


	public static final int LOGIN_COUNT = 3;// 登录的次数
	public static final String LOGIN_COUNT_MSG = "loginCount";// 登录的次数sessionID
	public static final String LOGIN_USERINFO_SEESION_ID = "loginUserInfoSessionId";// 登录将用户的信息存入session
	public static final String LOGIN_ADMIN_SEESION_ID = "loginAdminSessionId";// 登录将用户的信息存入session
	
	public static final String SIGN_BUYERID_SEESION_ID = "userid";// 注册将注册的编号存储到session中
	public static final String SIGN_SELLERTEL_SEESION_ID = "phoneNumber";// 注册将商家的注册信息存入session
	public static final String FORGET_BUYER_SESSION_ID = "forgetSessionId";// 忘记密码session（买家账号）
	public static final String FORGET_SELLER_SESSION_ID = "forgetSellerSessionId";// 忘记密码session（买家账号）
	public static final String OSS_HOST = "czsm/";// OSS 总文件夹
	public static final String PATH_HEAD = "head";// OSS 总文件夹
	public static final String OSS_GOODS_PATH = "czsm/seller/goods/";// OSS 总文件夹
	public static final String OSS_HEAD_PATH = "czsm/buyer/headPic/";// OSS 总文件夹
	public static final String OSS_EXCEL_PATH = "czsm/excel/";// OSS 总文件夹
	public static final String OSS_ADVERT_PATH = "czsm/advert/";// OSS 总文件夹——广告

	public static final String OSS_URL = "https://czsmoss.oss-cn-shenzhen.aliyuncs.com/";// OSS访问地址前缀
	public static final String GOODS_OSS_URL = "https://czsmoss.oss-cn-shenzhen.aliyuncs.com/czsm/seller/goods/";// 商品OSS访问地址前缀
	public static final String NEWS_OSS_URL = "http://czsmoss.oss-cn-shenzhen.aliyuncs.com/czsm/main/news/";// 资讯新闻OSS访问地址前缀
	public static final String EXHIBITION_OSS_URL = "http://czsmoss.oss-cn-shenzhen.aliyuncs.com/czsm/main/exhibition/";// 展会OSS访问地址前缀
	public static final String BUYER_HEADPIC_OSS_URL = "http://czsmoss.oss-cn-shenzhen.aliyuncs.com/czsm/buyer/headPic/";// 买家头像OSS访问地址前缀
	public static final String BUYER_QUOTE_PRICE_OSS_URL = "http://czsmoss.oss-cn-shenzhen.aliyuncs.com/czsm/buyer/quotePrice/";// 买家询价信息附件OSS访问地址前缀
	public static final String SELLER_HEADPIC_OSS_URL = "http://czsmoss.oss-cn-shenzhen.aliyuncs.com/czsm/seller/userInfo/";// 商家头像OSS访问地址前缀 、商家认证图片
	
	public static final String NO_ADUIT = "未审核"; // 未审核
	public static final String AUDITED = "已审核"; // 未审核
	public static final String PASS_AUDITED = "审核通过"; // 审核pass the audit
	public static final String PASS_NO_AUDITED = "审核不通过"; // 审核pass the audit
	public static final String DISABLED = "不可用"; // 审核pass the audit
	public static final String SOLD_OUT = "已下架"; // 已下架
	public static final String NO_VERIFY = "未认证"; // 未认证
	public static final String NORMAL = "正常"; // 未认证
	public static final String FORBIDDEN = "禁用"; // 未认证
	
	public static final String NUMBER_ZERO = "0"; // 
	public static final String NUMBER_ONE = "1"; // 
	public static final String NUMBER_TWO = "2"; // 
	public static final String NUMBER_THREE = "3"; // 
	public static final String NUMBER_FOUR = "4"; // 
	public static final String NUMBER_FIVE = "5"; // 
	public static final String NUMBER_SIX = "6"; // 
	public static final String NUMBER_SEVEN = "7"; // 
	public static final String NUMBER_EIGHT = "8"; // 
	public static final String NUMBER_NINE = "9"; // 
	public static final String NUMBER_TEN = "10"; // 
	public static final String NUMBER_ELEVEN = "11"; // 
	public static final String NUMBER_TWELVE = "12"; // 
	public static final int MUCH = 1000000; // 
	
	public static final String VERIFY = "审核"; // 0——审核、-1——上架、1——下架、2——查看、3——删除 
	public static final String PULL_SHELVES = "下架"; // 0——审核、-1——上架、1——下架、2——查看、3——删除 
	public static final String PUT_SHELVES = "上架"; // 0——审核、-1——上架、1——下架、2——查看、3——删除 
	public static final String CHECK = "查看"; // 0——审核、-1——上架、1——下架、2——查看、3——删除 
	public static final String DELETE = "删除"; // 0——审核、-1——上架、1——下架、2——查看、3——删除 
	public static final String OTHER = "其它"; // 0——审核、-1——上架、1——下架、2——查看、3——删除 

	public static final int VCODE_WIDE = 100; // 图形验证码图片的宽度
	public static final int VCODE_HEIGHT = 50; // 图形验证码图片的高度
	public static final String NO = "no";
	public static final String YES = "yes";
	public static final String HAVE = "have";
	public static final String ADD_INFO = "添加"; //添加
	public static final String UPDATE_INFO = "修改";  //修改  delete 删除
	public static final String ALL_SUPPLIERS = "全部供应商";  //修改  delete 删除
	public static final String PARTICIPATING_SUPPLIERS = "参展供应商";  //修改  delete 删除
	public static final String UNLIMITED = "不限";  //修改  delete 删除
	public static final String ORDINARY_SELLER = "普通供应商";  //修改  delete 删除
	public static final String GOLD_SELLER = "金牌供应商";  //修改  delete 删除
	public static final String TOP_SELLER = "顶级供应商";  //修改  delete 删除
	
	public static final String ADD_JUDAGE = "加入";  //修改  delete 删除
	public static final String REFUSE = "拒绝";  //修改  delete 删除
	public static final String PENDING = "待处理";  //修改  delete 删除
	public static final String SEPARATOR = "、";  //分隔符
	public static final String BLANK = "";  //空
	
	
	
	public static final String SELLER_EMAIL_REGISTR_TIME="sellerEmailRegisterTime";//存储邮箱入驻时间
	
	public static final String COOKIE_NAME="czsmCookie";
}
