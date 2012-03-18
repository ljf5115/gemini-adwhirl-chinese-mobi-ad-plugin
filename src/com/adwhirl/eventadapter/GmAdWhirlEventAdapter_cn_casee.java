package com.adwhirl.eventadapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.eventadapter.GmAdWhirlEventHandler.GmEventADType;
import com.adwhirl.eventadapter.IGmAdWhirlEventAdapter.GmAdWhirlCustomEventAdapter;
import com.adwhirl.obj.Extra;
import com.casee.adsdk.CaseeAdView;
import com.casee.adsdk.CaseeAdView.AdListener;

class GmAdWhirlEventAdapter_cn_casee extends GmAdWhirlCustomEventAdapter
		implements AdListener {

	public GmAdWhirlEventAdapter_cn_casee(AdWhirlLayout adLayout, Object obj) {
		super(adLayout, obj);
		// TODO Auto-generated constructor stub
	}

	private static final int CONST_INT_AD_REQUEST_INTERVAL = 10 * 1000;

	private CaseeAdView mADView;

	@Override
	protected void init(Object obj) {
		gmEventAdapterLog("casee->init");
		AdWhirlLayout adLayout = getAdwhirlLayout();
		if (adLayout != null) {
			Extra extra = adLayout.extra;
			int bgColor = Color.rgb(extra.bgRed, extra.bgGreen, extra.bgBlue);
			int fgColor = Color.rgb(extra.fgRed, extra.fgGreen, extra.fgBlue);

			// mADView = new CaseeAdView(
			// getAdwhirlActivity(), "D5636CAA41D035668A7D15DE9D3189E3", false,
			// 10 * 1000,//正式发布为false
			// Color.BLACK, Color.WHITE, false);
			// mADView.setListener(this);
			//
			// RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
			// RelativeLayout.LayoutParams.FILL_PARENT,
			// RelativeLayout.LayoutParams.WRAP_CONTENT);
			// lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			// adLayout.addView(mADView, lp);
			addCaseeViewAD(getAdwhirlActivity());
			// Show AD for certain seconds (extra.cycleTime).
			rotateThreadedDelayed();
			gmEventAdapterLog("casee->rotateThreadedDelayed");
		}
	}

	private CaseeAdView addCaseeViewAD(Activity activity) {

		// 创建广告条的容器
		RelativeLayout relativeLayout = new RelativeLayout(activity);
		LayoutParams params = new LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.FILL_PARENT);
		activity.addContentView(relativeLayout, params);

		CaseeAdView cav = new CaseeAdView(activity,
				GmAdWhirlEventAdapterData.getPublishID(GmEventADType.casee),
				GmAdWhirlEventAdapterData.getDebugEnabled(GmEventADType.casee),
				10 * 1000,// 正式发布为false
				Color.BLACK, Color.WHITE, false);

		// CaseeAdView cav = new CaseeAdView(activity,
		// "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", true, 10 * 1000,//测试模式为true
		// Color.BLACK, Color.WHITE, false);
		//
		params = new LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		// params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		relativeLayout.addView(cav, params);
		return cav;
	}

	@Override
	public void onFailedToReceiveAd(CaseeAdView arg0) {
		gmEventAdapterLog("casee->onFailedToReceiveAd");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				doRollover();
				setAdFetchDone(true);
				gmEventAdapterLog("casee->doRollover");
			}
		}
	}

	@Override
	public void onFailedToReceiveRefreshAd(CaseeAdView arg0) {
		gmEventAdapterLog("casee->onFailedToReceiveRefreshAd");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				doRollover();
				setAdFetchDone(true);
				gmEventAdapterLog("casee->doRollover");
			}
		}
	}

	@Override
	public void onReceiveAd(CaseeAdView arg0) {
		gmEventAdapterLog("casee->onReceiveAd");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				resetRollover(mADView);
				setAdFetchDone(true);
				gmEventAdapterLog("casee->resetRollover");
			} else {
				countImpression();
			}
		}
	}

	@Override
	public void onReceiveRefreshAd(CaseeAdView arg0) {
		gmEventAdapterLog("casee->onReceiveRefreshAd");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				resetRollover(mADView);
				setAdFetchDone(true);
				gmEventAdapterLog("casee->resetRollover");
			} else {
				countImpression();
			}
		}
	}

}
