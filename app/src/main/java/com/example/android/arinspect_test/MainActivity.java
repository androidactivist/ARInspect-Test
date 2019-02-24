package com.example.android.arinspect_test;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.arinspect_test.adapter.FactsAdapter;
import com.example.android.arinspect_test.model.FactsResponse;
import com.example.android.arinspect_test.model.FactsRows;
import com.example.android.arinspect_test.model.MainViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements FactsAdapter.FactsAdapterOnClickHandler{

    private final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.rv_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.tv_error_message_display)
    TextView mErrorTextView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private FactsAdapter mFactsAdapter;
    private MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Init ViewModel
        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mSwipeRefreshLayout.setRefreshing(true);
        refreshFacts();

        //load data via ViewModel
       /* if(NetworkUtils.isNetworkAvailable(this)) {
            //Start pull-to-refresh animation
            mSwipeRefreshLayout.setRefreshing(true);
            refreshFacts();
        } else {
            mErrorTextView.setVisibility(View.VISIBLE);
        }*/


        //Pull-to-refresh manually
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFacts();
            }
        });

    }

    private void refreshFacts() {
        //Observing the changes
        mMainViewModel.getAllFacts().observe(this, new Observer<FactsResponse>() {
            @Override
            public void onChanged(@Nullable FactsResponse factsResponses) {

                    if(factsResponses == null) {
                        mSwipeRefreshLayout.setRefreshing(false);
                        mErrorTextView.setVisibility(View.VISIBLE);
                    } else {
                        List<FactsRows> factsRows = factsResponses.getFactsRowsList();
                        mFactsAdapter = new FactsAdapter(MainActivity.this, factsRows);
                        getSupportActionBar().setTitle(factsResponses.getTitle());
                        mRecyclerView.setAdapter(mFactsAdapter);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

            }
        });

    }

    @Override
    public void onClick(FactsRows factsRows) {

    }
}
