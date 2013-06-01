package com.asiainfo.appframe.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 时间工具类{时间日期工具类,封装工作中常用的一些时间日期计算方法等,还可以提供更多的重载方法,用于时间的转化等.}
 * 
 * @author luhf
 * @date 2013-1-12 下午2:01:01
 */
public class Dates{

	private static final Logger log=LoggerFactory.getLogger(Dates.class);

	public static final String yyyyFormat="yyyy";
	public static final String MMFormat="MM";
	public static final String ddFormat="dd";
	public static final String HHFormat="HH";
	public static final String mmFormat="mm";
	public static final String ssFormat="ss";
	public static final String SSSFormat="SSS";
	public static final String MMddFormat="MM-dd";
	public static final String MMddFormat1="MMdd";
	public static final String hhmmFormat="HH:mm";
	public static final String hhmmFormat1="HHmm";
	public static final String yyyyMMddFormat="yyyy-MM-dd";
	public static final String yyyyMMddFormat1="yyyyMMdd";
	public static final String longFormat="yyyy-MM-dd HH:mm:ss";
	public static final String longFormat1="yyyyMMddHHmmss";
	public static final String fullFormat="yyyy-MM-dd HH:mm:ss.SSS";
	public static final String fullFormat1="yyyyMMddHHmmssSSS";
	public static final String[] WEEKS={"Sun","Mon","Tues","Wed","Thur","Fri","Sat"};
	public static final String[] fullWEEKS={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
	public static final String yyyyFormat_CN="yyyy年";
	public static final String MMFormat_CN="MM月";
	public static final String ddFormat_CN="dd日";
	public static final String HHFormat_CN="HH时";
	public static final String mmFormat_CN="mm分";
	public static final String ssFormat_CN="ss秒";
	public static final String SSSFormat_CN="SSS毫秒";
	public static final String MMddFormat_CN="MM月dd日";
	public static final String hhmmFormat_CN="HH时mm分";
	public static final String yyyyMMddFormat_CN="yyyy年MM月dd日";
	public static final String longFormat_CN="yyyy年MM月dd日 HH时mm分ss秒";
	public static final String fullFormat_CN="yyyy年MM月dd日 HH时mm分ss秒SSS毫秒";
	public static final String[] WEEKS_CN={"日","一","二","三","四","五","六"};
	public static final String[] fullWEEKS_CN={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};

	private static SimpleDateFormat sdf=null;
	private static Calendar cal=null;

	static{
		log.debug("启动时间工具类!");
	}

	/**
	 * 私用构造主法.因为此类是工具类.
	 */
	public Dates(){}

	/**
	 * 获取默认日期格式
	 * 
	 * @return 默认格式字符串 yyyy-MM-dd HH:mm:ss
	 */
	public static String getDefaultDatePattern(){
		return longFormat;
	}

	/**
	 * 使用预设格式格式化日期
	 * 
	 * @param date
	 *            日期
	 * @return 默认格式的日期字符串 2012-10-24 21:22:34
	 */
	public static String format(Date date){
		return format(date,getDefaultDatePattern());
	}

	/**
	 * 使用用户格式格式化日期
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            日期格式
	 * @return 指定格式的日期字符串
	 */
	public static String format(Date date,String pattern){
		if(date!=null){
			sdf=new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
		return "";
	}

	/**
	 * 根据预设格式返回当前日期
	 * 
	 * @return 默认格式的当前日期字符串 2012-10-24 21:22:34
	 */
	public static String getNow(){
		return format(new Date());
	}

	/**
	 * 根据用户格式返回当前日期
	 * 
	 * @param pattern
	 *            日期格式
	 * @return 指定格式的当前日期字符串
	 */
	public static String getNow(String pattern){
		return format(new Date(),pattern);
	}

	/**
	 * 使用预设格式解析字符串日期
	 * 
	 * @param date
	 *            日期字符串
	 * @return 默认日期格式的Date yyyy-MM-dd HH:mm:ss
	 */
	public static Date parse(String date){
		return parse(date,getDefaultDatePattern());
	}

	/**
	 * 使用指定格式解析字符串日期
	 * 
	 * @param date
	 *            日期字符串
	 * @param pattern
	 *            日期格式
	 * @return 指定日期格式的Date
	 */
	public static Date parse(String date,String pattern){
		sdf=new SimpleDateFormat(pattern);
		try{
			return sdf.parse(date);
		}catch(ParseException e){
			log.error("::使用指定格式解析字符串日期::parse(String date,String pattern)::"+e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 在日期上增加年数
	 * 
	 * @param date
	 *            日期
	 * @param n
	 *            要增加的年数
	 * @return 指定日期增加年数后Date
	 */
	public static Date addYear(Date date,int n){
		cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR,n);
		return cal.getTime();
	}

	/**
	 * 在日期上增加月数
	 * 
	 * @param date
	 *            日期
	 * @param n
	 *            要增加的月数
	 * @return 指定日期增加月数后Date
	 */
	public static Date addMonth(Date date,int n){
		cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH,n);
		return cal.getTime();
	}

	/**
	 * 在日期上增加天数
	 * 
	 * @param date
	 *            日期
	 * @param n
	 *            要增加的天数
	 * @return 指定日期增加天数后Date
	 */
	public static Date addDay(Date date,int n){
		cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE,n);
		return cal.getTime();
	}

	/**
	 * 在日期上增加小时数
	 * 
	 * @param date
	 *            日期
	 * @param n
	 *            要增加的小时数
	 * @return 指定日期增加小时数后Date
	 */
	public static Date addHour(Date date,int n){
		cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR,n);
		return cal.getTime();
	}

	/**
	 * 在日期上增加分钟数
	 * 
	 * @param date
	 *            日期
	 * @param n
	 *            要增加的分钟数
	 * @return 指定日期增加分钟数后Date
	 */
	public static Date addMinute(Date date,int n){
		cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE,n);
		return cal.getTime();
	}

