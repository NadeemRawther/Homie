package com.labour.homie.Entities;

public class Review {
    private String name;
    private String review;
    private String rating;

    public Review(String name, String review, String rating) {
        this.name = name;
        this.review = review;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getReview() {
        return review;
    }

    public String getRating() {
        return rating;
    }
}
