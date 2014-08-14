package com.hlib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.view.Display;

public class Toolkit {
	
	public static int px2dip(float pxValue,Context context) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static int dip2px(float dipValue,Context context) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	public static int px2sp(float pxValue,Context context) {
		final float scale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / scale + 0.5f);
	}
	

	public static int sp2px(float spValue,Context context) {
		final float scale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * scale + 0.5f);
	}
	
	// 获取ip
	public static String getLocalIpAddress(Context context) {
		// 获取wifi服务
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		// 判断wifi是否开启
		if (!wifiManager.isWifiEnabled()) {
			wifiManager.setWifiEnabled(true);
		}
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		int ipAddress = wifiInfo.getIpAddress();
		String ip = (ipAddress & 0xFF) + "." + ((ipAddress >> 8) & 0xFF) + "." 
				+ ((ipAddress >> 16) & 0xFF)+ "." + (ipAddress >> 24 & 0xFF);
		return ip;
	}

	// 获取到客户ID，即IMSI
	public static String getIMSI(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getSubscriberId();
	}

	// 获取当前应用的版本号：
	public static String getVersion(Context context) {
		// 获取packagemanager的实例
		PackageManager packageManager = context.getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo = null;
		try {
			packInfo = packageManager.getPackageInfo(context.getPackageName(),
					0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		if (null != packInfo) {
			return packInfo.versionName;
		} else {
			return "";
		}
	}

	// 获取手机的IMEI号码
	public static String getIMEI(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getDeviceId();
	}

	// 获取手机号码
	public static String getPhoneNumber(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getLine1Number();
	}

	// 获取手机的android操作系统版本
	public static String getDeviceSoftwareVersion() {
		return "" + android.os.Build.VERSION.RELEASE;
	}
	
	// 获取手机机型信息
	public static String getDeviceType() {
		return  android.os.Build.MODEL;//机型
	}

	// 获取到SIM卡唯一编号ID
	public static String getSimSerialNumber(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getSimSerialNumber();
	}

	// 从文件路径中获得文件名
	public static String getFileName(String apath) {
		int start = apath.lastIndexOf("/");
		int end = apath.lastIndexOf(".");
		if (start != -1 && end != -1) {
			return apath.substring(start + 1);
		} else {
			return null;
		}
	}

	// 获得屏幕宽度
	public static int getScreenWidth(Context context) {
		return context.getApplicationContext().getResources()
				.getDisplayMetrics().widthPixels;
	}

	// 获得屏幕宽度
	public static int getScreenHeight(Context context) {
		return context.getApplicationContext().getResources()
				.getDisplayMetrics().heightPixels
				- dip2px(20,context);// 天线高度
	}
	
	// 获取屏幕分辨率
	public static String getResolution(Activity activity) {
		Display mDisplay = activity.getWindowManager().getDefaultDisplay();
		int W = mDisplay.getWidth();
		int H = mDisplay.getHeight();
		return ""+W+"*"+H;
	}
}