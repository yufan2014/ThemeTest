package com.themetest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.tv_main_setting)
    TextView tvMainSetting;
    private SkinBroadcastReceiver skinBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        tvMainSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentSkinType = SkinManager.getCurrentSkinType(MainActivity.this);
                if (SkinManager.THEME_DAY == currentSkinType) {
                    SkinManager.changeSkin(MainActivity.this, SkinManager.THEME_NIGHT);
                } else {
                    SkinManager.changeSkin(MainActivity.this, SkinManager.THEME_DAY);
                }
            }
        });

    }


    private void registerSkinReceiver() {
        if (skinBroadcastReceiver == null) {
            skinBroadcastReceiver = new SkinBroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Log.i("onReceive", "MainActivity广播来了");
                    recreate();
                }
            };
            SkinManager.registerSkinReceiver(MainActivity.this, skinBroadcastReceiver);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
//        SkinManager.unregisterSkinReceiver(this, skinBroadcastReceiver);
    }
}
