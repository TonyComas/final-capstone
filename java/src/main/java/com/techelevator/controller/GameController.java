package com.techelevator.controller;

import com.techelevator.dao.GameDao;
import com.techelevator.model.Game;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@CrossOrigin
@RestController
@RequestMapping (path = "games")
public class GameController {

    private GameDao gameDao;

    public GameController(GameDao gameDao){
        this.gameDao = gameDao;
    }

    @RequestMapping (method = RequestMethod.GET)
    public List<Game> getAllGames(){
        return gameDao.getAllGames();
    }

    @RequestMapping (path = "/{id}", method = RequestMethod.GET)
    public Game getGameByID(@PathVariable("id") int id){
        return gameDao.getGameByID(id);
    }

    @RequestMapping (path = "/add-game" ,method = RequestMethod.POST)
    public boolean addGame(@RequestBody Game game){
        return addGame(game);
    }

    @RequestMapping (path = "/{id}", method = RequestMethod.DELETE)
    public void deleteGame(@PathVariable("id") int id){
        gameDao.deleteGame(id);
    }

    @RequestMapping (path = "/{id}", method = RequestMethod.PUT)
    public boolean updateGame(){
        return false;
    }


}
