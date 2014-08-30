package com.hlib.base.model;

public class HMessage {
	private String msgKey;
	private Object msg;
	public String getMsgKey() {
		return msgKey;
	}
	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}
	public Object getMsg() {
		return msg;
	}
	public void setMsg(Object msg) {
		this.msg = msg;
	}
	
	public void put(String msgKey,Object msg){
		this.msgKey = msgKey;
		this.msg = msg;
	}
	
}
