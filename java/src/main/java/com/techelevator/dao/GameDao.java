package com.techelevator.dao;


import com.techelevator.model.Game;

import java.util.List;

public interface GameDao {

    List<Game> getAllGames();

    List<Game> getGames(Game game);
    Game addGame(Game game);

    void deleteGame(int id);

    Game updateGame(Game game);

    Game getGameByID(int id);


}
