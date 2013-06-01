package com.asiainfo.webapp.util;

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asiainfo.appframe.util.Dates;

public class DatesTest{

	protected final Logger logger=LoggerFactory.getLogger(getClass());

	/**
	 * 获取默认日期格式
	 * 
	 * @return 默认格式字符串 yyyy-MM-dd HH:mm:ss
	 */
	@Test
	public void getDefaultPattern(){
		System.out.println("getDefaultPattern::"+Dates.getDefaultDatePattern());
	}

	/**
	 * 使用预设格式格式化日期
	 * 
	 * @param 日期
	 * @return 默认格式的日期字符串 2012-10-24 21:22:34
	 */
	@Test
	public void format(){
		System.out.println("format::"+Dates.format(new Date()));
	}

	/**
	 * 使用用户格式格式化日期
	 * 
	 * @param 日期
	 * @param pattern
	 *            日期格式
	 * @return 指定格式的日期字符串
	 */
	@Test
	public void format_(){
		System.out.println("format_::"+Dates.format(new Date(),Dates.longFormat));
	}

	/**
	 * 根据预设格式返回当前日期
	 * 
	 * @return 默认格式的当前日期字符串 2012-10-24 21:22:34
	 */
	@Test
	public void getNow(){
		System.out.println("getNow::"+Dates.getNow());
	}

	/**
	 * 根据用户格式返回当前日期
	 * 
	 * @param pattern
	 *            日期格式
	 * @return 指定格式的当前日期字符串
	 */
	@Test
	public void getNow_(){
		System.out.println("getNow_::"+Dates.getNow(Dates.longFormat));
	}

	/**
	 * 使用预设格式解析字符串日期
	 * 
	 * @param 日期字符串
	 * @return 默认日期格式的 yyyy-MM-dd HH:mm:ss
	 */
	@Test
	public void parse(){
		System.out.println("parse::"+Dates.format(Dates.parse("2013-05-29 13:14:00")));
	}

	/**
	 * 使用指定格式解析字符串日期
	 * 
	 * @param 日期字符串
	 * @param pattern
	 *            日期格式
	 * @return 指定日期格式的
	 */
	@Test
	public void parse_(){
		System.out.println("parse_::"+Dates.format(Dates.parse("2013-05-29 13:14:00",Dates.longFormat)));
	}

	/**
	 * 在日期上增加年数
	 * 
	 * @param 日期
	 * @param n
	 *            要增加的年数
	 * @return 指定日期增加年数后
	 */
	@Test
	public void addYear(){
		System.out.println("addYear::"+Dates.format(Dates.addYear(new Date(),1)));
	}

	/**
	 * 在日期上增加月数
	 * 
	 * @param 日期
	 * @param n
	 *            要增加的月数
	 * @return 指定日期增加月数后
	 */
	@Test
	public void addMonth(){
		System.out.println("addMonth::"+Dates.format(Dates.addMonth(new Date(),10)));
	}

	/**
	 * 在日期上增加天数
	 * 
	 * @param 日期
	 * @param n
	 *            要增加的天数
	 * @return 指定日期增加天数后
	 */
	@Test
	public void addDay(){
		System.out.println("addDay::"+Dates.format(Dates.addDay(new Date(),12)));
	}

	/**
	 * 在日期上增加小时数
	 * 
	 * @param 日期
	 * @param n
	 *            要增加的小时数
	 * @return 指定日期增加小时数后
	 */
	@Test
	public void addHour(){
		System.out.println("addHour::"+Dates.format(Dates.addHour(new Date(),24)));
	}

	/**
	 * 在日期上增加分钟数
	 * 
	 * @param 日期
	 * @param n
	 *            要增加的分钟数
	 * @return 指定日期增加分钟数后
	 */
	@Test
	public void addMinute(){
		System.out.println("addMinute::"+Dates.format(Dates.addMinute(new Date(),30)));
	}

	/**
	 * 在日期上增加秒数
	 * 
	 * @param 日期
	 * @param n
	 *            要增加的秒数
	 * @return 指定日期增加秒数后
	 */
	@Test
	public void addSecond(){
		System.out.println("addSecond::"+Dates.format(Dates.addSecond(new Date(),30)));
	}

