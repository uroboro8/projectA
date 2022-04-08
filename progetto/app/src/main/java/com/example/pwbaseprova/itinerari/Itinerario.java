package com.example.pwbaseprova.itinerari;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Itinerario {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("duration")
    private int duration;

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

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public List<String> getGallery() {
        return gallery;
    }

    public void setGallery(List<String> gallery) {
        this.gallery = gallery;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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
}
