package com.adwhirl.eventadapter;

import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.eventadapter.GmAdWhirlEventHandler.GmEventADType;
import com.adwhirl.eventadapter.IGmAdWhirlEventAdapter.GmAdWhirlCustomEventAdapter;
import com.madhouse.android.ads.AdListener;
import com.madhouse.android.ads.AdManager;
import com.madhouse.android.ads.AdView;

class GmAdWhirlEventAdapter_cn_smartmad extends GmAdWhirlCustomEventAdapter
		implements AdListener {

	private AdView mADView;

	public GmAdWhirlEventAdapter_cn_smartmad(AdWhirlLayout adLayout, Object obj) {
		super(adLayout, obj);
	}

	@Override
	protected void init(Object obj) {
		gmEventAdapterLog("smartmad->init");
		AdWhirlLayout adLayout = getAdwhirlLayout();
		if (adLayout != null) {
			Activity activity = getAdwhirlActivity();
			AdManager.setApplicationId(activity, GmAdWhirlEventAdapterData
					.getPublishID(GmEventADType.smartmad));
			mADView = new AdView(activity, null, 0,
					GmAdWhirlEventAdapterData
							.getDefaultPwdID(GmEventADType.smartmad), 30, 0,
					GmAdWhirlEventAdapterData
							.getDebugEnabled(GmEventADType.smartmad));
			mADView.setListener(this);

			// Extra extra = adLayout.extra;
			// int bgColor = Color.rgb(extra.bgRed, extra.bgGreen,
			// extra.bgBlue);
			// int fgColor = Color.rgb(extra.fgRed, extra.fgGreen,
			// extra.fgBlue);
			// mADView.setBackgroundColor(bgColor);

			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.FILL_PARENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			adLayout.addView(mADView, lp);

			// Show AD for certain seconds (extra.cycleTime).
			rotateThreadedDelayed();
			gmEventAdapterLog("smartmad->rotateThreadedDelayed");
		}
	}

	@Override
	public void dispose() {
		gmEventAdapterLog("smartmad->dispose");
		AdWhirlLayout tmpLayout = getAdwhirlLayout();
		if (tmpLayout != null) {
			if (mADView != null) {
				mADView.setVisibility(View.GONE);
				mADView.setListener(null);
				tmpLayout.removeView(mADView);
				mADView = null;
				gmEventAdapterLog("smartmad->removed");
			}
		}
		super.dispose();
	}

	@Override
	public void onAdEvent(AdView arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAdStatus(int arg0) {
		gmEventAdapterLog("smartmad->onAdStatus");
		if ((mADView != null) && (mADView.getVisibility() != View.GONE)) {
			if (isActiveAdView(mADView)) {
				if (!isAdFetchDone()) {
					if (arg0 == AdView.RETRUNCODE_OK) {
						resetRollover(mADView);
						gmEventAdapterLog("smartmad->resetRollover");
					} else {
						doRollover();
						gmEventAdapterLog("smartmad->doRollover");
					}
					setAdFetchDone(true);
				}
			}
		}
	}

}
