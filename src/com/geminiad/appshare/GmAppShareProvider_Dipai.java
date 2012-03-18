package com.geminiad.appshare;

import net.miidi.credit.MiidiCredit;
import android.app.Activity;

import com.adwhirl.eventadapter.GmAdWhirlEventAdapterData;
import com.adwhirl.eventadapter.GmAdWhirlEventHandler.GmEventADType;
import com.geminiad.appshare.GmAppShareManager.GmAppShareProviderType;
import com.geminiad.appshare.GmAppShareManager.GmCustomAppShareProvider;
import com.geminiad.appshare.GmAppShareManager.IGmPerformOnAppShareObserver;

public class GmAppShareProvider_Dipai extends GmCustomAppShareProvider {

	public GmAppShareProvider_Dipai(GmAppShareManager adManager) {
		super(adManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean getSupportAppShare(Activity activity) {
		MiidiCredit.init(activity,
				GmAdWhirlEventAdapterData.getPublishID(GmEventADType.dipai),
				GmAdWhirlEventAdapterData.getDefaultPwdID(GmEventADType.dipai),
				false);
		return true;
	}

	@Override
	public void performAppShare(Activity activity,
			IGmPerformOnAppShareObserver ls) {
		MiidiCredit.showAppOffers(activity);
	}

	@Override
	public GmAppShareProviderType getAppShareProviderType() {
		return GmAppShareProviderType.dipai;
	}

}
