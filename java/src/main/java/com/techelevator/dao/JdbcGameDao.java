package com.techelevator.dao;

import com.techelevator.model.Game;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGameDao implements GameDao{

    String fromVideoGameSQL = "SELECT video_games.game_id, video_games.game_name, video_games.description, video_games.release_date, video_games.game_logo, \n" +
            "(\n" +
            "\tSELECT STRING_AGG(DISTINCT developers.developer_name, ', ')\n" +
            "\tFROM developers\n" +
            "\tJOIN game_developers ON developers.developer_id = game_developers.developer_id\n" +
            "\tWHERE game_developers.game_id = video_games.game_id\n" +
            "    ) AS developer_names,\n" +
            "(\n" +
            "    SELECT STRING_AGG(DISTINCT publishers.publisher_name, ', ')\n" +
            "    FROM publishers\n" +
            "    JOIN game_publishers ON publishers.publisher_id = game_publishers.publisher_id\n" +
            "    WHERE game_publishers.game_id = video_games.game_id\n" +
            "    ) AS publisher_names,\n" +
            "\t\n" +
            "\tSTRING_AGG(DISTINCT genre.genre_name, ', ')\n" +
            "\tAS genres\n" +
            "\tFROM video_games\n" +
            "\t\n" +
            "\tLEFT JOIN game_genre ON video_games.game_id = game_genre.game_id\n" +
            "    LEFT JOIN genre ON game_genre.genre_id = genre.genre_id\n" +
            "\t\n" +
            "\tGROUP BY video_games.game_id";

    private JdbcTemplate jdbcTemplate;

    public JdbcGameDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> listy = new ArrayList<>();
        String sql = fromVideoGameSQL +";";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while(result.next()){
            listy.add(mapRowToGame(result));
        }
        return listy;
    }

    @Override
    public boolean addGame() {
        return false;
    }

    @Override
    public boolean deleteGame(int id) {
        String sql = "DELETE FROM video_games WHERE game_id = ?;";
//        jdbcTemplate.update(sql,id);

        return false;
    }

    @Override
    public boolean updateGame() {
        return false;
    }

    @Override
    public Game getGameByID(int id) {
        Game game = new Game();
        String sql = fromVideoGameSQL +" WHERE game_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        while(result.next()){
            game = mapRowToGame(result);
        }
        return game;
    }

    private Game mapRowToGame(SqlRowSet results){
        Game game = new Game();
        game.setGame_id(results.getInt("game_id"));
        game.setGame_name(results.getString("game_name"));
        game.setDescription(results.getString("description"));
        if (results.getDate("release_date")!=null) {
            game.setRelease_date(results.getDate("release_date").toLocalDate());
        }
        game.setDeveloper_name(results.getString("developer_names"));
        game.setPublisher_Name(results.getString("publisher_names"));
        game.setGame_Logo(results.getString("game_logo"));
        game.setGenres(results.getString("genres"));
        return game;
    }
}
