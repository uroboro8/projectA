package com.example.pwbaseprova.piatti;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Piatti {

    @SerializedName("piatti")
    private List<Piatto> piatti;

    public List<Piatto> getPiatti() {
        return piatti;
    }

}
