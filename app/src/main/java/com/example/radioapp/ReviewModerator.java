

package com.example.radioapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class ReviewModerator extends AppCompatActivity
{
Button homeButton;
Spinner spinner;
RatingBar ratingBarStimme;
RatingBar ratingBarHumor;
RatingBar ratingBarMusikauswahl;
Button buttonSendModeratorReview;
TextView reviewModeratorTextView;
TextInputEditText comment;
ImageView logoIU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_moderator);
        homeButton = findViewById(R.id.home);
        logoIU = findViewById(R.id.logoIU);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent second_intent = new Intent(ReviewModerator.this, MainActivity.class);
                startActivity(second_intent);
            }
        });
        // Views initialisieren
        spinner = findViewById(R.id.spinner);
        ratingBarHumor = findViewById(R.id.ratingBarHumor);
        ratingBarStimme = findViewById(R.id.ratingBarStimme);
        ratingBarMusikauswahl = findViewById(R.id.ratingBarMusikauswahl);
        buttonSendModeratorReview = findViewById(R.id.buttonSendReviewModerator);
        reviewModeratorTextView = findViewById(R.id.reviewModeratorTextView);
        comment = findViewById(R.id.comment);

        // Spinner zur Auswahl der Moderator:innen
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.moderator_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                Toast.makeText(ReviewModerator.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Click Listener "Radiomoderator:in bewerten"
        buttonSendModeratorReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Moderator:in Bewertung erstellen
                String spinnerString = spinner.getSelectedItem().toString();
                String Email = ""; // Hier könnte man noch die E-Mail des Radiosenders einfügen
                switch (spinnerString) {
                    case "Julia Sommer":
                        Email = "E-Mail Julia Sommer"; // E-Mail der Moderatorin muss noch eingefügt werden
                        break;
                    case "Max Winter":
                        Email = "E-Mail Max Winter"; // E-Mail des Moderator muss noch eingefügt werden
                        break;
                    case "Hans Meier":
                        Email = "E-Mail Hans Meier"; // E-Mail des Moderator muss noch eingefügt werden

                        break;
                }
                float Stimme = ratingBarStimme.getRating();
                float Humor = ratingBarHumor.getRating();
                float Musikauswahl = ratingBarMusikauswahl.getRating();
                String commentText = comment.getText().toString();
                reviewModeratorTextView.setText("Sie haben eine Bewertung an Moderator:in " + Email + " gesendet!\n"
                        + "Stimme: " + Stimme + "\n" + "Humor: " + Humor + "\n" + "Musikauswahl: " + Musikauswahl + "\n" +
                        "Sonstige Bemerkungen:\n" + commentText);

                // Intent zum Versender der Moderator-Bewertung per E-Mail an den ausgewählten Radiomoderator
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{Email});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Moderator:in Bewertung");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Sie haben eine Bewertung als Moderator:in erhalten!\n"
                + "Stimme: " + Stimme + "\n" + "Humor: " + Humor + "\n" + "Musikauswahl: " + Musikauswahl + "\n" +
                        "Sonstige Bemerkungen: \n" + commentText + "\n");

                // Prüfen, ob E-Mail Dienst installiert
                if (emailIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(emailIntent);
                }else {
                    Toast.makeText(ReviewModerator.this,"No email clients installed!",Toast.LENGTH_SHORT).show();
                }
                // Button deaktivieren
                buttonSendModeratorReview.setEnabled(false);
            }
        });

    }

}

