package com.example.android.arinspect_test.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FactsResponse implements Parcelable {

    @SerializedName("title")
    private String title;


    @SerializedName("rows")
    private List<FactsRows> factsRowsList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<FactsRows> getFactsRowsList() {
        return factsRowsList;
    }

    public void setFactsRowsList(List<FactsRows> factsRowsList) {
        this.factsRowsList = factsRowsList;
    }


    public FactsResponse(Parcel in) {
        this.title = in.readString();
        this.factsRowsList = new ArrayList<FactsRows>();
        in.readList(factsRowsList,FactsRows.class.getClassLoader());

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeList(factsRowsList);
    }


    @Override
    public int describeContents() {
        return 0;
    }



    public static final Parcelable.Creator<FactsResponse> CREATOR = new Parcelable.Creator<FactsResponse>
            () {

        @Override
        public FactsResponse createFromParcel(Parcel source) {
            return new FactsResponse(source);
        }

        @Override
        public FactsResponse[] newArray(int size) { return new FactsResponse[size]; }
    };
}