	/**
	 * 在日期上增加秒数
	 * 
	 * @param date
	 *            日期
	 * @param n
	 *            要增加的秒数
	 * @return 指定日期增加秒数后Date
	 */
	public static Date addSecond(Date date,int n){
		cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND,n);
		return cal.getTime();
	}

	/**
	 * 在日期上减少年数
	 * 
	 * @param date
	 *            日期
	 * @param n
	 *            要减少的年数
	 * @return 指定日期减少年数后Date
	 */
	public static Date delYear(Date date,int n){
		cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR,-n);
		return cal.getTime();
	}

	/**
	 * 在日期上减少月数
	 * 
	 * @param date
	 *            日期
	 * @param n
	 *            要减少的月数
	 * @return 指定日期减少月数后Date
	 */
	public static Date delMonth(Date date,int n){
		cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH,-n);
		return cal.getTime();
	}

	/**
	 * 在日期上减少天数
	 * 
	 * @param date
	 *            日期
	 * @param n
	 *            要减少的天数
	 * @return 指定日期减少天数后Date
	 */
	public static Date delDay(Date date,int n){
		cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE,-n);
		return cal.getTime();
	}

	/**
	 * 在日期上减少小时数
	 * 
	 * @param date
	 *            日期
	 * @param n
	 *            要减少的小时数
	 * @return 指定日期减少小时数后Date
	 */
	public static Date delHour(Date date,int n){
		cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR,n);
		return cal.getTime();
	}

	/**
	 * 在日期上减少分钟数
	 * 
	 * @param date
	 *            日期
	 * @param n
	 *            要减少的分钟数
	 * @return 指定日期减少分钟数后Date
	 */
	public static Date delMinute(Date date,int n){
		cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE,n);
		return cal.getTime();
	}

	/**
	 * 在日期上减少秒数
	 * 
	 * @param date
	 *            日期
	 * @param n
	 *            要减少的秒数
	 * @return 指定日期减少秒数后Date
	 */
	public static Date delSecond(Date date,int n){
		cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND,n);
		return cal.getTime();
	}

	/**
	 * 返回当前年份
	 * 
	 * @param date
	 *            日期
	 * @return 指定日期年份整型
	 */
	public static Integer getYear(){
		return getYear(new Date());
	}

	/**
	 * 返回年份
	 * 
	 * @param date
	 *            日期
	 * @return 指定日期年份整型
	 */
	public static Integer getYear(Date date){
		cal=Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 返回当前月份
	 * 
	 * @param date
	 *            日期
	 * @return 指定日期月份整型
	 */
	public static Integer getMonth(){
		return getMonth(new Date());
	}

	/**
	 * 返回月份
	 * 
	 * @param date
	 *            日期
	 * @return 指定日期月份整型
	 */
	public static Integer getMonth(Date date){
		cal=Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH)+1;
	}

	/**
	 * 返回当前日
	 * 
	 * @param date
	 *            日期
	 * @return 指定日期日整型
	 */
	public static Integer getDay(){
		return getDay(new Date());
	}

	/**
	 * 返回日
	 * 
	 * @param date
	 *            日期
	 * @return 指定日期日整型
	 */
	public static Integer getDay(Date date){
		cal=Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DATE);
	}

	/**
	 * 返回当前小时
	 * 
	 * @param date
	 *            日期
	 * @return 指定日期小时整型
	 */
	public static Integer getHour(){
		return getHour(new Date());
	}

	/**
	 * 返回小时
	 * 
	 * @param date
	 *            日期
	 * @return 指定日期小时整型
	 */
	public static Integer getHour(Date date){
		cal=Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 返回当前分钟
	 * 
	 * @param date
	 *            日期
	 * @return 指定日期分钟整型
	 */
	public static Integer getMinute(){
		return getMinute(new Date());
	}

	/**
	 * 返回分钟
	 * 
	 * @param date
	 *            日期
	 * @return 指定日期分钟整型
	 */
	public static Integer getMinute(Date date){
		cal=Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MINUTE);
	}

	/**
	 * 返回当前秒钟
	 * 
	 * @param date
	 *            日期
	 * @return 指定日期秒钟整型
	 */
	public static Integer getSecond(){
		return getSecond(new Date());
	}

	/**
	 * 返回秒钟
	 * 
	 * @param date
	 *            日期
	 * @return 指定日期秒钟整型
	 */
	public static Integer getSecond(Date date){
		cal=Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.SECOND);
	}

	/**
	 * 返回当前毫秒
	 * 
	 * @param date
	 *            日期
	 * @return 指定日期毫秒整型
	 */
	public static Integer getMilliSecond(){
		return getMilliSecond(new Date());
	}

	/**
	 * 返回毫秒
	 * 
	 * @param date
	 *            日期
	 * @return 指定日期毫秒整型
	 */
	public static Integer getMilliSecond(Date date){
		cal=Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MILLISECOND);
	}

	/**
	 * 按默认格式的字符串距离今天的天数
	 * 
	 * @param date
	 *            日期字符串
	 * @return 默认格式距离天数:0[不大于1天],负数则大于当前天数,正数则小宇当前天数
	 */
	public static int countDays(String date){
		long t=Calendar.getInstance().getTime().getTime();
		cal=Calendar.getInstance();
		cal.setTime(parse(date));
		long t1=cal.getTime().getTime();
		return (int)((t-t1)/(1000*3600*24));
	}

	/**
	 * 按用户格式字符串距离今天的天数
	 * 
	 * @param date
	 *            日期字符串
	 * @param pattern
	 *            日期格式
	 * @return 指定格式距离天数:0[不大于1天],负数则大于当前天数,正数则小宇当前天数
	 */
	public static int countDays(String date,String pattern){
		long t=Calendar.getInstance().getTime().getTime();
		cal=Calendar.getInstance();
		cal.setTime(parse(date,pattern));
		long t1=cal.getTime().getTime();
		return (int)((t-t1)/(1000*3600*24));
	}

	/**
	 * 判断是否是润年
	 * 
	 * @param date
	 *            日期
	 * @return true:是润年,false:不是润年
	 */
	public static boolean isLeapYear(Date date){
		cal=Calendar.getInstance();
		cal.setTime(date);
		return isLeapYear(cal.get(Calendar.YEAR));
	}

	/**
	 * 判断是否是润年
	 * 
	 * @param year
	 *            指定的年
	 * @return true:是润年,false:不是润年
	 */
	public static boolean isLeapYear(int year){
		GregorianCalendar calendar=new GregorianCalendar();
		return calendar.isLeapYear(year);
	}

	/**
	 * 判断指定的时间是否是今天
	 * 
	 * @param date
	 *            日期
	 * @return true:是今天,false:非今天
	 */
	public static boolean isInToday(Date d){
		return isSameDay(d,new Date());
	}

	/**
	 * 判断两时间是否是同一天
	 * 
	 * @param from
	 *            第一个时间点
	 * @param to
	 *            第二个时间点
	 * @return true:是同一天,false:非同一天
	 */
	public static boolean isSameDay(Date from,Date to){
		return format(from,yyyyMMddFormat).equals(format(to,yyyyMMddFormat));
	}

	/**
	 * 判断指定时间是否是周末
	 * 
	 * @param date
	 *            日期
	 * @return true:是周末,false:非周末
	 */
	public static boolean isWeeks(Date date){
		boolean isWeek=false;
		isWeek=(getWeek(date)-1==0||getWeek(date)-1==6);
		return isWeek;
	}

	/**
	 * 返回本月第一天日期
	 * 
	 * @param date
	 *            日期
	 * @return 当月第一天的日期
	 */
	public static Date getMonthFirstDay(){
		return getMonthFirstDay(new Date());
	}

	/**
	 * 返回该月第一天日期
	 * 
	 * @param date
	 *            日期
	 * @return 该月第一天的日期
	 */
	public static Date getMonthFirstDay(Date date){
		cal=Calendar.getInstance();
		cal.setTime(date);
		// 将日期设置为本月第一天
		cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),1);
		cal.set(Calendar.AM_PM,Calendar.HOUR);
		cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		return cal.getTime();
	}

	/**
	 * 返回当月最后一天日期
	 * 
	 * @param date
	 *            日期
	 * @return 当月最后一天的日期
	 */
	public static Date getMonthLastDay(){
		return getMonthLastDay(new Date());
	}

	/**
	 * 返回该月最后一天日期
	 * 
	 * @param date
	 *            日期
	 * @return 该月最后一天的日期
	 */
	public static Date getMonthLastDay(Date date){
		cal=Calendar.getInstance();
		cal.setTime(date);
		// 将日期设置为下一月第
		cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1,1);
		cal.set(Calendar.AM_PM,Calendar.HOUR);
		cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		// 减去1天，得到的即本月的最后一
		cal.add(Calendar.DATE,-1);
		return cal.getTime();
	}

	/**
	 * 得到本旬第一天
	 * 
	 * @param date
	 *            日期
	 * @return 本旬第一天的日期
	 */
	public static Date getFirstTenDay(){
		return getFirstTenDay(new Date());
	}

	/**
	 * 得到该旬第一天
	 * 
	 * @param date
	 *            日期
	 * @return 该旬第一天的日期
	 */
	public static Date getFirstTenDay(Date date){
		cal=Calendar.getInstance();
		cal.setTime(date);
		// 将日期设置为本月第一天
		cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),((cal.get(Calendar.DAY_OF_MONTH)-1)/10)*10+1);
		cal.set(Calendar.AM_PM,Calendar.HOUR);
		cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		return cal.getTime();
	}

	/**
	 * 求出指定时间那天是星期几
	 * 
	 * @param date
	 *            日期
	 * @return 星期1-7
	 */
	public static int getWeek(Date date){
		cal=Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 求出指定的时间那天是星期几
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            指定格式
	 * @return 星期几
	 */
	public static String getWeekString(Date date,String[] pattern){
		return pattern[getWeek(date)-1];
	}

	/**
	 * 取得指定时间离现在是多少时间以前，如：3秒前,2小时前等
	 * 
	 * @param date
	 *            日期
	 * @return 时间段描述
	 */

	public static String getAgoTimeString(Date date){
		cal=Calendar.getInstance();
		cal.setTime(date);
		Date agoTime=cal.getTime();
		Date now=new Date();
		long mtime=now.getTime()-agoTime.getTime();
		String str="";
		long stime=mtime/1000;
		long minute=60;
		long hour=60*60;
		long day=24*60*60;
		long weeks=7*24*60*60;
		long months=100*24*60*60;
		if(stime<minute){
			long time_value=stime;
			if(time_value<=0)
				time_value=1;
			str=time_value+"秒前";
		}else if(stime>=minute&&stime<hour){
			long time_value=stime/minute;
			if(time_value<=0)
				time_value=1;
			str=time_value+"分前";
		}else if(stime>=hour&&stime<day){
			long time_value=stime/hour;
			if(time_value<=0)
				time_value=1;
			str=time_value+"小时前";
		}else if(stime>=day&&stime<weeks){
			long time_value=stime/day;
			if(time_value<=0)
				time_value=1;
			str=time_value+"天前";
		}else if(stime>=weeks&&stime<months){
			long time_value=stime/weeks;
			if(time_value<=0)
				time_value=1;
			str=time_value+"星期前";
		}else{
			long time_value=stime/months;
			if(time_value<=0)
				time_value=1;
			str=time_value+"月前";
		}
		return str;
	}

	/**
	 * 得到今天的开始时间
	 * 
	 * @return 今天的开始时间
	 */
	public static Date getTodayBeginTime(){
		cal=Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.AM_PM,Calendar.HOUR);
		cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		cal.add(0,Calendar.DATE);
		return cal.getTime();
	}

	/**
	 * 得到今天的结束时间
	 * 
	 * @return 今天的结束时间
	 */
	public static Date getTodayEndTime(){
		cal=Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.AM_PM,Calendar.HOUR);
		cal.set(Calendar.HOUR_OF_DAY,23);
		cal.set(Calendar.MINUTE,59);
		cal.set(Calendar.SECOND,59);
		cal.add(0,Calendar.DATE);
		return cal.getTime();
	}

	/**
	 * 取得本周的开始时间
	 * 
	 * @return 本周的开始时间
	 */
	public static Date getThisWeekBeginTime(){
		cal=Calendar.getInstance();
		int week=getWeek(cal.getTime());
		week=week-1;
		int days=0;
		if(week==0)
			days=6;
		else
			days=week-1;
		cal.add(Calendar.DAY_OF_MONTH,-days);
		return parse(format(cal.getTime(),yyyyMMddFormat)+" 00:00:00");
	}

	/**
	 * 取得本周的开始时间
	 * 
	 * @param pattern
	 *            日期格式
	 * @return 指定格式的本周最开始时间
	 */
	public static Date getThisWeekBeginTime(String pattern){
		return parse(format(getThisWeekBeginTime(),pattern),pattern);
	}

	/**
	 * 取得本周的结束时间
	 * 
	 * @return 本周的结束时间
	 */
	public static Date getThisWeekEndTime(){
		cal=Calendar.getInstance();
		int week=getWeek(cal.getTime());
		week=week-1;
		int days=0;
		if(week!=0)
			days=7-week;
		cal.add(Calendar.DAY_OF_MONTH,days);
		return parse(format(cal.getTime(),yyyyMMddFormat)+" 23:59:59");
	}

	/**
	 * 取得本周的结束时间
	 * 
	 * @param pattern
	 *            日期格式
	 * @return 指定格式的本周最结束时间
	 */
	public static Date getThisWeekEndTime(String pattern){
		return parse(format(getThisWeekEndTime(),pattern),pattern);
	}

}