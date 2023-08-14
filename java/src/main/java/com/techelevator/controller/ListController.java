package com.techelevator.controller;

import com.techelevator.dao.ListDao;
import com.techelevator.model.Lists;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "lists")
public class ListController {

    private ListDao listDao;
    public ListController(ListDao listDao){
        this.listDao = listDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Lists> getAllLists(){
        return listDao.getAllLists();
    }

    @RequestMapping(path = "/user/{user_id}", method = RequestMethod.GET)
    public List<Lists> getAllListsForUser(@PathVariable("user_id")int user_id){
        return listDao.getAllListsForUserId(user_id);
    }

    @RequestMapping(path = "/{list_id}", method = RequestMethod.GET)
    public Lists getListFromListId(@PathVariable("list_id")int list_id){
        return listDao.getListForListId(list_id);
    }

    @RequestMapping(path = "/{list_id}/{game_id}", method = RequestMethod.POST)
    public boolean addGameToList(@PathVariable("list_id")int list_id, @PathVariable("game_id")int game_id){
        return listDao.addGameToList(game_id,list_id);
    }
}
