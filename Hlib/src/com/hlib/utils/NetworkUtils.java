package com.hlib.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {
	
	public enum NetworkType {
		Unavailable,WIFI,MOBILE
	}
	
	public static NetworkType checkNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return NetworkType.Unavailable;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						NetworkInfo netWorkInfo = info[i];
						if (netWorkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
							return NetworkType.WIFI;
						} else if (netWorkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
							return NetworkType.MOBILE;
						}
					}
				}
			}
		}

		return NetworkType.Unavailable;
	}
}
