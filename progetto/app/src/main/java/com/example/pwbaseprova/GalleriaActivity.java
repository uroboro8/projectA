package com.example.pwbaseprova;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.pwbaseprova.gallery.Gallery;
import com.example.pwbaseprova.gallery.ImageCustom;
import com.example.pwbaseprova.itinerari.Itinerari;
import com.example.pwbaseprova.piatti.Piatti;
import com.example.pwbaseprova.piatti.Piatto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GalleriaActivity extends AppCompatActivity implements  CustomAdapterGalleryImage.ItemClickListener,
        GestureDetector.OnGestureListener{

    private ArrayList<ImageCustom> gallery;
    private CustomAdapterGalleryImage adapter;

    private static final long VELOCITY_THRESHOLD=1000;
    private GestureDetectorCompat gDetector;

    private HttpHandler service;

    /*
     *  NOTE_DONE = richiesta non fatta
     *  OK =  successo
     *  BAD = fallimento
     */
    private String  REQUEST_CODE = "NOT_DONE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galleria);

        //Nasconde la barra in alto di default
        getSupportActionBar().hide();

        gDetector = new GestureDetectorCompat(this,this);

        //Creo la classe retrofit,dandogli l'url base e il convertitore per il Json
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://uroboro8.github.io/JsonRepository/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Http handler è un'interfaccia,retrofit si occupa di implementare il metodo
        service = retrofit.create(HttpHandler.class);

        //In questo mondo non ri-eseguo la chiamata http
        //passando da portrait a landscape e viceversa
        if(savedInstanceState == null) {
            gallery = new ArrayList<>();
            //Creo la request
            Call<Gallery> request = service.getAllImmaginiCustom();
            HttpCall(request);
        }else{
            gallery = savedInstanceState.getParcelableArrayList("gallery");
        }

        //Setto la RecyclerView per visualizzare gli elementi a grilia e gli imposto l'adapter per le mie immagini
        //Recycler View
        RecyclerView galleriaImmaginiRecyclerView = findViewById(R.id.recyclerViewGallery);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        galleriaImmaginiRecyclerView.setLayoutManager(gridLayoutManager);
        //Adapter
        adapter = new CustomAdapterGalleryImage(gallery);
        adapter.setClickListener(this);
        galleriaImmaginiRecyclerView.setAdapter(adapter);

        ImageView back = findViewById(R.id.backGalleriaFoto);
        back.setOnClickListener(v ->{
            finish();
        });
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("gallery",gallery);
    }

    public void HttpCall(Call<Gallery> request){
        //Faccio la chiamata al server
        request.enqueue(new Callback<Gallery>() {
            @Override
            public void onResponse(Call<Gallery> call, Response<Gallery> response) {
                if(response.isSuccessful()) {
                    List<ImageCustom> imageList = response.body().getGallery();
                    gallery.addAll(imageList);
                    adapter.notifyDataSetChanged();
                    Log.e("JSON",gallery + "");
                    REQUEST_CODE = "OK";
                }
            }
            @Override
            public void onFailure(Call<Gallery> call, Throwable t) {
                Log.e("failure",t.getMessage());
                REQUEST_CODE = "BAD";
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        //Se i dati NON sono stati recuperati la prima volta(es. no internet),rifaccio la chiamata
        if(REQUEST_CODE == "BAD"){
            Call<Gallery> request = service.getAllImmaginiCustom();
            HttpCall(request);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        ImageCustom image = adapter.getItem(position);
        GalleryDialogFragment dialogFragment=new GalleryDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("image_url",image.getImage());
        dialogFragment.setArguments(bundle);
        dialogFragment.show(getSupportFragmentManager(),"My  Fragment");
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.from_left_in, R.anim.from_right_out);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float vX, float vY) {
        if(Math.abs(vX) > Math.abs(vY)){
            if(vX < VELOCITY_THRESHOLD)
                return false;
            if(vX >= 0) {
                finish();
            }
        }
        return true;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

}