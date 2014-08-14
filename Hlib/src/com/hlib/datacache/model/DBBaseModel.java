package com.hlib.datacache.model;



public class DBBaseModel {
	private String dbName;//数据库名称
	private String dbDir;//数据库路径
	private boolean debugAble;//是否调试
	private Integer currentVersion;//当前版本
	private boolean isCipher = false;//是否加密
	
	
	public boolean isCipher() {
		return isCipher;
	}
	public void setCipher(boolean isCipher) {
		this.isCipher = isCipher;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getDbDir() {
		return dbDir;
	}
	public void setDbDir(String dbDir) {
		this.dbDir = dbDir;
	}
	public boolean isDebugAble() {
		return debugAble;
	}
	public void setDebugAble(boolean debugAble) {
		this.debugAble = debugAble;
	}
	public Integer getCurrentVersion() {
		return currentVersion;
	}
	public void setCurrentVersion(Integer currentVersion) {
		this.currentVersion = currentVersion;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" dbName : "+dbName +"\n");
		sb.append(" dbDir : "+dbDir+"\n");
		sb.append(" debugAble : "+debugAble+"\n");
		sb.append(" currentVersion : "+currentVersion);
		return sb.toString();
	}
}
