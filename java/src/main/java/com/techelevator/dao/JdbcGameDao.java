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

            "    LEFT JOIN genre ON game_genre.genre_id = genre.genre_id";




/*

"INSERT INTO video_games (game_name, description, release_date) " + "VALUES (?, ?, ?) RETURNING game_id;"
//if game id NOT null
"INSERT INTO game_developers (game_id, developer_id) " + "VALUES (?, ?);"
//for loop
"INSERT INTO game_publishers (game_id, publisher_id) " + "VALUES (?, ?);"
//for loop
"INSERT INTO game_genre (game_id, genre_id) " + "VALUES (?, ?);"
//for loop

*/


    private JdbcTemplate jdbcTemplate;

    public JdbcGameDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> listy = new ArrayList<>();

        String sql = fromVideoGameSQL +" GROUP BY video_games.game_id;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while(result.next()){
            listy.add(mapRowToGame(result));
        }
        return listy;
    }

    @Override
    public Game addGame(Game game) {

        Game newGame = game;
        int gameId = 0;
        String addGameSQL = "INSERT INTO video_games (game_name, description, release_date, game_logo) VALUES (?, ?, ?, ?) RETURNING game_id;";
        SqlRowSet results=jdbcTemplate.queryForRowSet(addGameSQL,newGame.getGame_name(),newGame.getDescription(),newGame.getRelease_date(), newGame.getGame_logo());
        while(results.next()){
            newGame.setGame_id(results.getInt("game_id"));
            gameId = results.getInt("game_id");
        }

        // adding Publisher : Start by constructing the SQL string.
        String addGameDevs = "INSERT INTO game_developers (game_id, developer_id) VALUES (?, ?);";
        // converting a provided string of comma space separated devs to a list of devs
        String compositeDevs = newGame.getDeveloper_name();
        List<String> devs = List.of(compositeDevs.split(", "));
        // run the SQL string for each dev in the list.
        for (String dev : devs){
            jdbcTemplate.update(addGameDevs,gameId, getDevID(dev));
        }


        // adding Publisher : repeating all steps done for Dev
        String addGamePublisher ="INSERT INTO game_publishers (game_id, publisher_id) VALUES (?, ?);";
        //
        String compositePubs = newGame.getPublisher_Name();
        List<String> pubs = List.of(compositePubs.split(", "));
        //
        for (String pub : pubs){
            jdbcTemplate.update(addGamePublisher,gameId, getPubID(pub));
        }

        // adding Genres : repeating steps for devs and publishers.
        String addGameGenre = "INSERT INTO game_genre (game_id, genre_id) VALUES (?, ?);";
        //
        String compositeGenres = newGame.getGenres();
        List<String> genres = List.of(compositeGenres.split(", "));
        //
        for (String genre : genres){
            jdbcTemplate.update(addGameGenre,gameId, getGenID(genre));
        }


        return newGame;
    }

    @Override
    public void deleteGame(int id) {
        String sql = "DELETE FROM game_publishers WHERE game_id = ?";
        jdbcTemplate.update(sql,id);
        sql = "DELETE FROM game_developers WHERE game_id = ?;";
        jdbcTemplate.update(sql,id);
        sql = "DELETE FROM game_genre WHERE game_id = ?;";
        jdbcTemplate.update(sql,id);
        sql = "DELETE FROM video_games WHERE game_id = ?;";
        jdbcTemplate.update(sql,id);
    }



    @Override
    public boolean updateGame() {
        return false;
    }

    @Override
    public Game getGameByID(int id) {
        Game game = new Game();

        String sql = fromVideoGameSQL +" WHERE video_games.game_id = ? GROUP BY video_games.game_id;";

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
        game.setGame_logo(results.getString("game_logo"));
        game.setGenres(results.getString("genres"));
        return game;
    }

    private int getDevID(String devName){
        String sql="SELECT developer_id FROM developers WHERE developer_name = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql,devName);
        int devId = 0;
        while (result.next()){
            devId= result.getInt("developer_id");
        }
        return devId;
    }
    private int getPubID(String pubName){
        String sql="SELECT publisher_id FROM publishers WHERE publisher_name = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql,pubName);
        int pubId = 0;
        while (result.next()){
            pubId= result.getInt("publisher_id");
        }
        return pubId;
    }
    private int getGenID(String GenName){
        String sql="SELECT genre_id FROM genre WHERE genre_name = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql,GenName);
        int GenId = 0;
        while (result.next()){
            GenId= result.getInt("genre_id");
        }
        return GenId;
    }
}
