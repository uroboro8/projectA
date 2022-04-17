package com.example.pwbaseprova;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ManeggioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maneggio);

        //Nasconde la barra in alto
        getSupportActionBar().hide();

        //Animazione per lo swipe in entrata
        overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out);

        //Implemento ritorno indietro al floating button
        FloatingActionButton back = findViewById(R.id.buttonBackManeggio);
        back.setOnClickListener(v -> {
            finish();
        });

        //prendiamo l'id di ogni immagine statica
        ImageView imageView = findViewById(R.id.imageCoverManeggio2);
        ImageView imageView1 = findViewById(R.id.imageCoverManeggio3);
        ImageView imageView2 = findViewById(R.id.imageCoverManeggio4);


        //qui facciamo apparire l'immagine dentro ad un fragment per poi zoommarla a piacere
        imageView1.setOnClickListener(v -> {
            GalleryDialogFragment dialogFragment=new GalleryDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("image_url","https://raw.githubusercontent.com/uroboro8/ImageRepository/main/images/m8.jpg");
            dialogFragment.setArguments(bundle);
            dialogFragment.show(getSupportFragmentManager(),"My  Fragment");
        });

        imageView2.setOnClickListener(v -> {
            GalleryDialogFragment dialogFragment=new GalleryDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("image_url","https://raw.githubusercontent.com/uroboro8/ImageRepository/main/images/m10.jpg");
            dialogFragment.setArguments(bundle);
            dialogFragment.show(getSupportFragmentManager(),"My  Fragment");
        });

        imageView.setOnClickListener(v -> {
            GalleryDialogFragment dialogFragment=new GalleryDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("image_url","https://raw.githubusercontent.com/uroboro8/ImageRepository/main/images/m9.jpg");
            dialogFragment.setArguments(bundle);
            dialogFragment.show(getSupportFragmentManager(),"My  Fragment");
        });

        buildSliderLayout();
    }

    //Qui implemento le immagini scrollabili nell'activity maneggio
    private void buildSliderLayout() {
        ///Image Slider
        ArrayList imageList = new ArrayList<SlideModel>();// Create image list

        imageList.add(new SlideModel("https://raw.githubusercontent.com/uroboro8/ImageRepository/main/images/m2.jpg", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel("https://raw.githubusercontent.com/uroboro8/ImageRepository/main/images/m3.jpg", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel("https://raw.githubusercontent.com/uroboro8/ImageRepository/main/images/m4.jpg", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel("https://raw.githubusercontent.com/uroboro8/ImageRepository/main/images/m5.jpg", ScaleTypes.CENTER_CROP));

        //qui apriamo le immagini dello slider in un fragment e zoommiamo
        ImageSlider imageSlider = findViewById(R.id.image_slider_maneggio);
        imageSlider.setImageList(imageList);
        imageSlider.setItemClickListener(position -> {
            SlideModel image = (SlideModel) imageList.get(position);
            GalleryDialogFragment dialogFragment=new GalleryDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("image_url",image.getImageUrl());
            dialogFragment.setArguments(bundle);
            dialogFragment.show(getSupportFragmentManager(),"My  Fragment");
        });
    }

    @Override
    public void finish() {
        super.finish();
        //Animazione per lo swipe in uscita
        overridePendingTransition(R.anim.from_left_in, R.anim.from_right_out);
    }
}