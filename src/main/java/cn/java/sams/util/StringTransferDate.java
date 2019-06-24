package cn.java.sams.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringTransferDate {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 将字符串转换成日期
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Date transfer(String str){
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 将日期转换成字符串
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String dateTransferString(Date date) {
		return sdf.format(date);
	}
}
