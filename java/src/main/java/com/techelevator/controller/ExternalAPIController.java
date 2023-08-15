package com.techelevator.controller;

import com.techelevator.model.Game;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "images")
public class ExternalAPIController {
    @RequestMapping (path = "/{game_id}", method = RequestMethod.GET)
    public Game getGameByID(@PathVariable("game_id") String gameId){

        return null;
    }
}
