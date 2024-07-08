package com.example.radioapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.CheckBox;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import android.content.res.Resources;
import android.content.res.TypedArray;


import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {
    Button homeButton;
    ImageView logoIU;
    Button reviewPlaylistButton;
    Button reviewModeratorButton;
    Button songwishButton;
    TextView songInformationTextView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeButton = findViewById(R.id.home);
        logoIU = findViewById(R.id.logoIU);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent second_intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(second_intent);
            }
        });

        // Views initialisieren
        reviewModeratorButton = findViewById(R.id.reviewModeratorButton);
        reviewPlaylistButton = findViewById(R.id.reviewPlaylistButton);
        songwishButton = findViewById(R.id.songwishButton);
        songInformationTextView = findViewById(R.id.songInformationTextView);

        // Zugriff auf das "songs"-Array
        Resources res = getResources();
        TypedArray songsArray = res.obtainTypedArray(R.array.songs);

        // Generiert eine zufällige Zahl zwischen 0 (inklusive) und 10 (exklusive)
        Random random = new Random();
        int randomInt = random.nextInt(9);

        // Zugriff auf ein zufälliges "song"-Array über das "songs"-Array
        int songId = songsArray.getResourceId(randomInt, randomInt);
        String[] song = res.getStringArray(songId);

        songInformationTextView.setText("Titel: "+song[0].toString()+ "\n"+"Interpret: "+
                song[1].toString() +"\n"+ "Album: " + song[2].toString());

        // TypedArray freigeben
        songsArray.recycle();

        // Click listener für Buttons um die dazugehörige Activity zu starten
        songwishButton.setOnClickListener(v -> {
            Intent first_intent = new Intent(MainActivity.this, Songwish.class);
            startActivity(first_intent);
        });
        reviewPlaylistButton.setOnClickListener(v -> {
            Intent second_intent = new Intent(MainActivity.this, ReviewPlaylist.class);
            startActivity(second_intent);
        });
        reviewModeratorButton.setOnClickListener(v -> {
            Intent third_intent = new Intent(MainActivity.this, ReviewModerator.class);
            startActivity(third_intent);
        });
    }
}
