package com.zhangjingjie.cms.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
    * @ClassName: Md5Util
    * @Description: TODO(对密码进行加密处理)
    * @author 张经杰
    * @date 2020年3月12日
    *
 */
public class Md5Util {
	//增加密码的安全系数
	private static String salt="zyj";
	
	public static String encode(String password) {
		String md5Hex = DigestUtils.md5Hex(password+salt);
		return md5Hex;
	}
}
