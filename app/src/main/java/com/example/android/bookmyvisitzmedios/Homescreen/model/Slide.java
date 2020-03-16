package com.example.android.bookmyvisitzmedios.Homescreen.model;

public class Slide {

    private int img;
    private  String text;

    public Slide(int img, String text) {
        this.img = img;
        this.text = text;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
