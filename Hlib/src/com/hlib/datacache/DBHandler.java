package com.hlib.datacache;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.res.XmlResourceParser;

import com.hlib.R;
import com.hlib.base.HApplication;
import com.hlib.datacache.model.DBBaseModel;
import com.hlib.datacache.model.DBVersion;
import com.hlib.utils.XCDirUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.DbUtils1;
import com.lidroid.xutils.DbUtils2;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.LogUtils;
/**
 * 数据库帮助类
 * @author haojinwei
 *
 */
public class DBHandler {
	
	private DbUtils dbUtils;//本地缓存数据库函链接，需要加密存储
	private ArrayList<DBVersion> versions;
	private DBBaseModel dbBaseModel = null;
	private Context context;
	
	private static DBHandler instance = null;
	public static DBHandler getInstance() {
		if (null == instance) {
			instance = new DBHandler();
		}
		return instance;
	}
	
	private DBHandler (){
		this.context = HApplication.getInstance();
		initDbUtils();
	}
	
	public static DbUtils getDbUtils() {
		return getInstance().dbUtils;
	}
	
	/**
	 * 初始化本地缓存数据库
	 */
	private void initDbUtils() {
		try {
			initDatabaseSettings(context);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		}
		if(null == dbBaseModel){
			return;
		}
		
		try {
			if(dbBaseModel.isCipher()){//创建加密的dbutils
				dbUtils = DbUtils2.create(context,dbBaseModel.getDbDir(),
											dbBaseModel.getDbName(),
											dbBaseModel.getCurrentVersion(),
											new localDbUpgradeListener());
			}else{//创建不加密的dbutils
				dbUtils = DbUtils1.create(context,dbBaseModel.getDbDir(),
											dbBaseModel.getDbName(),
											dbBaseModel.getCurrentVersion(),
											new localDbUpgradeListener());
			}
			
			dbUtils.configDebug(dbBaseModel.isDebugAble());
			dbUtils.configAllowTransaction(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 缓存数据库更新监听
	 * @author haojinwei
	 *
	 */
	private class localDbUpgradeListener implements DbUtils.DbUpgradeListener{
		@Override
		public void onUpgrade(DbUtils db, int oldVersion, int newVersion) {
			if(oldVersion == newVersion){
				return ;
			}else{
				try {
					initDatabaseVersions(context, oldVersion, newVersion);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				}
				
				if(null != versions){
					//具体更新
					DBVersion dbVersion = null;
					for(int i = 0;i<versions.size();i++){
						dbVersion = null;
						dbVersion = versions.get(i);
						ArrayList<String> sqls = dbVersion.getSqls();
						for(int j = 0; j < sqls.size(); j++){
							try {
								db.execNonQuery(sqls.get(j));
							} catch (DbException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		
	}
	
	
	/**
	 * 读取数据库版本信息
	 * @param context
	 * @param oldVersion
	 * @param currentVersion
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	private void initDatabaseVersions(Context context,int oldVersion,int currentVersion) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		XmlResourceParser xrp = context.getResources().getXml(R.xml.database_setting);
		try {
			DBVersion dbVersion = null;
			versions = new ArrayList<DBVersion>();
            // 直到文档的结尾处
            while (xrp.getEventType() != XmlPullParser.END_DOCUMENT) {
            	String tagName = xrp.getName();
                switch (xrp.getEventType()) {
				case XmlPullParser.START_TAG:
                    // 获取标签的名字
                    if (tagName.equalsIgnoreCase("dbversion")) {
                        //数据库名称
                        int version = xrp.getAttributeIntValue(null, "version",0);
                        if(version > oldVersion){
                        	dbVersion = new DBVersion();
                        	dbVersion.setVersion(version);
                        }
                    }
                    if(tagName.equalsIgnoreCase("sql")){
                    	if(null != dbVersion){
                    		String sql = xrp.nextText();
                    		dbVersion.getSqls().add(sql);
                    	}
                    }
					break;
				case XmlPullParser.END_TAG:
					if (tagName.equalsIgnoreCase("dbversion")) {
						if(dbVersion != null){
							versions.add(dbVersion);
							dbVersion = null;
						}
					}
					break;
				default:
					break;
				}
                // 获取解析下一条记录
                xrp.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }	
	}
	
	/**
	 * 读取数据库设置
	 * @param context
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	private void initDatabaseSettings(Context context) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		XmlResourceParser xrp = context.getResources().getXml(R.xml.database_setting);
		try {
            // 直到文档的结尾处
            while (xrp.getEventType() != XmlPullParser.END_DOCUMENT) {
            	String tagName = xrp.getName();
                switch (xrp.getEventType()) {
				case XmlPullParser.START_TAG:
					int currentVersion = 1;
                    // 获取标签的名字
                    if (tagName.equalsIgnoreCase("baseinfo")) {
                    	dbBaseModel = new DBBaseModel();
                    	
                    	//LogUtils.d("1111111111111111 "+dbBaseModel.getDbDir());
                        //数据库名称
                        String dbName = xrp.getAttributeValue(null, "dbName");
                        dbBaseModel.setDbName(dbName);
                        // 数据库目录
                        //String dbDir = xrp.getAttributeValue(null, "dbDir");
                        //dbBaseModel.setDbDir(dbDir);
                        dbBaseModel.setDbDir(XCDirUtils.getOrMkDir(context, "datacache").getAbsolutePath()+File.separator);
                        // 是否打印调试语句
                        boolean debugAble = xrp.getAttributeBooleanValue(null, "debugAble", false);
                        dbBaseModel.setDebugAble(debugAble);
                        // 通过属性名来获取属性值:数据库当前版本
                        currentVersion = xrp.getAttributeIntValue(null, "currentVersion",1);
                        dbBaseModel.setCurrentVersion(currentVersion);
                        
                        boolean isCipher = xrp.getAttributeBooleanValue(null, "isCipher",false);
                        dbBaseModel.setCipher(isCipher);
                    }
					break;
				default:
					break;
				}
                // 获取解析下一条记录
                xrp.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }	
	}
}
