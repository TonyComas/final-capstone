<template>
  <div id="listPage">
    <div class="flexbox">
  <div class="search">
    <h1>Search database</h1>
    <div>
      <input v-model="searchTerm" type="text" placeholder="Search ..." required>
    </div>
  </div>
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

.search {
  margin: 20px;
}

.search > h3 {
  font-weight: normal;
}

.search > h1,
.search > h3 {
  color: rgb(11, 226, 11);
  margin-bottom: 15px;
  text-shadow: 0 1px #124402;
}

.search > div {
  display: inline-block;
  position: relative;
  filter: drop-shadow(0 1px #01520c);
}

.search > div:after {
  content: "";
  background: rgb(11, 226, 11);
  width: 4px;
  height: 20px;
  position: absolute;
  top: 20px;
  right: -3px;
  transform: rotate(135deg);
}

.search > div > input {
  color: rgb(11, 226, 11);
  font-size: 16px;
  background: transparent;
  width: 25px;
  height: 25px;
  padding: 10px;
  border: solid 3px rgb(11, 226, 11);
  outline: none;
  border-radius: 35px;
  transition: width 0.5s;
}

.search > div > input::placeholder {
  color: rgb(11, 226, 11);;
  opacity: 0;
  transition: opacity 150ms ease-out;
}

.search > div > input:focus::placeholder {
  opacity: 1;
}

.search > div > input:focus,
.search > div > input:not(:placeholder-shown) {
  width: 250px;
}

div #games {
  display: flex;
  flex-wrap: wrap;
  grid-area: games;
}

div #listPage {
  width: 100%;
  display: grid;
  grid-template-columns: 1fr 200px;
  grid-template-areas: "games search";
}

div.searchbar {
  padding-top: 20px;
  color: rgb(11, 226, 11);
  grid-area: search;
}
</style>