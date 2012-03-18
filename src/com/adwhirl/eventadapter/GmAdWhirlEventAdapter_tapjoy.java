package com.adwhirl.eventadapter;

import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.eventadapter.GmAdWhirlEventHandler.GmEventADType;
import com.adwhirl.eventadapter.IGmAdWhirlEventAdapter.GmAdWhirlCustomEventAdapter;
import com.tapjoy.TapjoyConnect;
import com.tapjoy.TapjoyDisplayAdNotifier;
import com.tapjoy.TapjoyDisplayAdSize;

public class GmAdWhirlEventAdapter_tapjoy extends GmAdWhirlCustomEventAdapter
		implements TapjoyDisplayAdNotifier {

	private ViewGroup mADView;
	private View mTapjoyADView;
	private boolean mUpdateADViewFlag = false;

	private final Runnable mUpdateADViewRunnable = new Runnable() {
		public void run() {
			updateADViewUI();
		}
	};

	public GmAdWhirlEventAdapter_tapjoy(AdWhirlLayout adLayout, Object obj) {
		super(adLayout, obj);
	}

	@Override
	protected void init(Object obj) {
		gmEventAdapterLog("tapjoy->init");
		AdWhirlLayout adLayout = getAdwhirlLayout();
		if (adLayout != null) {
			GmTapjoyWrapper.initizlize(getAdwhirlActivity(),
					GmAdWhirlEventAdapterData
							.getPublishID(GmEventADType.tapjoy),
					GmAdWhirlEventAdapterData
							.getDefaultBannerID(GmEventADType.tapjoy));

			TapjoyConnect.getTapjoyConnectInstance().setBannerAdSize(
					TapjoyDisplayAdSize.TJC_AD_BANNERSIZE_640X100);
			TapjoyConnect.getTapjoyConnectInstance().getDisplayAd(this);

			mADView = new RelativeLayout(getAdwhirlActivity());

			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.FILL_PARENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			adLayout.addView(mADView, lp);

			// Show AD for certain seconds (extra.cycleTime).
			rotateThreadedDelayed();
			gmEventAdapterLog("tapjoy->rotateThreadedDelayed");
		}
	}

	@Override
	public void dispose() {
		gmEventAdapterLog("tapjoy->dispose");
		AdWhirlLayout tmpLayout = getAdwhirlLayout();
		if (tmpLayout != null) {
			if (mADView != null) {
				if (mTapjoyADView != null) {
					mTapjoyADView.setVisibility(View.GONE);
					mADView.removeAllViews();
				}
				mADView.setVisibility(View.GONE);
				tmpLayout.removeView(mADView);
				mADView = null;
				gmEventAdapterLog("adwo->removed");
			}
		}
		super.dispose();
	}

	private void updateADViewUI() {
		gmEventAdapterLog("tapjoy->updateADViewUI");

		if (mUpdateADViewFlag) {
			AdWhirlLayout adLayout = getAdwhirlLayout();
			if (adLayout != null) {
				if (mADView != null) {
					mADView.removeAllViews();
					mADView.addView(mTapjoyADView);

					if (isActiveAdView(mADView)) {
						if (!isAdFetchDone()) {
							resetRollover(mADView);
							setAdFetchDone(true);
							gmEventAdapterLog("tapjoy->resetRollover");
						} else {
							countImpression();
						}
					}
				}
			}
			mUpdateADViewFlag = false;
		}
	}

	@Override
	public void getDisplayAdResponse(View adView) {
		gmEventAdapterLog("tapjoy->getDisplayAdResponse");
		AdWhirlLayout adLayout = getAdwhirlLayout();
		if (adLayout != null) {
			mTapjoyADView = adView;
			LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT,
					LayoutParams.WRAP_CONTENT);
			int width = mADView.getMeasuredWidth();
			// Resize if it's too big.
			if (mTapjoyADView.getLayoutParams().width > width) {
				// 6.4 ratio
				lp = new LayoutParams(width, (width * 10) / 64);
				mTapjoyADView.setLayoutParams(lp);
			}

			Handler handler = adLayout.getHandler();
			if (handler != null) {
				mUpdateADViewFlag = true;
				handler.post(mUpdateADViewRunnable);
			}
		}
	}

	@Override
	public void getDisplayAdResponseFailed(String error) {
		gmEventAdapterLog("tapjoy->getDisplayAdResponseFailed");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				doRollover();
				setAdFetchDone(true);
				gmEventAdapterLog("tapjoy->doRollover");
			}
		}
	}

}
