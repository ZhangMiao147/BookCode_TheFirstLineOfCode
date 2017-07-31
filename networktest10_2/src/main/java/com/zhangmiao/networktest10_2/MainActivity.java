package com.zhangmiao.networktest10_2;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    public static final int SHOW_RESPONSE = 0;
    private Button sendRequest;
    private TextView responseText;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_RESPONSE:
                    String response = (String) msg.obj;
                    //在这里进行UI操作，将结果显示到界面上
                    responseText.setText(response);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendRequest = (Button) findViewById(R.id.send_request);
        responseText = (TextView) findViewById(R.id.response_text);
        sendRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.send_request) {
            Log.d(TAG, "onClick");
            sendRequestWithHttpURLConnection();
            //sendRequestWithHttpClient();
        }
    }

    private void sendRequestWithHttpURLConnection() {
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL("http://www.cnblogs.com/zhangmiao14/p/7209302.html");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    if (connection.getResponseCode() == 200) {
                        InputStream in = connection.getInputStream();
                        //下面对获取到的输入流进行读取
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        Message message = new Message();
                        message.what = SHOW_RESPONSE;
                        //将服务器返回的结果存放到Message中
                        message.obj = response.toString();
                        handler.sendMessage(message);
                    } else {
                        Message message = new Message();
                        message.what = SHOW_RESPONSE;
                        //将服务器返回的结果存放到Message中
                        message.obj = "" + connection.getResponseCode();
                        handler.sendMessage(message);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void sendRequestWithHttpClient() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet("http://www.baidu.com");
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        //请求和响应都成功了
                        HttpEntity entity = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity, "utf-8");
                        Message message = new Message();
                        message.what = SHOW_RESPONSE;
                        //将服务器返回的结果存放到Message中
                        message.obj = response.toString();
                        handler.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

}
