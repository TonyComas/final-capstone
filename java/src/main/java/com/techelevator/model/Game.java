package com.techelevator.model;

import java.time.LocalDate;

public class Game {

    private int game_id;
    private String game_name;
    private String description;
    private LocalDate release_date;
    private String developer_names;
    private String publisher_names;
    private String game_logo;
    private String genres;

    public Game (){

    }

    public Game(int game_id, String game_name, String description, LocalDate release_date, String developer_name, String publisher_Name, String game_logo, String genres) {
        this.game_id = game_id;
        this.game_name = game_name;
        this.description = description;
        this.release_date = release_date;
        this.developer_names = developer_name;
        this.publisher_names = publisher_Name;
        this.game_logo = game_logo;
        this.genres = genres;
    }

    public Game(int game_id, String game_name, String description, String release_date, String developer_name, String publisher_Name, String game_logo, String genres) {
        this.game_id = game_id;
        this.game_name = game_name;
        this.description = description;
        this.release_date = LocalDate.parse(release_date);
        this.developer_names = developer_name;
        this.publisher_names = publisher_Name;
        this.game_logo = game_logo;
        this.genres = genres;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    public String getDeveloper_names() {
        return developer_names;
    }

    public void setDeveloper_names(String developer_names) {
        this.developer_names = developer_names;
    }

    public String getPublisher_names() {
        return publisher_names;
    }

    public void setPublisher_names(String publisher_names) {
        this.publisher_names = publisher_names;
    }

    public String getGame_logo() {
        return game_logo;
    }

    public void setGame_logo(String game_logo) {
        this.game_logo = game_logo;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }
}
