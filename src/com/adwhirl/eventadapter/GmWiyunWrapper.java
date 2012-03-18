package com.adwhirl.eventadapter;

import android.content.Context;

import com.wiyun.offer.WiOffer;

public class GmWiyunWrapper {

	private static boolean mInitializedFlag = false;

	public static void initizlize(Context context, String publishId,
			String password) {
		if (!mInitializedFlag) {

			WiOffer.init(context, publishId, password);
			WiOffer.setSandboxMode(false);
			mInitializedFlag = true;
		}
	}

	public static void showOffers() {
		WiOffer.showOffers();
	}

}
