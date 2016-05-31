package com.themetest;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class OtherActivity extends BaseActivity {

    @InjectView(R.id.tv_main_setting)
    TextView tvMainSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        ButterKnife.inject(this);

        tvMainSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentSkinType = ThemeManager.getCurrentThemeType(OtherActivity.this);
                if (ThemeManager.THEME_DAY == currentSkinType) {
                    ThemeManager.changeTheme(OtherActivity.this, ThemeManager.THEME_NIGHT);
                } else {
                    ThemeManager.changeTheme(OtherActivity.this, ThemeManager.THEME_DAY);
                }
            }
        });

    }
}
