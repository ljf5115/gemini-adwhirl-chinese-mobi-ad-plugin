package com.adwhirl.eventadapter;

import android.graphics.Color;
import android.widget.RelativeLayout;
import cn.appmedia.ad.AdManager;
import cn.appmedia.ad.AdViewListener;
import cn.appmedia.ad.BannerAdView;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.eventadapter.GmAdWhirlEventHandler.GmEventADType;
import com.adwhirl.eventadapter.IGmAdWhirlEventAdapter.GmAdWhirlCustomEventAdapter;
import com.adwhirl.obj.Extra;

public class GmAdWhirlEventAdapter_cn_appmedia extends
		GmAdWhirlCustomEventAdapter implements AdViewListener {

	private BannerAdView mADView;

	static {
		AdManager.setAid(GmAdWhirlEventAdapterData
				.getPublishID(GmEventADType.appmedia));
	}

	public GmAdWhirlEventAdapter_cn_appmedia(AdWhirlLayout adLayout, Object obj) {
		super(adLayout, obj);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init(Object obj) {
		gmEventAdapterLog("appmedia->init");
		AdWhirlLayout adLayout = getAdwhirlLayout();
		if (adLayout != null) {
			Extra extra = adLayout.extra;
			int bgColor = Color.rgb(extra.bgRed, extra.bgGreen, extra.bgBlue);
			int fgColor = Color.rgb(extra.fgRed, extra.fgGreen, extra.fgBlue);

			mADView = new BannerAdView(getAdwhirlActivity());
			mADView.setAdListener(this);
			mADView.setAdTextColor(fgColor);
			mADView.setBackgroundColor(bgColor);

			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.FILL_PARENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			adLayout.addView(mADView, lp);

			// Show AD for certain seconds (extra.cycleTime).
			rotateThreadedDelayed();
			gmEventAdapterLog("appmedia->rotateThreadedDelayed");
		}
	}

	@Override
	public void dispose() {
		gmEventAdapterLog("appmedia->dispose");
		AdWhirlLayout tmpLayout = getAdwhirlLayout();
		if (tmpLayout != null) {
			if (mADView != null) {
				// //It seems that adwo do not like adView to be GONE.
				// mADView.setVisibility(View.GONE);
				mADView.setAdListener(null);
				tmpLayout.removeView(mADView);
				mADView = null;
				gmEventAdapterLog("appmedia->removed");
			}
		}
		super.dispose();
	}

	@Override
	public void onReceiveAdFailure(BannerAdView arg0) {
		gmEventAdapterLog("appmedia->onReceiveAdFailure");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				doRollover();
				setAdFetchDone(true);
				gmEventAdapterLog("appmedia->doRollover");
			}
		}
	}

	@Override
	public void onReceiveAdSuccess(BannerAdView arg0) {
		gmEventAdapterLog("appmedia->onReceiveAd");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				resetRollover(mADView);
				setAdFetchDone(true);
				gmEventAdapterLog("appmedia->resetRollover");
			} else {
				countImpression();
			}
		}
	}

}
