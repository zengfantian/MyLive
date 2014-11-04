package cn.com.hoonsoft.sms;

import android.content.Context;
import cn.sharesdk.analysis.MobclickAgent;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class StatisticManager {

	public static void initAnalysisSDK(Context context) {
		// 统计
		MobclickAgent.onError(context);
		MobclickAgent.openActivityDurationTrack(false);
		MobclickAgent.setBaseURL("http://61.153.100.86/api");
	}

	public static void onPause(Context context) {
		MobclickAgent.onPageEnd("MainActivity");
	}

	public static void onResume(Context context) {
		MobclickAgent.onPageStart("MainActivity");
	}

	public static void registerAnalysisHandler(final Context context) {
		EventHandler analysisHandler = new EventHandler() {

			@Override
			public void beforeEvent(int event, Object data) {
				MobclickAgent.onEventBegin(context, "smssdk_event_request_time", eventToString(event));
			}

			public void afterEvent(int event, int result, Object data) {
				MobclickAgent.onEventEnd(context, "smssdk_event_request_time", eventToString(event));
				if(SMSSDK.RESULT_COMPLETE == result){
					MobclickAgent.onEvent(context, "smssdk_event_request_success", eventToString(event));
				}else{
					MobclickAgent.onEvent(context, "smssdk_event_request_fail", eventToString(event));
				}
			}

		};
		SMSSDK.registerEventHandler(analysisHandler);
	}

	private static String eventToString(int event) {
		switch (event) {
			case SMSSDK.EVENT_GET_CONTACTS:
				return "EVENT_GET_CONTACTS";
			case SMSSDK.EVENT_GET_FRIENDS_IN_APP:
				return "EVENT_GET_FRIENDS_IN_APP";
			case SMSSDK.EVENT_GET_NEW_FRIENDS_COUNT:
				return "EVENT_GET_NEW_FRIENDS_COUNT";
			case SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES:
				return "EVENT_GET_SUPPORTED_COUNTRIES";
			case SMSSDK.EVENT_GET_VERIFICATION_CODE:
				return "EVENT_GET_VERIFICATION_CODE";
			case SMSSDK.EVENT_SUBMIT_USER_INFO:
				return "EVENT_SUBMIT_USER_INFO";
			default:
				return "EVENT_SUBMIT_VERIFICATION_CODE";
		}
	}

}
