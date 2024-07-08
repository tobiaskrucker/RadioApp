package com.example.radioapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class Songwish extends AppCompatActivity {
    Button homeButton;
    Button sendSongwishButton;
    TextInputEditText TitelTextInput;
    TextInputEditText InterpretTextInput;
    TextInputEditText AlbumTextInput;
    TextView songInformation;
    Song song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songwish);
        homeButton = findViewById(R.id.home);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent second_intent = new Intent(Songwish.this, MainActivity.class);
                startActivity(second_intent);
            }
        });

        // Views initialisieren
        sendSongwishButton = findViewById(R.id.sendSongwishButton);
        TitelTextInput = findViewById(R.id.TitelTextInput);
        InterpretTextInput = findViewById(R.id.InterpretTextInput);
        AlbumTextInput = findViewById(R.id.AlbumTextInput);
        songInformation = findViewById(R.id.songInformation);

        sendSongwishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Songwunsch erstellen
                String Titel = TitelTextInput.getText().toString();
                String Interpret = InterpretTextInput.getText().toString();
                String Album = AlbumTextInput.getText().toString();
                // Model initialisieren
                song = new Song(Titel, Interpret, Album);
                // Intent um Songwunsch per E-Mail an Radiosender senden
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                // Hier muss die E-Mail durch die E-Mail Adresse des Radiosenders ersetzt werden
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"Beispiel@mail.de"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Songwunsch");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Titel: " + song.getTitel()
                        + "\n Interpret: " + song.getInterpret() + "\n Album: " + song.getAlbum());

                // Prüfen, ob E-Mail Dienst installiert
                if (emailIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(emailIntent);
                }else {
                    Toast.makeText(Songwish.this,"No email clients installed!",
                            Toast.LENGTH_SHORT).show();}

                // TextView mit gewünschten Song in der Activity anzeigen
                songInformation.setText("Sie haben sich " + song.getTitel() + " von "
                        + song.getInterpret() + " aus dem Album " + song.getAlbum() + " gewünscht!");
                // Button deaktivieren
                sendSongwishButton.setEnabled(false);
            }
        });
    }
}
