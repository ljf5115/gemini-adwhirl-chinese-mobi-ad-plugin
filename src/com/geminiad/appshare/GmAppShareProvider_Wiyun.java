package com.geminiad.appshare;

import android.app.Activity;

import com.adwhirl.eventadapter.GmAdWhirlEventAdapterData;
import com.adwhirl.eventadapter.GmWiyunWrapper;
import com.adwhirl.eventadapter.GmAdWhirlEventHandler.GmEventADType;
import com.geminiad.appshare.GmAppShareManager.GmAppShareProviderType;
import com.geminiad.appshare.GmAppShareManager.GmCustomAppShareProvider;
import com.geminiad.appshare.GmAppShareManager.IGmPerformOnAppShareObserver;

public class GmAppShareProvider_Wiyun extends GmCustomAppShareProvider {

	public GmAppShareProvider_Wiyun(GmAppShareManager adManager) {
		super(adManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean getSupportAppShare(Activity activity) {
		GmWiyunWrapper.initizlize(activity,
				GmAdWhirlEventAdapterData.getPublishID(GmEventADType.wiyun),
				GmAdWhirlEventAdapterData.getDefaultPwdID(GmEventADType.wiyun));
		return true;
	}

	@Override
	public GmAppShareProviderType getAppShareProviderType() {
		return GmAppShareProviderType.wiyun;
	}

	@Override
	public void performAppShare(Activity activity,
			IGmPerformOnAppShareObserver ls) {
		GmWiyunWrapper.showOffers();
	}
}
