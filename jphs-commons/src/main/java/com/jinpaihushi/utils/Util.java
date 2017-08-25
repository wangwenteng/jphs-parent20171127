package com.jinpaihushi.utils;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

public class Util {

    public static final Logger succLog = Logger.getLogger("LoggerSucc");

    public static final Logger failLog = Logger.getLogger("LoggerFail");

    public static final Logger debugLog = Logger.getLogger("debugLogger");

    public static final Logger consoleLog = Logger.getLogger("consoleLogger");

    public Util() {
    }

    //用于数据保留2位小数使�?
    public static DecimalFormat df = new DecimalFormat("#.00");

    //Timestamp转String
    public static String getDate(Timestamp t) {
        String str = "";
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 定义格式，不显示毫秒
            str = df.format(t);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String getDate(Date d) {
        String str = "";
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            str = df.format(d);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    // 通用的ToString方法
    @SuppressWarnings("rawtypes")
    public String toString() {
        try {
            Map map = BeanUtils.describe(this);
            Iterator keyIt = map.keySet().iterator();
            StringBuffer aBuffer = new StringBuffer();
            while (keyIt.hasNext()) {
                String key = (String) keyIt.next();
                if ("class".equals(key)) {
                    continue;
                }
                String value = (String) map.get(key);
                if (aBuffer.length() > 0) {
                    aBuffer.append(", ");
                }
                aBuffer.append(key + " = [" + value + "]");
            }
            return aBuffer.toString();
        }
        catch (Exception e) {
            return super.toString();
        }
    }

}
