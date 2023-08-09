package com.techelevator.controller;

import com.techelevator.dao.SearchDao;
import com.techelevator.model.Game;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@CrossOrigin
@RestController
@RequestMapping (path = "search")
public class SearchController {

    private SearchDao searchDao;

    public SearchController(SearchDao searchDao){
        this.searchDao = searchDao;
    }

    @RequestMapping (path = "/devs" , method = RequestMethod.GET)
    public List<String> getDevsList(){
        return searchDao.getAllDevs();
    }

    @RequestMapping (path = "/pubs" , method = RequestMethod.GET)
    public List<String> getpubsList(){
        return searchDao.getAllPubs();
    }
    @RequestMapping (path = "/genre" , method = RequestMethod.GET)
    public List<String> getgenreList(){
        return searchDao.getAllGenres();
    }

    @RequestMapping (path = "/devs" , method = RequestMethod.PUT)
    public void addDev(@RequestBody String devName){
        searchDao.addDev(devName);
    }

    @RequestMapping (path = "/pubs" , method = RequestMethod.PUT)
    public void addPub(@RequestBody String pubName){
        searchDao.addPub(pubName);
    }
    @RequestMapping (path = "/genre" , method = RequestMethod.PUT)
    public void addGenre(@RequestBody String genreName){
        searchDao.addGenre(genreName);
    }



}
