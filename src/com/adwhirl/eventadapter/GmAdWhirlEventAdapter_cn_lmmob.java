package com.adwhirl.eventadapter;

import android.view.View;
import android.widget.RelativeLayout;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.eventadapter.GmAdWhirlEventHandler.GmEventADType;
import com.adwhirl.eventadapter.IGmAdWhirlEventAdapter.GmAdWhirlCustomEventAdapter;
import com.lmmob.ad.sdk.LmMobAdRequestListener;
import com.lmmob.ad.sdk.LmMobAdView;
import com.lmmob.ad.sdk.LmMobEngine;

public class GmAdWhirlEventAdapter_cn_lmmob extends GmAdWhirlCustomEventAdapter
		implements LmMobAdRequestListener {

	private LmMobAdView mADView;

	public GmAdWhirlEventAdapter_cn_lmmob(AdWhirlLayout adLayout, Object obj) {
		super(adLayout, obj);
	}

	static {
		LmMobEngine.init(GmAdWhirlEventAdapterData
				.getPublishID(GmEventADType.lmmob));
	}

	@Override
	protected void init(Object obj) {
		gmEventAdapterLog("lmmob->init");
		AdWhirlLayout adLayout = getAdwhirlLayout();
		if (adLayout != null) {
			// Extra extra = adLayout.extra;
			// int bgColor = Color.rgb(extra.bgRed, extra.bgGreen,
			// extra.bgBlue);
			// int fgColor = Color.rgb(extra.fgRed, extra.fgGreen,
			// extra.fgBlue);

			mADView = new LmMobAdView(getAdwhirlActivity());
			// mADView.setLinstener(this);

			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.FILL_PARENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			adLayout.addView(mADView, lp);

			// Show AD for certain seconds (extra.cycleTime).
			rotateThreadedDelayed();
			gmEventAdapterLog("lmmob->rotateThreadedDelayed");
		}
	}

	@Override
	public void dispose() {
		gmEventAdapterLog("lmmob->dispose");
		AdWhirlLayout tmpLayout = getAdwhirlLayout();
		if (tmpLayout != null) {
			if (mADView != null) {
				mADView.setVisibility(View.GONE);
				// mADView.setListener(null);
				tmpLayout.removeView(mADView);
				mADView = null;
				gmEventAdapterLog("lmmob->removed");
			}
		}
		super.dispose();
	}

	@Override
	public void adRecieveFailure() {
		gmEventAdapterLog("lmmob->adRecieveFailure");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				doRollover();
				setAdFetchDone(true);
				gmEventAdapterLog("lmmob->doRollover");
			}
		}
	}

	@Override
	public void adRecieveSuccess() {
		gmEventAdapterLog("lmmob->adRecieveSuccess");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				resetRollover(mADView);
				setAdFetchDone(true);
				gmEventAdapterLog("lmmob->resetRollover");
			} else {
				countImpression();
			}
		}
	}
}
