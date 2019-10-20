package com.attencecheckin.javabackend.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateUtil {
	private static final String FORMAT_YMD = "yyyy-MM-dd";
	private static final String FORMAT_YMDHIS = "yyyy-MM-dd HH:mm:ss";
	private static final long MILLIS_OF_DAY = 1000 * 60 * 60 * 24;
	private static final long MILLIS_OF_HOUR = 1000 * 60 * 60;

	/**
	 * 在当前时间之前（精确到天）：不包括当天
	 * @param strTime
	 * @return
	 */
	public static boolean isBeforeNowTime(String strTime, boolean isInclude) {
		boolean isBeforeNowTime = false;
		try {
			DateFormat formatter = new SimpleDateFormat(FORMAT_YMD);
			Date testDate = formatter.parse(strTime);
			Calendar nowCal = Calendar.getInstance();
			if(isInclude) {
				nowCal.add(Calendar.DATE, 1);
			}
			Date nowDate = formatter.parse(nowCal.get(Calendar.YEAR) + "-"
					+ (nowCal.get(Calendar.MONTH) + 1) + "-"
					+ nowCal.get(Calendar.DATE));
//			System.out.println("=" + nowDate.getTime());
//			System.out.println("=" + testDate.getTime());
			isBeforeNowTime = testDate.before(nowDate);
//			System.out.println("=" + isBeforeNowTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return isBeforeNowTime;
	}

	/**
	 * 在当前时间之前（精确到天）：不包括当天
	 * @param strTime
	 * @return
	 */
	public static boolean isBeforeNowTime(String strTime) {
		return isBeforeNowTime(strTime, false);
	}
	
	/**
	 * @param strTime
	 * @return
	 */
	public static Date getDateTime(String strTime) {
		Date testDate = null;
		try {
			DateFormat formatter = new SimpleDateFormat(FORMAT_YMDHIS);
			testDate = formatter.parse(strTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testDate;
	}

	/**
	 * @param date
	 * @return
	 */
	public static String getDateTime(Date date) {
		String strTime = null;
		try {
			DateFormat formatter = new SimpleDateFormat(FORMAT_YMDHIS);
			date = (date == null) ? Calendar.getInstance().getTime() : date;
			strTime = formatter.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strTime;
	}

	/**
	 * 
	 * @param strTime
	 * @param days
	 *            +-days
	 * @return
	 */
	public static boolean isBeforeNowTime(String strTime, int days) {
		boolean isBeforeNowTime = false;
		try {
			DateFormat formatter = new SimpleDateFormat(FORMAT_YMD);
			Date testDate = formatter.parse(strTime);
			Calendar nowCal = Calendar.getInstance();
			nowCal.add(Calendar.DATE, days);
			Date nowDate = nowCal.getTime();
			// System.out.println(nowDate.toString());
			isBeforeNowTime = testDate.before(nowDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return isBeforeNowTime;
	}

	// ////start
	//
	// public static long getIntervalNow(String tmpTime, boolean isMaxTime,
	// boolean isDays) {
	// try {
	// DateFormat formatter = new SimpleDateFormat(FORMAT_YMD);
	// long tmpMillis = formatter.parse(DateUtil.getStrDate(tmpTime))
	// .getTime();
	// long nowMillis = Calendar.getInstance().getTimeInMillis();
	// return (isMaxTime ? (tmpMillis - nowMillis)
	// : (nowMillis - tmpMillis))
	// / (isDays ? MILLIS_OF_DAY : MILLIS_OF_HOUR);
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	// return 0;
	// }
	public static long getIntervalNowDays(String tmpTime) {
		String nowDate = DateUtil.getNowDate();
		return getIntervalDays(tmpTime, nowDate);
	}

	public static long getIntervalDays(String maxTime, String minTime) {
		return getInterval(maxTime, minTime, true);
	}

	public static long getIntervalHours(String maxTime, String minTime) {
		return getInterval(maxTime, minTime, false);
	}

	public static long getInterval(String maxTime, String minTime,
                                   boolean isDays) {
		try {
			DateFormat formatter = new SimpleDateFormat(FORMAT_YMD);
			Date date1 = formatter.parse(DateUtil.getStrDate(maxTime));
			Date date2 = formatter.parse(DateUtil.getStrDate(minTime));
			return (date1.getTime() - date2.getTime())
					/ (isDays ? MILLIS_OF_DAY : MILLIS_OF_HOUR);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	// ////end
	// 添加天数后的日期时间
	//解析格式为:年月日时分秒
	public static String addIntervalDays(String strTime, int days) {
		try {
			DateFormat df = new SimpleDateFormat(FORMAT_YMDHIS);
			Date tmpDate = TextUtil.isNotNull(strTime) ? df.parse(strTime)
					: new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(tmpDate);
			cal.add(Calendar.DATE, days);
			strTime = df.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return strTime;
	}
	// ////end
	// 添加天数后的日期时间
	//解析格式为:年月日
	public static String addDays(String strTime, int days) {
		try {
			DateFormat df = new SimpleDateFormat(FORMAT_YMD);
			Date tmpDate = TextUtil.isNotNull(strTime) ? df.parse(strTime)
					: new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(tmpDate);
			cal.add(Calendar.DATE, days);
			strTime = df.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return strTime;
	}

	public static String getStrDate(String strTime) {
		try {
			return strTime.substring(0, 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getStrTime(String strTime) {
		try {
			return strTime.substring(10, 19);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getNowDate() {
		return getNowDate(0);
	}

	public static String getNowDate(int days) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YMD);
		cal.add(Calendar.DATE, days);
		return sdf.format(cal.getTime());
	}

	// 计算两个任意时间中间的间隔天数（这个比较常用）
	public static int getIntervalDays(GregorianCalendar startday,
			GregorianCalendar endday) {
		normalCalendar(startday);
		normalCalendar(endday);
		if (startday.after(endday)) {
			GregorianCalendar cal = startday;
			startday = endday;
			endday = cal;
		}
		long sl = startday.getTimeInMillis();
		long el = endday.getTimeInMillis();
		long ei = el - sl;
		return (int) (ei / MILLIS_OF_DAY);
	}

	/*
	 * 取日期范围中包含的天数
	 */
	public static int getDaysInRange(long lowerTime, long upperTime) {
		Long result = new Long((upperTime - lowerTime) / MILLIS_OF_DAY);
		return result.intValue();
	}

	public static int getDaysInRange(java.util.Date lwDate,
			java.util.Date upDate) {
		GregorianCalendar upGc = new GregorianCalendar();
		GregorianCalendar lwGc = new GregorianCalendar();

		upGc.setTime(upDate);
		lwGc.setTime(lwDate);

		return getDaysInRange(lwGc, upGc);
	}

	public static int getDaysInRange(Timestamp lwTs, Timestamp upTs) {
		GregorianCalendar upGc = new GregorianCalendar();
		GregorianCalendar lwGc = new GregorianCalendar();

		upGc.setTime(upTs);
		lwGc.setTime(lwTs);

		return getDaysInRange(lwGc, upGc);
	}

	public static int getDaysInRange(GregorianCalendar lwGc,
			GregorianCalendar upGc) {
		normalCalendar(lwGc);
		normalCalendar(upGc);
		return getDaysInRange(lwGc.getTimeInMillis(), upGc.getTimeInMillis());
	}

	public static GregorianCalendar normalCalendar(GregorianCalendar gc) {
		gc.set(Calendar.HOUR_OF_DAY, 0);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);
		return gc;
	}

	/*
	 * 取日期范围中包含的天数 -- 结束
	 */

	/**
	 * 判断后一个日期是不是明天
	 **/
	public static boolean isTomorrow(Timestamp day1, Timestamp day2) {
		GregorianCalendar gc1 = new GregorianCalendar();
		GregorianCalendar gc2 = new GregorianCalendar();

		gc1.setTime(day1);
		gc2.setTime(day2);

		gc1.add(Calendar.DATE, 1); // 变成明天

		if (gc1.get(Calendar.YEAR) == gc2.get(Calendar.YEAR)
				&& gc1.get(Calendar.MONTH) == gc2.get(Calendar.MONTH)
				&& gc1.get(Calendar.DATE) == gc2.get(Calendar.DATE))
			return true;
		else
			return false;
	}

	/**
	 * 比较两个日期 day1 == day2 return = 0 day1 > day2 return > 0 ,day1在day2之后 day1 <
	 * day2 return < 0
	 */
	public static int compareDay(Timestamp day1, Timestamp day2) {
		GregorianCalendar gc1 = new GregorianCalendar();
		GregorianCalendar gc2 = new GregorianCalendar();

		gc1.setTime(day1);
		gc2.setTime(day2);

		if (gc1.get(Calendar.YEAR) == gc2.get(Calendar.YEAR)
				&& gc1.get(Calendar.MONTH) == gc2.get(Calendar.MONTH)
				&& gc1.get(Calendar.DATE) == gc2.get(Calendar.DATE))
			return 0;
		else if (gc1.after(gc2))
			return 1;
		else
			return -1;
	}

	/**
	 * 比较两个日期 排除 时间因素 只负责比较 年月日 day1 == day2 return = 0 day1 > day2 return > 0
	 * ,day1在day2之后 day1 < day2 return < 0
	 */
	public static int compareDay(java.util.Date day1, java.util.Date day2) {
		GregorianCalendar gc1 = new GregorianCalendar();
		GregorianCalendar gc2 = new GregorianCalendar();

		gc1.setTime(day1);
		gc2.setTime(day2);

		gc1 = normalCalendar(gc1);
		gc2 = normalCalendar(gc2);

		if (gc1.get(Calendar.YEAR) == gc2.get(Calendar.YEAR)
				&& gc1.get(Calendar.MONTH) == gc2.get(Calendar.MONTH)
				&& gc1.get(Calendar.DATE) == gc2.get(Calendar.DATE))
			return 0;
		else if (gc1.after(gc2))
			return 1;
		else
			return -1;
	}

	public static int comparisonNowDate(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int comparisonValue = 0;
		try {
			Date date1 = Calendar.getInstance().getTime();
			Date date2 = sdf.parse(dateStr);
			if (date1.before(date2))
				comparisonValue = -1;
			else if (date1.after(date2))
				comparisonValue = 1;
			else
				comparisonValue = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comparisonValue;
	}

	public static final int SECOND = 1000;
	public static final int MINUTE = SECOND * 60;
	public static final int HOUR = MINUTE * 60;
	public static final int DAY = HOUR * 24;
	public static final int WEEK = DAY * 7;
	public static final int YEAR = DAY * 365; // or 366 ???

	/**
	 * This is the time difference between GMT time and Vietnamese time
	 */
	public static final long GMT_VIETNAM_TIME_OFFSET = HOUR * 7;

	/**
	 * This is the time difference between GMT time and SERVER time
	 */
	// private static long SERVER_TIME_OFFSET = HOUR * (new
	// DateOptions()).serverHourOffset;

	private static DateFormat ddMMyyyyFormat = new SimpleDateFormat(
			"dd/MM/yyyy");
	private static DateFormat defaultFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static DateFormat defaultDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// DateFormat.getDateInstance(DateFormat.DEFAULT);
	private static DateFormat datetimeFormat = DateFormat.getDateTimeInstance(
			DateFormat.DEFAULT, DateFormat.DEFAULT);
	private static DateFormat shortDateFormat = new SimpleDateFormat("MM-dd");// DateFormat.getDateInstance(DateFormat.DEFAULT);

	/**
	 * private constructor
	 */
	private DateUtil() {// prevent instantiation
	}

	public static int transitionTimeToSecond(String timeStr) {
		if (timeStr.length() == 0)
			return 0;
		String[] timeArray = timeStr.split(":");
		int hourN = 0;
		int minuteN = 0;
		int secondN = 0;
		if (timeArray.length == 3) {
			hourN = Integer.valueOf(timeArray[0]).intValue();
			minuteN = Integer.valueOf(timeArray[1]).intValue();
			secondN = Integer.valueOf(timeArray[2]).intValue();
		}
		int second = hourN * 60 * 60 + minuteN * 60 + secondN;
		return second;
	}

	public static String transitionSecondToTime(int second) {
		long hours = second / (60 * 60);
		long remain = second - (hours * (60 * 60));
		long minutes = remain / 60;
		long seconds = remain - (minutes * 60);
		String timeStr = "";
		String hoursStr = "";
		String minutesStr = "";
		String secondsStr = "";

		if (String.valueOf(hours).length() == 1)
			hoursStr = "0" + hours;
		else
			hoursStr = String.valueOf(hours);

		if (String.valueOf(minutes).length() == 1)
			minutesStr = "0" + String.valueOf(minutes);
		else
			minutesStr = String.valueOf(minutes);

		if (String.valueOf(seconds).length() == 1)
			secondsStr = "0" + String.valueOf(seconds);
		else
			secondsStr = String.valueOf(seconds);
		timeStr = hoursStr + ":" + minutesStr + ":" + secondsStr;

		return timeStr;

	}

	public static synchronized String getDateDDMMYYYY(Date date) {
		return ddMMyyyyFormat.format(date);
	}

	public static synchronized String formatDate(Date date) {
		if (date == null)
			return "";
		return dateFormat.format(date);
	}

	public static synchronized String formatShortDate(Timestamp timeToDate) {
		if (timeToDate == null)
			return "";
		return shortDateFormat.format(timeToDate);
	}

	public static synchronized String formatTime(Timestamp timeToDate) {
		if (timeToDate == null)
			return "";
		return defaultFormat.format(timeToDate);
	}

	public static synchronized String formatDate(Timestamp date) {
		if (date == null)
			return "";
		return defaultDateFormat.format(date);
	}

	public static synchronized String formatDateTime(Date date) {
		return datetimeFormat.format(date);
	}

	// public static Timestamp getCurrentGMTTimestamp() {
	// return new Timestamp(System.currentTimeMillis() - SERVER_TIME_OFFSET);
	// }

	public static synchronized Timestamp getWeekTimestamp() {
		return new Timestamp(System.currentTimeMillis() - WEEK);
	}

	public static synchronized Timestamp getMonthTimestamp() {
		return new Timestamp(System.currentTimeMillis() - WEEK);
	}

	public static Timestamp getCurrentCNTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	// public static void updateCurrentGMTTimestamp(Timestamp timeToUpdate) {
	// timeToUpdate.setTime(System.currentTimeMillis() - SERVER_TIME_OFFSET);
	// }

	public static void updateCurrentCNTimestamp(Timestamp timeToUpdate) {
		timeToUpdate.setTime(System.currentTimeMillis());
	}

	public static Date getVietnamDateFromGMTDate(Date date) {
		return new Date(date.getTime() + GMT_VIETNAM_TIME_OFFSET);
	}

	public static Date convertGMTDate(Date gmtDate, int hourOffset) {
		return new Date(gmtDate.getTime() + hourOffset * HOUR);
	}

	public static Timestamp convertGMTTimestamp(Timestamp gmtTimestamp,
                                                int hourOffset) {
		return new Timestamp(gmtTimestamp.getTime() + hourOffset * HOUR);
	}

	public static Timestamp getCurrentGMTTimestampExpiredYear(int offsetYear) {
		// return new Timestamp(System.currentTimeMillis() +
		// offsetYear*(365*24*60*60*1000));
		Calendar now = Calendar.getInstance();
		now.add(Calendar.YEAR, offsetYear);
		return new Timestamp(now.getTime().getTime());
	}

	public static Timestamp getCurrentGMTTimestampExpiredMonth(int offsetMonth) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MONTH, offsetMonth);
		return new Timestamp(now.getTime().getTime());
	}

	public static String getTimestampFormat(Timestamp gmtTimestamp) {
		if (gmtTimestamp == null)
			return "";
		String timeStr = gmtTimestamp.toString();
		if (timeStr.length() > 19) {
			timeStr = timeStr.substring(0, 19);
		}
		return timeStr;
	}

	public static String getDateString(String cut) {
		Calendar calendar = Calendar.getInstance();
		String yearStr = String.valueOf(calendar.get(Calendar.YEAR));
		String monthStr = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		String dateStr = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

		if (monthStr.length() == 1)
			monthStr = "0" + monthStr;
		if (dateStr.length() == 1)
			dateStr = "0" + dateStr;
		String dateString = yearStr + cut + monthStr + cut + dateStr + cut;
		return dateString;
	}

	public static String getTimeString() {
		Calendar calendar = Calendar.getInstance();
		String yearStr = String.valueOf(calendar.get(Calendar.YEAR));
		String monthStr = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		String dateStr = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		String hourStr = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
		String minuteStr = String.valueOf(calendar.get(Calendar.MINUTE));
		String secondStr = String.valueOf(calendar.get(Calendar.SECOND));

		if (monthStr.length() == 1)
			monthStr = "0" + monthStr;
		if (dateStr.length() == 1)
			dateStr = "0" + dateStr;
		if (hourStr.length() == 1)
			hourStr = "0" + hourStr;
		if (minuteStr.length() == 1)
			minuteStr = "0" + minuteStr;
		if (secondStr.length() == 1)
			secondStr = "0" + secondStr;
		String timeString = yearStr + monthStr + dateStr + hourStr + minuteStr
				+ secondStr;
		return timeString;
	}

	// modified by joe at 080910 以下是从薛凡的DateUtil.java中提取过来,因为两个重名了
	/**
	 * @author werwolf
	 * @reviewer
	 * @version 1.0, 2008-07-02
	 * @env JDK1.5
	 * @modified
	 */
	/**
	 * 
	 * @param hours
	 * @return
	 */
	public static String getTime(int hours) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar nowCal = Calendar.getInstance();
		if (hours != 0)
			nowCal.add(Calendar.HOUR_OF_DAY, hours);
		return formatter.format(nowCal.getTime());
	}

	/**
	 * 获取距当前日期 hours 小时的 strFormat 格式化后的字符串
	 * 
	 * @param hours
	 * @param strFormat
	 * @return
	 */
	public static String getTime(int hours, String strFormat) {
		DateFormat formatter = new SimpleDateFormat(strFormat);
		Calendar nowCal = Calendar.getInstance();
		if (hours != 0)
			nowCal.add(Calendar.HOUR_OF_DAY, hours);
		return formatter.format(nowCal.getTime());
	}

	/**
	 * 获取当前日期格式化字符串
	 * 
	 * @param strContent
	 * @return
	 */
	public static String datetime(String strContent) {
		Calendar cal = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat(strContent);
		return df.format(cal.getTime());
	}

	public static Timestamp changeTimestamp(String timeStr) throws Exception {
		Date date1 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		Timestamp afterTime = new Timestamp(date1.getTime());
		try {
			Date date2 = sdf.parse(timeStr);
			afterTime = new Timestamp(date2.getTime());
		} catch (Exception e) {
			throw new Exception("日期格式： '" + timeStr + "' 输入有误！请重新输入。");
		}
		return afterTime;
	}

	public static String getAddDateString(String dateString, int dateNum) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String addDateStr = "";
		try {
			Date date2 = sdf.parse(dateString);
			cal.setTime(date2);
			cal.add(Calendar.DATE, dateNum);
			addDateStr = sdf.format(cal.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addDateStr;
	}

	public static boolean isOverTime(String dateString, int dateNum) {
		Calendar cal = Calendar.getInstance();
		Date date1 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		boolean isOver = false;
		try {
			Date date2 = sdf.parse(dateString);
			cal.setTime(date2);
			cal.add(Calendar.DATE, dateNum);
			if (cal.getTime().before(date1))
				isOver = true;
			// System.out.println("下一天的时间是：" + sdf.format(cal.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isOver;
	}

	public static int comparisonDateStr(String dateStr1, String dateStr2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int comparisonValue = 0;
		try {
			Date date1 = sdf.parse(dateStr1);
			Date date2 = sdf.parse(dateStr2);
			if (date1.before(date2))
				comparisonValue = -1;
			else if (date1.after(date2))
				comparisonValue = 1;
			else
				comparisonValue = 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return comparisonValue;
	}

	public static void main(String[] args) {
		boolean flag = DateUtil.isBeforeNowTime("2014-12-11");
		boolean flag1 = !DateUtil.isBeforeNowTime("2014-12-12");
		System.out.println(flag);
		System.out.println(flag1);
	}
}
