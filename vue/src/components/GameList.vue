<template>
  <div id="listPage">
    <div id="games">
      <GameDetailVue v-for="game in $store.getters.games"
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

    };
  },
  computed: {

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