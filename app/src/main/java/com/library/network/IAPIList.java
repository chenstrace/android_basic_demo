package com.library.network;


import com.example.android_basic_demo.SearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface IAPIList {
    @GET("{moudle}/v1/system/user/query/imagelist")
    Call<SearchResult> listInfo(@Path("moudle") String moudle, @Query("o") String o);
}
