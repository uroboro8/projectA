package com.example.pwbaseprova.itinerari;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Itinerari {

    @SerializedName("itinerari")
    private List<Itinerario> itinerari;

    public List<Itinerario> getItinerari() {
        return itinerari;
    }

    public void setItinerari(List<Itinerario> itinerari) {
        this.itinerari = itinerari;
    }
}
