package com.example.android.arinspect_test.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class FactsRows implements Parcelable{

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("imageHref")
    private String imageHref;


    public FactsRows(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.imageHref = in.readString();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(imageHref);

    }


    public static final Parcelable.Creator<FactsRows> CREATOR = new Parcelable.Creator<FactsRows>
            () {

        @Override
        public FactsRows createFromParcel(Parcel source) {
            return new FactsRows(source);
        }

        @Override
        public FactsRows[] newArray(int size) { return new FactsRows[size]; }
    };
}