	/**
	 * 在日期上减少年数
	 * 
	 * @param 日期
	 * @param n
	 *            要减少的年数
	 * @return 指定日期减少年数后
	 */
	@Test
	public void delYear(){
		System.out.println("delYear::"+Dates.format(Dates.delYear(new Date(),30)));
	}

	/**
	 * 在日期上减少月数
	 * 
	 * @param 日期
	 * @param n
	 *            要减少的月数
	 * @return 指定日期减少月数后
	 */
	@Test
	public void delMonth(){
		System.out.println("delYear::"+Dates.format(Dates.delMonth(new Date(),30)));
	}

	/**
	 * 在日期上减少天数
	 * 
	 * @param 日期
	 * @param n
	 *            要减少的天数
	 * @return 指定日期减少天数后
	 */
	@Test
	public void delDay(){
		System.out.println("delDay::"+Dates.format(Dates.delDay(new Date(),30)));
	}

	/**
	 * 在日期上减少小时数
	 * 
	 * @param 日期
	 * @param n
	 *            要减少的小时数
	 * @return 指定日期减少小时数后
	 */
	@Test
	public void delHour(){
		System.out.println("delHour::"+Dates.format(Dates.delHour(new Date(),30)));
	}

	/**
	 * 在日期上减少分钟数
	 * 
	 * @param 日期
	 * @param n
	 *            要减少的分钟数
	 * @return 指定日期减少分钟数后
	 */
	@Test
	public void delMinute(){
		System.out.println("delMinute::"+Dates.format(Dates.delMinute(new Date(),30)));
	}

	/**
	 * 在日期上减少秒数
	 * 
	 * @param 日期
	 * @param n
	 *            要减少的秒数
	 * @return 指定日期减少秒数后
	 */
	@Test
	public void delSecond(){
		System.out.println("delSecond::"+Dates.format(Dates.delSecond(new Date(),30)));
	}

	/**
	 * 返回年份
	 * 
	 * @param 日期
	 * @return 指定日期年份字符串
	 */
	@Test
	public void getYear(){
		System.out.println("getYear::"+Dates.getYear(new Date()));
	}

	/**
	 * 返回月份
	 * 
	 * @param 日期
	 * @return 指定日期月份字符串
	 */
	@Test
	public void getMonth(){
		System.out.println("getMonth::"+Dates.getMonth(new Date()));
	}

	/**
	 * 返回日
	 * 
	 * @param 日期
	 * @return 指定日期日字符串
	 */
	@Test
	public void getDay(){
		System.out.println("getDay::"+Dates.getDay(new Date()));
	}

	/**
	 * 返回小时
	 * 
	 * @param 日期
	 * @return 指定日期小时字符串
	 */
	@Test
	public void getHour(){
		System.out.println("getHour::"+Dates.getHour(new Date()));
	}

	/**
	 * 返回分钟
	 * 
	 * @param 日期
	 * @return 指定日期分钟字符串
	 */
	@Test
	public void getMinute(){
		System.out.println("getMinute::"+Dates.getMinute(new Date()));
	}

	/**
	 * 返回秒钟
	 * 
	 * @param 日期
	 * @return 指定日期秒钟字符串
	 */
	@Test
	public void getSecond(){
		System.out.println("getSecond::"+Dates.getSecond(new Date()));
	}

	/**
	 * 返回毫秒
	 * 
	 * @param 日期
	 * @return 指定日期毫秒字符串
	 */
	@Test
	public void getMilliSecond(){
		System.out.println("getMilliSecond::"+Dates.getMilliSecond(new Date()));
	}

	/**
	 * 按默认格式的字符串距离今天的天数
	 * 
	 * @param 日期字符串
	 * @return 默认格式距离天数:0[不大于1天],负数则大于当前天数,正数则小宇当前天数
	 */
	@Test
	public void countDays(){
		System.out.println("countDays::"+Dates.countDays("2013-05-29 00:00:00"));
	}

	/**
	 * 按用户格式字符串距离今天的天数
	 * 
	 * @param 日期字符串
	 * @param pattern
	 *            日期格式
	 * @return 指定格式距离天数:0[不大于1天],负数则大于当前天数,正数则小宇当前天数
	 */
	@Test
	public void countDays_(){
		System.out.println("countDays_::"+Dates.countDays("2013-05-29",Dates.yyyyMMddFormat));
	}

