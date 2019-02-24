package com.example.android.arinspect_test.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.android.arinspect_test.rest.ApiClient;
import com.example.android.arinspect_test.rest.ApiInterface;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private final String TAG = MainViewModel.class.getSimpleName();

    private MutableLiveData<FactsResponse> mFacts;

    //method to get All facts
    public LiveData<FactsResponse> getAllFacts(){
        if(mFacts == null){
            mFacts = new MutableLiveData<>();
            loadRecipes();
        }
        return mFacts;

    }
    private void loadRecipes() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<FactsResponse> call = apiInterface.getAllFacts();

        call.enqueue(new Callback<FactsResponse>() {
            @Override
            public void onResponse(Call<FactsResponse> call, Response<FactsResponse> response) {
                mFacts.setValue(response.body());
            }

            @Override
            public void onFailure(Call<FactsResponse> call, Throwable t) {
                mFacts.setValue(null);
                Log.e(TAG,"Something went wrong while getting facts!");

                if (t instanceof IOException) {
                    Log.e(TAG,"This is an actual network failure!");
                }
                else {
                    Log.e(TAG,"Conversion issue! big problems!");
                }
            }
        });

    }
}
