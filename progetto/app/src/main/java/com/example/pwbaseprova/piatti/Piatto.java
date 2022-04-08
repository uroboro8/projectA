package com.example.pwbaseprova.piatti;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Piatto implements Parcelable {

    @SerializedName("id")
    private int id;

    @SerializedName("nome")
    private String name;

    @SerializedName("type")
    private String type;

    @SerializedName("image")
    private String image;

    @SerializedName("description")
    private String description;

    protected Piatto(Parcel in) {
        id = in.readInt();
        name = in.readString();
        type = in.readString();
        image = in.readString();
        description = in.readString();
    }

    public static final Creator<Piatto> CREATOR = new Creator<Piatto>() {
        @Override
        public Piatto createFromParcel(Parcel in) {
            return new Piatto(in);
        }

        @Override
        public Piatto[] newArray(int size) {
            return new Piatto[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "\n{" + "\n" +
                "id=" + id + "\n" +
                ", name='" + name + '\''  + "\n" +
                ", type='" + type + '\'' + "\n" +
                ", image='" + image + '\'' + "\n" +
                ", description='" + description + '\'' + "\n" +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(type);
        parcel.writeString(image);
        parcel.writeString(description);
    }
}
