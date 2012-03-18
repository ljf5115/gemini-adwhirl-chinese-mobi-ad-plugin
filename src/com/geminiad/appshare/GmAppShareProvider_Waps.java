package com.geminiad.appshare;

import android.app.Activity;
import android.content.Context;

import com.geminiad.appshare.GmAppShareManager.GmAppShareProviderType;
import com.geminiad.appshare.GmAppShareManager.GmCustomAppShareProvider;
import com.geminiad.appshare.GmAppShareManager.IGmPerformOnAppShareObserver;
import com.waps.AppConnect;

public class GmAppShareProvider_Waps extends GmCustomAppShareProvider {

	public GmAppShareProvider_Waps(GmAppShareManager adManager) {
		super(adManager);
		// AppConnect.getInstance(getADMAnager().getContext());
	}

	@Override
	public boolean getSupportAppShare(Activity activity) {
		AppConnect.getInstance(activity);
		AppConnect.getInstance(activity).setPushAudio(false);
		return true;
	}

	@Override
	public void performAppShare(Activity activity,
			IGmPerformOnAppShareObserver ls) {
		Context appContext = getADMAnager().getContext();
		AppConnect.getInstance(appContext).showOffers(activity);
	}

	@Override
	public GmAppShareProviderType getAppShareProviderType() {
		return GmAppShareProviderType.waps;
	}

}
