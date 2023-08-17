package com.techelevator.controller;

import com.techelevator.dao.ListDao;
import com.techelevator.model.Game;
import com.techelevator.model.GameList;
import com.techelevator.model.GameToList;
import com.techelevator.model.Lists;
import org.springframework.web.bind.annotation.*;

import java.lang.ref.PhantomReference;
import java.lang.reflect.Array;
import java.security.Principal;
import java.util.ArrayList;
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

    @RequestMapping(path = "/game", method = RequestMethod.POST)
    public boolean addGameToList(@RequestBody GameToList gameToList){
        int list_id = listDao.getListIdFromListNameAndUserId(gameToList.getUser_id(), gameToList.getList_name());
        return listDao.addGameToList(gameToList.getGame_id(), list_id);
    }

    @RequestMapping(path = "/game", method = RequestMethod.DELETE)
    public boolean deleteGameFromList(@RequestBody GameToList gameToList){
        int list_id = listDao.getListIdFromListNameAndUserId(gameToList.getUser_id(), gameToList.getList_name());
        return listDao.deleteGameFromList(gameToList.getGame_id(), list_id);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<GameList> getListOfListOfGames(Principal principal) {
        List<GameList> outerArray = new ArrayList<>();


        GameList favoriteList = new GameList();

        List<Game> favorites = new ArrayList<>();
        Game game1 = new Game();
        game1.setGame_name("Game 1");
        game1.setDescription("This is Game 1");

        Game game2 = new Game();
        game2.setGame_name("Game 2");
        game2.setDescription("This is game 2");
        favorites.add(game1);
        favorites.add(game2);

        favoriteList.setGames(favorites);
        favoriteList.setListName("Favorites");

        GameList wishListList = new GameList();


        List<Game> wishList = new ArrayList<>();
        Game game3 = new Game();
        game3.setGame_name("Game 3");
        game3.setDescription("This is Game 3");

        Game game4 = new Game();
        game4.setGame_name("Game 4");
        game4.setDescription("This is game 4");
        wishList.add(game3);
        wishList.add(game4);

        wishListList.setGames(wishList);
        wishListList.setListName("WishList");

        outerArray.add(wishListList);
        outerArray.add(favoriteList);

        return outerArray;
    }
}
