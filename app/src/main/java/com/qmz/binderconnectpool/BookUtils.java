package com.qmz.binderconnectpool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15757 on 2018/1/9.
 */

public class BookUtils {
    private static List<Book> books;

    public static void addBook(Book book){
        System.out.println("==========BookUtils==========="+book);
        if(books==null){
            books=new ArrayList<>();
            books.add(book);
        }
    }

    public static List<Book> getBooks(){
        return books;
    }
}
