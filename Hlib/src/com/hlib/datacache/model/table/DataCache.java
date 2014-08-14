package com.hlib.datacache.model.table;

import java.io.Serializable;
import java.sql.Blob;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

@Table(name = "data_cache")
public class DataCache implements Serializable{
	private static final long serialVersionUID = -227687600871401659L;

	@Id
	private String reqParamMD5Key;
	
	@Column(defaultValue = "0",column="insertTime")
	private long insertTime;
	
	@Column(defaultValue = "0",column="cacheData")
	private byte[] cachedata;

	public String getReqParamMD5Key() {
		return reqParamMD5Key;
	}

	public void setReqParamMD5Key(String reqParamMD5Key) {
		this.reqParamMD5Key = reqParamMD5Key;
	}

	public long getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(long insertTime) {
		this.insertTime = insertTime;
	}


	public void setCachedata(byte[] cachedata) {
		this.cachedata = cachedata;
	}

	public byte[] getCachedata() {
		return cachedata;
	}
	
	
	
	
}
