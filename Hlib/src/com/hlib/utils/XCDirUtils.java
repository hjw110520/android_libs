package com.hlib.utils;

import java.io.File;

import android.content.Context;
import android.os.Environment;

/**
 * @author haojinwei
 * 文件目录处理
 */
public  class XCDirUtils {
	/**
	 * 获取更目录
	 * @param context
	 * @param dirMode
	 * @return
	 */
	public static final File getRootDir(Context context,DirMode dirMode) {
		File rootDir = null;
		switch (dirMode) {
			case DirMode_SDCARD:
				if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
					rootDir = Environment.getExternalStorageDirectory(); //sdcard root path /mnt/sdcord/
				}
				break;
			case DirMode_SYSCACHE:
				rootDir = context.getCacheDir(); //默认存储path
				break;
			case DirMode_COMPATIBLE:
				if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
					rootDir = Environment.getExternalStorageDirectory(); //sdcard root path /mnt/sdcord/
				}else{
					rootDir = context.getCacheDir(); //默认存储path
				}
				break;
			default:
				break;
		}
		return rootDir;
	}
	
	/**
	 * 获取dir
	 * @param context
	 * @param dirMode
	 * @param dirName
	 * @return
	 */
	public static final File getOrMkDir(Context context,DirMode dirMode,String dirName) {
		File root = getRootDir(context,dirMode);
		File returnDir = null;
		if(null != root){
			returnDir = new File(root.getAbsolutePath()+File.separator+dirName);
		}
		if (null != returnDir && !returnDir.exists()) {
			returnDir.mkdirs();
		}
		return returnDir;
	}
	
	public static final File getOrMkDir(Context context,String dirName) {
		return getOrMkDir(context,DirMode.DirMode_COMPATIBLE, dirName);
	}
	
	public enum DirMode{
		 DirMode_SDCARD,//存储到sdcard
		 DirMode_SYSCACHE, //存储到系统目录
		 DirMode_COMPATIBLE;//优先存储到sdcard，无sdcard存储到系统目录
	}

}
