package com.techelevator.dao;

import com.techelevator.model.Lists;
import org.springframework.jdbc.core.JdbcTemplate;
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
        String sql = "SELECT list_id, list_name, user_id FROM ";

        return lists;
    }

    @Override
    public List<Lists> getAllListsForUserId(int user_id) {
        return null;
    }

    @Override
    public Lists getListForListId(int list_id) {
        return null;
    }

    @Override
    public boolean addGameToList(int game_id) {
        return false;
    }
}
