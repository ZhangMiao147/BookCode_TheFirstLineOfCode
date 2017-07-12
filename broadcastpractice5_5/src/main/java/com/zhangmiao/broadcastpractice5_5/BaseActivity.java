package com.zhangmiao.broadcastpractice5_5;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Author: zhangmiao
 * Date: 2017/7/11
 */
public class BaseActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
