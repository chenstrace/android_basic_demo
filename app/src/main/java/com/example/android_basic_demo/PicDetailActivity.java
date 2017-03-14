package com.example.android_basic_demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class PicDetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pic_detail);

        Bundle b = getIntent().getBundleExtra("p2");
        String info = b.getString("p1");

        TextView textView = (TextView) findViewById(R.id.description);
        textView.setText(info);
    }
}
