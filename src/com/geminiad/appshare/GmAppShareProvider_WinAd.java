package com.geminiad.appshare;

import android.app.Activity;
import android.content.Context;

import com.geminiad.appshare.GmAppShareManager.GmAppShareProviderType;
import com.geminiad.appshare.GmAppShareManager.GmCustomAppShareProvider;
import com.geminiad.appshare.GmAppShareManager.IGmPerformOnAppShareObserver;
import com.winad.offers.AdManager;

public class GmAppShareProvider_WinAd extends GmCustomAppShareProvider {

	public GmAppShareProvider_WinAd(GmAppShareManager adManager) {
		super(adManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean getSupportAppShare(Activity activity) {
		AdManager.init(activity);
		return true;
	}

	@Override
	public GmAppShareProviderType getAppShareProviderType() {
		return GmAppShareProviderType.winad;
	}

	@Override
	public void performAppShare(Activity activity,
			IGmPerformOnAppShareObserver ls) {
		Context appContext = getADMAnager().getContext();
		AdManager.showAdOffers(appContext);
	}

}
