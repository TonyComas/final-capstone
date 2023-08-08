<template>
<div>
    <div class="singleGame" v-if="game">
        <GameDetail :game="game" />
        <p id="name">Game Name: {{game.game_name}}</p>
        <p id="description">Description: {{game.description}}</p>
        <p id="release_date">Game Release Date: {{game.release_date}}</p>
        <p id="developers">Developers: {{game.developer_names}}</p>
        <p id="publishers">Publishers: {{game.publisher_names}}</p>
        <p id="genres">Genres: {{game.genres}}</p>
    
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
    created() {
        this.gameId = this.$route.params.gameId;
        GameServices.getGameById(this.gameId).then( response => {
            this.game = response.data;
        }).catch( err => console.error(err) );
    }
}
</script>

<style>

</style>