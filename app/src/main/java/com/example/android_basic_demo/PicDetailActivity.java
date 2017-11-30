package com.example.android_basic_demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class PicDetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pic_detail);

        Bundle b = getIntent().getBundleExtra("p2");
        String imageUrl = b.getString("p1");

        ImageView imageView = (ImageView) findViewById(R.id.pic_detail);
        Glide.with(this).load(imageUrl).crossFade().into(imageView);
    }
}
