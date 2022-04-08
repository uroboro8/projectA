package com.example.pwbaseprova.piatti;

import com.google.gson.annotations.SerializedName;

public class Piatto {

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
}
