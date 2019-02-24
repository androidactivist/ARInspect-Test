package com.example.android.arinspect_test;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    @BindView(R.id.pb_loading_indicator)
    ProgressBar mProgressBar;

    private FactsAdapter mFactsAdapter;
    private MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mProgressBar.setVisibility(View.VISIBLE);

        mMainViewModel.getAllFacts().observe(this, new Observer<FactsResponse>() {
            @Override
            public void onChanged(@Nullable FactsResponse factsResponses) {
                List<FactsRows> factsRows = factsResponses.getFactsRowsList();
                mFactsAdapter = new FactsAdapter(MainActivity.this, factsRows);
                getSupportActionBar().setTitle(factsResponses.getTitle());
                mProgressBar.setVisibility(View.INVISIBLE);
                mRecyclerView.setAdapter(mFactsAdapter);
            }
        });

    }

    @Override
    public void onClick(FactsRows factsRows) {

    }
}
