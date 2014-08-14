package com.hlib.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;

/**
 * 
 * @author haojinwei
 * 日期格式化处理
 */
@SuppressLint("SimpleDateFormat")
public class XcDateTool {

	public static SimpleDateFormat dateSdf1 = new SimpleDateFormat("yyyyMMdd");
	public static SimpleDateFormat dateSdf2 = new SimpleDateFormat("yyyy-M-d");
	public static SimpleDateFormat dateSdf3 = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat dateSdf4 = new SimpleDateFormat("yyyy年MM月dd日");
	
	public static SimpleDateFormat timeSdf1 = new SimpleDateFormat("HH:mm");
	public static SimpleDateFormat timeSdf2 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
	public static SimpleDateFormat timeSdf3 = new SimpleDateFormat("yyyy-M-d H:m");
	public static SimpleDateFormat timeSdf4 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static SimpleDateFormat timeSdf5 = new SimpleDateFormat("yyyy年M月d日 h:m");
	
	/**
	 * @param date
	 * @param sdf
	 * @return
	 */
	public static String dateToStringFormat(Date date, SimpleDateFormat sdf) {
		if (null == date) {
//			return null;
			return "";
		}
		return sdf.format(date);
	}
	
	/**
	 * @param dateStr
	 * @param sdf
	 * @return
	 */
	public static String dateToStringFormat(String dateStr, SimpleDateFormat sdf) {
		if(XcStringUtils.isBlank(dateStr)){
			return null;
		}
		return sdf.format(StringToDateParse(dateStr, sdf));
	}
	
	/**
	 * StringToDateParse
	 * @param dateStr
	 * @param sdf
	 * @return
	 */
	public static Date StringToDateParse(String dateStr, SimpleDateFormat sdf) {
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static Calendar now = null;
	
	/**
	 * @return
	 */
	public static int getCurrentYear() {
		if (null == now) {
			now = Calendar.getInstance();
		}
		return now.get(Calendar.YEAR);
	}
	
	/**
	 * @return
	 */
	public static int getCurrentMonth() {
		if (null == now) {
			now = Calendar.getInstance();
		}
		return now.get(Calendar.MONTH) + 1;
	}

	/**
	 * @return
	 */
	public static int getCurrentDay() {
		if (null == now) {
			now = Calendar.getInstance();
		}
		return now.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * @param beforeDay
	 * @param cDate
	 * @return
	 */
	public static Date beforeDay(int beforeDay, Date cDate) {
		if (beforeDay < 0) {
			return null;
		}
		if (cDate == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(cDate);
		calendar.add(Calendar.DATE, -beforeDay);
		return calendar.getTime();
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date getTimeOf12(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return  cal.getTime();
    }
}
