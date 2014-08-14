package com.hlib.datacache.model;

import java.util.ArrayList;

public class DBVersion {
	private Integer version;//版本号码
	private ArrayList<String> sqls = new ArrayList<String>();//sql语句
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public ArrayList<String> getSqls() {
		return sqls;
	}
	public void setSqls(ArrayList<String> sqls) {
		this.sqls = sqls;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" version : "+version+"\n");
		sb.append(" sqls : 总共"+sqls.size()+"条"+"\n");
		for (String sql : sqls) {
			sb.append(" sql : "+sql+"\n");
		}
		return sb.toString();
	}
}
