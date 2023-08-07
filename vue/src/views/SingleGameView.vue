<template>
<div>

    <div class="singleGame" v-if="game">
        <GameDetail :game="game" />
        <p>Game Name: {{game.game_name}}</p>
        <p>{{game.game_logo}}</p>
        <img v-bind:src="game.game_logo" alt="">
        <p>Description: {{game.description}}</p>
        <p>Game Release Date: {{game.release_date}}</p>
        <p>Developers: {{game.developer_names}}</p>
        <p>Publishers: {{game.publisher_names}}</p>
    
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
div.singleProduct {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items:center;
}
div.singleProduct a {
    text-decoration: none;
    font-size: 1.2rem;
    color: darkblue;
}
div.singleProduct a:hover {
    color: purple;
    text-decoration: underline;
}
</style>