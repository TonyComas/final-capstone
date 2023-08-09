package com.techelevator.dao;

import java.util.List;

public interface SearchDao {
    List<String> getAllDevs();
    List<String> getAllPubs();
    List<String> getAllGenres();
    void addDev(String dev);
    void addPub(String pub);
    void addGenre(String genre);

}
