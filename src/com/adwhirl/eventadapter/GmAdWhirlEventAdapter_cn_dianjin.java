package com.adwhirl.eventadapter;

import android.graphics.Color;
import android.view.View;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.eventadapter.GmAdWhirlEventHandler.GmEventADType;
import com.adwhirl.eventadapter.IGmAdWhirlEventAdapter.GmAdWhirlCustomEventAdapter;
import com.nd.dianjin.OfferBanner;
import com.nd.dianjin.utility.BannerColorConfig;

public class GmAdWhirlEventAdapter_cn_dianjin extends GmAdWhirlCustomEventAdapter {
	OfferBanner mOfferBanner = null;
	public GmAdWhirlEventAdapter_cn_dianjin(AdWhirlLayout adLayout, Object obj) {
		super(adLayout, obj);
	}

	@Override
	protected void init(Object obj) {
		gmEventAdapterLog("dianjin->init");
		AdWhirlLayout adLayout = getAdwhirlLayout();
		if (adLayout != null) {
//			final RelativeLayout mainLayout = new RelativeLayout(getAdwhirlActivity());
			BannerColorConfig colorConfig = new BannerColorConfig();
			colorConfig.setBackgroundColor(Color.BLUE);
			colorConfig.setDetailColor(0xFF3300);
			colorConfig.setNameColor(0xFF3300);
			colorConfig.setRewardColor(0xFF3300);
			int id = 0;
			try {
				id = Integer.parseInt(GmAdWhirlEventAdapterData
						.getPublishID(GmEventADType.dianjin));
			} catch (Exception e) {

			}	
			mOfferBanner = new OfferBanner(getAdwhirlActivity(), id,
					GmAdWhirlEventAdapterData.getDefaultPwdID(GmEventADType.dianjin),
					6000, OfferBanner.AnimationType.ANIMATION_PUSHUP, colorConfig);
//			mainLayout.addView(space, lp);

			adLayout.addView(mOfferBanner);
			// Show AD for certain seconds (extra.cycleTime).
			rotateThreadedDelayed();
			gmEventAdapterLog("dianjin->rotateThreadedDelayed");
		}
	}

	@Override
	public void dispose() {
		gmEventAdapterLog("dianjin->dispose");
		AdWhirlLayout tmpLayout = getAdwhirlLayout();
		if (tmpLayout != null) {
			if (mOfferBanner != null) {
				mOfferBanner.setVisibility(View.GONE);
				// mADView.setListener(null);
				tmpLayout.removeView(mOfferBanner);
				mOfferBanner = null;
				gmEventAdapterLog("dianjin->removed");
			}
		}
		super.dispose();
	}

}
