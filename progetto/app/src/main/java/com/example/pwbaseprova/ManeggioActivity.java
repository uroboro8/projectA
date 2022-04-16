package com.example.pwbaseprova;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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

        ImageSlider imageSlider = findViewById(R.id.image_slider_maneggio);
        imageSlider.setImageList(imageList);
    }

    @Override
    public void finish() {
        super.finish();
        //Animazione per lo swipe in uscita
        overridePendingTransition(R.anim.from_left_in, R.anim.from_right_out);
    }
}