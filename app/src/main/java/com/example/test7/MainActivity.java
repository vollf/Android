package com.example.test7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    private PowerConnectionReceiver powerConnectionReceiver;
    private BatteryLevelReceiver batteryLevelReceiver;
    private NetworkChangeReceiver networkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注册处理动作，监听电量变化
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        powerConnectionReceiver = new PowerConnectionReceiver();
        //注册监听充电状态变化的接收器，将监听电量变化的广播传入到该接收器上
        this.registerReceiver(powerConnectionReceiver, ifilter);
        batteryLevelReceiver = new BatteryLevelReceiver();
        //注册监听电量显著变化的接收器，将监听电量变化的广播传入到该接收器上
        this.registerReceiver(batteryLevelReceiver, ifilter);
        //构建新的信息过滤器
        IntentFilter intentFilter = new IntentFilter();
        //增加监听网络连接状态变化的广播动作
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        //注册监听网络连接状态变化的接收器，将监听电量变化的广播传入到该接收器上
        this.registerReceiver(networkChangeReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销接收器
        unregisterReceiver(powerConnectionReceiver);
        unregisterReceiver(batteryLevelReceiver);
        unregisterReceiver(networkChangeReceiver);
    }
}