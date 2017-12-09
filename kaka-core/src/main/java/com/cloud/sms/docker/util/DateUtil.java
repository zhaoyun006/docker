package com.cloud.sms.docker.util;

import com.cloud.sms.docker.ci.entity.DockerCloudImagesBuildLogEntity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 
 * @author zy
 * @Date 2016-06-21
 * 获取时间格式
 */
public class DateUtil {

	public static final String DATE_FORMAT = "yyyy-MM-dd";

	public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * format "yyyy-MM-dd HH:mm:ss"可以随意组合
 	 */
	public static String getDate(String format){
		String d = new SimpleDateFormat(format).format(Calendar.getInstance().getTime());
		return d;
	}

	/**
	 * // 获取时间戳
	 * @return
	 */
	public static long getDateStamp(){
		return System.currentTimeMillis();
	}


	/**
	 *
	 * @param date
	 * @return
	 */
	public static String utc2Date(String date){
		if (CheckUtil.checkString(date)){
			date = date.replace("T", " ").split("\\.")[0];
			return date;
		}
		return "";
	}



}