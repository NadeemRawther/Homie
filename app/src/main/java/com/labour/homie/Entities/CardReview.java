package com.labour.homie.Entities;

public class CardReview {
    private String name;
    private String review;

    public CardReview(String name, String review) {
        this.name = name;
        this.review = review;
    }

    public String getName() {
        return name;
    }

    public String getReview() {
        return review;
    }
}
