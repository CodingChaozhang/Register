package com.lcz.register.utils;
/**
 * 生成随机字符串工具类
 */
import java.util.UUID;

public class CodeUtil {
	//生成唯一的激活码
	public static String generateUniqueCode(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
