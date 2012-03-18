package com.adwhirl.eventadapter;

import com.uucun.adsdk.UUAppConnect;

import android.content.Context;

public class GmAppjoyWrapper {

	private static boolean mInitializedFlag = false;

	public static void initizlize(Context context, String publishId,
			String password) {
		if (!mInitializedFlag) {

			UUAppConnect.getInstance(context).initSdk();
			mInitializedFlag = true;
		}
	}

	public static void showOffers(Context context) {
		UUAppConnect.getInstance(context).showOffers();
	}
}
