<template>
<div>
    <div class="singleGame" v-if="game">
        <GameDetail :game="game" />
        <p id="name"><span style = "font-weight: bold; font-size: 18px;">Game Name</span>: {{game.game_name}}</p>
        <p id="description"><span style = "font-weight: bold; font-size: 18px;">Description</span>: {{game.description}}</p>
        <p id="release_date"><span style = "font-weight: bold; font-size: 18px;">Release Date</span>: {{game.release_date}}</p>
        <p id="developers"><span style = "font-weight: bold; font-size: 18px;">Developers</span>: {{game.developer_names}}</p>
        <p id="publishers"><span style = "font-weight: bold; font-size: 18px;">Publishers</span>: {{game.publisher_names}}</p>
        <p id="genres"><span style = "font-weight: bold; font-size: 18px;">Genres</span>: {{game.genres}}</p>

        <button v-on:click="updateGame()">CLICK ME</button>
        <form action="">
            <input type="text" v-model="game.game_name">
            <input type="text" v-model="game.description">
            <input type="text" v-model="game.release_date">
            <input type="text" v-model="game.developer_names">
            <input type="text" v-model="game.publisher_names">
            <input type="text" v-model="game.genres">
            
                      <!-- submit/ -->
            
        </form>

    
        <div>
            <router-link :to="{ name:'games' }">Back to Game Listing</router-link>
        </div>
    </div>
  </div>
</template>

<script>
import GameDetail from '@/components/GameDetail.vue'
import GameServices from '@/services/GameServices.js'

export default {
    components: {
        GameDetail
    },
    data() {
        return {
            gameId: 0,
            game: null
        }
    },
    methods: {
        updateGame() {
            console.log("Called UpdateGame Method");
            console.log(this.game);
            this.$store.dispatch("updateGame",this.game);
        }
    },
    created() {
        if (this.$store.getters.games.length === 0) {
            this.$store.dispatch("loadGames");
        } 
        this.gameId = this.$route.params.gameId;
        GameServices.getGameById(this.gameId).then( response => {
            this.game = response.data;
        }).catch( err => console.error(err) );
    }
}
</script>

<style>


</style>