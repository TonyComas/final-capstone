<template>
  <div>
    <div class="ListName">
      <label for="ListName">List Name: </label>
      <input type="text" name="ListName" />
    </div>
    <div class="ListDescription">
      <label for="ListDescription">Description: </label>
      <input type="text" name="ListDesciption" />
    </div>
  </div>
</template>

<script>
import GameServices from "../services/GameServices";
export default {
  name: "create-list",
  data() {
    return {
      list: [
        {
          listName: "WishList",
          games: [
            {
              game_id: 0,
              game_name: "Game 3",
              description: "This is Game 3",
              release_date: null,
              developer_names: null,
              publisher_names: null,
              game_logo: null,
              genres: null,
            },
            {
              game_id: 0,
              game_name: "Game 4",
              description: "This is game 4",
              release_date: null,
              developer_names: null,
              publisher_names: null,
              game_logo: null,
              genres: null,
            },
          ],
        },
        {
          listName: "Favorites",
          games: [
            {
              game_id: 0,
              game_name: "Game 1",
              description: "This is Game 1",
              release_date: null,
              developer_names: null,
              publisher_names: null,
              game_logo: null,
              genres: null,
            },
            {
              game_id: 0,
              game_name: "Game 2",
              description: "This is game 2",
              release_date: null,
              developer_names: null,
              publisher_names: null,
              game_logo: null,
              genres: null,
            },
          ],
        },
      ],
    };
  },
};
</script>

<style>
</style>