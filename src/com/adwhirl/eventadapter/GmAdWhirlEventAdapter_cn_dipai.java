package com.adwhirl.eventadapter;

import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;

import com.adpooh.adscast.banner.AdkBannerView;
import com.adpooh.adscast.banner.AdkListener;
import com.adpooh.adscast.banner.AdkManager;
import com.adwhirl.AdWhirlLayout;
import com.adwhirl.eventadapter.GmAdWhirlEventHandler.GmEventADType;
import com.adwhirl.eventadapter.IGmAdWhirlEventAdapter.GmAdWhirlCustomEventAdapter;
import com.adwhirl.obj.Extra;

public class GmAdWhirlEventAdapter_cn_dipai extends GmAdWhirlCustomEventAdapter
		implements AdkListener {

	private AdkBannerView mADView;

	public GmAdWhirlEventAdapter_cn_dipai(AdWhirlLayout adLayout, Object obj) {
		super(adLayout, obj);
	}

	static {
		int id = 0;
		try {
			id = Integer.parseInt(GmAdWhirlEventAdapterData
					.getPublishID(GmEventADType.dipai));
		} catch (Exception e) {

		}
		AdkManager.initialParam(id, GmAdWhirlEventAdapterData
				.getDefaultPwdID(GmEventADType.dipai));
	}

	@Override
	protected void init(Object obj) {
		gmEventAdapterLog("dipai->init");
		AdWhirlLayout adLayout = getAdwhirlLayout();
		if (adLayout != null) {
			mADView = new AdkBannerView(getAdwhirlActivity());
			mADView.setListener(this);

			Extra extra = adLayout.extra;
			int bgColor = Color.rgb(extra.bgRed, extra.bgGreen, extra.bgBlue);
			// int fgColor = Color.rgb(extra.fgRed, extra.fgGreen,
			// extra.fgBlue);
			mADView.setBackgroundColor(bgColor);

			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.FILL_PARENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			adLayout.addView(mADView, lp);

			// Show AD for certain seconds (extra.cycleTime).
			rotateThreadedDelayed();
			gmEventAdapterLog("dipai->rotateThreadedDelayed");
		}
	}

	@Override
	public void dispose() {
		gmEventAdapterLog("dipai->dispose");
		AdWhirlLayout tmpLayout = getAdwhirlLayout();
		if (tmpLayout != null) {
			if (mADView != null) {
				mADView.setVisibility(View.GONE);
				mADView.setListener(null);
				tmpLayout.removeView(mADView);
				mADView = null;
				gmEventAdapterLog("dipai->removed");
			}
		}
		super.dispose();
	}

	@Override
	public void onFailedAd(AdkBannerView arg0) {
		gmEventAdapterLog("dipai->onFailedAd");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				doRollover();
				setAdFetchDone(true);
				gmEventAdapterLog("dipai->doRollover");
			}
		}
	}

	@Override
	public void onReceiveAd(AdkBannerView arg0) {
		gmEventAdapterLog("dipai->onReceiveAd");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				resetRollover(mADView);
				setAdFetchDone(true);
				gmEventAdapterLog("dipai->resetRollover");
			} else {
				countImpression();
			}
		}
	}

}
