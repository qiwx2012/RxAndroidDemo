package com.example.jzg.rxandroiddemo;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;

import static android.content.ContentValues.TAG;

/**
 * @author: qiwx
 * email: qiwx@jingzhengu.com
 * @time: 2017/3/8  19:29
 * @desc:
 */

public class ActRxTest1 extends Activity {

    @BindView(R.id.txtContent)
    TextView txtContent;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.img)
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_rxtest1);
        ButterKnife.bind(this);
    }


    private void test1() {
        txtContent.setText("");
        String[] names = {"ddd", "vvvv", "cccdddd"};
        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(TAG, "Action: " + s);
                txtContent.append(s);
            }
        });


    }

    private void test2() {
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        });
        observable.subscribe(subscriber);
    }

    private void test3() {
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
                img.setImageDrawable(drawable);
                //setContentView(iv);
            }
        });
    }


    Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
            txtContent.append("结束");
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            txtContent.append(s);
        }

    };


    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                test1();
                break;
            case R.id.btn2:
                txtContent.setText("");
                test2();
                break;
            case R.id.btn3:
                test3();
                break;
            case R.id.btn4:
                break;
        }
    }
}
