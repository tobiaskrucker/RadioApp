

package com.example.radioapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReviewPlaylist extends AppCompatActivity
{
    Button homeButton;
    RatingBar ratingBarPlaylist;
    Button ratePlaylist;
    TextView ratingTextView;
    float stars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_playlist);
        homeButton = findViewById(R.id.home);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent second_intent = new Intent(ReviewPlaylist.this, MainActivity.class);
                startActivity(second_intent);
            }
        });

        // Views initialiseren
        ratingBarPlaylist = findViewById(R.id.ratingBar);
        ratePlaylist = findViewById(R.id.ratePlaylistButton);
        ratingTextView = findViewById(R.id.ratingTextView);

ratePlaylist.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        // Bewertung für Playlist erstellen
        stars = ratingBarPlaylist.getRating();
        ratingTextView.setText("Sie haben die aktuelle Playlist mit " + stars + " Sternen bewertet \nVielen Dank!");
        // Button deaktivieren
        ratePlaylist.setEnabled(false);
        // Intent zum Versender der Playlist-Bewertung per E-Mail an den Radiomoderator
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        // Hier muss die E-Mail durch die E-Mail Adresse des Radiosenders ersetzt werden
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"example@e-mail.de"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Playlist Bewertung");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Sie haben eine Bewertung für Ihre Playlist erhalten!\n"
        + stars + " Sterne");

        // Prüfen, ob E-Mail Dienst installiert
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        }else {
            Toast.makeText(ReviewPlaylist.this,"No email clients installed!",Toast.LENGTH_SHORT).show();
        }
    }});


    }
}

