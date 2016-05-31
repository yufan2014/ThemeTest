package com.themetest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class BaseActivity extends Activity {

    //当前Activit的主题
    public int themeBase;
    private ThemeBroadcastReceiver themeBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //设置主题
        ThemeManager.onActivityCreateSetTheme(this);
        super.onCreate(savedInstanceState);
        //获取当前的主题
        themeBase = ThemeManager.getCurrentThemeType(this);

        themeBroadcastReceiver = new ThemeBroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                recreate();
            }
        };
        ThemeManager.registerThemeReceiver(this, themeBroadcastReceiver);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ThemeManager.unregisterThemeReceiver(this,themeBroadcastReceiver);
    }
}
