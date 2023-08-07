package com.techelevator.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class JdbcGameDaoTest extends BaseDaoTests{

    private JdbcGameDao sut;

    @Before
     public void setup (){
        sut = new JdbcGameDao(dataSource);

    }

    @Test
    public void getAllGamesWorksAsIntendedMaybe(){
        Assert.assertEquals(5,sut.getAllGames().size());
    }
}
