package com.adwhirl.eventadapter;

import android.view.View;
import android.widget.RelativeLayout;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.eventadapter.GmAdWhirlEventHandler.GmEventADType;
import com.adwhirl.eventadapter.IGmAdWhirlEventAdapter.GmAdWhirlCustomEventAdapter;
import com.vpon.adon.android.AdListener;
import com.vpon.adon.android.AdOnPlatform;
import com.vpon.adon.android.AdView;

public class GmAdWhirlEventAdapter_cn_vpon extends GmAdWhirlCustomEventAdapter
		implements AdListener {

	private AdView mADView;

	public GmAdWhirlEventAdapter_cn_vpon(AdWhirlLayout adLayout, Object obj) {
		super(adLayout, obj);
	}

	@Override
	protected void init(Object obj) {
		gmEventAdapterLog("vpon->init");
		AdWhirlLayout adLayout = getAdwhirlLayout();
		if (adLayout != null) {
			mADView = new AdView(getAdwhirlActivity());
			mADView.setAdListener(this);

			// get platform id from obj
			AdOnPlatform platformId = AdOnPlatform.CN;
			try {
				Integer tmpValue = (Integer) obj;
				switch (tmpValue) {
				case GmAdWhirlEventHandler.CONST_INT_VPON_TW:
					platformId = AdOnPlatform.TW;
					break;
				}
			} finally {
			}

			// get publish id for diff platform
			String publishId;
			switch (platformId) {
			case TW:
				publishId = GmAdWhirlEventAdapterData
						.getPublishID(GmEventADType.vpontw);
				break;
			default:// case CN:
				publishId = GmAdWhirlEventAdapterData
						.getPublishID(GmEventADType.vpon);
				break;
			}

			mADView.setLicenseKey(publishId, platformId, true);
			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.FILL_PARENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			adLayout.addView(mADView, lp);

			// Show AD for certain seconds (extra.cycleTime).
			rotateThreadedDelayed();
			gmEventAdapterLog("vpon->rotateThreadedDelayed");
		}
	}

	@Override
	public void dispose() {
		gmEventAdapterLog("vpon->dispose");
		AdWhirlLayout tmpLayout = getAdwhirlLayout();
		if (tmpLayout != null) {
			if (mADView != null) {
				mADView.setVisibility(View.GONE);
				mADView.setAdListener(null);
				tmpLayout.removeView(mADView);
				mADView = null;
				gmEventAdapterLog("vpon->removed");
			}
		}
		super.dispose();
	}

	@Override
	public void onFailedToRecevieAd(AdView adView) {
		gmEventAdapterLog("vpon->onFailedToReceiveAd");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				doRollover();
				setAdFetchDone(true);
				gmEventAdapterLog("vpon->doRollover");
			}
		}
	}

	@Override
	public void onRecevieAd(AdView adView) {
		gmEventAdapterLog("vpon->onReceiveAd");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				resetRollover(mADView);
				setAdFetchDone(true);
				gmEventAdapterLog("vpon->resetRollover");
			} else {
				countImpression();
			}
		}
	}

}
