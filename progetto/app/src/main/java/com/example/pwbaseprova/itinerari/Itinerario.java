package com.example.pwbaseprova.itinerari;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Itinerario implements Parcelable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("duration")
    private float duration;

    @SerializedName("price")
    private int price;

    @SerializedName("cover")
    private String cover;

    @SerializedName("images")
    private List<String> gallery;

    @SerializedName("type")
    private String type;

    @SerializedName("description")
    private String description;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getDuration() {
        return duration;
    }

    public int getPrice() {
        return price;
    }

    public String getCover() {
        return cover;
    }

    public List<String> getGallery() {
        return gallery;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    protected Itinerario(Parcel in) {
        id = in.readInt();
        name = in.readString();
        duration = in.readFloat();
        price = in.readInt();
        cover = in.readString();
        gallery = in.createStringArrayList();
        type = in.readString();
        description = in.readString();
    }

    public static final Creator<Itinerario> CREATOR = new Creator<Itinerario>() {
        @Override
        public Itinerario createFromParcel(Parcel in) {
            return new Itinerario(in);
        }

        @Override
        public Itinerario[] newArray(int size) {
            return new Itinerario[size];
        }
    };

    @Override
    public String toString() {
        return "\n{" +
                "id=" + id + ",\n" +
                "name='" + name + '\'' + ",\n" +
                "duration=" + duration + ",\n" +
                "price=" + price + ",\n" +
                "cover='" + cover + '\'' + ",\n" +
                "gallery=" + gallery + ",\n" +
                "type='" + type + '\'' + ",\n" +
                "description='" + description + '\'' + ",\n" +
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
        parcel.writeFloat(duration);
        parcel.writeInt(price);
        parcel.writeString(cover);
        parcel.writeStringList(gallery);
        parcel.writeString(type);
        parcel.writeString(description);
    }
}
