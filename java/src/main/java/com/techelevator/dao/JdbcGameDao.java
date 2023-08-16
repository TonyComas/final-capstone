package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Game;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.relational.core.sql.IsNull;
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
    public List<Game> getGames(Game game) {
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

        String addGameSQL = "INSERT INTO video_games (game_name, description, release_date, game_logo) VALUES (?, ?, ?, ?) RETURNING game_id;";
        //
        int gameId = jdbcTemplate.queryForObject(addGameSQL,int.class, game.getGame_name(),game.getDescription(),game.getRelease_date(), game.getGame_logo());

        game.setGame_id(gameId);

        addPublishersForGame(game);
        addGenresForGame(game);
        addDevelopersForGame(game);

        return getGameByID(gameId);
    }

    @Override
    public void deleteGame(int id) {
//        count statements on the jdbc templates, and add returned value to sums.  If returned sum is 0, then throw an error stating nothing to delete.
        deletePubsForGameId(id);
        deleteDevsForGameId(id);
        deleteGenresForGameId(id);
        deleteReviewsForGameId(id);
        deleteGamesFromLists(id);
        String sql = "DELETE FROM video_games WHERE game_id = ?;";
        jdbcTemplate.update(sql,id);
    }

    private void deletePubsForGameId(int id){
        String sql = "DELETE FROM game_publishers WHERE game_id = ?";
        jdbcTemplate.update(sql,id);
    }
    private void deleteDevsForGameId(int id){
        String sql = "DELETE FROM game_developers WHERE game_id = ?;";
        jdbcTemplate.update(sql,id);
    }
    private void deleteGenresForGameId(int id){
        String sql = "DELETE FROM game_genre WHERE game_id = ?;";
        jdbcTemplate.update(sql,id);
    }
    private void deleteReviewsForGameId(int id){
        String sql = "DELETE FROM reviews WHERE game_id = ?";
        jdbcTemplate.update(sql, id);
    }
    private void deleteGamesFromLists(int game_id){
        String sql = "DELETE FROM games_list WHERE game_id = ?;";
        jdbcTemplate.update(sql, game_id);
    }

    private void addPublishersForGame(Game game){
        String addGamePublisher ="INSERT INTO game_publishers (game_id, publisher_id) VALUES (?, ?);";

        String compositePubs = game.getPublisher_names();
        List<String> pubs = List.of(compositePubs.split(", "));

        for (String pub : pubs){
            jdbcTemplate.update(addGamePublisher,game.getGame_id(), getPubID(pub));
        }
    }
    private void addDevelopersForGame(Game game){
        String addGameDevs = "INSERT INTO game_developers (game_id, developer_id) VALUES (?, ?);";

        String compositeDevs = game.getDeveloper_names();
        List<String> devs = List.of(compositeDevs.split(", "));

        for (String dev : devs){
            jdbcTemplate.update(addGameDevs,game.getGame_id(), getDevID(dev));
        }
    }
    private void addGenresForGame(Game game){
        String addGameGenre = "INSERT INTO game_genre (game_id, genre_id) VALUES (?, ?);";

        String compositeGenres = game.getGenres();
        List<String> genres = List.of(compositeGenres.split(", "));

        for (String genre : genres){
            jdbcTemplate.update(addGameGenre,game.getGame_id(), getGenID(genre));
        }
    }



    @Override
    public Game updateGame(Game game) {
        Game updatedGame = new Game();
        int gameId = game.getGame_id();
        String sql = "UPDATE video_games SET game_name = ?, description = ?, release_date = ?, game_logo = ? WHERE game_id = ?;";
        jdbcTemplate.update(sql, game.getGame_name(), game.getDescription(), game.getRelease_date(), game.getGame_logo(), gameId);

        deleteDevsForGameId(gameId);
        deletePubsForGameId(gameId);
        deleteGenresForGameId(gameId);

        addDevelopersForGame(game);
        addPublishersForGame(game);
        addGenresForGame(game);

        updatedGame = getGameByID(game.getGame_id());

        return updatedGame;
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

    @Override
    public List<Game> getAllGamesByListId(int list_id) {
        List<Integer> listOfGameIds = new ArrayList<>();
        String sql = "SELECT game_id FROM  games_list WHERE list_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, list_id);
        while (result.next()){
            int newId = result.getInt("game_id");
            listOfGameIds.add(newId);
        }
        List<Game> gameList = new ArrayList<>();
        for(int id : listOfGameIds){
            gameList.add(getGameByID(id));
        }
        return gameList;
    }

    private Game mapRowToGame(SqlRowSet results){
        Game game = new Game();
        game.setGame_id(results.getInt("game_id"));
        game.setGame_name(results.getString("game_name"));
        game.setDescription(results.getString("description"));
        if (results.getDate("release_date")!=null) {
            game.setRelease_date(results.getDate("release_date").toLocalDate());
        }
        game.setDeveloper_names(results.getString("developer_names"));
        game.setPublisher_names(results.getString("publisher_names"));
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
        if (devId==0){
            devId = addNewDeveloper(devName);
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
        if (pubId==0){
            pubId = addNewPublisher(pubName);
        }
        return pubId;
    }
    private int getGenID(String GenName){
        String sql="SELECT genre_id FROM genre WHERE genre_name = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql,GenName);
        int genId = 0;
        while (result.next()){
            genId= result.getInt("genre_id");
        }
        if (genId==0){
            genId = addNewGenre(GenName);
        }
        return genId;
    }

    private int addNewDeveloper(String devName){
        String sql = "INSERT INTO developers (developer_name) VALUES (?) RETURNING developer_id";
        return jdbcTemplate.queryForObject(sql, int.class, devName);
    }
    private int addNewPublisher(String pubName){
        String sql = "INSERT INTO publishers (publisher_name) VALUES (?) RETURNING publisher_id";
        return jdbcTemplate.queryForObject(sql, int.class, pubName);
    }
    private int addNewGenre(String genreName){
        String sql = "INSERT INTO genre (genre_name) VALUES (?) RETURNING genre_id";
        return jdbcTemplate.queryForObject(sql, int.class, genreName);
    }


}