	/**
	 * 判断是否是润年
	 * 
	 * @param 日期
	 * @return true:是润年,false:不是润年
	 */
	@Test
	public void isLeapYear(){
		System.out.println("isLeapYear::"+(Dates.isLeapYear(new Date())==true?"闰年":"平年"));
	}

	/**
	 * 判断是否是润年
	 * 
	 * @param year
	 *            指定的年
	 * @return true:是润年,false:不是润年
	 */
	@Test
	public void isLeapYear_(){
		System.out.println("isLeapYear_::"+(Dates.isLeapYear(2013)==true?"闰年":"平年"));
	}

	/**
	 * 判断指定的时间是否是今天
	 * 
	 * @param 日期
	 * @return true:是今天,false:非今天
	 */
	@Test
	public void isInToday(){
		String date="2013-04-21";
		System.out.println("isInToday::"+(Dates.isInToday(Dates.parse(date,Dates.yyyyMMddFormat))==true?"是今天":"不是今天"));
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
	@Test
	public void isSameDay(){
		String date="2013-04-21";
		System.out.println("isSameDay::"+(Dates.isSameDay(new Date(),Dates.parse(date,Dates.yyyyMMddFormat))==true?"是同一天":"不是同一天"));
	}

	/**
	 * 求出指定的时间那天是星期几
	 * 
	 * @param 日期
	 * @param pattern
	 *            指定格式
	 * @return 星期几
	 */
	@Test
	public void getWeek(){
		System.out.println("getWeek::"+Dates.getWeek(new Date()));
	}

	/**
	 * 求出指定时间那天是星期几
	 * 
	 * @param 日期
	 * @return 星期1-7
	 */
	@Test
	public void getWeek_(){
		String[] fullWEEKS_CN={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
		System.out.println("getWeek_::"+Dates.getWeekString(new Date(),fullWEEKS_CN));
	}

	/**
	 * 判断指定时间是否是周末
	 * 
	 * @param 日期
	 * @return true:是周末,false:非周末
	 */
	@Test
	public void isWeeks(){
		System.out.println("isWeeks::"+(Dates.isWeeks(new Date())==true?"周末":"非周末"));
	}

	/**
	 * 取得指定时间离现在是多少时间以前，如：3秒前,2小时前等
	 * 
	 * @param 日期
	 * @return 时间段描述
	 */

	@Test
	public void getAgoTime(){
		System.out.println("getAgoTime::"+Dates.getAgoTimeString(new Date()));
	}

	/**
	 * 得到今天的开始时间
	 * 
	 * @return 今天的开始时间
	 */
	@Test
	public void getTodayBegime(){
		System.out.println("getTodayBegime::"+Dates.format(Dates.getTodayBeginTime()));
	}

	/**
	 * 得到今天的结束时间
	 * 
	 * @return 今天的结束时间
	 */
	@Test
	public void getTodayEndTime(){
		System.out.println("getTodayEndTime::"+Dates.format(Dates.getTodayEndTime()));
	}

	/**
	 * 取得本周的开始时间
	 * 
	 * @return 本周的开始时间
	 */
	@Test
	public void getThisWeekBegime(){
		System.out.println("getThisWeekBegime::"+Dates.format(Dates.getThisWeekBeginTime()));
	}

	/**
	 * 取得本周的开始时间
	 * 
	 * @param pattern
	 *            日期格式
	 * @return 指定格式的本周最开始时间
	 */
	@Test
	public void getThisWeekBegime_(){
		System.out.println("getThisWeekBegime_::"+Dates.format(Dates.getThisWeekBeginTime(Dates.longFormat_CN),Dates.longFormat_CN));
	}

	/**
	 * 取得本周的结束时间
	 * 
	 * @return 本周的结束时间
	 */
	@Test
	public void getThisWeekEndTime(){
		System.out.println("getThisWeekEndTime::"+Dates.format(Dates.getThisWeekEndTime()));
	}

	/**
	 * 取得本周的结束时间
	 * 
	 * @param pattern
	 *            日期格式
	 * @return 指定格式的本周最结束时间
	 */
	@Test
	public void getThisWeekEndTime_(){
		System.out.println("getThisWeekEndTime_::"+Dates.format(Dates.getThisWeekEndTime(Dates.longFormat_CN),Dates.longFormat_CN));
	}
}
