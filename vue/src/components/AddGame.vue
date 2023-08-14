<template>
  <form v-on:submit.prevent>
    <div class="add-form">
    <div id="addGame">
      <div class="Game_Title">
        <label class="addGame" for="title">Game Name: </label>
        <textarea class="addGame" type="text" name="title" v-model="game.game_name" required rows="1"/>
      </div>
      <div class="Description">
        <label class="addGame" for="description">Game Description: </label>
        <textarea class="addGame" type="text" name="description" v-model="game.description" required rows="5"/>
      </div>
      <div class="Release_Date">
        <label class="addGame" for="release">Release Date: </label>
        <input class="addGame" type="date" name="release" v-model="game.release_date" required/>
      </div>
      <div class="Developer">
          <label class="addGame" for="developer">Developer Names: </label>
          <textarea class="addGame" type="text" name="developer" v-model="game.developer_names" required rows="2"/>
      </div>
      <div class="Publisher">
          <label class="addGame" for="publisher">Publisher Names: </label>
          <textarea class="addGame" type="text" name="publisher" v-model="game.publisher_names" required rows="2"/>
      </div>
      <div class="Game_Logo">
          <label class="addGame" for="logo">Input link for Image: </label>
          <textarea class="addGame" type="url" name="logo" v-model="game.game_logo" required rows="5"/>
      </div>
      <div class="genre">
          <label class="addGame" for="genre">Game Genres: </label>
          <textarea class="addGame" type="text" name="genre" v-model="game.genres" required rows="2"/>
      </div>
      <div class="actions">
          <button id="addGame" type="submit" v-if="game.game_name != ''" v-on:click="saveGame()">Add Game</button>
      </div>
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
      genres: this.$store.getters.genres,
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

.add-form textarea, input {
  display: block;
  width: 25%;
  padding: 10px;
  border-radius: 3px;
  font-size: 16px;
  margin-bottom: 10px;
  background: black;
  color: green;
  font-family: 'Press Start 2P', cursive;
  font-weight: 50;
  margin: 2px;
  padding: 5px;
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
  color: rgb(11, 226, 11)
}

textarea {
  width: 280px;
}

h1 {
color: rgb(11, 226, 11);
margin-bottom: 15px;
margin-top: 15px;
}


input.addGame{
  width:280px;
  border-radius: 4px;
  border-width: 1px;
}

</style>