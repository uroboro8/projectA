package com.example.pwbaseprova;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pwbaseprova.itinerari.Itinerario;
import com.example.pwbaseprova.piatti.Piatto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DettaglioItinerarioActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private static final long VELOCITY_THRESHOLD=1000;
    private GestureDetectorCompat gDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettaglio_itinerario);

        //Nasconde la barra in alto
        getSupportActionBar().hide();

        //Animazione per lo swipe in entrata
        overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out);

        gDetector = new GestureDetectorCompat(this,this);

        Intent intent = getIntent();
        Itinerario itinerario = intent.getParcelableExtra("item-value");

        ImageView cover = findViewById(R.id.imageCoverItinerario);
        loadImage(itinerario.getCover(),cover);

        TextView title =  findViewById(R.id.titleItinerario);
        title.setText(itinerario.getName());

        TextView description =  findViewById(R.id.descriptionItinerario);
        description.setText(itinerario.getDescription());

        TextView duration  = findViewById(R.id.durationItinerario);
        String durationString = String.valueOf(itinerario.getDuration());
        String[] durationSplit = durationString.split("\\.");
        if (durationSplit[1].equals("0"))
            duration.setText(durationSplit[0] + " ore");
        else
            duration.setText((durationString) + " ore");


        TextView price = findViewById(R.id.priceItinerario);
        price.setText(String.valueOf(itinerario.getPrice()) + " euro");

        TextView category = findViewById(R.id.categoryItinerario);
        category.setText(itinerario.getType());

        ArrayList<String> galleryItinerario = new ArrayList<>();
        galleryItinerario.addAll(itinerario.getGallery());

        ImageView imageItinerario1 = findViewById(R.id.imageItinerario1);
        ImageView imageItinerario2 = findViewById(R.id.imageItinerario2);
        ImageView imageItinerario3 = findViewById(R.id.imageItinerario3);
        ImageView[] imageList = new ImageView[] { imageItinerario1, imageItinerario2, imageItinerario3};

        for(int i=0;i<galleryItinerario.size();i++){
            loadImage(galleryItinerario.get(i),imageList[i]);
        }

        FloatingActionButton back = findViewById(R.id.floatingActionButtonBack);
        back.setOnClickListener(v -> {
            finish();
        });
    }

    private void loadImage(String imageLink,ImageView imageView){
        if(imageLink != null && imageLink.trim().length() != 0)
            Picasso.get().load(imageLink).into(imageView);
    }

    @Override
    public void finish() {
        super.finish();
        //Animazione per lo swipe in uscita
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