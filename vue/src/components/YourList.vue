<template>
  <div id="listPage2">
    <div id="title" class="alsoTitle">
      <h1>{{ $route.params.name }}</h1>
    </div>
      <div class="search2">
        <h1>Search List</h1>
        <div>
          <input
            v-model="searchTerm"
            class="search-bar2"
            type="text"
            placeholder="Search ..."
            required
          />
        </div>
      </div>

    <div id="games2">
      <GameDetailVue
        v-for="game in filteredGames"
        :key="game.id"
        :game="game"
      />
    </div>
  </div>
</template>

<script>
import GameDetailVue from "./GameDetail.vue";
export default {
  name: "game-list",
  components: {
    GameDetailVue,
  },
  data() {
    return {
      searchTerm: "",
    };
  },
  computed: {
    filteredGames() {
      const gameList = this.$store.getters.games;
      let filteredGames = [];
      gameList.forEach((game) => {
        if (filteredGames) {
          if (
            game.game_name.toLowerCase().includes(this.searchTerm.toLowerCase())
          ) {
            filteredGames.push(game);
          } else if (
            game.publisher_names
              .toLowerCase()
              .includes(this.searchTerm.toLowerCase())
          ) {
            filteredGames.push(game);
          } else if (
            game.developer_names
              .toLowerCase()
              .includes(this.searchTerm.toLowerCase())
          ) {
            filteredGames.push(game);
          } else if (
            game.genres.toLowerCase().includes(this.searchTerm.toLowerCase())
          ) {
            filteredGames.push(game);
          }
        }
      });
      return filteredGames;
    },
  },
  created() {
    this.$store.dispatch("loadGamesInList", this.$route.params.id);
  },
};
</script>

<style>
div #games2 {
  display: flex;
  flex-wrap: wrap;
  grid-area: games;
}

.alsoTitle {
  grid-area: title;
  
}

.alsoTitle h1 {
  text-align: center;
  display: block;
  font-size: 50px;
}


.search-bar2 {
  font-family: "Press Start 2P", cursive;
}

.search2 > h3 {
  font-weight: normal;
}

.search2 > h1,
.search2 > h3 {
  color: rgb(11, 226, 11);
  margin-bottom: 15px;
  text-shadow: 0 1px #124402;
}

.search2 > div {
  display: inline-block;
  position: relative;
  filter: drop-shadow(0 1px #01520c);
}

.search2 > div:after {
  content: "";
  background: rgb(11, 226, 11);
  width: 4px;
  height: 20px;
  position: absolute;
  top: 20px;
  right: -3px;
  transform: rotate(135deg);
}

.search2 > div > input {
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

.search2 > div > input::placeholder {
  color: rgb(11, 226, 11);
  opacity: 0;
  transition: opacity 150ms ease-out;
}

.search2 > div > input:focus::placeholder {
  opacity: 1;
}

.search2 > div > input:focus,
.search2 > div > input:not(:placeholder-shown) {
  width: 250px;
}

div #games2 {
  display: flex;
  flex-wrap: wrap;
  grid-area: games;
}

div #listPage2 {
  width: 100%;
  display: grid;
  grid-template-rows: 50px 1fr;
  grid-template-columns: 1fr 200px;
  grid-template-areas:
    "title title"
    "games search";
}

div.searchbar2 {
  padding-top: 20px;
  color: rgb(11, 226, 11);
  grid-area: search;
}
</style>