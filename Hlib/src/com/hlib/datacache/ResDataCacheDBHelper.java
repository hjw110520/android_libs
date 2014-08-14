package com.hlib.datacache;

import java.util.Date;

import android.database.sqlite.SQLiteDatabase;

import com.hlib.datacache.model.table.DataCache;
import com.hlib.utils.ObjectTraskit;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

/**
 * 暂存的网点本地数据操作工�?
 * 
 * @author administrator
 * 
 */
public class ResDataCacheDBHelper {
	//100天有�?
	public static final long CacheValideTime_Forever = 100*24*3600*1000;
	//1天有�?
	public static final long CacheValideTime_OneDay = 24*3600*1000;
	//1小时有效
	public static final long CacheValideTime_OneHour = 3600*1000;
	//半小时有�?
	public static final long CacheValideTime_Minute30 = 1800*1000;
	//10分钟有效
	public static final long CacheValideTime_Minute10 = 600*1000;
	//5分钟有效
	public static final long CacheValideTime_Minute5 = 300*1000;
	//1分钟有效
	public static final long CacheValideTime_Minute1 = 60*1000;
		
	private SQLiteDatabase db = null;
	private static ResDataCacheDBHelper helper;
	public static final int ApplyStatusLocalSaveValue = -1000;
	
	private DbUtils dbUtils;
	public static ResDataCacheDBHelper getInstance() {
		if (null == helper) {
			helper = new ResDataCacheDBHelper();
		}
		return helper;
	}

	private ResDataCacheDBHelper() {
		dbUtils = DBHandler.getDbUtils();
	}
	
	/**
	 * 存储缓存数据
	 * @param reqParamMD5Key
	 * @param resData
	 * @return
	 */
	public boolean saveOrUpdateResponseData(String reqParamMD5Key, Object resData) {
		if (null == reqParamMD5Key || null == resData) {
			return false;
		}
		DataCache dc = new DataCache();
		dc.setReqParamMD5Key(reqParamMD5Key);
		dc.setInsertTime(new Date().getTime());
		dc.setCachedata(ObjectTraskit.trans2Bytes(resData));
		try {
			dbUtils.saveOrUpdate(dc);
			return true;
		} catch (DbException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 获取缓存数据
	 * @param reqParamMD5Key
	 * @param valideTimeLong
	 * @return
	 */
	public Object getResponseData(String reqParamMD5Key, long valideTimeLong) {
		if (null == reqParamMD5Key) {
			return null;
		}
		Object resData = null;
		try {
			DataCache dc = dbUtils.findFirst(Selector.from(DataCache.class).where("reqParamMD5Key","=",reqParamMD5Key).and("insertTime",">",valideTimeLong));
			resData = ObjectTraskit.trans2Object(dc.getCachedata());
		} catch (DbException e) {
			e.printStackTrace();
		}
		
		return resData;
	}
	
	/**
	 * 删除缓存数据
	 * @param reqParamMD5Key
	 */
	public void deleteResponseData(String reqParamMD5Key) {
		if (null == reqParamMD5Key) {
			return;
		}
		try {
			dbUtils.deleteById(DataCache.class, reqParamMD5Key);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}
}
