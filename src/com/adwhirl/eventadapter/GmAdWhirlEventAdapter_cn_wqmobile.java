package com.adwhirl.eventadapter;

import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.eventadapter.IGmAdWhirlEventAdapter.GmAdWhirlCustomEventAdapter;
import com.geminiad.demo.R;
import com.wqmobile.sdk.widget.ADView;
import com.wqmobile.sdk.widget.WQCallback;

public class GmAdWhirlEventAdapter_cn_wqmobile extends
		GmAdWhirlCustomEventAdapter implements WQCallback {

	private ADView mADView;

	public GmAdWhirlEventAdapter_cn_wqmobile(AdWhirlLayout adLayout, Object obj) {
		super(adLayout, obj);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init(Object obj) {
		gmEventAdapterLog("wqmobile->init");
		AdWhirlLayout adLayout = getAdwhirlLayout();
		if (adLayout != null) {
			Activity activity = getAdwhirlActivity();
			mADView = new ADView(activity);
			mADView.Init(activity.getResources().openRawResource(
					R.raw.wqappsetting));

			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.FILL_PARENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			// EditText text = new EditText(act);
			// text.setWidth(LayoutParams.FILL_PARENT);
			// text.setHeight(60);
			adLayout.addView(mADView, lp);
			// Show AD for certain seconds (extra.cycleTime).
			rotateThreadedDelayed();
			gmEventAdapterLog("wqmobile->rotateThreadedDelayed");
		}
	}

	@Override
	public void dispose() {
		gmEventAdapterLog("wqmobile->dispose");
		AdWhirlLayout tmpLayout = getAdwhirlLayout();
		if (tmpLayout != null) {
			if (mADView != null) {
				// mADView.setListener(null);
				mADView.setVisibility(View.GONE);
				tmpLayout.removeView(mADView);
				mADView = null;
				gmEventAdapterLog("wqmobile->removed");
			}
		}
		super.dispose();
	}

	@Override
	public void didFailReceiveAd() {
		gmEventAdapterLog("wqmobile->onFailedToReceiveFreshAd");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				doRollover();
				setAdFetchDone(true);
				gmEventAdapterLog("wqmobile->doRollover");
			}
		}
	}

	@Override
	public void didReceiveAd() {
		gmEventAdapterLog("wqmobile->onReceivedFreshAd");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				AdWhirlLayout adLayout = getAdwhirlLayout();
				if (adLayout != null) {
					adLayout.adWhirlManager.resetRollover();
					countImpression();
				}
				setAdFetchDone(true);
				gmEventAdapterLog("wqmobile->resetRollover");
			} else {
				countImpression();
			}
		}
	}

}
