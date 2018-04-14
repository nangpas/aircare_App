package com.example.bnm20.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class Alarm_setting extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_setting);

        toolbar = (Toolbar) findViewById(R.id.menu_alarm_toolbar);
        setSupportActionBar(toolbar);
    }
}
