package com.hlib.utils;

import java.security.MessageDigest;

public class XcMD5kit {
	// 生成MD5摘要
	public static String MD5encrypt(String message) throws Exception {
		return encrypt(message.getBytes(), "MD5");
	}

	public static String MD5encrypt(byte[] data) throws Exception {
		return encrypt(data, "MD5");
	}

	// algorithm: MD5 or SHA-1
	// return string length: 32 if algorithm = MD5, or 40 if algorithm = SHA-1
	protected static String encrypt(byte[] data, String algorithm)
			throws Exception {
		if (data == null) {
			throw new Exception("message is null.");
		}
		if (!"MD5".equals(algorithm) && !"SHA-1".equals(algorithm)) {
			throw new Exception("algorithm must be MD5 or SHA-1.");
		}
		MessageDigest md = MessageDigest.getInstance(algorithm);
		md.reset();
		md.update(data);
		byte[] digest = md.digest();

		StringBuffer hexString = new StringBuffer();
		String sHexBit = null;
		for (int i = 0; i < digest.length; i++) {
			sHexBit = Integer.toHexString(0xFF & digest[i]);
			if (sHexBit.length() == 1) {
				sHexBit = "0" + sHexBit;
			}
			hexString.append(sHexBit);
		}
		return hexString.toString();
	}

}
