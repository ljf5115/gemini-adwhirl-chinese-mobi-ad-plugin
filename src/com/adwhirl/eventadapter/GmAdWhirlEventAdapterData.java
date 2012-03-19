package com.adwhirl.eventadapter;

import java.util.List;

import android.util.Log;

import com.adwhirl.eventadapter.GmAdWhirlEventHandler.GmEventADType;
import com.adwhirl.obj.Extra;
import com.adwhirl.obj.Ration;
import com.adwhirl.util.AdWhirlUtil;

public class GmAdWhirlEventAdapterData {

	// --------------------------------------------------------------------------------
	// 以下为调试选项
	// --------------------------------------------------------------------------------
	// 是否为广告调试状态
	public static final boolean CONST_ADDEBUG_ENABLED = false;

	// 是否打印广告接口EventAdapter调试信息，便于查找广告SDK接口的问题
	public static final boolean CONST_EVENTADAPTERLOG_ENABLED = true;

	// 是否自动根据手机LOCALE的语言来限制展示广告类型。
	// 用途：手机语言为英文情况下，不展示中文广告，仅展示英文广告。
	// 注意：使用此功能，要求你的广告分配比例中至少有一项广告是不同目标语言的，
	// 例，中文为主的软件中需要至少有一个英文广告（或支持多语言的广告），如：ADMOB。
	public static final boolean CONST_FITADLANGUAGE_ENABLED = false;

	// 是否为强制读取服务器设置
	public static final boolean CONST_FORCEFETCHSERVER_ENABLED = false;

	// 是否为默认广告（例如admob）强制调试模式（如果想仅调试某个默认类型的广告，请选择强制模式）
	// 注意：此选项将覆盖自定义广告调试模式。
	public static final boolean CONST_DEFAULTADDEBUG_FORCEMODE_ENABLED = false;
	// 默认广告（例如admob）强制调试模式类型
	public static final int CONST_DEFAULTADDEBUG_FORCEMODE_TYPE = AdWhirlUtil.NETWORK_TYPE_ADMOB;

	// 是否为自定义广告强制调试模式（如果想仅调试某个类型的广告，请选择强制模式）
	public static final boolean CONST_EVENTADDEBUG_FORCEMODE_ENABLED = false;
	// 是否在自定义广告强制调试模式下获取广告失败时仍然继续循环强制显示该广告
	// 默认为直接切换下一广告商，此设置将（在30秒后）重新尝试获取当前广告，
	// 可用于配合CONST_EVENTADDEBUG_FORCEMODE_ENABLED选项编译固定版本供广告商测试使用。
	public static final boolean CONST_EVENTADDEBUG_FORCELOOPMODE_ENABLED = false;
	// 自定义广告强制调试模式类型
	public static final GmEventADType CONST_EVENTADDEBUG_FORCEMODE_TYPE1 = GmEventADType.wqmobile;

	// 是否为设置默认的自定义广告分配
	public static final boolean CONST_ADDEBUG_USEDEFAULTRATION_ENABLED = true;

	// --------------------------------------------------------------------------------
	// 以下均为本应用ID，使用者请将其换成自己在各个站点申请的ID。
	// --------------------------------------------------------------------------------
	// Adwhirl
	public static final String CONST_STR_APPID_ADWHIRL = "c925f76be6f94e9ca0c3e63f4411df8c";

	// Admob
	private static final String CONST_STR_APPID_ADMOB = "a14e1bdfa12f0d1";
	// mobfox
	private static final String CONST_STR_APPID_MOBFOX = "c3589a9025e1671b3b1164cf33622a64";
	// tapjoy
	private static final String CONST_STR_APPID_TAPJOY = "bba49f11-b87f-4c0f-9632-21aa810dd6f1";// "deed7c0e-223f-4b49-a858-10edd54c4258"
	private static final String CONST_STR_KEY_TAPJOY = "yiQIURFEeKm0zbOggubu";// "u5D9G7UnDXCdddOhnN9Q"

