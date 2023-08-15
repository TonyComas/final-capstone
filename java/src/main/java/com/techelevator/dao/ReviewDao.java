package com.techelevator.dao;

import com.techelevator.model.Reviews;

import java.util.List;

public interface ReviewDao {

    List<Reviews> getAllReviews();

    List<Reviews> getReviewsFromGameId(int game_id);
    Reviews getReviewFromReviewId(int review_id);

    Reviews addReview(Reviews review);

    void deleteReview(int review_id);

    Reviews updateReview(Reviews review);

}
