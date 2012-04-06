package com.adwhirl.eventadapter;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.eventadapter.IGmAdWhirlEventAdapter.GmAdWhirlCustomEventAdapter;
import com.dianru.sdk.AdLoader;
import com.dianru.sdk.AdSpace;

public class GmAdWhirlEventAdapter_cn_dianru extends GmAdWhirlCustomEventAdapter {
	AdSpace mAdSpace = null;
	public GmAdWhirlEventAdapter_cn_dianru(AdWhirlLayout adLayout, Object obj) {
		super(adLayout, obj);
	}

	@Override
	protected void init(Object obj) {
		gmEventAdapterLog("dianru->init");
		AdWhirlLayout adLayout = getAdwhirlLayout();
		if (adLayout != null) {
//			final RelativeLayout mainLayout = new RelativeLayout(getAdwhirlActivity());
			mAdSpace = new AdSpace(getAdwhirlActivity());
//			mAdSpace.setInterval(30);	//设置广告刷新时间
//			mAdSpace.setType(0);		//设置广告类型，此处其值为0
//			mAdSpace.setKeyword("game");//设置关键字
			LayoutParams lp = new RelativeLayout.LayoutParams(
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
			lp.addRule(RelativeLayout.CENTER_VERTICAL);
			adLayout.addView(mAdSpace, lp);
//			mainLayout.addView(space, lp);

			// Show AD for certain seconds (extra.cycleTime).
			rotateThreadedDelayed();
			gmEventAdapterLog("dianru->rotateThreadedDelayed");
		}
	}

	@Override
	public void dispose() {
		gmEventAdapterLog("dianru->dispose");
		AdWhirlLayout tmpLayout = getAdwhirlLayout();
		if (tmpLayout != null) {
			if (mAdSpace != null) {
				mAdSpace.setVisibility(View.GONE);
				// mADView.setListener(null);
				tmpLayout.removeView(mAdSpace);
				mAdSpace = null;
				gmEventAdapterLog("dianru->removed");
			}
		}
    	AdLoader.destroy();	
		super.dispose();
	}

}
