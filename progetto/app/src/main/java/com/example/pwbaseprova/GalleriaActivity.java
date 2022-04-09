package com.example.pwbaseprova;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.pwbaseprova.gallery.Gallery;
import com.example.pwbaseprova.gallery.ImageCustom;
import com.example.pwbaseprova.piatti.Piatti;
import com.example.pwbaseprova.piatti.Piatto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GalleriaActivity extends AppCompatActivity implements  CustomAdapterGalleryImage.ItemClickListener {

    ArrayList<ImageCustom> gallery;
    CustomAdapterGalleryImage adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galleria);

        gallery = new ArrayList<>();

        RecyclerView myRw2 = findViewById(R.id.recyclerViewGallery);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false);
        myRw2.setLayoutManager(gridLayoutManager);
        adapter = new CustomAdapterGalleryImage(gallery);
        adapter.setClickListener(this);
        myRw2.setAdapter(adapter);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://uroboro8.github.io/JsonRepository/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Http handler è un'interfaccia,retrofit si occupa di istanziarla
        HttpHandler service = retrofit.create(HttpHandler.class);

        //Gli dico quale metodo usare
        Call<Gallery> piatti = service.getAllImmaginiCustom();

        //Faccio la chiamata al server
        piatti.enqueue(new Callback<Gallery>() {
            @Override
            public void onResponse(Call<Gallery> call, Response<Gallery> response) {
                if(response.isSuccessful()) {
                    List<ImageCustom> imageList = response.body().getGallery();
                    gallery.addAll(imageList);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Gallery> call, Throwable t) {

            }
        });

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


}