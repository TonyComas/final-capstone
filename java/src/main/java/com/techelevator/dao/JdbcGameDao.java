package com.techelevator.dao;

import com.techelevator.model.Game;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGameDao implements GameDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcGameDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> listy = new ArrayList<>();
        String sql = "SELECT game_id, game_name, description, release_date FROM video_games";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while(result.next()){
            listy.add(mapRowToGame(result));
        }
        return listy;
    }

    private Game mapRowToGame(SqlRowSet results){
        Game game = new Game();
        game.setGame_id(results.getInt("game_id"));
        game.setGame_name(results.getString("game_name"));
        game.setDescription(results.getString("description"));
        if (results.getDate("release_date")!=null) {
            game.setRelease_date(results.getDate("release_date").toLocalDate());
        }
        return game;
    }
}
