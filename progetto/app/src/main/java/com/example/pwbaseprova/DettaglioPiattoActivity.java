package com.example.pwbaseprova;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pwbaseprova.piatti.Piatto;
import com.squareup.picasso.Picasso;

public class DettaglioPiattoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettaglio_piatto);

        Intent intent = getIntent();
        Piatto piatto = intent.getParcelableExtra("item-value");

        TextView title = findViewById(R.id.titlePiatto);
        TextView description = findViewById(R.id.descriptionPiatto);
        ImageView image = findViewById(R.id.imagePiatto);

        title.setText(piatto.getName());
        description.setText(piatto.getDescription());
        Picasso.get().load(piatto.getImage()).into(image);
    }
}