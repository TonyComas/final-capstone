package com.techelevator.dao;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class jdbcExternalAPIDao implements ExternalAPIDao{
    String tokenString="&token&key=b21164149bab4297929833e12b72394d";
    @Override
    public List<String> getImageURLs(String gameId) {
        String uri="https://api.rawg.io/api/games?search="+gameId+tokenString;
        RestTemplate apiRestTemplate=new RestTemplate();
//        SqlRowSet results = apiRestTemplate.getForObject(uri);
        return null;
    }
}
