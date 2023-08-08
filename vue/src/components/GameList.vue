<template>
  <div id="games">
    <h1>Video Games</h1>
    <GameDetailVue v-for="game in $store.getters.games"
    :key="game.id"
    :game="game"/>
  </div>
</template>

<script>
import GameDetailVue from './GameDetail.vue';
export default {
  name: "game-list",
  components: {
      GameDetailVue
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
</style>