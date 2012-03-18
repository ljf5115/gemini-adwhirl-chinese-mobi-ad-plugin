package com.adwhirl.eventadapter;

import android.widget.RelativeLayout;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.eventadapter.GmAdWhirlEventHandler.GmEventADType;
import com.adwhirl.eventadapter.IGmAdWhirlEventAdapter.GmAdWhirlCustomEventAdapter;
import com.mt.airad.AirAD;
import com.mt.airad.AirAD.AirADListener;

public class GmAdWhirlEventAdapter_cn_airad extends GmAdWhirlCustomEventAdapter
		implements AirADListener {

	private AirAD mADView;

	static {
		AirAD.setGlobalParameter(
				GmAdWhirlEventAdapterData.getPublishID(GmEventADType.airad),
				false);
	}

	public GmAdWhirlEventAdapter_cn_airad(AdWhirlLayout adLayout, Object obj) {
		super(adLayout, obj);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init(Object obj) {
		gmEventAdapterLog("airad->init");
		AdWhirlLayout adLayout = getAdwhirlLayout();
		if (adLayout != null) {
			// Extra extra = adLayout.extra;
			// int bgColor = Color.rgb(extra.bgRed, extra.bgGreen,
			// extra.bgBlue);
			// int fgColor = Color.rgb(extra.fgRed, extra.fgGreen,
			// extra.fgBlue);

			mADView = new AirAD(getAdwhirlActivity());
			mADView.setBackgroundAutoHidden(true);
			mADView.setAirADListener(this);

			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.FILL_PARENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			adLayout.addView(mADView, lp);

			// Show AD for certain seconds (extra.cycleTime).
			rotateThreadedDelayed();
			gmEventAdapterLog("airad->rotateThreadedDelayed");
		}
	}

	@Override
	public void dispose() {
		gmEventAdapterLog("airad->dispose");
		AdWhirlLayout tmpLayout = getAdwhirlLayout();
		if (tmpLayout != null) {
			if (mADView != null) {
				// //It seems that adwo do not like adView to be GONE.
				// mADView.setVisibility(View.GONE);
				mADView.setAirADListener(null);
				tmpLayout.removeView(mADView);
				mADView = null;
				gmEventAdapterLog("airad->removed");
			}
		}
		super.dispose();
	}

	@Override
	public void onAdBannerClicked() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdBannerDidDismiss() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdBannerDidShow() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdBannerWillDismiss() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdBannerWillShow() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdContentDidDismiss() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdContentDidShow() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdContentLoadFinished() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdContentWillDismiss() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdContentWillShow() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdReceived() {
		gmEventAdapterLog("airad->onAdReceived");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				resetRollover(mADView);
				setAdFetchDone(true);
				gmEventAdapterLog("airad->resetRollover");
			} else {
				countImpression();
			}
		}
	}

	@Override
	public void onAdReceivedFailed() {
		gmEventAdapterLog("airad->onAdReceivedFailed");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				doRollover();
				setAdFetchDone(true);
				gmEventAdapterLog("airad->doRollover");
			}
		}
	}

	@Override
	public void onAirADFailed() {
		gmEventAdapterLog("airad->onAirADFailed");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				doRollover();
				setAdFetchDone(true);
				gmEventAdapterLog("airad->doRollover");
			}
		}
	}

}
