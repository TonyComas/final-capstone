package com.techelevator.controller;

import com.techelevator.dao.GameDao;
import com.techelevator.model.Game;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping (path = "/?", method = RequestMethod.POST)
    public boolean addGame(){
        return false;
    }

    @RequestMapping (path = "/?", method = RequestMethod.DELETE)
    public boolean deleteGame(){
        return false;
    }

    @RequestMapping (path = "/?", method = RequestMethod.PUT)
    public boolean updateGame(){
        return false;
    }


}
