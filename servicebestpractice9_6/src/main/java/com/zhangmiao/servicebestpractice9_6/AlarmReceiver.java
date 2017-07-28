package com.zhangmiao.servicebestpractice9_6;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Author: zhangmiao
 * Date: 2017/7/28
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, LongRunningService.class);
        context.startService(i);//保证LongRunningService可以每隔一个小时就会启动一次
    }
}
