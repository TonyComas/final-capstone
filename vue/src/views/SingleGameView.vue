<template>
<div>

    <div class="singleProduct" v-if="game">
        <GameDetail :game="game" />
        <p>Item Id: {{game.game_id}}</p>
    
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