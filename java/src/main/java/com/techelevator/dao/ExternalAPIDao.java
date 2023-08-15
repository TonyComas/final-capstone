package com.techelevator.dao;

import java.util.List;

public interface ExternalAPIDao {

    List<String> getImageURLs(String gameId);
}
