<template>
  <div>
    <div class="game">
      <router-link
        :to="{
          name: 'single-game-view',
          params: {
            gameId: game.game_id,
          },
        }"
      >
        <div class="individual">
          <img class="logo" v-bind:src="game.game_logo" alt="" />
          <h1 class="name">{{ game.game_name }}</h1>
          
        </div>
        
      </router-link>
      <input
            class="deleteButton"
            type="button"
            value="Remove"
            @click="deleteGame(game.game_id)"
          />
    </div>
  </div>
</template>

<script>
import GameServices from "../services/GameServices";
export default {
  name: "game-detail",
  props: ["game"],
  computed: {},
  methods: {
    //This is where we will add games to list for users. Dope
    deleteGame(gameId) {
      GameServices.deleteGame(gameId).then((response) => {
        if (response.status === 200) {
          this.$store.commit("DELETE_GAME", gameId);
        }
      });
    },
  },
};
</script>

<style>
img.logo {
  width: 300px;
  height: 300px;
  object-fit: contain;
  border-radius: 25px;
  background-color: white;
}
h1.name {
    font-size: 15px;
    color: black;
    text-decoration: none;
}

a {
    text-decoration: none;
}


div.game {
    display: flexbox;
    width: 313px;
    margin-right: 30px;
}

div.individual {
    display: flexbox;
    padding-top: 4px;
    padding-right: 5px;
    padding-left: 5px;
    padding-bottom: 4px;
    margin-top: 30px;
    border-color: rgba(80, 80, 80, 0.459);
}

div.individual:hover {
    border-width: 2px;
    border-style: solid;
    border-radius: 25px;
    background-color: rgba(80, 80, 80, 0.4);
    border-color: rgba(80, 80, 80, 0.658)
}

.deleteButton {
  font-size: 10px;
  height: 20px;
  border-radius: 30px;
  border-width: 1px;
  text-align: right;
}

.deleteButton:hover {
    background-color: gray;
    font-size: 11px;
}
</style>