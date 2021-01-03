package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.SwitchPreference;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {


    SwipeRefreshLayout swipeRefreshLayout;

    TextView textView;

    int anInt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout);
        textView = findViewById(R.id.trrrr);

        anInt++;

        textView.setText(anInt);



    }







    void ss(){





        textView.setText("hello");


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                textView.setText("goodbye");

            }
        }, 5000);






    }









}