package com.techelevator.controller;

import com.techelevator.dao.GameDao;
import com.techelevator.model.Game;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


}
