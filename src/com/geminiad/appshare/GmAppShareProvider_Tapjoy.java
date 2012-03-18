package com.geminiad.appshare;

import android.app.Activity;

import com.adwhirl.eventadapter.GmAdWhirlEventHandler.GmEventADType;
import com.adwhirl.eventadapter.GmAdWhirlEventAdapterData;
import com.adwhirl.eventadapter.GmTapjoyWrapper;
import com.geminiad.appshare.GmAppShareManager.GmAppShareProviderType;
import com.geminiad.appshare.GmAppShareManager.GmCustomAppShareProvider;
import com.geminiad.appshare.GmAppShareManager.IGmPerformOnAppShareObserver;

public class GmAppShareProvider_Tapjoy extends GmCustomAppShareProvider {

	public GmAppShareProvider_Tapjoy(GmAppShareManager adManager) {
		super(adManager);
	}

	@Override
	public boolean getSupportAppShare(Activity activity) {
		GmTapjoyWrapper
				.initizlize(activity, GmAdWhirlEventAdapterData
						.getPublishID(GmEventADType.tapjoy),
						GmAdWhirlEventAdapterData
								.getDefaultPwdID(GmEventADType.tapjoy));
		return true;
	}

	@Override
	public void performAppShare(Activity activity,
			IGmPerformOnAppShareObserver ls) {
		GmTapjoyWrapper.showOffers();
	}

	@Override
	public GmAppShareProviderType getAppShareProviderType() {
		return GmAppShareProviderType.tapjoy;
	}

}
