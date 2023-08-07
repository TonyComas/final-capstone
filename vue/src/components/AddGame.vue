<template>
  <form v-on:submit.prevent>
    <div>
      <div class="Game_Title">
        <label for="title">Game Name</label>
        <input type="text" name="title" v-model="game.game_name" />
      </div>
      <div class="Description">
        <label for="description">Game Description</label>
        <input type="text" name="description" v-model="game.description" />
      </div>
      <div class="Release_Date">
        <label for="release">Release Date</label>
        <input type="date" name="release" v-model="game.release_date" />
      </div>
      <div class="Developer">
          <label for="developer">Developer Name</label>
          <input type="text" name="developer" v-model="game.developer_names" />
      </div>
      <div class="Publisher">
          <label for="publisher">Publisher Name</label>
          <input type="text" name="publisher" v-model="game.publisher_names" />
      </div>
      <div class="Game_Logo">
          <label for="logo">Input link for Image</label>
          <input type="url" name="logo" v-model="game.game_logo" />
      </div>
      <div class="genre">
          <label for="genre">Game Genres</label>
          <input type="text" name="genre" v-model="game.genres">
      </div>
      <div class="actions">
          <button type="submit" v-on:click="saveGame()">Add Game</button>
      </div>
    </div>
  </form>
</template>

<script>
import GameService from "../services/GameServices.js";
export default {
  name: "add-game",
  props: ["fullGame"],
  data() {
    return {
      game: {
        fullGame: this.fullGame,
        game_name: "",
        description: "",
        release_date: null,
        developer_names:"",
        publisher_names:"",
        game_logo: null,
        genres:""
      },
    };
  },
  methods: {
      saveGame() {
          GameService.AddGame(this.game)
            .then( response => {
                if (response.status === 201) {
                    this.router.push( {
                        name: 'games'
                    });
                }
            })
      }
  }
};
</script>

<style>
</style>