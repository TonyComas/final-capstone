<template>
  <div id="games">
    <GameDetailVue v-for="game in $store.getters.games"
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

    };
  },
  computed: {

  },
  created() {
      GameService.getAllGames().then( response => {
         this.$store.commit("SET_ALL_GAMES",response.data);
      })
  }
};
</script>

<style>

div #games {
  display:flex;
  flex-wrap: wrap;
}
</style>