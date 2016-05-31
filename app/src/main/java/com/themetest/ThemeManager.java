package com.themetest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;


/**
 * 切换主题的管理工具
 */
public class ThemeManager {
    public final static int THEME_DAY = 0;
    public final static int THEME_NIGHT = 1;
    private static String CONFIG = "config";
    private static String ThemeKey = "ThemeKey";
    private static SharedPreferences sp;

    private static final String IntentExtra_Theme = "IntentExtra_ThemeTheme";
    private static final String IntentActionTheme = "com.change_theme.action";
    private static final IntentFilter intentFilter = new IntentFilter(IntentActionTheme);
    private static final Intent intent = new Intent(IntentActionTheme);

    /**
     * 注册广播，主要防止设置界面太深，而之前的页面改不了，更换主题必须重启Activity才能有效果
     * @param activity
     * @param themeBroadcastReceiver
     */
    public static void registerThemeReceiver(Activity activity, ThemeBroadcastReceiver themeBroadcastReceiver) {
        if (themeBroadcastReceiver != null) {
            activity.registerReceiver(themeBroadcastReceiver, intentFilter);
        }
    }

    public static void unregisterThemeReceiver(Activity activity, ThemeBroadcastReceiver themeBroadcastReceiver) {
        if (themeBroadcastReceiver != null) {
            activity.unregisterReceiver(themeBroadcastReceiver);
        }
    }

    /**
     * 获取当前主题的Type
     * @param context
     * @return  0：白天主题；1：夜间主题
     */
    public static int getCurrentThemeType(Context context) {
        return getSharePreTheme(context, THEME_DAY);
    }

    private static void setThemeType(Context context, int theme) {
        saveSharePreTheme(context, theme);
    }

    /**
     * 获取当前主题
     * @param context
     * @return
     */
    public static int getCurrentTheme(Context context) {
        int saveThemeType = getCurrentThemeType(context);
        int currentTheme;
        switch (saveThemeType) {
            default:
            case THEME_DAY:
                currentTheme = R.style.DayTheme;
                break;
            case THEME_NIGHT:
                currentTheme = R.style.NightTheme;
                break;
        }
        return currentTheme;
    }

    /**
     * 改变主题皮肤
     *
     * @param activity 当前Activity
     * @param theme    两种选择
     */
    public static void changeTheme(Activity activity, int theme) {
        int currentTheme;
        switch (theme) {
            default:
            case THEME_DAY:
                setThemeType(activity.getApplicationContext(), THEME_DAY);
                currentTheme = R.style.DayTheme;
                break;
            case THEME_NIGHT:
                setThemeType(activity.getApplicationContext(), THEME_NIGHT);
                currentTheme = R.style.NightTheme;
                break;
        }
        //发送广播
        activity.sendBroadcast(intent);
        intent.putExtra(IntentExtra_Theme, currentTheme);
        //重新加载
        activity.recreate();
    }


    public static void onActivityCreateSetTheme(Activity activity) {
        int currentTheme = getCurrentTheme(activity.getApplicationContext());
        activity.setTheme(currentTheme);
    }

    private static void saveSharePreTheme(Context context, int value) {
        if (sp == null) {
            sp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        sp.edit().putInt(ThemeKey, value).commit();
    }

    private static int getSharePreTheme(Context context, int defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        return sp.getInt(ThemeKey, defValue);
    }

}
