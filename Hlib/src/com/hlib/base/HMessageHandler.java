package com.hlib.base;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.WeakHashMap;

import com.hlib.base.model.HMessage;


public class HMessageHandler {
	private static HMessageHandler instance;
	
	private WeakHashMap<String, ArrayList<WeakReference<OnReceiveMessageListener>>> msgHandlerMap = new WeakHashMap<String, ArrayList<WeakReference<OnReceiveMessageListener>>>();
	
	public static HMessageHandler getInstance(){
		if(instance == null){
			instance = new HMessageHandler();
		}
		return instance;
	}
	
	public void addMsgHandler(String msgKey, OnReceiveMessageListener msgHandler) {
		if (null == msgKey || null == msgHandler) {
			return;
		}
		ArrayList<WeakReference<OnReceiveMessageListener>> list = msgHandlerMap.get(msgKey);
		if (null == list) {
			list = new ArrayList<WeakReference<OnReceiveMessageListener>>();
			msgHandlerMap.put(msgKey, list);
		}
		int index = list.indexOf(msgHandler);
		if (-1 == index) {
			list.add(new WeakReference<OnReceiveMessageListener>(msgHandler));
		}
	}
	
	public void removeMsgHandler(OnReceiveMessageListener msgHandler) {
		if (null == msgHandler) {
			return;
		}
		Collection<ArrayList<WeakReference<OnReceiveMessageListener>>> values = msgHandlerMap
				.values();
		for (ArrayList<WeakReference<OnReceiveMessageListener>> list : values) {
			if (null == list || 0 == list.size()) {
				continue;
			}
			
			int index = list.indexOf(msgHandler);
			if (-1 != index) {
				list.remove(msgHandler);
			}
		}
	}
	
	public void pushMsg(String msgKey, HMessage msg) {
		ArrayList<WeakReference<OnReceiveMessageListener>> list = msgHandlerMap.get(msgKey);
		if (null == list) {
			return;
		}

		for (WeakReference<OnReceiveMessageListener> item : list) {
			OnReceiveMessageListener handler = item.get();
			if(null != handler){
				handler.onReceiveMessage(msg);
			}
		}
	}
}
