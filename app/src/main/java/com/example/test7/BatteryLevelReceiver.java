package com.example.test7;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;

public class BatteryLevelReceiver extends BroadcastReceiver {

    private static final String TAG = "BatteryLevelReceiver";
    //当battery为0时表示下一时刻出现的显著电量变化为进入低电量状态
    private int battery = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        //获取当前剩余电量
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        //获取电量最大值
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        //计算当前电量百分比
        float batteryPct = level * 100 / (float)scale;
        if(batteryPct < 20 && battery == 0){
            //如果电量小于20%，则打印电量低
            Log.d(TAG, "当前处于低电量状态");
            //表示下一时刻出现的显著电量变化为脱离低电量状态
            battery = 1;
        }else if(batteryPct == 20 && battery == 1){
            //如果电量大于20%，则当前已脱离低电量状态
            Log.d(TAG, "当前已脱离低电量状态");
            //表示下一时刻出现的显著电量变化为进入低电量状态
            battery = 0;
        }
    }
}