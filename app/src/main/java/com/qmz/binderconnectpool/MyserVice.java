package com.qmz.binderconnectpool;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by 15757 on 2018/1/9.
 */

public class MyserVice extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new BinderPool();
    }
}
