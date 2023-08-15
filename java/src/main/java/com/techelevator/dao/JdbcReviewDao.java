package com.techelevator.dao;

import com.techelevator.model.Reviews;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcReviewDao implements ReviewDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcReviewDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Reviews> getAllReviews() {
        List<Reviews> reviews = new ArrayList<>();
        String sql = "SELECT review_id, game_id, user_id, rating, review_title, review_body " +
                "FROM reviews;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while(result.next()){
            reviews.add(mapRowToReview(result));
        }
        return reviews;
    }

    @Override
    public List<Reviews> getReviewsFromGameId(int game_id) {
        List<Reviews> reviews = new ArrayList<>();
        String sql = "SELECT review_id, game_id, user_id, rating, review_title, review_body " +
                "FROM reviews WHERE game_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, game_id);
        while(result.next()){
            reviews.add(mapRowToReview(result));
        }
        return reviews;
    }

    @Override
    public Reviews getReviewFromReviewId(int review_id) {
        Reviews review = new Reviews();
        String sql = "SELECT review_id, game_id, user_id, rating, review_title, review_body " +
                "FROM reviews WHERE review_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, review_id);
        while(result.next()){
            review = mapRowToReview(result);
        }
        return review;

    }

    @Override
    public Reviews addReview(Reviews review) {
        Reviews newReview = new Reviews();
        String sql = "INSERT INTO reviews (game_id, user_id, rating, review_title, review_body) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING review_id;";
        int newReviewId = jdbcTemplate.queryForObject(sql, int.class, review.getGame_id(), review.getUser_id(), review.getRating(), review.getReview_title(), review.getReview_body());
        newReview = getReviewFromReviewId(newReviewId);
        return newReview;
    }

    @Override
    public void deleteReview(int review_id) {
        String sql = "DELETE FROM reviews WHERE review_id = ?;";
        jdbcTemplate.update(sql,review_id);
    }

    @Override
    public Reviews updateReview(Reviews review) {
        Reviews updatedReview = new Reviews();
        String sql = "UPDATE reviews SET rating = ?, review_title = ?, review_body = ? WHERE review_id = ?;";
        jdbcTemplate.update(sql, review.getRating(), review.getReview_title(), review.getReview_body(), review.getReview_id());
        updatedReview = getReviewFromReviewId(review.getReview_id());

        return updatedReview;
    }

    @Override
    public List<Reviews> getReviewFromUserID(int userID) {
        List<Reviews> reviews = new ArrayList<>();
        String sql = "SELECT review_id, game_id, user_id, rating, review_title, review_body " +
                "FROM reviews WHERE user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userID);
        while(result.next()){
            reviews.add(mapRowToReview(result));
        }
        return reviews;
    }


    private Reviews mapRowToReview(SqlRowSet result){
        Reviews review = new Reviews();
        review.setReview_id(result.getInt("review_id"));
        review.setGame_id(result.getInt("game_id"));
        review.setUser_id(result.getInt("user_id"));
        review.setRating(result.getInt("rating"));
        review.setReview_title(result.getString("review_title"));
        review.setReview_body(result.getString("review_body"));
        return review;
    }

}