	// 多盟
	private static final String CONST_STR_APPID_DOMOB = "56OJya8ouMXNjQBxg/";
	// 亿动
	private static final String CONST_STR_APPID_YIDONG = "3e915ac4e83edf08";
	private static final String CONST_STR_BANNERID_YIDONG = "90003888";
	// 安沃
	private static final String CONST_STR_APPID_ADWO = "30baea00bc7c4017b23ce064403d054d";
	// 哇棒
	private static final String CONST_STR_APPID_WOOBOO = "7352eeaac8854f669f87d3458fd2567f";
	// 微云
	private static final String CONST_STR_APPID_WIYUN = "492b775415093733";
	private static final String CONST_STR_PWDID_WIYUN = "jfAHEgrgGNwHrkmEGvug3Eby32auguZw";
	private static final String CONST_STR_BANNERID_WIYUN = "b89b05a342381c69";
	// 有米（目前存在BUG）
	private static final String CONST_STR_APPID_YOUMI = "8c74fbb755fbdade";
	private static final String CONST_STR_PASSWORD_YOUMI = "18c26c9df856a9b2";
	// VPON
	private static final String CONST_STR_KEY_VPON = "f507039031350e1e013145934bdb024c";
	private static final String CONST_STR_KEY_VPONTW = "ff808081312213590131462263440303";
	// 易传媒
	private static final String CONST_STR_APPID_ADCHINA = "73464";
	// 力美
	private static final String CONST_STR_APPID_LMMOB = "e3645094096181c0bcfccc0cb28a289f";
	// AirAD
	private static final String CONST_STR_APPID_AIRAD = "8c8123e9-01da-4a12-87df-da1e53c4c37a";
	// 迪派
	// 注意：因为迪派的APPID使用的是整形数值，而本代码使用的是字符串，
	// 所以请以字符串形式填写，代码内部会在适当时候转换该值。
	private static final String CONST_STR_APPID_DIPAI = "1050";// "8";
	private static final String CONST_STR_KEY_DIPAI = "u2da5rbl4zxwcx7m";// "8888888888888888";//
	// 安机
	private static final String CONST_STR_APPID_ANJI = null;// "S_189_0903162610";
	// AppMedia
	private static final String CONST_STR_APPID_APPMEDIA = "531908c9a2643abd";
	// 指点传媒
	// 注意：指点传媒的APPID需要存放在AndroidManifest.xml中
	private static final String CONST_STR_APPID_ZHIDIAN = "AA6D47747386B5C99ABDE1A023136537";
	// 架势
	private static final String CONST_STR_APPID_CASEE = "1550CFCB0F7A0205C4EC7F990C1020E4";

	// --------------------------------------------------------------------------------
	// 自定义广告强制调试模式配置函数
	// --------------------------------------------------------------------------------
	private static boolean mForceEventADMode = CONST_EVENTADDEBUG_FORCEMODE_ENABLED;

	public static boolean isForceEventADMode() {
		return mForceEventADMode;
	}

	public static void setForceEventADMode(boolean value) {
		mForceEventADMode = value;
	}

	private static GmEventADType mForceEventADType = CONST_EVENTADDEBUG_FORCEMODE_TYPE1;

	public static GmEventADType getForceEventADType() {
		return mForceEventADType;
	}

	public static void setForceEventADType(GmEventADType value) {
		mForceEventADType = value;
	}

	// --------------------------------------------------------------------------------
	// 当无法连接广告聚合网站时，使用以下默认色彩、调度设置。
	// --------------------------------------------------------------------------------
	public static void initDefaultExtra(Extra extra) {
		extra.bgRed = 0;
		extra.bgGreen = 0;
		extra.bgBlue = 0;
		extra.bgAlpha = 1;

		extra.fgRed = 255;
		extra.fgGreen = 255;
		extra.fgBlue = 255;
		extra.fgAlpha = 1;

		extra.cycleTime = 30;
		extra.locationOn = 1;
		extra.transition = 1;
	}

	// --------------------------------------------------------------------------------
	// 当无法连接广告聚合网站时，使用以下默认广告流量分配设置。
	// --------------------------------------------------------------------------------
	public static void initDefaultRationList(List<Ration> rationsList) {
		if (CONST_ADDEBUG_USEDEFAULTRATION_ENABLED) {
			Ration ration = null;

			rationsList.clear();

			// --------------------------------------------------------------------------------
			// ADMOB DATA
			// --------------------------------------------------------------------------------
			ration = new Ration();
			// NID为聚合网站为每个不同的广告产生的用于区分展示和点击计数的ID
			// （你可以在应用启动后LOGCAT调试信息里看到该值，并抄写在此即可，
			// 不改也没有关系，只是没有展示、点击计数而已，反正也不怎么靠谱！）
			ration.nid = "7fd107d4ca0144e6ace0a9cb1314cfea";
			ration.type = AdWhirlUtil.NETWORK_TYPE_ADMOB;
			ration.name = "admob";
			ration.weight = 50;
			ration.priority = 1;
			// admob key
			ration.key = CONST_STR_APPID_ADMOB;
			rationsList.add(ration);

			// --------------------------------------------------------------------------------
			// DOMOB DATA
			// --------------------------------------------------------------------------------
			ration = new Ration();
			ration.nid = "23c170d68c1f49c3b4922de030f4baf6";
			ration.type = AdWhirlUtil.NETWORK_TYPE_EVENT;
			ration.name = "event";
			ration.weight = 50;
			ration.priority = 2;
			// 该值内容与您在聚合网站上填写的一致（注意：厂商名与函数名以“|;|”分割）
			ration.key = "cn_domob|;|adWhirlEventInterstitial_domob";
			rationsList.add(ration);
		}
	}

