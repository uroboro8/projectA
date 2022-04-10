package com.example.pwbaseprova;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GestureDetectorCompat;


import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pwbaseprova.piatti.Piatto;
import com.squareup.picasso.Picasso;

public class DettaglioPiattoActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private static final long VELOCITY_THRESHOLD=1000;
    private GestureDetectorCompat gDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettaglio_piatto);

        getSupportActionBar().hide();

        overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out);

        gDetector = new GestureDetectorCompat(this,this);


        Intent intent = getIntent();
        Piatto piatto = intent.getParcelableExtra("item-value");

        TextView title = findViewById(R.id.titlePiatto);
        TextView description = findViewById(R.id.descriptionPiatto);
        ImageView image = findViewById(R.id.imagePiatto);
        ImageView back = findViewById(R.id.backDettaglioPiatto);


        title.setText(piatto.getName());
        description.setText(piatto.getDescription());

        if(piatto.getImage() != null && piatto.getImage().trim().length() != 0)
            Picasso.get().load(piatto.getImage()).into(image);

        back.setOnClickListener(v ->{
            finish();
        });

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