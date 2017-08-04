package com.coolweather.app.util;

/**
 * Author: zhangmiao
 * Date: 2017/8/4
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
