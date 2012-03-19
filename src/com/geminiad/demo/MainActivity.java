package com.geminiad.demo;

import org.json.JSONObject;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.AdWhirlLayout.IGmOnEventAdapterChangedListener;
import com.adwhirl.AdWhirlManager;
import com.adwhirl.AdWhirlManager.IGmOnCustomConfigListener;
import com.adwhirl.eventadapter.GmAdWhirlEventAdapterData;
import com.adwhirl.eventadapter.GmAdWhirlEventHandler.GmEventADType;
import com.mobclick.android.MobclickAgent;
import com.mobclick.android.UmengOnlineConfigureListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String CONST_STR_WEBVIEW_URL = "file:///android_asset/welcome.htm";

	private WebView mWebView = null;
	private Button mSelectADButton = null;
	private Button mShowAppShareButton = null;
	private Handler mHandler = null;

	private boolean mOnlinParamReady = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mHandler = new Handler();

		RelativeLayout tmpLayout = null;
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		// --------------------------------------------------------------------------------
		// 显示底部广告
		// --------------------------------------------------------------------------------
		tmpLayout = (RelativeLayout) findViewById(R.id.gmADBottomLayout);
		if (tmpLayout != null) {
			// 创建广告Layout对象，实际应用中这一句就够！
			// （注意：如果不使用TAPJOY，参数Handler可以传null）
			AdWhirlLayout ret = new AdWhirlLayout(this,
					GmAdWhirlEventAdapterData.CONST_STR_APPID_ADWHIRL, mHandler);

			// 下面这句是为显示广告商类型而设置的监听器，开发者自己代码中无需添加
			ret.setOnEventAdapterChangedListener(new IGmOnEventAdapterChangedListener() {

				@Override
				public void onEventAdapterChanged(GmEventADType adType) {
					if (adType.ordinal() < GmEventADType.values().length) {
						Toast.makeText(MainActivity.this,
								"正在展示的广告商:" + getADProviderName(adType),
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

			// 把广告添加到界面中
			tmpLayout.addView(ret, lp);
		}

		// --------------------------------------------------------------------------------
		// 选择强制显示的自定义AD类型
		// --------------------------------------------------------------------------------
		mSelectADButton = (Button) findViewById(R.id.gmBtn_SelectAD);
		mSelectADButton.setText("选择展示广告：" + getSeletedADName());
		mSelectADButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String[] items = new String[GmEventADType.values().length + 1];
				for (int i = 0; i < GmEventADType.values().length; i++) {
					items[i] = getADProviderName(GmEventADType.values()[i]);
				}
				items[items.length - 1] = CONST_STR_ADNAME_RANDOM;

				Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setIcon(R.drawable.appicon);
				builder.setTitle("选择自定义广告类型");
				builder.setSingleChoiceItems(items, getEventADIndex(),
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								setEventADIndex(which);
								dialog.dismiss();
							}
						});

				builder.create().show();
			}
		});

		// --------------------------------------------------------------------------------
		// 显示积分列表页面
		// --------------------------------------------------------------------------------
		mShowAppShareButton = (Button) findViewById(R.id.gmBtn_More);
		mShowAppShareButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, AppShareActivity.class);
				startActivity(intent);
			}
		});

		// 加载说明html文件
		mWebView = (WebView) findViewById(R.id.gmWebView);
		mWebView.loadUrl(CONST_STR_WEBVIEW_URL);

		// 获取在线参数
		MobclickAgent.updateOnlineConfig(this);
		MobclickAgent
				.setOnlineConfigureListener(new UmengOnlineConfigureListener() {

					@Override
					public void onDataReceived(JSONObject arg0) {
						mOnlinParamReady = true;
					}
				});

		// 调用自定义广告配置字符串
		// 字符串由工具自动生成
		AdWhirlManager.setCustomConfigListener(new IGmOnCustomConfigListener() {

			@Override
			public String getConfigString() {
				// 注：此函数将会被异步调用，故不必担心延迟影响主程序运行。
				// 开发者可以是通过访问自己设定的任意网址链接（可以参考或直接修改fetchConfig函数），
				// 也可以使用类似在线参数功能获取广告配置，
				// 只要返回对应生成的JASON字符串就行。
				// 关于与在线更新的线程同步之类的问题需要开发者自己写代码解决，这里不做细究。
				return MobclickAgent.getConfigParams(MainActivity.this,
						"adconfig");
			}
		});

		// 调用友盟的自动更新功能
		// （用来检查本DEMO程序是否有新版本，新版本将会提供更多广告资讯哦！）
		MobclickAgent.update(this);
	}

	@Override
	protected void onPause() {
		// 友盟统计
		MobclickAgent.onPause(this);
		super.onPause();
	}

	@Override
	protected void onResume() {
		// 友盟统计
		MobclickAgent.onResume(this);
		super.onResume();

	}

	private static final String CONST_STR_ADNAME_RANDOM = "随机广告（默认）";

	// 广告商名称
	public static String getADProviderName(GmEventADType adType) {
		String ret = "\n新广告商";
		switch (adType) {
		case tapjoy:
			ret = "TAPJOY";
			break;
		case mobfox:
			ret = "MOBFOX";
			break;
		case domob:
			ret = "多盟广告";
			break;
		case smartmad:
			ret = "亿动智道";
			break;
		case adwo:
			ret = "安沃传媒";
			break;
		case wooboo:
			ret = "哇棒";
			break;
		case wiyun:
			ret = "微云";
			break;
		case youmi:
			ret = "有米广告";
			break;
		case vpon:
			ret = "VPON";
			break;
		case vpontw:
			ret = "VPON(台湾)";
			break;
		case dipai:
			ret = "米迪（迪派）";
			break;
		case lmmob:
			ret = "力美广告";
			break;
		case baidu:
			ret = "百度移动联盟";
			break;
		case waps:
			ret = "万普世纪传媒";
			break;
		case anji:
			ret = "安机互联";
			break;
		case adchina:
			ret = "易传媒";
			break;
		case airad:
			ret = "AirAD";
			break;
		case mobwin:
			ret = "腾讯聚赢";
			break;
		case appmedia:
			ret = "AppMedia";
			break;
		case zhidian:
			ret = "指点传媒";
			break;
		case appjoy:
			ret = "AppJoy";
			break;
		case winad:
			ret = "赢告";
			break;
		case casee:
			ret = "架势";
			break;
		case wqmobile:
			ret = "帷千动媒";
			break;
		}
		return ret;
	}

	private int getEventADIndex() {
		int ret = GmEventADType.values().length;
		if (GmAdWhirlEventAdapterData.isForceEventADMode()) {
			ret = GmAdWhirlEventAdapterData.getForceEventADType().ordinal();
		}
		return ret;
	}

	private String getSeletedADName() {
		String ret = CONST_STR_ADNAME_RANDOM;
		if (GmAdWhirlEventAdapterData.isForceEventADMode()) {
			ret = getADProviderName(GmAdWhirlEventAdapterData
					.getForceEventADType());
		}
		return ret;
	}

	private void setEventADIndex(int index) {
		if ((index < 0) || (index >= GmEventADType.values().length)) {
			GmAdWhirlEventAdapterData.setForceEventADMode(false);
		} else {
			GmAdWhirlEventAdapterData.setForceEventADMode(true);
			GmAdWhirlEventAdapterData.setForceEventADType(GmEventADType
					.values()[index]);
		}
		String tmpADName = getSeletedADName();
		mSelectADButton.setText("选择展示广告商：" + tmpADName);
		Toast.makeText(MainActivity.this, "下个广告开始展示：" + tmpADName,
				Toast.LENGTH_SHORT).show();
	}

}