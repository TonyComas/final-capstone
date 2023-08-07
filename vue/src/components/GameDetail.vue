<template>
  <div class="game">
     
      <router-link :to="{
          name: 'single-game-view',
          params: {
              gameId: game.game_id
          }
          }">
           <h2>{{game.game_name}}</h2>
           <img v-bind:src="game.game_logo" alt="">

          </router-link>
  </div>
</template>

<script>
export default {
name: 'game-detail',
props: ['game'],
computed: {

},
methods: {
    //This is where we will add games to list for users. Dope

}
}
</script>

<style>

</style>