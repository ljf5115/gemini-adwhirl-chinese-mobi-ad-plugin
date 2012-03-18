package com.adwhirl.eventadapter;

import android.view.View;
import android.widget.RelativeLayout;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.eventadapter.GmAdWhirlEventHandler.GmEventADType;
import com.adwhirl.eventadapter.IGmAdWhirlEventAdapter.GmAdWhirlCustomEventAdapter;
import com.wiyun.ad.AdView;
import com.wiyun.ad.AdView.AdListener;

class GmAdWhirlEventAdapter_cn_wiyun extends GmAdWhirlCustomEventAdapter
		implements AdListener {

	private static final int CONST_INT_AD_REQUEST_INTERVAL = 30;

	private AdView mADView;

	public GmAdWhirlEventAdapter_cn_wiyun(AdWhirlLayout adLayout, Object obj) {
		super(adLayout, obj);
	}

	@Override
	protected void init(Object obj) {
		gmEventAdapterLog("wiyun->init");
		AdWhirlLayout adLayout = getAdwhirlLayout();
		if (adLayout != null) {
			// Extra extra = adLayout.extra;
			// int bgColor = Color.rgb(extra.bgRed, extra.bgGreen,
			// extra.bgBlue);
			// int fgColor = Color.rgb(extra.fgRed, extra.fgGreen,
			// extra.fgBlue);

			mADView = new AdView(getAdwhirlActivity());
			mADView.setResId(GmAdWhirlEventAdapterData
					.getDefaultBannerID(GmEventADType.wiyun));
			// interval
			mADView.setRefreshInterval(CONST_INT_AD_REQUEST_INTERVAL);
			// test mode
			if (GmAdWhirlEventAdapterData.getDebugEnabled(GmEventADType.wiyun)) {
				mADView.setTestMode(true);
				mADView.setTestAdType(AdView.AD_TYPE_BANNER);
			}
			mADView.setListener(this);
			mADView.requestAd();

			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.FILL_PARENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			adLayout.addView(mADView, lp);

			// Show AD for certain seconds (extra.cycleTime).
			rotateThreadedDelayed();
			gmEventAdapterLog("wiyun->rotateThreadedDelayed");
		}
	}

	@Override
	public void dispose() {
		gmEventAdapterLog("wiyun->dispose");
		AdWhirlLayout tmpLayout = getAdwhirlLayout();
		if (tmpLayout != null) {
			if (mADView != null) {
				mADView.setVisibility(View.GONE);
				mADView.setListener(null);
				tmpLayout.removeView(mADView);
				mADView = null;
				gmEventAdapterLog("wiyun->removed");
			}
		}
		super.dispose();
	}

	@Override
	public void onAdClicked() {
		gmEventAdapterLog("wiyun->onAdClicked");
		if (isActiveAdView(mADView)) {
			countClick();
			gmEventAdapterLog("wiyun->countClick");
		}
	}

	@Override
	public void onAdLoadFailed() {
		gmEventAdapterLog("wiyun->onAdLoadFailed");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				doRollover();
				setAdFetchDone(true);
				gmEventAdapterLog("wiyun->doRollover");
			}
		}
	}

	@Override
	public void onAdLoaded() {
		gmEventAdapterLog("wiyun->onAdLoaded");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				resetRollover(mADView);
				setAdFetchDone(true);
				gmEventAdapterLog("wiyun->resetRollover");
			} else {
				countImpression();
			}
		}
	}

	@Override
	public void onExitButtonClicked() {
	}

	@Override
	public void onAppDownloadFailed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAppDownloaded() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMiniSiteClosed() {
		// TODO Auto-generated method stub
		
	}

}
