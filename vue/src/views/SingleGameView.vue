<template>
<div>
    <div class="singleGame" v-if="game">
        <GameDetail :game="game" />
        <p id="name"><span style = "font-weight: bold; font-size: 18px;">Game Name</span>: {{game.game_name}}</p>
        <p id="description"><span style = "font-weight: bold; font-size: 18px;">Description</span>: {{game.description}}</p>
        <p id="release_date"><span style = "font-weight: bold; font-size: 18px;">Release Date</span>: {{game.release_date}}</p>
        <p id="developers"><span style = "font-weight: bold; font-size: 18px;">Developers</span>: {{game.developer_names}}</p>
        <p id="publishers"><span style = "font-weight: bold; font-size: 18px;">Publishers</span>: {{game.publisher_names}}</p>
        <p id="genres"><span style = "font-weight: bold; font-size: 18px;">Genres</span>: {{game.genres}}</p>

        <button v-on:click="updateGame()">CLICK ME</button>
        <form action="">
            <input type="text" v-model="game.game_name">
            <input type="text" v-model="game.description">
            <input type="text" v-model="game.release_date">
            <input type="text" v-model="game.developer_names">
            <input type="text" v-model="game.publisher_names">
            <input type="text" v-model="game.genres">
            
                      <!-- submit/ -->
            
        </form>

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

</style>