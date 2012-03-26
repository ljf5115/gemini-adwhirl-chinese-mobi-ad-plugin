package com.adwhirl.eventadapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;

import com.adchina.android.ads.AdEngine;
import com.adchina.android.ads.AdListener;
import com.adchina.android.ads.AdManager;
import com.adchina.android.ads.views.AdView;
import com.adchina.android.ads.views.animations.AnimationManager;
import com.adwhirl.AdWhirlLayout;
import com.adwhirl.eventadapter.GmAdWhirlEventHandler.GmEventADType;
import com.adwhirl.eventadapter.IGmAdWhirlEventAdapter.GmAdWhirlCustomEventAdapter;
import com.adwhirl.obj.Extra;

public class GmAdWhirlEventAdapter_cn_adchina extends
		GmAdWhirlCustomEventAdapter implements AdListener {

	private AdView mADView;

	static {
		AdManager.setAdspaceId(GmAdWhirlEventAdapterData
				.getPublishID(GmEventADType.adchina));
		// AdManager.setShrinkFSAdspaceId("70213");
		// AdManager.setFullScreenAdspaceId("72937");
		// AdManager.setVideoAdspaceId("71018");
		AdManager.setDebugMode(GmAdWhirlEventAdapterData
				.getDebugEnabled(GmEventADType.adchina));
		AdManager
				.setLogMode(true);
		AdManager.setAnimations(AnimationManager.ANY_ANIMATION);
	}

	public GmAdWhirlEventAdapter_cn_adchina(AdWhirlLayout adLayout, Object obj) {
		super(adLayout, obj);
	}

	@Override
	protected void init(Object obj) {
		gmEventAdapterLog("adchina->init");
		AdWhirlLayout adLayout = getAdwhirlLayout();
		Activity activity = getAdwhirlActivity();
		if ((adLayout != null) && (activity != null)) {
			Extra extra = adLayout.extra;
			int bgColor = Color.rgb(extra.bgRed, extra.bgGreen, extra.bgBlue);
			int fgColor = Color.rgb(extra.fgRed, extra.fgGreen, extra.fgBlue);
			AdManager.setAdWindowBackgroundColor(bgColor);
			AdManager.setAdWindowBackgroundOpacity(fgColor);
			// AdManager.setShowFullScreenTimer(true);
			// AdManager.setFullScreenTimerBgColor(0xaaaaaaaa);
			AdManager.setAppName(activity.getPackageName());

			Display display = activity.getWindowManager().getDefaultDisplay();
			String resolution = display.getWidth() + "x" + display.getHeight();
			AdManager.setResolution(resolution);
			final AdEngine engine = AdEngine
					.initAdEngine(adLayout.getContext());
			AdEngine.setAdListener(this);

			mADView = new AdView(adLayout.getContext());
			mADView.setVisibility(View.GONE);
			// 建议按宽高20:3的比例设置广告展示区
			// mAdView.setLayoutParams(new LinearLayout.LayoutParams(display
			// .getWidth(), display.getWidth() * 3 / 20));

			// RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
			// RelativeLayout.LayoutParams.FILL_PARENT,
			// RelativeLayout.LayoutParams.WRAP_CONTENT);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					display.getWidth(), display.getWidth() * 3 / 20);
			adLayout.addView(mADView, lp);
			engine.addBannerAdView(mADView);
			engine.startBannerAd();

			// Show AD for certain seconds (extra.cycleTime).
			rotateThreadedDelayed();
			gmEventAdapterLog("adchina->rotateThreadedDelayed");
		}
	}

	@Override
	public void dispose() {
		gmEventAdapterLog("adchina->dispose");
		AdWhirlLayout tmpLayout = getAdwhirlLayout();
		if (tmpLayout != null) {
			if (mADView != null) {
				// mAdView.setVisibility(View.GONE);
				final AdEngine engine = AdEngine.getAdEngine();
				engine.stopBannerAd();
				engine.removeBannerAdView(mADView);
				tmpLayout.removeView(mADView);
				mADView = null;
				gmEventAdapterLog("adchina->removed");
			}
		}
		super.dispose();
	}

	@Override
	public boolean OnRecvSms(AdView arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onDisplayFullScreenAd() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFailedToPlayVideoAd() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFailedToReceiveAd(AdView arg0) {
		gmEventAdapterLog("adchina->onFailedToReceiveAd");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				doRollover();
				setAdFetchDone(true);
				gmEventAdapterLog("adchina->doRollover");
			}
		}
	}

	@Override
	public void onFailedToReceiveFullScreenAd() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFailedToReceiveVideoAd() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFailedToRefreshAd(AdView arg0) {
		gmEventAdapterLog("adchina->onFailedToRefreshAd");
		if (mADView != null) {
			if (!isAdFetchDone()) {
				doRollover();
				setAdFetchDone(true);
				gmEventAdapterLog("adchina->doRollover");
			}
		}
	}

	@Override
	public void onPlayVideoAd() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onReceiveAd(AdView arg0) {
		gmEventAdapterLog("adchina->onReceiveAd");
		if (mADView != null) {
			if (!isAdFetchDone()) {
				mADView.setVisibility(View.VISIBLE);
				resetRolloverOnly();
				setAdFetchDone(true);
				gmEventAdapterLog("adchina->resetRollover");
			} else {
				countImpression();
			}
		}
	}

	@Override
	public void onReceiveFullScreenAd() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRefreshAd(AdView arg0) {
		gmEventAdapterLog("adchina->onRefreshAd");
		if (mADView != null) {
			if (!isAdFetchDone()) {
				mADView.setVisibility(View.VISIBLE);
				resetRolloverOnly();
				setAdFetchDone(true);
				gmEventAdapterLog("adchina->resetRollover");
			} else {
				countImpression();
			}
		}
	}

	@Override
	public void onReceiveVideoAd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEndFullScreenLandpage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStartFullScreenLandPage() {
		// TODO Auto-generated method stub
		
	}

}
