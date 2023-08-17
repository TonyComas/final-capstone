<template>
  <div class="UserReviewsDiv">
    <router-link
        :to="{
          name: 'single-game-view',
          params: {
            gameId: this.selectedGame.game_id,
          },
        }"
    >
    <img class="logo_card" v-bind:src="this.selectedGame.game_logo"  />
    </router-link>
    <div class="review_body">
      <img
          src="../assets/giphy.gif"
          v-bind:title="userReviews.rating + ' Star Review'"
          class="ratingStar"
          v-for="n in userReviews.rating"
          v-bind:key="n"
        />
      <h3 class="name user_reviews_card" >- {{userReviews.review_title}}
          <!-- {{userReviews.game_id}} -->
          </h3>
      <p>{{userReviews.review_body}}</p>
    </div>

  </div>
</template>

<script>
// import GameServices from "@/services/GameServices.js";
export default {
    name: "list-of-reviews",
    props: ["userReviews"],
    gameId: 0,
    selectedGame: null,
    computed: {

    },
  methods: {
    // grabTheGameID(props){
    //   this.gameId = props.userReviews.game_id;
    // }
    selectedGame(){
      this.gameId = this.userReviews.game_id
      console.log(this.gameId)
      // console.log(this.$store.getters.games.find(game => game.game_id === this.gameId))
      this.selectedGame=this.$store.getters.games.find(game => game.game_id === this.gameId);
      console.log(this.selectedGame.game_name)
      // this.state.games.find(game => game.game_id === this.gameId)
      
    }
  },
  created() {
  
  this.$store.dispatch("loadGames");
  this.selectedGame()
  }

}
</script>

<style>
img.logo_card {
  width: 75px;
  height: 75px;
  object-fit: contain;
  border-radius: 15px;
  margin-left: 15px;
  margin-right: 15px;
  background-color: white;
  float: left;
}
.review_body{
  margin-bottom: 45px;
  padding-left:15px;
}

h3.user_reviews_card {
  vertical-align: middle;
  margin-top: 7px;
  margin-left: 5px;
}

</style>