	// --------------------------------------------------------------------------------
	// 默认广告强制调试模式处理函数
	// --------------------------------------------------------------------------------
	public static void debugDefaultAdapter(Ration ration) {
		if (CONST_DEFAULTADDEBUG_FORCEMODE_ENABLED) {
			switch (CONST_DEFAULTADDEBUG_FORCEMODE_TYPE) {
			case AdWhirlUtil.NETWORK_TYPE_ADMOB:
				ration.type = AdWhirlUtil.NETWORK_TYPE_ADMOB;
				ration.name = "admob";
				ration.key = CONST_STR_APPID_ADMOB;
				break;
			}
		}
	}

	// --------------------------------------------------------------------------------
	// 自定义广告强制调试模式处理函数
	// --------------------------------------------------------------------------------
	public static void debugCustomEventAdapter(Ration ration) {
		if (mForceEventADMode) {
			Log.i(AdWhirlUtil.ADWHIRL,
					"!!!!!!!!AD_DEBUG_FORCE_MODE_ACTIVE!!!!!!!!");

			ration.type = AdWhirlUtil.NETWORK_TYPE_EVENT;
			ration.name = "event";
			// 该值内容与您在聚合网站上填写的一致（注意：厂商名与函数名以“|;|”分割）
			if (mForceEventADType == GmEventADType.tapjoy) {
				ration.key = "tapjoy|;|adWhirlEventInterstitial_tapjoy";
			} else if (mForceEventADType == GmEventADType.mobfox) {
				ration.key = "cn_mobfox|;|adWhirlEventInterstitial_mobfox";
			} else if (mForceEventADType == GmEventADType.smartmad) {
				ration.key = "cn_smartmad|;|adWhirlEventInterstitial_smartmad";
			} else if (mForceEventADType == GmEventADType.adwo) {
				ration.key = "cn_adwo|;|adWhirlEventInterstitial_adwo";
			} else if (mForceEventADType == GmEventADType.domob) {
				ration.key = "cn_domob|;|adWhirlEventInterstitial_domob";
			} else if (mForceEventADType == GmEventADType.wooboo) {
				ration.key = "cn_wooboo|;|adWhirlEventInterstitial_wooboo";
			} else if (mForceEventADType == GmEventADType.wiyun) {
				ration.key = "cn_wiyun|;|adWhirlEventInterstitial_wiyun";
			} else if (mForceEventADType == GmEventADType.youmi) {
				ration.key = "cn_youmi|;|adWhirlEventInterstitial_youmi";
			} else if (mForceEventADType == GmEventADType.vpon) {
				ration.key = "cn_vpon|;|adWhirlEventInterstitial_vpon";
			} else if (mForceEventADType == GmEventADType.vpontw) {
				ration.key = "cn_vpontw|;|adWhirlEventInterstitial_vpontw";
			} else if (mForceEventADType == GmEventADType.dipai) {
				ration.key = "cn_dipai|;|adWhirlEventInterstitial_dipai";
			} else if (mForceEventADType == GmEventADType.lmmob) {
				ration.key = "cn_lmmob|;|adWhirlEventInterstitial_lmmob";
			} else if (mForceEventADType == GmEventADType.baidu) {
				ration.key = "cn_baidu|;|adWhirlEventInterstitial_baidu";
			} else if (mForceEventADType == GmEventADType.waps) {
				ration.key = "cn_waps|;|adWhirlEventInterstitial_waps";
			} else if (mForceEventADType == GmEventADType.anji) {
				ration.key = "cn_anji|;|adWhirlEventInterstitial_anji";
			} else if (mForceEventADType == GmEventADType.adchina) {
				ration.key = "cn_adchina|;|adWhirlEventInterstitial_adchina";
			} else if (mForceEventADType == GmEventADType.airad) {
				ration.key = "cn_airad|;|adWhirlEventInterstitial_airad";
			} else if (mForceEventADType == GmEventADType.mobwin) {
				ration.key = "cn_mobwin|;|adWhirlEventInterstitial_mobwin";
			} else if (mForceEventADType == GmEventADType.appmedia) {
				ration.key = "cn_appmedia|;|adWhirlEventInterstitial_appmedia";
			} else if (mForceEventADType == GmEventADType.zhidian) {
				ration.key = "cn_zhidian|;|adWhirlEventInterstitial_zhidian";
			} else if (mForceEventADType == GmEventADType.appjoy) {
				ration.key = "cn_appjoy|;|adWhirlEventInterstitial_appjoy";
			} else if (mForceEventADType == GmEventADType.winad) {
				ration.key = "cn_winad|;|adWhirlEventInterstitial_winad";
			} else if (mForceEventADType == GmEventADType.casee) {
				ration.key = "cn_casee|;|adWhirlEventInterstitial_casee";
			} else if (mForceEventADType == GmEventADType.wqmobile) {
				ration.key = "cn_wqmobile|;|adWhirlEventInterstitial_wqmobile";
			}
		}
	}

