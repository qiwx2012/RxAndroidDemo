package com.example.jzg.rxandroiddemo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.tvConetxt)
    TextView tvConetxt;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "Item: " + "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "Item: " + "onError");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "Item: " + s);
                tvConetxt.append(s);
            }
        };

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }

        };


        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        });

        String [] names={"ddd","vvvv","cccdddd"};

        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(TAG, "Action: " + s);
            }
        });


        observable.subscribe(observer);

        Observable.create(new Observable.OnSubscribe<Drawable>() {

            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getResources().getDrawable(android.R.mipmap.sym_def_app_icon);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        }).subscribe(new Observer<Drawable>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
           public void onNext(Drawable drawable) {
                /*tvConetxt.setImageDrawable(drawable);
                setContentView(iv);*/
            }
        });

        //setContentView(iv);
    }



    @OnClick(R.id.tvConetxt)
    public void onClick() {


    }
}
