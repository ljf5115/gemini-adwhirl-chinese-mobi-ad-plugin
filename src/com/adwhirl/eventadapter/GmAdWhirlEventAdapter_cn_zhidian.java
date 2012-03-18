package com.adwhirl.eventadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.eventadapter.IGmAdWhirlEventAdapter.GmAdWhirlCustomEventAdapter;
import com.adzhidian.sundry.AdListener;
import com.adzhidian.view.AdView;

public class GmAdWhirlEventAdapter_cn_zhidian extends
		GmAdWhirlCustomEventAdapter implements AdListener {

	private GmZhidianLayout mADView;

	public GmAdWhirlEventAdapter_cn_zhidian(AdWhirlLayout adLayout, Object obj) {
		super(adLayout, obj);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init(Object obj) {
		gmEventAdapterLog("zhidian->init");
		AdWhirlLayout adLayout = getAdwhirlLayout();
		if (adLayout != null) {
			// Extra extra = adLayout.extra;
			// int bgColor = Color.rgb(extra.bgRed, extra.bgGreen,
			// extra.bgBlue);
			// int fgColor = Color.rgb(extra.fgRed, extra.fgGreen,
			// extra.fgBlue);

			mADView = new GmZhidianLayout(getAdwhirlActivity());
			AdView tmpADView = new AdView(getAdwhirlActivity());
			tmpADView.setReceiveAdListener(this);
			mADView.getInternalLayout().addView(tmpADView);
			mADView.setTag(tmpADView);

			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.FILL_PARENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			adLayout.addView(mADView, lp);

			// Show AD for certain seconds (extra.cycleTime).
			rotateThreadedDelayed();
			gmEventAdapterLog("zhidian->rotateThreadedDelayed");
		}
	}

	@Override
	public void dispose() {
		gmEventAdapterLog("zhidian->dispose");
		AdWhirlLayout tmpLayout = getAdwhirlLayout();
		if (tmpLayout != null) {
			if (mADView != null) {
				mADView.setVisibility(View.GONE);
				if (mADView.getTag() != null) {
					AdView tmpADView = (AdView) mADView.getTag();
					tmpADView.setReceiveAdListener(null);
					tmpADView.unRegisterBroadCast(getAdwhirlActivity());
					// mADView.removeAllViews();
				}
				tmpLayout.removeView(mADView);
				mADView = null;
				gmEventAdapterLog("zhidian->removed");
			}
		}
		super.dispose();
	}

	@Override
	public void onFailedToReceiveAd() {
		gmEventAdapterLog("zhidian->onFailedToReceiveAd");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				doRollover();
				setAdFetchDone(true);
				gmEventAdapterLog("zhidian->doRollover");
			}
		}
	}

	@Override
	public void onReceiveAd() {
		gmEventAdapterLog("zhidian->onReceiveAd");
		if (isActiveAdView(mADView)) {
			if (!isAdFetchDone()) {
				resetRollover(mADView);
				setAdFetchDone(true);
				gmEventAdapterLog("zhidian->resetRollover");
			} else {
				countImpression();
			}
		}
	}

	// --------------------------------------------------------------------------------
	// GmZhidianLayout: This is a relative layout used for return as ADView.
	//
	// Because the Zhidian' adView does not return a proper View class and
	// it need lock a LinearLayout size to 50dip. So I use a GmWAPSLayout which
	// contains a LinearLayout GmZhidianLayout used to display AD.
	// When a new AdView is added to GmZhidianInternalLayout,
	// the override addView function will set GmZhidianInternalLayout to 50dip.
	// --------------------------------------------------------------------------------
	public static class GmZhidianLayout extends RelativeLayout {

		private GmZhidianInternalLayout mInternalLayout = null;

		public GmZhidianLayout(Context context) {
			super(context);
			mInternalLayout = new GmZhidianInternalLayout(context);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
			this.addView(mInternalLayout, lp);
		}

		public LinearLayout getInternalLayout() {
			return mInternalLayout;
		}

		// Tag is used to store the real adView(which is not a View class)
		// object return from WAPS.
		private Object mTag = null;

		public Object getTag() {
			return mTag;
		}

		public void setTag(Object tag) {
			mTag = tag;
		}

		// --------------------------------------------------------------------------------
		// GmWAPSInternalLayout
		// --------------------------------------------------------------------------------
		public static class GmZhidianInternalLayout extends LinearLayout {

			public GmZhidianInternalLayout(Context context) {
				super(context);
			}

			@Override
			public void addView(View child) {
				// When add new AdView, convert adView's layout before add to
				// internal layout.
				child.setLayoutParams(new ViewGroup.LayoutParams(
						LayoutParams.FILL_PARENT, convertDIP2Pixel(
								getContext(), 50)));
				super.addView(child);
			}
		}
	}
}
