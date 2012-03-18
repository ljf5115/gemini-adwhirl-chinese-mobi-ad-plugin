package com.adwhirl.eventadapter;

import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.eventadapter.GmAdWhirlEventHandler.GmEventADType;
import com.adwhirl.eventadapter.IGmAdWhirlEventAdapter.GmAdWhirlCustomEventAdapter;
import com.adwhirl.obj.Extra;
import com.antutu.adtutu.TutuAdView;

public class GmAdWhirlEventAdapter_cn_anji extends GmAdWhirlCustomEventAdapter {

	private TutuAdView mADView;

	public GmAdWhirlEventAdapter_cn_anji(AdWhirlLayout adLayout, Object obj) {
		super(adLayout, obj);
	}

	@Override
	protected void init(Object obj) {
		gmEventAdapterLog("anji->init");
		AdWhirlLayout adLayout = getAdwhirlLayout();
		if (adLayout != null) {
			Extra extra = adLayout.extra;
			int bgColor = Color.rgb(extra.bgRed, extra.bgGreen, extra.bgBlue);
			int fgColor = Color.rgb(extra.fgRed, extra.fgGreen, extra.fgBlue);

			mADView = new TutuAdView(getAdwhirlActivity());
			mADView.SetUserID(GmAdWhirlEventAdapterData
					.getPublishID(GmEventADType.anji));
			mADView.setBackgroundColor(bgColor);
			mADView.setTextColor(fgColor);
			mADView.setTextSize(24);

			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.FILL_PARENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			adLayout.addView(mADView, lp);

			// Show AD for certain seconds (extra.cycleTime).
			rotateThreadedDelayed();
			gmEventAdapterLog("anji->rotateThreadedDelayed");
		}
	}

	@Override
	public void dispose() {
		gmEventAdapterLog("anji->dispose");
		AdWhirlLayout tmpLayout = getAdwhirlLayout();
		if (tmpLayout != null) {
			if (mADView != null) {
				mADView.setVisibility(View.GONE);
				// mADView.setListener(null);
				tmpLayout.removeView(mADView);
				mADView = null;
				gmEventAdapterLog("anji->removed");
			}
		}
		super.dispose();
	}

}
