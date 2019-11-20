package com.example.javaflux;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity{

    private CounterView first, second, third;
    private SummaryView summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        first = (CounterView) findViewById(R.id.first);
        second = (CounterView) findViewById(R.id.second);
        third = (CounterView) findViewById(R.id.third);
        summary = (SummaryView) findViewById(R.id.summary);
    }

    @Override
    protected void onResume() {
        super.onResume();
        summary.attach();
        first.attach();
        second.attach();
        third.attach();
    }

    @Override
    protected void onPause() {
        super.onPause();
        summary.detach();
        first.detach();
        second.detach();
        third.detach();
    }
}
