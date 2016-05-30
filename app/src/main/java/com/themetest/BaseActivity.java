package com.themetest;

import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity {

    //当前Activity的主题
    public int skinBase;
//    private SkinBroadcastReceiver skinBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //设置主题
        SkinManager.onActivityCreateSetSkin(this);
        super.onCreate(savedInstanceState);
        //获取当前的主题
        skinBase = SkinManager.getCurrentSkinType(this);


//        skinBroadcastReceiver = new SkinBroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                recreate();
//            }
//        };
//        SkinManager.registerSkinReceiver(this, skinBroadcastReceiver);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        SkinManager.unregisterSkinReceiver(this,skinBroadcastReceiver);
    }
}
