package com.geminiad.appshare;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

public class GmAppShareManager {

	public static final String CONST_STR_APPSAHREMANAGER = "GmAppShareManager";
	public static final String CONST_STR_ILLEGAL_ARGUMENT = "Illegal Argument";

	public static enum GmAppShareProviderType {
		none, tapjoy, waps, youmi, wiyun, dipai, zhidian, appjoy, winad, dianjoy,
	}

	public GmAppShareManager(Context context) {
		if (context == null) {
			Log.d(CONST_STR_APPSAHREMANAGER, CONST_STR_ILLEGAL_ARGUMENT);
		}
		mContext = context;
		mAppShareProviders = new LinkedList<IGmCustomAppShareProvider>();
	}

	private Context mContext = null;

	public Context getContext() {
		return mContext;
	}

	private List<IGmCustomAppShareProvider> mAppShareProviders = null;

	public List<IGmCustomAppShareProvider> getAppShareProviders() {
		return mAppShareProviders;
	}

	// In override class, this function will create AppShareprovider according
	// to Locale type
	public final IGmCustomAppShareProvider getAppShareProvider(int flag) {
		IGmCustomAppShareProvider ret = null;

		if (mAppShareProviders.size() > 0) {
			if (flag >= mAppShareProviders.size()) {
				flag = 0;
			}
			ret = mAppShareProviders.get(flag);
		}
		return ret;
	}

	// --------------------------------------------------------------------------------
	// IGmCustomAppShareProvider
	// --------------------------------------------------------------------------------
	public interface IGmCustomAppShareProvider {
		boolean getSupportAppShare(Activity activity);

		GmAppShareProviderType getAppShareProviderType();

		void performAppShare(Activity activity, IGmPerformOnAppShareObserver ls);
	}

	// --------------------------------------------------------------------------------
	// IGmPerformOnAppShareObserver
	// --------------------------------------------------------------------------------
	public interface IGmPerformOnAppShareObserver {
		void onPerformAppShareFailed();
	}

	// --------------------------------------------------------------------------------
	// GmCustomAppShareProvider
	// --------------------------------------------------------------------------------
	public abstract static class GmCustomAppShareProvider implements
			IGmCustomAppShareProvider {

		private GmAppShareManager mADManager = null;

		public GmAppShareManager getADMAnager() {
			return mADManager;
		}

		public GmCustomAppShareProvider(GmAppShareManager adManager) {
			mADManager = adManager;
		}
	}

	// --------------------------------------------------------------------------------
	// GmDummyAppShareProvider
	// --------------------------------------------------------------------------------
	public static class GmDummyAppShareProvider extends
			GmCustomAppShareProvider {

		public GmDummyAppShareProvider(GmAppShareManager adManager) {
			super(adManager);
		}

		@Override
		public boolean getSupportAppShare(Activity activity) {
			return false;
		}

		@Override
		public void performAppShare(Activity activity,
				IGmPerformOnAppShareObserver ls) {
		}

		@Override
		public GmAppShareProviderType getAppShareProviderType() {
			return GmAppShareProviderType.none;
		}
	}

}
