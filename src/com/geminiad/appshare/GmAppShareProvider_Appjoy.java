package com.geminiad.appshare;

import android.app.Activity;
import android.content.Context;

import com.adwhirl.eventadapter.GmAppjoyWrapper;
import com.geminiad.appshare.GmAppShareManager.GmAppShareProviderType;
import com.geminiad.appshare.GmAppShareManager.GmCustomAppShareProvider;
import com.geminiad.appshare.GmAppShareManager.IGmPerformOnAppShareObserver;

public class GmAppShareProvider_Appjoy extends GmCustomAppShareProvider {

	public GmAppShareProvider_Appjoy(GmAppShareManager adManager) {
		super(adManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean getSupportAppShare(Activity activity) {
		GmAppjoyWrapper.initizlize(activity, null, null);
		return true;
	}

	@Override
	public void performAppShare(Activity activity,
			IGmPerformOnAppShareObserver ls) {
		Context appContext = getADMAnager().getContext();
		GmAppjoyWrapper.showOffers(appContext);
	}

	@Override
	public GmAppShareProviderType getAppShareProviderType() {
		return GmAppShareProviderType.appjoy;
	}

}
