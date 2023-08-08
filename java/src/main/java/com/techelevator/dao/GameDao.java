package com.techelevator.dao;


import com.techelevator.model.Game;

import java.util.List;

public interface GameDao {

    List<Game> getAllGames();

    Game addGame(Game game);

    void deleteGame(int id);

    boolean updateGame();


    Game getGameByID(int id);
}
