<template>
  <div class="body">
    <div class="single-game" v-if="game">

      <img class="logo" v-bind:src="game.game_logo" alt="" />

      <div class="button-container">
        <div class="buttons">
      <input 
            class="delete-button"
            type="button"
            value="Remove"
            @click="deleteGame(game.game_id)"
          />
      <button class="update-button" v-on:click="showForm = !showForm">
        Update
      </button>
      </div>
      </div>
        <div class="game_info" v-if="!showForm">
        <p id="name"><span>Game Name</span>: {{game.game_name}}</p>
        <p id="description"><span>Description</span>: {{game.description}}</p>
        <p id="release_date"><span>Release Date</span>: {{game.release_date}}</p>
        <p id="developers"><span>Developers</span>: {{game.developer_names}}</p>
        <p id="publishers"><span>Publishers</span>: {{game.publisher_names}}</p>
        <p id="genres"><span>Genres</span>: {{game.genres}}</p>
        </div>


      <form
        v-on:submit.prevent="updateGame()"
        action=""
        v-if="showForm"
        class="game-form"
      >
        <label for="Game Name">Game Name</label>
        <input type="text" v-model="game.game_name" placeholder="Game Name" />
        <label for="Description">Description</label>

        <input
          type="text"
          v-model="game.description"
          placeholder="Description"
        />
        <label for="Release Date">Release Date</label>

        <input type="date" v-model="game.release_date" />
                <label for="Developers">Developers</label>

        <input
          type="text"
          v-model="game.developer_names"
          placeholder="Developers"
        />
        <label for="Publishers">Publishers</label>

        <input
          type="text"
          v-model="game.publisher_names"
          placeholder="Publishers"
        />
        <label for="Genres">Genres</label>

        <input type="text" v-model="game.genres" placeholder="Genres" />
        <button class="update-button">Update</button>
      </form>
      <div class="back-link">
        <button
          class="back-link-style"
          @click="$router.push({ name: 'games' })"
        >
          Back to Game Listing
        </button>
      </div>
      <ReviewDisplayVue :reviews="game.reviews" />
    </div>
  </div>
</template>
<script>

import GameServices from "@/services/GameServices.js";
import ReviewDisplayVue from "../components/ReviewDisplay.vue";
export default {
  components: {
    
    ReviewDisplayVue
  },
  data() {
    return {
      gameId: 0,
      game: null,
      showForm: false,
    };
  },
  methods: {
    updateGame() {
      this.$store.dispatch("updateGame", this.game);
      this.toggleForm();
    },
    deleteGame(gameId) {
      GameServices.deleteGame(gameId).then((response) => {
        if (response.status === 200) {
          this.$store.commit("DELETE_GAME", gameId);
          this.$router.push({
            name: "games",
          });
        }
      });
    },
    toggleForm() {
      this.showForm = !this.showForm;
    },
  },
  created() {
    if (this.$store.getters.games.length === 0) {
      this.$store.dispatch("loadGames");
    }
    this.gameId = this.$route.params.gameId;
    GameServices.getGameById(this.gameId)
      .then((response) => {
        this.game = response.data;
      })
      .catch((err) => console.error(err));

    console.log("Fetching", this.gameId);
    this.$store.dispatch("loadReviews", this.gameId);
  },
};
</script>
<style>
@import url("https://fonts.cdnfonts.com/css/gotham-6");

.body {
  font-family: "Gotham", sans-serif;
  color: green;
  
}

.button-container {
  display: flex;
  flex-direction:column;
  align-items: flex-start;
  margin-bottom: 10px;
  padding-top: 20px;
}

.buttons {
  display: flex;
}

span {
  color:rgb(11, 226, 11);
  max-width: 600px;
}

.single-game {
  display: grid;
  grid-template-rows: auto 1fr;
  max-width: 1920px;
  min-width: 1850px;
  margin: 0 auto;
  padding: 20px;
  border-radius: 5px;
}
.game_info {
  max-width: 400px;
}

img.logo {
  width: 300px;
  height: 300px;
  object-fit: contain;
  border-radius: 25px;
  background-color: white;
}

