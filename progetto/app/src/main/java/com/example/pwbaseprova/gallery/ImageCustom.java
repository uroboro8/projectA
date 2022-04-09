package com.example.pwbaseprova.gallery;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ImageCustom implements Parcelable {

    @SerializedName("id")
    private int id;

    @SerializedName("image")
    private String image;

    protected ImageCustom(Parcel in) {
        id = in.readInt();
        image = in.readString();
    }

    public static final Creator<ImageCustom> CREATOR = new Creator<ImageCustom>() {
        @Override
        public ImageCustom createFromParcel(Parcel in) {
            return new ImageCustom(in);
        }

        @Override
        public ImageCustom[] newArray(int size) {
            return new ImageCustom[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(image);
    }
}
