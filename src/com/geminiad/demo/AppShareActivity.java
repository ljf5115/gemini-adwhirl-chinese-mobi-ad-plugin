package com.geminiad.demo;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.AdWhirlLayout.IGmOnEventAdapterChangedListener;
import com.adwhirl.eventadapter.GmAdWhirlEventAdapterData;
import com.adwhirl.eventadapter.GmAdWhirlEventHandler.GmEventADType;
import com.geminiad.appshare.GmAppShareProvider_Appjoy;
import com.geminiad.appshare.GmAppShareProvider_Dianjoy;
import com.geminiad.appshare.GmAppShareProvider_Dipai;
import com.geminiad.appshare.GmAppShareManager;
import com.geminiad.appshare.GmAppShareManager.GmAppShareProviderType;
import com.geminiad.appshare.GmAppShareManager.IGmCustomAppShareProvider;
import com.geminiad.appshare.GmAppShareManager.IGmPerformOnAppShareObserver;
import com.geminiad.appshare.GmAppShareProvider_Tapjoy;
import com.geminiad.appshare.GmAppShareProvider_Waps;
import com.geminiad.appshare.GmAppShareProvider_WinAd;
import com.geminiad.appshare.GmAppShareProvider_Wiyun;
import com.geminiad.appshare.GmAppShareProvider_Zhidian;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class AppShareActivity extends Activity {

	private Handler mHandler = null;
	private GmAppShareManager mAppShareManager = null;

	// 广告商名称
	public static String getADProviderName(GmAppShareProviderType adType) {
		String ret = "\n新广告商";
		switch (adType) {
		case tapjoy:
			ret = "Tapjoy AppOffer\n\n(国外积分墙，国内慢，适合国外发布)";
			break;
		case dipai:
			ret = "米迪（迪派）应用积分墙";
			break;
		case waps:
			ret = "万普应用推荐列表";
			break;
		case youmi:
			ret = "有米应用积分墙";
			break;
		case wiyun:
			ret = "微云应用推广墙";
			break;
		case zhidian:
			ret = "指点传媒应用墙";
			break;
		case appjoy:
			ret = "Appjoy应用墙";
			break;
		case winad:
			ret = "赢告积分墙";
			break;
		case dianjoy:
			ret = "点乐积分墙";
			break;
		}
		return ret;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.appsharemain);

		mHandler = new Handler();

		// 初始化需要使用的积分列表，可自由选择
		mAppShareManager = new GmAppShareManager(this);

		// 万普
		mAppShareManager.getAppShareProviders().add(
				new GmAppShareProvider_Waps(mAppShareManager));
		// 微云
		mAppShareManager.getAppShareProviders().add(
				new GmAppShareProvider_Wiyun(mAppShareManager));
		// 迪派
		mAppShareManager.getAppShareProviders().add(
				new GmAppShareProvider_Dipai(mAppShareManager));
		// 赢告
		mAppShareManager.getAppShareProviders().add(
				new GmAppShareProvider_WinAd(mAppShareManager));
		// 指点传媒
		mAppShareManager.getAppShareProviders().add(
				new GmAppShareProvider_Zhidian(mAppShareManager));
		// Appjoy
		mAppShareManager.getAppShareProviders().add(
				new GmAppShareProvider_Appjoy(mAppShareManager));
		// 点乐
		mAppShareManager.getAppShareProviders().add(
				new GmAppShareProvider_Dianjoy(mAppShareManager));
		// Tapjoy
		mAppShareManager.getAppShareProviders().add(
				new GmAppShareProvider_Tapjoy(mAppShareManager));

		initUIComponents();
	}

	private void initUIComponents() {
		LinearLayout tmpLinearLayout = (LinearLayout) findViewById(R.id.gmAppShareMainLayout);

		Button tmpButton = null;
		for (final IGmCustomAppShareProvider tmpProvider : mAppShareManager
				.getAppShareProviders()) {
			if ((tmpProvider != null)
					&& tmpProvider.getSupportAppShare(AppShareActivity.this)) {
				tmpButton = new Button(this);
				tmpButton.setText(getADProviderName(tmpProvider
						.getAppShareProviderType()));
				tmpButton.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						tmpProvider.performAppShare(AppShareActivity.this,
								new IGmPerformOnAppShareObserver() {

									@Override
									public void onPerformAppShareFailed() {
										Toast.makeText(AppShareActivity.this,
												"调用应用列表失败！", Toast.LENGTH_LONG);
									}
								});
					}
				});
				tmpLinearLayout.addView(tmpButton);
			}
		}

		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		// 底部广告
		RelativeLayout tmpRelativeLayout = (RelativeLayout) findViewById(R.id.gmAppShareADBottomLayout);
		if (tmpRelativeLayout != null) {
			AdWhirlLayout ret = new AdWhirlLayout(this,
					GmAdWhirlEventAdapterData.CONST_STR_APPID_ADWHIRL, mHandler);

			// 这句开发者无需加
			ret.setOnEventAdapterChangedListener(new IGmOnEventAdapterChangedListener() {

				@Override
				public void onEventAdapterChanged(GmEventADType adType) {
					if (adType.ordinal() < GmEventADType.values().length) {
						Toast.makeText(
								AppShareActivity.this,
								"正在展示的中文广告商:"
										+ MainActivity
												.getADProviderName(adType),
								Toast.LENGTH_LONG).show();
					}
				}

				@Override
				public void onImpression() {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onCount() {
					// TODO Auto-generated method stub
					
				}
			});
			tmpRelativeLayout.addView(ret, lp);
		}
	}
}
