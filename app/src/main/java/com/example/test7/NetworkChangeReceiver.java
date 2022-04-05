package com.example.test7;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkChangeReceiver extends BroadcastReceiver {

    private static final String TAG = "NetworkChangeReceiver";
    //用以保证一次充电只打印一次
    private boolean network = true;
    
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnected();
        //WiFi
        boolean isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
        //流量
        boolean isWiMax = activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE;
        if(isConnected && network){
            network = false;
            //打印网络类型
            if(isWiFi){
                Log.d(TAG, "当前网络类型为WIFI");
            }else if(isWiMax){
                Log.d(TAG, "当前网络类型为移动网络");
            }else{
                Log.d(TAG, "当前网络类型未知");
            }
        }else{
            Log.d(TAG, "网络连接已断开");
            network = true;
        }
    }
}