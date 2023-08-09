package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcSearchDao implements SearchDao{
    private JdbcTemplate jdbcTemplate;

    public JdbcSearchDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public List<String> getAllDevs() {
        List<String> devs = new ArrayList<>();
        String sql = "SELECT developer_name FROM developers;";
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            devs.add(results.getString("developer_name"));
        }
        return devs;
    }

    @Override
    public List<String> getAllPubs() {
        List<String> pubs = new ArrayList<>();
        String sql = "SELECT publisher_name FROM publishers;";
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            pubs.add(results.getString("publisher_name"));
        }
        return pubs;
    }

    @Override
    public List<String> getAllGenres() {
        List<String> genres = new ArrayList<>();
        String sql = "SELECT genre_name FROM Genre;";
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            genres.add(results.getString("genre_name"));
        }
        return genres;
    }

    @Override
    public void addDev(String dev) {
        String sql = "INSERT INTO developers (developer_name) VALUES (?)";
        jdbcTemplate.update(sql,dev);
    }

    @Override
    public void addPub(String pub) {
        String sql = "INSERT INTO publishers (publisher_name) VALUES (?)";
        jdbcTemplate.update(sql,pub);
    }

    @Override
    public void addGenre(String genre) {
        String sql = "INSERT INTO genre (genre_name) VALUES (?)";
        jdbcTemplate.update(sql,genre);
    }
}