	// --------------------------------------------------------------------------------
	// 当你信不过广告聚合网站所返回的默认广告商（如ADMOB）的ID时，此处可以进行验证、纠正。
	// --------------------------------------------------------------------------------
	public static void validateRation(Ration ration) {
		switch (ration.type) {
		case AdWhirlUtil.NETWORK_TYPE_ADMOB:
			ration.key = CONST_STR_APPID_ADMOB;
			break;
		}
	}

	// --------------------------------------------------------------------------------
	// 获取各个广告商的APPID
	// --------------------------------------------------------------------------------
	public static String getPublishID(GmEventADType type) {
		String ret = "";

		switch (type) {
		case mobfox:
			ret = CONST_STR_APPID_MOBFOX;
			break;
		case tapjoy:
			ret = CONST_STR_APPID_TAPJOY;
			break;
		case domob:
			ret = CONST_STR_APPID_DOMOB;
			break;
		case adchina:
			ret = CONST_STR_APPID_ADCHINA;
			break;
		case adwo:
			ret = CONST_STR_APPID_ADWO;
			break;
		case airad:
			ret = CONST_STR_APPID_AIRAD;
			break;
		case appmedia:
			ret = CONST_STR_APPID_APPMEDIA;
			break;
		case smartmad:
			ret = CONST_STR_APPID_YIDONG;
			break;
		case wooboo:
			ret = CONST_STR_APPID_WOOBOO;
			break;
		case wiyun:
			ret = CONST_STR_APPID_WIYUN;
			break;
		case youmi:
			ret = CONST_STR_APPID_YOUMI;
			break;
		case vpon:
			ret = CONST_STR_KEY_VPON;
			break;
		case vpontw:
			ret = CONST_STR_KEY_VPONTW;
			break;
		case dipai:
			ret = CONST_STR_APPID_DIPAI;
			break;
		case lmmob:
			ret = CONST_STR_APPID_LMMOB;
			break;
		case anji:
			ret = CONST_STR_APPID_ANJI;
			break;
		case casee:
			ret = CONST_STR_APPID_CASEE;
			break;
		}
		return ret;
	}

	// --------------------------------------------------------------------------------
	// 获取各个广告商的PWDID
	// --------------------------------------------------------------------------------
	public static String getDefaultPwdID(GmEventADType type) {
		String ret = "";

		switch (type) {
		case tapjoy:
			ret = CONST_STR_KEY_TAPJOY;
			break;
		case smartmad:
			ret = CONST_STR_BANNERID_YIDONG;
			break;
		case youmi:
			ret = CONST_STR_PASSWORD_YOUMI;
			break;
		case dipai:
			ret = CONST_STR_KEY_DIPAI;
			break;
		case wiyun:
			ret = CONST_STR_PWDID_WIYUN;
			break;
		}
		return ret;
	}

	// --------------------------------------------------------------------------------
	// 获取各个广告商的BANNER ID
	// --------------------------------------------------------------------------------
	public static String getDefaultBannerID(GmEventADType type) {
		String ret = "";

		switch (type) {
		case wiyun:
			ret = CONST_STR_BANNERID_WIYUN;
			break;
		}
		return ret;
	}

	// --------------------------------------------------------------------------------
	// 设置是否为测试模式，顶上设置CONST_ADDEBUG_ENABLED，此处无需更改！
	// --------------------------------------------------------------------------------
	public static boolean getDebugEnabled(GmEventADType type) {
		return CONST_ADDEBUG_ENABLED;
	}
}
