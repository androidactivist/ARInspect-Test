package com.example.android.arinspect_test.rest;

import com.example.android.arinspect_test.model.FactsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    //https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json
    @GET("facts.json")
    Call<FactsResponse> getAllFacts();
}
