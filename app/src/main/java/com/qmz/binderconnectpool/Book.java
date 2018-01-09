package com.qmz.binderconnectpool;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 15757 on 2018/1/9.
 */

public class Book implements Parcelable {
    public int bookId;
    public String bookName;
    protected Book(Parcel in) {
        bookId=in.readInt();
        bookName=in.readString();

    }
    public Book(int bookId, String bookName) {
        this.bookId = bookId;
        this.bookName = bookName;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bookId);
        dest.writeString(bookName);
    }
    public void readFromParcel(Parcel in){
        bookId=in.readInt();
        bookName=in.readString();
    }

    @Override
    public String toString() {
        return bookId+"--"+bookName+"\n";
    }
}
