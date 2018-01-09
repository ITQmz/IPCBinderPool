package com.qmz.binderconnectpool;

import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    IAddBook iAddBook;
    IGetBook iGetBook;
    Button btn;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.btn);
        textView=findViewById(R.id.text);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(){
                    @Override
                    public void run() {
                        System.out.println("===============getbook=start");
                        IBinder serviceGet=BinderPoolUtils.getInstance(MainActivity.this).queryBinder(1);
                        iGetBook=IGetBook.Stub.asInterface(serviceGet);
                        List<Book> books= null;
                        try {
                            books = iGetBook.getBook();
                            System.out.println("===============getbook==="+books);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        final List<Book> finalBooks = books;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(finalBooks.toString());
                            }
                        });

                    }
                }.start();

            }
        });

            new Thread(){
                @Override
                public void run() {
                    System.out.println("================start");
                    BinderPoolUtils.getInstance(MainActivity.this);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    IBinder serviceAdd=BinderPoolUtils.getInstance(MainActivity.this).queryBinder(0);
                    iAddBook=IAddBook.Stub.asInterface(serviceAdd);
                    Book book=new Book(1,"第一本书");

                    try {
                        iAddBook.addBook(book);
                        System.out.println("================"+book.toString());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }.start();



    }
}
