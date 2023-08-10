package com.techelevator.model;

public class Reviews {
    private int review_id;
    private int game_id;
    private int user_id;
    private int rating;
    private String review_title;
    private String review_body;

    public Reviews() {
    }

    public Reviews(int review_id, int game_id, int user_id, int rating, String review_title, String review_body) {
        this.review_id = review_id;
        this.game_id = game_id;
        this.user_id = user_id;
        this.rating = rating;
        this.review_title = review_title;
        this.review_body = review_body;
    }

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview_title() {
        return review_title;
    }

    public void setReview_title(String review_title) {
        this.review_title = review_title;
    }

    public String getReview_body() {
        return review_body;
    }

    public void setReview_body(String review_body) {
        this.review_body = review_body;
    }
}
