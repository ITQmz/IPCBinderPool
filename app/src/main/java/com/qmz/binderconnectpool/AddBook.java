package com.qmz.binderconnectpool;

import android.os.RemoteException;

/**
 * Created by 15757 on 2018/1/9.
 */

public class AddBook extends IAddBook.Stub {
    @Override
    public void addBook(Book book) throws RemoteException {
        BookUtils.addBook(book);
    }
}
