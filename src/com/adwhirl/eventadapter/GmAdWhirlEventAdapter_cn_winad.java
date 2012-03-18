package com.adwhirl.eventadapter;

import android.graphics.Color;
import android.widget.RelativeLayout;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.eventadapter.IGmAdWhirlEventAdapter.GmAdWhirlCustomEventAdapter;
import com.winad.android.banner.ads.AdView;

public class GmAdWhirlEventAdapter_cn_winad extends GmAdWhirlCustomEventAdapter {

	private AdView mADView;

	public GmAdWhirlEventAdapter_cn_winad(AdWhirlLayout adLayout, Object obj) {
		super(adLayout, obj);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init(Object obj) {
		gmEventAdapterLog("winad->init");
		AdWhirlLayout adLayout = getAdwhirlLayout();
		if (adLayout != null) {
			// Extra extra = adLayout.extra;
			// int bgColor = Color.rgb(extra.bgRed, extra.bgGreen,
			// extra.bgBlue);
			// int fgColor = Color.rgb(extra.fgRed, extra.fgGreen,
			// extra.fgBlue);
			mADView = new AdView(getAdwhirlActivity(), null, 0, 0, Color.WHITE,
					adLayout.extra.cycleTime);
			//mADView.setListener(this);

			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.FILL_PARENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			adLayout.addView(mADView, lp);

			// Show AD for certain seconds (extra.cycleTime).
			rotateThreadedDelayed();
			gmEventAdapterLog("winad->rotateThreadedDelayed");
		}
	}

	@Override
	public void dispose() {
		gmEventAdapterLog("winad->dispose");
		AdWhirlLayout tmpLayout = getAdwhirlLayout();
		if (tmpLayout != null) {
			if (mADView != null) {
				// //It seems that adwo do not like adView to be GONE.
				// mADView.setVisibility(View.GONE);
				//mADView.setListener(null);
				tmpLayout.removeView(mADView);
				mADView = null;
				gmEventAdapterLog("winad->removed");
			}
		}
		super.dispose();
	}

}
