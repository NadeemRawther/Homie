package com.labour.homie.Entities;

public class ImagAndText {
    private String title;
    private int image;


    public ImagAndText(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }
}
