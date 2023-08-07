package com.techelevator.model;

import java.time.LocalDate;

public class Game {

    private int game_id;
    private String game_name;
    private String description;
    private LocalDate release_date;
    private String developer_name;
    private String publisher_Name;
    private String game_Logo;

    public Game (){

    }

    public Game (int game_ID, String game_name, String description, LocalDate release_date){
        this.game_id = game_ID;
        this.game_name=game_name;
        this.description=description;
        this.release_date=release_date;
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

    public String getDeveloper_name() {
        return developer_name;
    }

    public void setDeveloper_name(String developer_name) {
        this.developer_name = developer_name;
    }

    public String getPublisher_Name() {
        return publisher_Name;
    }

    public void setPublisher_Name(String publisher_Name) {
        this.publisher_Name = publisher_Name;
    }

    public String getGame_Logo() {
        return game_Logo;
    }

    public void setGame_Logo(String game_Logo) {
        this.game_Logo = game_Logo;
    }
}
