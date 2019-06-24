package cn.java.sams.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
	private static final String KEY = "ssjweb@163.com";
	
	public static String md5Encrypt(String password) {
		password = password == null?"":password+KEY;
		char[] md5Digist = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};//字典
		try {//使用MD5加密算法加密
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] b= password.getBytes();//将新的字符串转换为byte数组
			md.update(b);
			byte[] mssarr = md.digest();//真正加密 用一个byte数组接收加密后的类容
			
			int len = mssarr.length;
			char[] str = new char[len*2];
			int k = 0;
			for(int i = 0; i < len ; i++) {
				byte by = mssarr[i];
				str[k++] = md5Digist[(by >> 4) & 0xf];
				str[k++] = md5Digist[by & 0xf];
			}
			return new String(str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	public static String md5(String str) {
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");				
				md.update(str.getBytes());
			byte[] rs = md.digest();
			return new BigInteger(1,rs).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
public static String md5(String str,String str1) {
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			md.update(str1.getBytes());
			byte[] rs = md.digest();
			return new BigInteger(1,rs).toString(16);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
