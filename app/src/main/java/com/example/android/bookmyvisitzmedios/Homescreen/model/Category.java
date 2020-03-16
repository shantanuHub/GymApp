package com.example.android.bookmyvisitzmedios.Homescreen.model;

public class Category {

    private String title;
    private String location;
    private int    images;
    private String rating ;
    private String time;
    private int coverimage;

    public Category(String title, int images, int coverimage) {
        this.title = title;
        this.images = images;
        this.coverimage = coverimage;
    }

    public Category(String title, int images) {
        this.title = title;
        this.images = images;
    }

    public String getTime() {
        return time;
    }

    public Category(String title, String description, int images, String rating, String time) {
        this.title = title;
        this.location = description;
        this.images = images;
        this.rating = rating;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImdb() {
        return time;
    }

    public void setImdb(String imdb) {
        this.time = imdb;
    }

    public int getCoverimage() {
        return coverimage;
    }
}