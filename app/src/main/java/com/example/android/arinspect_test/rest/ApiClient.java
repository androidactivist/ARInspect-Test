package com.example.android.arinspect_test.rest;

import com.example.android.arinspect_test.BuildConfig;
import com.example.android.arinspect_test.IdlingResources;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    final static String FACTS_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        OkHttpClient client = new OkHttpClient();

        if(BuildConfig.DEBUG) {
            IdlingResources.registerOkHttp(client);
        }


        //OkHttpClient.Builder okhttp = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //client.addInterceptor(loggingInterceptor);

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(FACTS_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
