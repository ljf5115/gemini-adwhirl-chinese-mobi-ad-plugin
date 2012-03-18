package com.adwhirl.eventadapter;

import android.widget.RelativeLayout;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.eventadapter.IGmAdWhirlEventAdapter.GmAdWhirlCustomEventAdapter;
import com.tencent.mobwin.AdListener;
import com.tencent.mobwin.AdView;

public class GmAdWhirlEventAdapter_cn_mobwin extends
		GmAdWhirlCustomEventAdapter implements AdListener {

	private AdView mADView;

	public GmAdWhirlEventAdapter_cn_mobwin(AdWhirlLayout adLayout, Object obj) {
		super(adLayout, obj);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init(Object obj) {
		gmEventAdapterLog("mobwin->init");
		AdWhirlLayout adLayout = getAdwhirlLayout();
		if (adLayout != null) {
			// Extra extra = adLayout.extra;
			// int bgColor = Color.rgb(extra.bgRed, extra.bgGreen,
			// extra.bgBlue);
			// int fgColor = Color.rgb(extra.fgRed, extra.fgGreen,
			// extra.fgBlue);

			mADView = new AdView(getAdwhirlActivity());
			mADView.setAdListener(this);

			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.FILL_PARENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			adLayout.addView(mADView, lp);

			// Show AD for certain seconds (extra.cycleTime).
			rotateThreadedDelayed();
			gmEventAdapterLog("mobwin->rotateThreadedDelayed");
		}
	}

	@Override
	public void dispose() {
		gmEventAdapterLog("mobwin->dispose");
		AdWhirlLayout tmpLayout = getAdwhirlLayout();
		if (tmpLayout != null) {
			if (mADView != null) {
				// mADView.setVisibility(View.GONE);
				mADView.setAdListener(null);
				tmpLayout.removeView(mADView);
				mADView = null;
				gmEventAdapterLog("mobwin->removed");
			}
		}
		super.dispose();
	}

	@Override
	public void onAdClick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onReceiveAd() {
		gmEventAdapterLog("mobwin->onReceiveAd");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				resetRolloverOnly();
				//resetRollover(mADView);
				setAdFetchDone(true);
				gmEventAdapterLog("mobwin->resetRollover");
			} else {
				countImpression();
			}
		}
	}

	@Override
	public void onReceiveFailed(int arg0) {
		gmEventAdapterLog("mobwin->onReceiveFailed");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				doRollover();
				setAdFetchDone(true);
				gmEventAdapterLog("mobwin->doRollover");
			}
		}
	}

}
