<template>
  <div class="body">
    <div class="single-game" v-if="game">
      <GameDetail :game="game" />
      <input 
            class="deleteButton"
            type="button"
            value="Remove"
            @click="deleteGame(game.game_id)"
          />
      <button class="update-button" v-on:click="showForm = !showForm">
        Update
      </button>

      <div class="game-info" v-if="!showForm">
        <p>Game Name: {{ game.game_name }}</p>
        <p>Description:{{ game.description }}</p>
        <p>Game Release Date: {{ game.release_date }}</p>
        <p>Developers: {{ game.developer_names }}</p>
        <p>Publishers: {{ game.publisher_names }}</p>
        <p>Genres: {{ game.genres }}</p>
      </div>


      <form
        v-on:submit.prevent="updateGame()"
        action=""
        v-if="showForm"
        class="game-form"
      >
        <input type="text" v-model="game.game_name" placeholder="Game Name" />
        <input
          type="text"
          v-model="game.description"
          placeholder="Description"
        />
        <input type="date" v-model="game.release_date" />
        <input
          type="text"
          v-model="game.developer_names"
          placeholder="Developers"
        />
        <input
          type="text"
          v-model="game.publisher_names"
          placeholder="Publishers"
        />
        <input type="text" v-model="game.genres" placeholder="Genres" />
        <button class="update-button">Update</button>
      </form>

      <div class="back-link">
        <router-link :to="{ name: 'games' }">Back to Game Listing</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import GameDetail from "@/components/GameDetail.vue";
import GameServices from "@/services/GameServices.js";

export default {
  components: {
    GameDetail,
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
      console.log("Called UpdateGame Method");
      console.log(this.game);
      this.$store.dispatch("updateGame", this.game);
      this.toggleForm();
    },
     deleteGame(gameId) {
      GameServices.deleteGame(gameId).then((response) => {
        if (response.status === 200) {
          this.$store.commit("DELETE_GAME", gameId);
          this.$router.push( {
              name: 'games'
          })
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
  },
};
</script>

<style>
.single-game {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  border-radius: 5px;
}

.update-button {
  font-size: 10px;
  height: 20px;
  border-radius: 30px;
  border-width: 1px;
  text-align: right;
}

.update-button:hover {
  background-color: gray;
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

</style>