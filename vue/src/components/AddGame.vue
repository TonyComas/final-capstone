<template>
  <form v-on:submit.prevent>
    <div id="addGame">
      <div class="Game_Title">
        <label class="addGame" for="title">Game Name: </label>
        <input class="addGame" type="text" name="title" v-model="game.game_name" required/>
      </div>
      <div class="Description">
        <label class="addGame" for="description">Game Description: </label>
        <input class="addGame" type="text" name="description" v-model="game.description" required/>
      </div>
      <div class="Release_Date">
        <label class="addGame" for="release">Release Date: </label>
        <input class="addGame" type="date" name="release" v-model="game.release_date" required/>
      </div>
      <div class="Developer">
          <label class="addGame" for="developer">Developer Name: </label>
          <input class="addGame" type="text" name="developer" v-model="game.developer_names" required />
      </div>
      <div class="Publisher">
          <label class="addGame" for="publisher">Publisher Name: </label>
          <input class="addGame" type="text" name="publisher" v-model="game.publisher_names" required />
      </div>
      <div class="Game_Logo">
          <label class="addGame" for="logo">Input link for Image: </label>
          <input class="addGame" type="url" name="logo" v-model="game.game_logo" required/>
      </div>
      <div class="genre">
          <label class="addGame" for="genre">Game Genres: </label>
          <input class="addGame" type="text" name="genre" v-model="game.genres" required>
      </div>
      <div class="actions">
          <button id="addGame" type="submit" v-if="game.game_name != ''" v-on:click="saveGame()">Add Game</button>
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
        release_date: "2000-01-01",
        developer_names:"",
        publisher_names:"",
        game_logo: "https://cdn.vectorstock.com/i/preview-1x/65/30/default-image-icon-missing-picture-page-vector-40546530.jpg",
        genres:""
      },
    };
  },
  methods: {
      saveGame() {
          GameService.addGame(this.game)
            .then( response => {
                if (response.status === 200) {
                    this.$router.push( {
                        name: 'games'
                    });
                }
            })
      }
  }
};
</script>

<style>

div {
  padding-bottom: 10px;
}

button.addGame {
  width: 100px;
  border-radius: 4px;
  border-width: 1px;
}

button.addGame:hover {
  background-color: rgba(128, 128, 128, 0.4);
}

label.addGame {
  max-width: 200px;
  display: block;
}

input.addGame{
  width:200px;
  border-radius: 4px;
  border-width: 1px;
}

</style>