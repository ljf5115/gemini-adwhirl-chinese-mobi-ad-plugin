package com.adwhirl.eventadapter;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.eventadapter.IGmAdWhirlEventAdapter.GmAdWhirlCustomEventAdapter;
import com.baidu.AdType;
import com.baidu.AdView;
import com.baidu.AdViewListener;
import com.baidu.FailReason;

public class GmAdWhirlEventAdapter_cn_baidu extends GmAdWhirlCustomEventAdapter
		implements AdViewListener {

	private static List<WeakReference<AdView>> mAdViewList = new LinkedList<WeakReference<AdView>>();

	public AdView getAdViewInList(Context context) {
		AdView ret = null;

		if (context != null) {
			for (WeakReference<AdView> tmpAdViewRef : mAdViewList) {
				AdView tmpAdView = tmpAdViewRef.get();
				if (tmpAdView != null) {
					if (tmpAdView.getContext() == context) {
						ret = tmpAdView;
						break;
					}
				}
			}
		}
		return ret;
	}

	public GmAdWhirlEventAdapter_cn_baidu(AdWhirlLayout adLayout, Object obj) {
		super(adLayout, obj);
	}

	@Override
	protected void init(Object obj) {
		gmEventAdapterLog("baidu->init");
		AdWhirlLayout adLayout = getAdwhirlLayout();
		if (adLayout != null) {
			AdView tmpADView = getAdViewInList(getAdwhirlActivity());
			if (tmpADView == null) {
				tmpADView = new AdView(getAdwhirlActivity(), AdType.IMAGE);
				mAdViewList.add(new WeakReference<AdView>(tmpADView));
			}
			if (tmpADView != null) {
				tmpADView.setVisibility(View.VISIBLE);
				tmpADView.setListener(this);
				RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
						RelativeLayout.LayoutParams.FILL_PARENT,
						RelativeLayout.LayoutParams.WRAP_CONTENT);
				adLayout.addView(tmpADView, lp);

				// Show AD for certain seconds (extra.cycleTime).
				rotateThreadedDelayed();
				gmEventAdapterLog("baidu->rotateThreadedDelayed");
			}
		}
	}

	@Override
	public void dispose() {
		gmEventAdapterLog("baidu->dispose");
		AdWhirlLayout tmpLayout = getAdwhirlLayout();
		if (tmpLayout != null) {
			AdView tmpADView = getAdViewInList(getAdwhirlActivity());
			if (tmpADView != null) {
				tmpADView.setVisibility(View.GONE);
				tmpADView.setListener(null);
				tmpLayout.removeView(tmpADView);
				tmpADView = null;
				gmEventAdapterLog("baidu->removed");
			}
		}
		super.dispose();
	}

	@Override
	public void onAdSwitch() {
	}

	@Override
	public void onReceiveFail(FailReason arg0) {
		gmEventAdapterLog("baidu->onReceiveFail");
		AdView tmpADView = getAdViewInList(getAdwhirlActivity());
		if (isActiveAdView(tmpADView)) {
			if (!isAdFetchDone()) {
				doRollover();
				setAdFetchDone(true);
				gmEventAdapterLog("baidu->doRollover");
			}
		}
	}

	@Override
	public void onReceiveSuccess() {
		gmEventAdapterLog("baidu->onReceiveSuccess");
		AdView tmpADView = getAdViewInList(getAdwhirlActivity());
		if (isActiveAdView(tmpADView)) {
			if (!isAdFetchDone()) {
				resetRollover(tmpADView);
				setAdFetchDone(true);
				gmEventAdapterLog("baidu->resetRollover");
			} else {
				countImpression();
			}
		}
	}

}
