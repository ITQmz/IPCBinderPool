package com.qmz.binderconnectpool;

import android.os.RemoteException;

import java.util.List;

/**
 * Created by 15757 on 2018/1/9.
 */

public class GetBook extends IGetBook.Stub {
    @Override
    public List<Book> getBook() throws RemoteException {
        return BookUtils.getBooks();
    }
}
