package com.geminiad.appshare;

import android.app.Activity;

import com.dianjoy.Dianjoy;
import com.geminiad.appshare.GmAppShareManager.GmAppShareProviderType;
import com.geminiad.appshare.GmAppShareManager.GmCustomAppShareProvider;
import com.geminiad.appshare.GmAppShareManager.IGmPerformOnAppShareObserver;

public class GmAppShareProvider_Dianjoy extends GmCustomAppShareProvider {

	public GmAppShareProvider_Dianjoy(GmAppShareManager adManager) {
		super(adManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean getSupportAppShare(Activity activity) {
		Dianjoy.initDianjoyContext(activity);
		return true;
	}

	@Override
	public GmAppShareProviderType getAppShareProviderType() {
		return GmAppShareProviderType.dianjoy;
	}

	@Override
	public void performAppShare(Activity activity,
			IGmPerformOnAppShareObserver ls) {
		Dianjoy.showOffers();
	}

}
