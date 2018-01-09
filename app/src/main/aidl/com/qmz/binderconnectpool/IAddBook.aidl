// IAddBook.aidl
package com.qmz.binderconnectpool;

import com.qmz.binderconnectpool.Book;
interface IAddBook {
  void addBook(inout Book book);

}
