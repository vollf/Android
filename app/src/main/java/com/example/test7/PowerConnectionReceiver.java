package com.example.test7;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;

public class PowerConnectionReceiver extends BroadcastReceiver {

    private static final String TAG = "PowerConnectionReceiver";
    //用以保证一次充电只打印一次
    private boolean power = true;

    @Override
    public void onReceive(Context context, Intent intent) {
        //获取广播发送的当前手机充电状态信息
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        //如果收到电池状态充电中或电池状态满的信号则显示正在充电
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;
        //获取广播发送的当前手机充电方式信息
        int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        //如果收到usb充电信号则充电方式为usb接口
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        //如果收到壁式充电信号则充电方式为壁式接口
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
        //当检测到处于充电状态是打印到logcat显示正在充电，并打印使用的充电方式
        if(isCharging && power){
            power = false;
            if(usbCharge){
                Log.d(TAG, "当前手机正通过USB接口进行充电");
            }else if(acCharge){
                Log.d(TAG, "当前手机正通过交流充电器进行充电");
            }else{
                Log.d(TAG, "当前手机正通过未知方式进行充电");
            }
        }else if(!isCharging){
            power = true;
        }
    }
}