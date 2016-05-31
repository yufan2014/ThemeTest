package com.themetest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.tv_main_setting)
    TextView tvMainSetting;
    @InjectView(R.id.tv_main_to_other)
    TextView tv_main_to_other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        tvMainSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentSkinType = ThemeManager.getCurrentThemeType(MainActivity.this);
                if (ThemeManager.THEME_DAY == currentSkinType) {
                    ThemeManager.changeTheme(MainActivity.this, ThemeManager.THEME_NIGHT);
                } else {
                    ThemeManager.changeTheme(MainActivity.this, ThemeManager.THEME_DAY);
                }
            }
        });


        tv_main_to_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,OtherActivity.class));
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
