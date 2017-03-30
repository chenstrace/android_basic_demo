package com.example.android_basic_demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class
ImageListAdapter extends BaseAdapter {

    private Context mContext;

    private LayoutInflater mLayoutInflater;

    private List<ListDatum> mEntries = new ArrayList<>();

    public ImageListAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mEntries.size();
    }

    @Override
    public Object getItem(int position) {
        return mEntries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        RelativeLayout itemView;
        if (convertView == null) {
            itemView = (RelativeLayout) mLayoutInflater.inflate(R.layout.list_item, parent, false);
        } else {
            itemView = (RelativeLayout) convertView;
        }
        ImageView imageView = (ImageView) itemView.findViewById(R.id.listImage);
        TextView titleText = (TextView) itemView.findViewById(R.id.listTitle);
        TextView descriptionText = (TextView) itemView.findViewById(R.id.listDescription);




        String imageUrl = mEntries.get(position).getUrl();

        Glide.with(mContext).load(imageUrl).centerCrop().crossFade().into(imageView);


        String title = mEntries.get(position).getText();
        titleText.setText(title);
        final String description = mEntries.get(position).getSummary();
        descriptionText.setText(description);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PicDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("p1", description);
                intent.putExtra("p2", bundle);
                mContext.startActivity(intent, bundle);
            }
        });
        return itemView;
    }

    public void upDateEntries(List<ListDatum> entries) {
        mEntries.addAll(entries);
        notifyDataSetChanged();
    }


}