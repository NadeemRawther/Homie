package com.labour.homie.Entities;

public class CardReview {
    private String name;
    private String review;
    private String userid;
    private String rating;

    public CardReview(String name, String review,String userid,String rating) {
        this.name = name;
        this.review = review;
        this.userid = userid;
        this.rating = rating;
    }

    public String getName(){
        return name;
    }

    public String getReview(){
        return review;
    }

    public String getUserid(){
        return userid;
    }

    public String getRating(){
        return rating;
    }
}
