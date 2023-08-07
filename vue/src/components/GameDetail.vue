<template>
  <div class="game">
      <h2>{{game.game_name}}</h2>
      <router-link :to="{
          name: 'single-game-view',
          params: {
              gameId: game.game_id
          }
          }">
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