.update-button {
  font-size: 10px;
  height: 20px;
  border-radius: 30px;
  border-width: 1px;
  text-align: left;
}
.update-button:hover {
  background-color: rgb(255, 255, 255);
  font-size: 11px;
}
.game-info p {
  margin-bottom: 15px;
}
.game-info strong {
  font-weight: bold;
}

.game-form {
  margin-top: 20px;
}
.game-form input {
  width: 100%;
  padding: 10px;
  border-radius: 3px;
  font-size: 16px;
  margin-bottom: 10px;
}
.game-form button {
  font-size: 10px;

  height: 20px;
  border-radius: 30px;
  border-width: 1px;
  text-align: right;
}
.game-form button:hover {
  background-color: #0056b3;
}

.delete-button {
  font-size: 10px;
  height: 30px;
  border-radius: 30px;
  border-width: 1px;
  text-align: right;
  box-sizing: border-box;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  background-color: transparent;
  border: 2px solid #e74c3c;
  border-radius: 0.6em;
  color: #e74c3c;
  cursor: pointer;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-align-self: center;
  -ms-flex-item-align: center;
  align-self: center;
  font-size: 0.8rem;
  font-weight: 400;
  line-height: 1;
  margin: 5px;
  padding: 0.5em 1em;
  text-decoration: none;
  text-align: center;
  text-transform: uppercase;
  font-weight: 700;
  position: relative;
  z-index: 1;
  transition: box-shadow 300ms ease-in-out, color 300ms ease-in-out;
}

.delete-button:hover,
.delete-button:focus {
  color: #fff;
  outline: 0;
}

.delete-button:hover {
  background-color: rgb(196, 99, 99);
  font-size: 11px;
  box-shadow: 0 0 20px 20px #e74c3c inset;
}

.update-button {
  font-size: 10px;
  height: 20px;
  border-radius: 30px;
  border-width: 1px;
  text-align: right;
}

.update-button:hover {
  background-color: rgb(255, 255, 255);
  font-size: 11px;
}

.game-form button:hover {
  background-color: #0056b3;
}

.update-button {
  font-size: 10px;
  height: 30px;
  border-radius: 30px;
  border-width: 1px;
  text-align: right;
  box-sizing: border-box;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  background-color: transparent;
  border: 2px solid #0aa546;
  border-radius: 0.6em;
  color: #0aa546;
  cursor: pointer;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-align-self: center;
  -ms-flex-item-align: center;
  align-self: center;
  font-size: 0.8rem;
  font-weight: 400;
  line-height: 1.2;
  margin: 5px;
  padding: 0.5em 1em;
  text-decoration: none;
  text-align: center;
  text-transform: uppercase;
  font-weight: 700;
  position: relative;
  z-index: 1;
  transition: box-shadow 300ms ease-in-out, color 300ms ease-in-out;
}

.update-button:hover,
.update-button:focus {
  color: #fff;
  outline: 0;
}

.update-button:hover {
  background-color: rgb(99, 196, 120);
  font-size: 11px;
  box-shadow: 0 0 20px 20px #4ae73c inset;
}

.back-link-style {
  -webkit-touch-callout: none;
  -webkit-user-select: none;
  -khtml-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  -webkit-tap-highlight-color: transparent;
}

.back-link-style {
	width: 150px;
	height: 50px;
	cursor: pointer;
	font-size: 20px;
	font-weight: bold;
	color: rgb(11, 226, 11);
	background-color: transparent;
	border: 1px solid rgb(16, 87, 2);
	box-shadow: 1px 1px 0 rgb(16, 87, 2),
		-1px -1px 0 rgb(16, 87, 2),
		-1px 1px 0 rgb(16, 87, 2),
		1px -1px 0 rgb(16, 87, 2);
	transition: 500ms ease-in-out;
  margin-top: 20px;
}

.back-link-style:hover {
  box-shadow: 20px 5px 0 rgb(53, 255, 3), -20px -5px 0 rgb(53, 255, 3);
}

.b:focus {
  outline: none;
}

#description {
  color: green;
}
</style>