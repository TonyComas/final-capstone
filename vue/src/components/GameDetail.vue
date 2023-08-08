<template>
  <div class="game">
     
      <router-link :to="{
          name: 'single-game-view',
          params: {
              gameId: game.game_id
          }
          }">
            <div class="individual">
                <img class="logo" v-bind:src="game.game_logo" alt="">
                <h2 class="name">{{game.game_name}}</h2>
            </div>
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
img.logo {
    width: 250px;
    max-height: 300px;
}
h2.class {

}
div.game {
    display: flex;
}

div.individual {
    
}



</style>