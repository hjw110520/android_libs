package com.hlib.utils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	
	/**
	 * 在字符串判断是否存在匹配的字符
	 * @param paramStr
	 *            要查找的字符串
	 * @param subStr
	 *            待匹配的字符
	 * @return
	 */
	public static boolean isExistSubString(String paramStr, String subStr) {
		if (null == paramStr || null == subStr) {
			return false;
		}
		if (paramStr.indexOf(subStr) >= 0) {
			return true;
		}
		return false;
	}

	public static boolean isNotBlank(String str) {
		if (null == str || "".equals(str)) {
			return false;
		} else if ("".equals(str.trim())) {
			return false;
		} else if ("".equals(replaceBlank(str))) {
			return false;
		}
		return true;
	}

	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\\\\r|\\\\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	public static String replaceSqlBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\\\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	public static String trimString(String str) {
		if (null != str) {
			return str.trim();
		}
		return str;
	}

	// Empty checks
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Checks if a String is empty ("") or null.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isEmpty(null)      = true
	 * StringUtils.isEmpty("")        = true
	 * StringUtils.isEmpty(" ")       = false
	 * StringUtils.isEmpty("bob")     = false
	 * StringUtils.isEmpty("  bob  ") = false
	 * </pre>
	 * 
	 * <p>
	 * NOTE: This method changed in Lang version 2.0. It no longer trims the
	 * String. That functionality is available in isBlank().
	 * </p>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if the String is empty or null
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * <p>
	 * Checks if a String is not empty ("") and not null.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isNotEmpty(null)      = false
	 * StringUtils.isNotEmpty("")        = false
	 * StringUtils.isNotEmpty(" ")       = true
	 * StringUtils.isNotEmpty("bob")     = true
	 * StringUtils.isNotEmpty("  bob  ") = true
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if the String is not empty and not null
	 */
	public static boolean isNotEmpty(String str) {
		return !StringUtils.isEmpty(str);
	}

	/**
	 * <p>
	 * Checks if a String is whitespace, empty ("") or null.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isBlank(null)      = true
	 * StringUtils.isBlank("")        = true
	 * StringUtils.isBlank(" ")       = true
	 * StringUtils.isBlank("bob")     = false
	 * StringUtils.isBlank("  bob  ") = false
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if the String is null, empty or whitespace
	 * @since 2.0
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Removes control characters (char &lt;= 32) from both ends of this String
	 * returning an empty String ("") if the String is empty ("") after the trim
	 * or if it is <code>null</code>.
	 * 
	 * <p>
	 * The String is trimmed using {@link String#trim()}. Trim removes start and
	 * end characters &lt;= 32. To strip whitespace use
	 * {@link #stripToEmpty(String)}.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.trimToEmpty(null)          = ""
	 * StringUtils.trimToEmpty("")            = ""
	 * StringUtils.trimToEmpty("     ")       = ""
	 * StringUtils.trimToEmpty("abc")         = "abc"
	 * StringUtils.trimToEmpty("    abc    ") = "abc"
	 * </pre>
	 * 
	 * @param str
	 *            the String to be trimmed, may be null
	 * @return the trimmed String, or an empty String if <code>null</code> input
	 * @since 2.0
	 */
	public static String trimToEmpty(String str) {
		if (str == null || str.equals("null")) {
			return "";
		}
		return str.trim();
	}

	/**
	 * 如果str 为 null ‘null’ 则返回""
	 * 
	 * @param str
	 *            nullToEmpty
	 * @return
	 */
	public static String nullToEmpty(String str) {
		if (StringUtils.isEmpty(str) || "null".equals(str)) {
			return "";
		}
		return str;
	}

	/**
	 * 半角转换为全角
	 * 
	 * @param input
	 * @return
	 */
	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	/**
	 * 去除特殊字符或将所有中文标号替换为英文标号
	 * 
	 * @param str
	 * @return
	 */
	public static String stringFilter(String str) {
		str = str.replaceAll("【", "[").replaceAll("】", "]")
				.replaceAll("！", "!").replaceAll("：", ":");// 替换中文标号
		String regEx = "[『』]"; // 清除掉特殊字符
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	public static String stringTrimAll(String str) {
		return stringFilter(ToDBC(replaceSqlBlank(str)));
	}

	/**
	 * 判断字符串数字是否为0
	 * @param str
	 * @return
	 */
	public static boolean isNumberNotZero(String str) {
		if (isNotBlank(str)) {
			try {
				Double i = Double.parseDouble(str);
				if (i != 0) {
					return true;
				}
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	/**
	 * 保留两位小数
	 * @param str
	 * @return
	 */
	public static String getTwoDecimal(String str) {
		if (isBlank(str)) {
			return "0";
		}
		String reuslt = "";
		try {
			BigDecimal bg = new BigDecimal(str);
			BigDecimal resValue = bg.setScale(2, BigDecimal.ROUND_HALF_UP);
			if (resValue.doubleValue() == 0) {
				reuslt = "0";
			} else {
				reuslt = resValue.toString();
			}
		} catch (Exception e) {
			reuslt = "";
		}
		return reuslt;
	}
	
	/**
	 * 保留最多两位小数
	 * @param str
	 * @return
	 */
	public static String getMoreTwoDecimal(String str) {
		String reuslt = "";
		try {
			BigDecimal bg = new BigDecimal(str);
			BigDecimal resValue = bg.setScale(2, BigDecimal.ROUND_HALF_UP);
			String de = resValue.toString();
			String decimaValue = de.split("\\.")[1];
			String intValue = de.split("\\.")[0];
			String t = decimaValue.replaceAll("0+$", "");
			if(t.equals("")){
				reuslt = intValue;
			}else{
				reuslt = intValue+"."+t;
			}
		} catch (Exception e) {
		}
		return reuslt;
	}
}
