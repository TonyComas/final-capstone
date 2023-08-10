<template>
  <div id="listPage">
    <div class="searchbar">
      <label for="searchTerm">Search Bar</label>
      <input v-model="searchTerm" type="text" id="searchTerm"/>
      </div>
    <div id="games">
      
      <GameDetailVue v-for="game in filteredGames"
      :key="game.id"
      :game="game"/>
    </div>
  </div>
</template>

<script>
import GameDetailVue from './GameDetail.vue';
export default {
  name: "game-list",
  components: {
      GameDetailVue,
      
  },
  data() {
    return {
      searchTerm: '',

    };
  },
  computed: {
    filteredGames(){
    const gameList = this.$store.getters.games
    let filteredGames = [];
    gameList.forEach(game => {
      if (game.game_name.toLowerCase().includes(this.searchTerm.toLowerCase())) {
        filteredGames.push(game);
      } else if (game.publisher_names.toLowerCase().includes(this.searchTerm.toLowerCase())) {
        filteredGames.push(game);
      } else if (game.developer_names.toLowerCase().includes(this.searchTerm.toLowerCase())) {
        filteredGames.push(game);
      } else if (game.genres.toLowerCase().includes(this.searchTerm.toLowerCase())) {
        filteredGames.push(game);
      } 
    });
    return filteredGames;

    }
  },
  created() {
      this.$store.dispatch("loadGames")
  }
};
</script>

<style>

div #games {
  display:flex;
  flex-wrap: wrap;
  grid-area: games;
}



div #listPage {
  display: grid;
  grid-template-columns: 1fr 200px;
  grid-template-areas: "games search";
}

</style>