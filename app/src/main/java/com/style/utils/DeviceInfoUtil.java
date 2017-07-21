package com.style.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.style.framework.R;
import com.style.manager.ToastManager;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by xiajun on 2017/1/9.
 * 设备信息工具类
 */

public class DeviceInfoUtil {

    public static int dp2px(Context context, float dpValue) {
        float pxValue = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
        return (int) (pxValue + 0.5f);//0.5f是为了四舍五入，但有时候四舍五入不一定就好
    }

    public static int px2dp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static float sp2px(Context context, int sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }


    public static int getScreenWidth(Context context) {
        return getMetrics(context).widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return getMetrics(context).heightPixels;
    }

    public static Display getDisplay(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay();
    }

    public static DisplayMetrics getMetrics(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        getDisplay(context).getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）
        float density = metric.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
        return metric;
    }

    public static String getIMEI(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String id = tm.getDeviceId();
            if (id != null) {
                return tm.getDeviceId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void openEditSms(Context context, String phone) {
        Uri smsToUri = Uri.parse("smsto:" + phone);
        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        intent.putExtra("sms_body", "我最近成为了脉连的用户，可以随时查找与别人的连系，让交友变得简单可信。交朋友、找关系好用极了，推荐给你试试（下载链接:www.yimxl.com）。");
        context.startActivity(intent);
    }

    public static void notifyUpdateGallary(Context context, File photoFile) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(photoFile);
        intent.setData(uri);
        context.sendBroadcast(intent);
    }

    public static int[] getViewLocationOnScreen(View view) {
        int[] locations = new int[2];
        view.getLocationOnScreen(locations);
        int x = locations[0];// 获取组件当前位置的横坐标
        int y = locations[1];// 获取组件当前位置的纵坐标
        return locations;
    }


    public static boolean isSDcardAvailable() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            ToastManager.showToastOnApplication(R.string.sd_card_unavailable);
            return false;
        }
        return true;
    }


    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.getHostAddress().isEmpty()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("WifiIpAddress==", ex.toString());
        }
        return null;
    }
}
