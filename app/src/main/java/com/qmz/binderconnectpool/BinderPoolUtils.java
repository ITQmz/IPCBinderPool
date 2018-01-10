package com.qmz.binderconnectpool;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.concurrent.CountDownLatch;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Created by 15757 on 2018/1/9.
 */

public class BinderPoolUtils {
    private static BinderPoolUtils instance;
    private  IBinderPool binderPool;
    private Context mcontext;
    private CountDownLatch mcountDownlatch;

    private  ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binderPool=IBinderPool.Stub.asInterface(service);
            System.out.println("Thread.currentThread()onServiceConnected======="+Thread.currentThread().getId());

            try {
                binderPool.asBinder().linkToDeath(mbinderDeath,0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            mcountDownlatch.countDown();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private IBinder.DeathRecipient mbinderDeath=new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            binderPool.asBinder().unlinkToDeath(mbinderDeath,0);
            binderPool=null;
            connectServices();
        }
    };

    public BinderPoolUtils(Context context) {
        mcontext= context.getApplicationContext();
        connectServices();
    }

    public  static BinderPoolUtils getInstance(Context context){
        if(instance==null){
            synchronized (BinderPoolUtils.class) {
                instance = new BinderPoolUtils(context);
            }

        }
        return instance;
    }


    public  IBinder queryBinder(int code){

            try {
                if(binderPool!=null) {
                    return binderPool.queryBinder(code);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return null;

    }

    private synchronized void connectServices() {
        mcountDownlatch=new CountDownLatch(1);
        Intent intent=new Intent();
        intent.setAction("com.qmz.myservice");
        intent.setPackage("com.qmz.binderconnectpool");
        mcontext.bindService(intent,serviceConnection,BIND_AUTO_CREATE);

        System.out.println("Thread.currentThread()connectServices======="+Thread.currentThread().getId());
        try {
            mcountDownlatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
