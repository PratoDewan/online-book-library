package com.prato.onlinebooklibrary.model;

public class ReviewDto {
    private float rating;
    private String comment;

    public void setComment(String comment) {
        this.comment = comment;
    }
    public void setRating(float rating){
        this.rating=rating;
    }

    public float getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}
