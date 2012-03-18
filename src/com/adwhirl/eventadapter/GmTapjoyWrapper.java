package com.adwhirl.eventadapter;

import com.tapjoy.TapjoyConnect;
import com.tapjoy.TapjoyEarnedPointsNotifier;
import com.tapjoy.TapjoyLog;
import com.tapjoy.TapjoyNotifier;
import com.tapjoy.TapjoySpendPointsNotifier;

import android.content.Context;

public class GmTapjoyWrapper {

	private static boolean mInitializedFlag = false;

	private static TapjoyEarnedPointsNotifier mEarnedPointsNotifier = new TapjoyEarnedPointsNotifier() {

		@Override
		public void earnedTapPoints(int amount) {
			// TODO Auto-generated method stub

		}
	};

	public static void initizlize(Context context, String publishId,
			String password) {
		if (!mInitializedFlag) {

			// Enables logging to the console.
			TapjoyLog.enableLogging(true);
			// Connect with the Tapjoy server. Call this when the application
			// first starts.
			// REPLACE THE APP ID WITH YOUR TAPJOY APP ID.
			// REPLACE THE SECRET KEY WITH YOUR SECRET KEY.
			TapjoyConnect.requestTapjoyConnect(context, publishId, password);

			// For PAID APPS ONLY. Replace your Paid App Pay-Per-Action ID as
			// the parameter.
			// TapjoyConnect.getTapjoyConnectInstance(this).enablePaidAppWithActionID("ENTER_YOUR_PAID_APP_ACTION_ID_HERE");

			// Set our earned points notifier to this class.
			TapjoyConnect.getTapjoyConnectInstance().setEarnedPointsNotifier(
					mEarnedPointsNotifier);

			mInitializedFlag = true;
		}
	}

	public static void showOffers() {
		// This will show Offers web view from where you can download the latest
		// offers.
		// Note: If you want to provide your own publisher id then use following
		// method to show offer web view:
		// TapjoyOffers.getTapjoyOffersInstance().showOffers(this,
		// "provide here publisher id");
		TapjoyConnect.getTapjoyConnectInstance().showOffers();
	}

	// --------------------------------------------------------------------------------
	// GmCustomTapjoyNotifier
	// --------------------------------------------------------------------------------
	public static class GmCustomTapjoyNotifier {

		public GmCustomTapjoyNotifier() {
			mProcessDoneFlag = false;
		}

		public void reset() {
			mProcessDoneFlag = false;
		}

		protected boolean mProcessDoneFlag = false;

		public boolean getProcessDoneFlag() {
			return mProcessDoneFlag;
		}

		protected boolean mSucceededFlag = false;

		public boolean getSucceededFlag() {
			return mSucceededFlag;
		}

		protected int mTotal = 0;

		public int getTotal() {
			return mTotal;
		}

	}

	// --------------------------------------------------------------------------------
	// GmTapjoyNotifier
	// --------------------------------------------------------------------------------
	public static class GmTapjoyNotifier extends GmCustomTapjoyNotifier
			implements TapjoyNotifier {

		public GmTapjoyNotifier() {
			super();
		}

		@Override
		public void getUpdatePoints(String currencyName, int pointTotal) {
			mSucceededFlag = true;
			mTotal = pointTotal;
			mProcessDoneFlag = true;
		}

		@Override
		public void getUpdatePointsFailed(String error) {
			mSucceededFlag = false;
			mProcessDoneFlag = true;
		}

	}

	// --------------------------------------------------------------------------------
	// GmTapjoySpendPointsNotifier
	// --------------------------------------------------------------------------------
	public static class GmTapjoySpendPointsNotifier extends
			GmCustomTapjoyNotifier implements TapjoySpendPointsNotifier {

		@Override
		public void getSpendPointsResponse(String currencyName, int pointTotal) {
			mSucceededFlag = true;
			mTotal = pointTotal;
			mProcessDoneFlag = true;
		}

		@Override
		public void getSpendPointsResponseFailed(String error) {
			mSucceededFlag = false;
			mProcessDoneFlag = true;
		}
	}

}
