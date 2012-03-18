package com.adwhirl.eventadapter;

import net.youmi.android.AdViewListener;
import net.youmi.android.AdManager;
import net.youmi.android.AdView;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.eventadapter.GmAdWhirlEventHandler.GmEventADType;
import com.adwhirl.eventadapter.IGmAdWhirlEventAdapter.GmAdWhirlCustomEventAdapter;

class GmAdWhirlEventAdapter_cn_youmi extends GmAdWhirlCustomEventAdapter
		implements AdViewListener {

	private static final int CONST_INT_AD_REQUEST_INTERVAL = 30;
	private static boolean mInitializedFlag = false;

	private AdView mADView;

	public GmAdWhirlEventAdapter_cn_youmi(AdWhirlLayout adLayout, Object obj) {
		super(adLayout, obj);
	}

	public static void initizlize(Context context) {
		if (!mInitializedFlag) {
			AdManager
					.init(context, GmAdWhirlEventAdapterData
							.getPublishID(GmEventADType.youmi),
							GmAdWhirlEventAdapterData
									.getDefaultPwdID(GmEventADType.youmi),
							CONST_INT_AD_REQUEST_INTERVAL,
							GmAdWhirlEventAdapterData
									.getDebugEnabled(GmEventADType.youmi));
			AdManager.disableUpdateApp();
		}
	}

	@Override
	protected void init(Object obj) {
		gmEventAdapterLog("youmi->init");
		AdWhirlLayout adLayout = getAdwhirlLayout();
		if (adLayout != null) {
			initizlize(adLayout.getContext().getApplicationContext());

			// Extra extra = adLayout.extra;
			// int bgColor = Color.rgb(extra.bgRed, extra.bgGreen,
			// extra.bgBlue);
			// int fgColor = Color.rgb(extra.fgRed, extra.fgGreen,
			// extra.fgBlue);

			mADView = new AdView(getAdwhirlActivity());
			mADView.setAdViewListener(this);

			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.FILL_PARENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			adLayout.addView(mADView, lp);

			// Show AD for certain seconds (extra.cycleTime).
			rotateThreadedDelayed();
			gmEventAdapterLog("youmi->rotateThreadedDelayed");
		}
	}

	@Override
	public void dispose() {
		gmEventAdapterLog("youmi->dispose");
		AdWhirlLayout tmpLayout = getAdwhirlLayout();
		if (tmpLayout != null) {
			if (mADView != null) {
				mADView.setVisibility(View.GONE);
				mADView.setAdViewListener(null);
				tmpLayout.removeView(mADView);
				mADView = null;
				gmEventAdapterLog("youmi->removed");
			}
		}
		super.dispose();
	}

	@Override
	public void onConnectFailed(AdView arg0) {
		gmEventAdapterLog("youmi->onConnectFailed");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				doRollover();
				setAdFetchDone(true);
				gmEventAdapterLog("youmi->doRollover");
			}
		}
	}

	@Override
	public void onAdViewSwitchedAd(AdView arg0) {
		gmEventAdapterLog("youmi->onAdViewSwitchedAd");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				resetRollover(mADView);
				setAdFetchDone(true);
				gmEventAdapterLog("youmi->resetRollover");
			} else {
				countImpression();
			}
		}
	}

}
