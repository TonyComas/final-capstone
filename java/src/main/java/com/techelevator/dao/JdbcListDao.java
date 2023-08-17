package com.techelevator.dao;

import com.techelevator.model.Lists;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcListDao implements ListDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcListDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Lists> getAllLists() {
        List<Lists> lists = new ArrayList<>();
        String sql = "SELECT list_id, list_name, user_id FROM lists";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while(result.next()){
            lists.add(mapRowToList(result));
        }

        return lists;
    }

    @Override
    public List<Lists> getAllListsForUserId(int user_id) {
        List<Lists> lists = new ArrayList<>();
        String sql = "SELECT list_id, list_name, user_id FROM lists WHERE user_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, user_id);
        while(result.next()){
            lists.add(mapRowToList(result));
        }

        return lists;
    }

    @Override
    public Lists getListForListId(int list_id) {
        Lists list = new Lists();
        String sql = "SELECT list_id, list_name, user_id FROM lists WHERE list_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, list_id);
        while(result.next()){
            list = mapRowToList(result);
        }
        return list;
    }

    @Override
    public boolean addGameToList(int game_id, int list_id) {
        String sql = "INSERT INTO games_list(game_id, list_id) VALUES (?, ?)";
        try {
            jdbcTemplate.update(sql, game_id, list_id);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteGameFromList(int game_id, int list_id) {
        String sql = "DELETE FROM games_list WHERE game_id = ? AND list_id = ?;";
        try{
            jdbcTemplate.update(sql, game_id, list_id);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    @Override
    public int getListIdFromListNameAndUserId(int user_id, String list_name){
        String sql = "SELECT list_id FROM lists WHERE list_name = ? AND user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, list_name, user_id);
        int list_id = 0;
        while(result.next()){
            list_id = result.getInt("list_id");
        }
        return list_id;
    }



    private Lists mapRowToList(SqlRowSet result){
        Lists list = new Lists();
        list.setList_id(result.getInt("list_id"));
        list.setList_name(result.getString("list_name"));
        list.setUser_id(result.getInt("user_id"));

        return list;
    }

}
