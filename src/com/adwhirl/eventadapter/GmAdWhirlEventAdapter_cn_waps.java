package com.adwhirl.eventadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.eventadapter.IGmAdWhirlEventAdapter.GmAdWhirlCustomEventAdapter;
import com.waps.AdView;
import com.waps.AppConnect;

public class GmAdWhirlEventAdapter_cn_waps extends GmAdWhirlCustomEventAdapter {

	private GmWAPSLayout mADView;

	public GmAdWhirlEventAdapter_cn_waps(AdWhirlLayout adLayout, Object obj) {
		super(adLayout, obj);
		AppConnect.getInstance(getAdwhirlLayout().getContext());
	}

	@Override
	protected void init(Object obj) {
		gmEventAdapterLog("waps->init");
		AdWhirlLayout adLayout = getAdwhirlLayout();
		if (adLayout != null) {

			GmWAPSLayout mADView = new GmWAPSLayout(getAdwhirlActivity());
			AdView tmpADView = new AdView(getAdwhirlActivity(),
					mADView.getInternalLayout());
			mADView.setTag(tmpADView);

			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.FILL_PARENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			adLayout.addView(mADView, lp);

			tmpADView.DisplayAd();

			// Show AD for certain seconds (extra.cycleTime).
			rotateThreadedDelayed();
			gmEventAdapterLog("waps->rotateThreadedDelayed");
		}
	}

	@Override
	public void dispose() {
		gmEventAdapterLog("waps->dispose");
		AdWhirlLayout tmpLayout = getAdwhirlLayout();
		if (tmpLayout != null) {
			if (mADView != null) {
				mADView.setVisibility(View.GONE);
				//mADView.setListener(null);
				tmpLayout.removeView(mADView);
				mADView = null;
				gmEventAdapterLog("waps->removed");
			}
		}
		super.dispose();
	}

	// --------------------------------------------------------------------------------
	// GmWAPSLayout: This is a relative layout used for return as ADView.
	//
	// Because the WAPS' adView does not return a proper View class and
	// it need lock a LinearLayout size to 50dip. So I use a GmWAPSLayout which
	// contains a LinearLayout GmWAPSInternalLayout used to display AD.
	// When a new AdView is added to GmWAPSInternalLayout,
	// the override addView function will set GmWAPSInternalLayout to 50dip.
	// --------------------------------------------------------------------------------
	public static class GmWAPSLayout extends RelativeLayout {

		private GmWAPSInternalLayout mInternalLayout = null;

		public GmWAPSLayout(Context context) {
			super(context);
			mInternalLayout = new GmWAPSInternalLayout(context);
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
		public static class GmWAPSInternalLayout extends LinearLayout {

			public GmWAPSInternalLayout(Context context) {
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
