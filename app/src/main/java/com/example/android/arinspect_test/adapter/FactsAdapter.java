package com.example.android.arinspect_test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.arinspect_test.R;
import com.example.android.arinspect_test.model.FactsResponse;
import com.example.android.arinspect_test.model.FactsRows;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FactsAdapter extends RecyclerView.Adapter<FactsAdapter.MyViewHolder> {

    private Context mContext;
    private List<FactsRows> mFacts;
    private final FactsAdapterOnClickHandler mClickHandler;

    //Constructor
    public FactsAdapter(FactsAdapterOnClickHandler mClickHandler, List<FactsRows> allFacts) {
        this.mClickHandler = mClickHandler;
        this.mFacts = allFacts;
    }


    public interface FactsAdapterOnClickHandler {
        void onClick(FactsRows factsRows);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View rootView = inflater.from(mContext).inflate(R.layout.facts_item, viewGroup,false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        FactsRows factsRows = mFacts.get(i);
        myViewHolder.title.setText(factsRows.getTitle());
        myViewHolder.desc.setText(factsRows.getDescription());
        Picasso.get().load(factsRows.getImageHref())
                .resize(278,185)
                .into(myViewHolder.image_icon);

    }

    @Override
    public int getItemCount() {
        return mFacts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title_text)
        TextView title;

        @BindView(R.id.desc_text)
        TextView desc;

        @BindView(R.id.image_icon)
        ImageView image_icon;

        public MyViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, itemView);
        }

        @OnClick
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            FactsRows helper = mFacts.get(adapterPosition);
            mClickHandler.onClick(helper);
        }
    }
}



