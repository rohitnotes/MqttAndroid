package com.style.manager;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.style.framework.R;

import example.app.MyApp;


public class ToastManager {

    public static void showToastNoMoreData(Context context) {
        showToast(context, R.string.no_more);
    }

    public static void showToast(Context context, String str) {
        if (!TextUtils.isEmpty(str) && null != context) {
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showToast(Context context, int resId) {
        if (null != context) {
            Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showToastOnApplication(int resId) {
        showToast(MyApp.getAppContext(), resId);
    }

    public static void showToastOnApplication(String str) {
        showToast(MyApp.getAppContext(), str);
    }

    public static void showToastonFailureOnApp() {
        showToastOnApplication(R.string.request_fail);
    }

    public static void showToastRequestFail(Context context) {
        showToast(context, R.string.request_fail);
    }

    public static void showToastLong(Context context, String msg) {
        if (!TextUtils.isEmpty(msg) && null != context) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        }
    }

    public static void showToastLong(Context context, int resId) {
        if (null != context) {
            Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
        }
    }
}
