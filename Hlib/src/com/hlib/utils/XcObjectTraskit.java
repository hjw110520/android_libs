package com.hlib.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

public class XcObjectTraskit {
	/**
	 * 将需要保存为BLOB类型的字段进行序列化
	 * 
	 * @param obj
	 * @return
	 */
	public static byte[] trans2Bytes(Object obj) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream ooStream = new ObjectOutputStream(baos);
			ooStream.writeObject(obj);
			ooStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				baos.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return baos.toByteArray();
	}

	/**
	 * 将存储的 BLOB 字段反序列化
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object trans2Object(byte[] bytes) {
		Object result = new Object();
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		try {
			ObjectInputStream oiStream = new ObjectInputStream(bais);
			result = oiStream.readObject();
		} catch (EOFException e) {

		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static Object copyObject(Object origin) {
		if (null == origin) {
			return null;
		} else {
			return trans2Object(trans2Bytes(origin));
		}
	}
}
