package com.shsun.demo;

import org.lee.android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button myListViewBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        myListViewBtn = (Button) findViewById(R.id.mylistview_btn);
        myListViewBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyListViewActivity.class);
                startActivity(intent);
            }

        });
    }
}