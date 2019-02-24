package com.example.android.arinspect_test.rest;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    final static String FACTS_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        OkHttpClient.Builder okhttp = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okhttp.addInterceptor(loggingInterceptor);

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(FACTS_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okhttp.build())
                    .build();
        }
        return retrofit;
    }
}
