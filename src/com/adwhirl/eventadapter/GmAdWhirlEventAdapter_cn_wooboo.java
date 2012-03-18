package com.adwhirl.eventadapter;

import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.eventadapter.GmAdWhirlEventHandler.GmEventADType;
import com.adwhirl.eventadapter.IGmAdWhirlEventAdapter.GmAdWhirlCustomEventAdapter;
import com.adwhirl.obj.Extra;
import com.wooboo.adlib_android.AdListener;
import com.wooboo.adlib_android.WoobooAdView;

class GmAdWhirlEventAdapter_cn_wooboo extends GmAdWhirlCustomEventAdapter
		implements AdListener {

	private static final int CONST_INT_AD_REQUEST_INTERVAL = 30;

	private WoobooAdView mADView;

	public GmAdWhirlEventAdapter_cn_wooboo(AdWhirlLayout adLayout, Object obj) {
		super(adLayout, obj);
	}

	@Override
	protected void init(Object obj) {
		gmEventAdapterLog("wooboo->init");
		AdWhirlLayout adLayout = getAdwhirlLayout();
		if (adLayout != null) {
			Extra extra = adLayout.extra;
			int bgColor = Color.rgb(extra.bgRed, extra.bgGreen, extra.bgBlue);
			int fgColor = Color.rgb(extra.fgRed, extra.fgGreen, extra.fgBlue);

			mADView = new WoobooAdView(getAdwhirlActivity(),
					GmAdWhirlEventAdapterData
							.getPublishID(GmEventADType.wooboo), bgColor,
					fgColor,
					GmAdWhirlEventAdapterData
							.getDebugEnabled(GmEventADType.wooboo),
					CONST_INT_AD_REQUEST_INTERVAL, null);
			mADView.setAdListener(this);

			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.FILL_PARENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			adLayout.addView(mADView, lp);

			// Show AD for certain seconds (extra.cycleTime).
			rotateThreadedDelayed();
			gmEventAdapterLog("wooboo->rotateThreadedDelayed");
		}
	}

	@Override
	public void dispose() {
		gmEventAdapterLog("wooboo->dispose");
		AdWhirlLayout tmpLayout = getAdwhirlLayout();
		if (tmpLayout != null) {
			if (mADView != null) {
				mADView.setVisibility(View.GONE);
				mADView.setAdListener(null);
				tmpLayout.removeView(mADView);
				mADView = null;
				gmEventAdapterLog("wooboo->removed");
			}
		}
		super.dispose();
	}

	@Override
	public void onFailedToReceiveAd(Object arg0) {
		gmEventAdapterLog("wooboo->onFailedToReceiveAd");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				doRollover();
				setAdFetchDone(true);
				gmEventAdapterLog("wooboo->doRollover");
			}
		}
	}

	@Override
	public void onReceiveAd(Object arg0) {
		gmEventAdapterLog("wooboo->onReceiveAd");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				resetRollover(mADView);
				setAdFetchDone(true);
				gmEventAdapterLog("wooboo->resetRollover");
			} else {
				countImpression();
			}
		}
	}

	@Override
	public void onPlayFinish() {
		// TODO Auto-generated method stub
		
	}

}
