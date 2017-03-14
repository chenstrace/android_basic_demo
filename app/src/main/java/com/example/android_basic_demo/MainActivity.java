package com.example.android_basic_demo;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.ListView;


import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.library.network.IAPIList;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends Activity {

    PullToRefreshListView mListView;
    private ImageListAdapter mAdapter;

    //private PtrClassicFrameLayout m_ptrFrame;
    private String baseUrl = "http://wapi.reotest.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pulltorefresh);
        mAdapter = new ImageListAdapter(this);
        mListView = (PullToRefreshListView) this.findViewById(R.id.pl_refresh);
        mListView.setAdapter(mAdapter);
        mListView.setMode(PullToRefreshBase.Mode.BOTH);


        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                makeRequest();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                makeRequest();
            }
        });
    }

    protected void makeRequest()
    {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(3, TimeUnit.SECONDS);
        Retrofit.Builder retrofit = new Retrofit.Builder();
        retrofit.baseUrl(baseUrl);
        retrofit.addConverterFactory(GsonConverterFactory.create());
        retrofit.client(builder.build());
        Retrofit client = retrofit.build();

        IAPIList api = client.create(IAPIList.class);
        Call<SearchResult> call = api.listInfo("app", "1");

        call.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                Log.i("cjl", " --------success-------- " + response.body());
                if (response.body() != null)
                {
                    mAdapter.upDateEntries(response.body().getListData());
                }
                mListView.onRefreshComplete();
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                Log.e("cjl", " --------error-------- " + t);
            }
        });

    }

}


