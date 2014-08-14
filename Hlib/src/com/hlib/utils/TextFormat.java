package com.hlib.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 内容校验
 * 
 * @author haojinwei
 * 
 */
public class TextFormat {

	public enum FormatType {
		TelePhone, MobilePhone, EMail, Postcode, WebQQ, Decimal, IntegerBy5, CustName
	}

	/**
	 * 校验数据格式是否合法
	 * 
	 * @param type
	 * @param verifyParam
	 * @return
	 */
	public static boolean isLegal(FormatType type, String verifyParam) {
		Pattern pat = null;
		Matcher mat = null;
		if (null != type) {
			if (null == getRegex(type)) {
				return false;
			}
			pat = Pattern.compile(getRegex(type));
			mat = pat.matcher(verifyParam);
			if (mat.find()) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * 获取校验规则
	 * 
	 * @param type
	 * @return
	 */
	public static String getRegex(FormatType type) {
		if (FormatType.MobilePhone.equals(type)) {// 手机号
			return "^1[0-9]{10}$";
		} else if (FormatType.TelePhone.equals(type)) {// 电话号
			return "^(\\d){3,4}((-)?)(\\d){7,8}$";
		} else if (FormatType.Postcode.equals(type)) {// 邮编
			return "^[0-9]{1}(\\d+){5}$";
		} else if (FormatType.EMail.equals(type)) {// 邮箱
			return "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
		} else if (FormatType.WebQQ.equals(type)) {// QQ
			return "^[1-9]{1}(\\d+){8,9}$";
		} else if (FormatType.Decimal.equals(type)) {// 两位小数
			return "^[0-9]+(.[0-9]{1,2})?$";
		} else if (FormatType.IntegerBy5.equals(type)) {// 五位整数
			return "^[1-9][0-9]{0,4}$";
		} else if (FormatType.CustName.equals(type)) { // 客户姓名，只能为汉字和字母
			return "^[A-Za-z\u4e00-\u9fa5\\s]+$";
		}
		return null;
	}
}
