package com.example.jzg.rxandroiddemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.tvConetxt)
    TextView tvConetxt;
    @BindView(R.id.bnt1)
    Button bnt1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.tvConetxt, R.id.bnt1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvConetxt:
                break;
            case R.id.bnt1:
                startActivity(new Intent(MainActivity.this, ActRxTest1.class));
                break;
        }
    }
}
