package com.qmz.binderconnectpool;

import android.os.IBinder;
import android.os.RemoteException;

/**
 * Created by 15757 on 2018/1/9.
 */

public class BinderPool extends IBinderPool.Stub {
    private static final int ADD=0;
    private static final int GET=1;
    @Override
    public IBinder queryBinder(int code) throws RemoteException {
        switch (code){
            case ADD :
                return new AddBook();

            case GET:
                return new GetBook();

        }
        return null;
    }
}
