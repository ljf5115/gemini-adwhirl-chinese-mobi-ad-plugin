package com.geminiad.appshare;

import android.app.Activity;

import com.adzhidian.view.AdView;
import com.geminiad.appshare.GmAppShareManager.GmAppShareProviderType;
import com.geminiad.appshare.GmAppShareManager.GmCustomAppShareProvider;
import com.geminiad.appshare.GmAppShareManager.IGmPerformOnAppShareObserver;

public class GmAppShareProvider_Zhidian extends GmCustomAppShareProvider {

	public GmAppShareProvider_Zhidian(GmAppShareManager adManager) {
		super(adManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean getSupportAppShare(Activity activity) {
		return true;
	}

	@Override
	public GmAppShareProviderType getAppShareProviderType() {
		return GmAppShareProviderType.zhidian;
	}

	@Override
	public void performAppShare(Activity activity,
			IGmPerformOnAppShareObserver ls) {
		AdView.startrecommendWall(activity);
	}

}
