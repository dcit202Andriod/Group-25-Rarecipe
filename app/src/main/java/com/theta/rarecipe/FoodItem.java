package com.theta.rarecipe;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class FoodItem implements Parcelable {
    private final String name;
    private final String imageUrl;
    private final String creatorName;

    public FoodItem(String name, String imageUrl, String creatorName) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.creatorName = creatorName;
    }


    protected FoodItem(Parcel in) {
        name = in.readString();
        imageUrl = in.readString();
        creatorName = in.readString();
    }

    public static final Creator<FoodItem> CREATOR = new Creator<FoodItem>() {
        @Override
        public FoodItem createFromParcel(Parcel in) {
            return new FoodItem(in);
        }

        @Override
        public FoodItem[] newArray(int size) {
            return new FoodItem[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCreatorName() {
        return creatorName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(imageUrl);
        dest.writeString(creatorName);
    }
}
