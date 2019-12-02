package com.czsm.util;

import java.util.Date;

public class StringUtil {
   
    
    public static final String EMPTY = "";

     
    public static final int INDEX_NOT_FOUND = -1;

     
  
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

   
    public static boolean isNotEmpty(String str) {
        return !StringUtil.isEmpty(str);
    }
 
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

   
    public static boolean isNotBlank(String str) {
        return !StringUtil.isBlank(str);
    }

    public static String getSerializeID() {
    	 return DateUtil.dateToStr(new Date(), DateUtil.YYYYMMDDHHMMSS);
    }
}

