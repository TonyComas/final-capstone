package com.techelevator.controller;

import com.techelevator.dao.ReviewDao;
import com.techelevator.model.Reviews;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "reviews")
public class ReviewController {

    private ReviewDao reviewDao;

    public ReviewController(ReviewDao reviewDao){
        this.reviewDao = reviewDao;
    }

    @RequestMapping (path = "", method = RequestMethod.GET)
    public List<Reviews> getAllReviews() {
        return reviewDao.getAllReviews();
    }
    @RequestMapping (path = "/game/{game_id}", method = RequestMethod.GET)
    public List<Reviews> getReviewsForGame(@PathVariable("game_id")int game_id) {
        return reviewDao.getReviewsFromGameId(game_id);
    }

    @RequestMapping (path = "", method = RequestMethod.POST)
    public Reviews addReview(@RequestBody Reviews review){
        return reviewDao.addReview(review);
    }

    @RequestMapping (path = "/{review_id}", method = RequestMethod.GET)
    public Reviews getReviewFromReviewId(@PathVariable("review_id")int review_id) {
        return reviewDao.getReviewFromReviewId(review_id);
    }

    @RequestMapping (path = "/{review_id}", method = RequestMethod.DELETE)
    public void deleteReview(@PathVariable("review_id")int review_id){
        reviewDao.deleteReview(review_id);
    }



}
