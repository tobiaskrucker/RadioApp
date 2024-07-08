package com.example.radioapp;

public class Song {
    private String titel;
    private String interpret;
    private String album;

    // Konstruktor
    public Song(String titel, String interpret, String album) {
        this.titel = titel;
        this.interpret = interpret;
        this.album = album;
    }

    // Getter- und Setter-Methoden
    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getInterpret() {
        return interpret;
    }

    public void setInterpret(String interpret) {
        this.interpret = interpret;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
