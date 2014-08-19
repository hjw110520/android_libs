package com.busunit.base;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;
/**
 * 移动设备异常信息
 *
 * @author zongyao.jin
 */

@Table(name = "crashlog")
public class EmsMobiErrorLog implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3356293404748012658L;
	
	@Id
	private int id;
	
	private String errorId;
	private String sendTime;
	private String errorTime;
	private String loginName;
	private String osType;//2 android
	private String sysVersion;
	private String appVersion;
	private String sysDeviceType;
	private String density;
	private String message;
	
	private String deviceCode=""; 
	
	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	
	public String getErrorTime() {
		return errorTime;
	}

	public void setErrorTime(String errorTime) {
		this.errorTime = errorTime;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getErrorId() {
		return errorId;
	}

	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public String getSysVersion() {
		return sysVersion;
	}

	public void setSysVersion(String sysVersion) {
		this.sysVersion = sysVersion;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getSysDeviceType() {
		return sysDeviceType;
	}

	public void setSysDeviceType(String sysDeviceType) {
		this.sysDeviceType = sysDeviceType;
	}

	public String getDensity() {
		return density;
	}

	public void setDensity(String density) {
		this.density = density;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

