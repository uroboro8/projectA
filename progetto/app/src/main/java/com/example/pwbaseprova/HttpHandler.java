package com.example.pwbaseprova;

import com.example.pwbaseprova.gallery.Gallery;
import com.example.pwbaseprova.itinerari.Itinerari;
import com.example.pwbaseprova.piatti.Piatti;
import retrofit2.Call;
import retrofit2.http.GET;

public interface  HttpHandler {

    @GET("piatti/piatti.json")
    Call<Piatti> getAllPiatti();

    @GET("itinerari/itinerari.json")
    Call<Itinerari> getAllItinerari();

    @GET("galleria/gallery.json")
    Call<Gallery> getAllImmaginiCustom();


}
