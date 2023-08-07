<template>
  <div id="games">
    <h1>Video Games</h1>
    <GameDetailVue v-for="game in games"
    :key="game.id"
    :game="game"/>
  </div>
</template>

<script>
import GameService from '@/services/GameServices.js'
import GameDetailVue from './GameDetail.vue';
export default {
  name: "game-list",
  components: {
      GameDetailVue
  },
  data() {
    return {
        games: []
    };
  },
  computed: {

  },
  created() {
      GameService.getAllGames().then( response => {
          this.games = response.data;
      })
  }
};
</script>

<style>
</style>