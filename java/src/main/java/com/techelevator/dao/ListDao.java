package com.techelevator.dao;

import com.techelevator.model.Game;
import com.techelevator.model.Lists;

import java.util.List;

public interface ListDao {

    List<Lists> getAllLists();

    List<Lists> getAllListsForUserId(int user_id);

    Lists getListForListId(int list_id);

    boolean addGameToList(int game_id);

}
