package com.example.pwbaseprova.gallery;

import com.example.pwbaseprova.itinerari.Itinerario;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Gallery {

    @SerializedName("galleria")
    private List<ImageCustom> gallery;

    public List<ImageCustom> getGallery() {
        return gallery;
    }

}
