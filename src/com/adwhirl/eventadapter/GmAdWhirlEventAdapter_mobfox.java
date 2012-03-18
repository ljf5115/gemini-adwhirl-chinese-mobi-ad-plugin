package com.adwhirl.eventadapter;

import android.view.View;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.eventadapter.GmAdWhirlEventHandler.GmEventADType;
import com.adwhirl.eventadapter.IGmAdWhirlEventAdapter.GmAdWhirlCustomEventAdapter;
import com.mobfox.sdk.*;

class GmAdWhirlEventAdapter_mobfox extends GmAdWhirlCustomEventAdapter
		implements BannerListener {

	private MobFoxView mADView;

	public GmAdWhirlEventAdapter_mobfox(AdWhirlLayout adLayout, Object obj) {
		super(adLayout, obj);
	}

	@Override
	protected void init(Object obj) {
		gmEventAdapterLog("mobfox->init");
		AdWhirlLayout adLayout = getAdwhirlLayout();
		if (adLayout != null) {

			mADView = new MobFoxView(getAdwhirlActivity(),
					GmAdWhirlEventAdapterData
							.getPublishID(GmEventADType.mobfox),
					com.mobfox.sdk.Mode.LIVE, true, true);
			mADView.setBannerListener(this);
			mADView.setVisibility(View.GONE);

			// Show AD for certain seconds (extra.cycleTime).
			rotateThreadedDelayed();
			gmEventAdapterLog("mobfox->rotateThreadedDelayed");
		}
	}

	@Override
	public void dispose() {
		gmEventAdapterLog("mobfox->dispose");
		AdWhirlLayout tmpLayout = getAdwhirlLayout();
		if (tmpLayout != null) {
			if (mADView != null) {
				mADView.setVisibility(View.GONE);
				mADView.setBannerListener(null);
				tmpLayout.removeView(mADView);
				mADView = null;
				gmEventAdapterLog("mobfox->removed");
			}
		}
		super.dispose();
	}

	@Override
	public void bannerLoadFailed(RequestException arg0) {
		gmEventAdapterLog("mobfox->bannerLoadFailed");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				doRollover();
				setAdFetchDone(true);
				gmEventAdapterLog("mobfox->doRollover");
			}
		}
	}

	@Override
	public void bannerLoadSucceeded() {
		gmEventAdapterLog("mobfox->bannerLoadSucceeded");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				resetRollover(mADView);
				setAdFetchDone(true);
				gmEventAdapterLog("mobfox->resetRollover");
			} else {
				countImpression();
			}
		}
	}

	@Override
	public void noAdFound() {
		gmEventAdapterLog("mobfox->noAdFound");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				doRollover();
				setAdFetchDone(true);
				gmEventAdapterLog("mobfox->doRollover");
			}
		}
	}

}
