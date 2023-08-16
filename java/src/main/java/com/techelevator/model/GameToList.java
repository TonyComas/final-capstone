package com.techelevator.model;

public class GameToList {
    private int game_id;
    private int user_id;
    private String list_name;

    public GameToList() {
    }

    public GameToList(int game_id, int user_id, String list_name) {
        this.game_id = game_id;
        this.user_id = user_id;
        this.list_name = list_name;
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

    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }
}
