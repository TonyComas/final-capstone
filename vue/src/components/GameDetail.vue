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
import GameServices from '../services/GameServices'
export default {
name: 'game-detail',
props: ['game'],
computed: {

},
methods: {
    //This is where we will add games to list for users. Dope
    deleteGame(gameId) {
        GameServices.deleteGame(gameId)
            .then( response => {
                if (response.status === 200) {
                    this.router.push( {
                        name: 'games'
                    });
                }
            })
    }
}
}
</script>

<style>

</style>