package com.techelevator.model;

import java.time.LocalDate;

public class Game {

    private int game_id;
    private String game_name;
    private String description;
    private LocalDate release_date;

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
}
