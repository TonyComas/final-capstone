<template>
  <form v-on:submit.prevent="addNewReview">
  
    <div class="form-element">
      <label for="title">Review Title:</label>
      <input id="title" type="text" v-model="newReview.review_title" />
    </div>
    <div class="form-element">
      <label for="rating">Rating:</label>
      <select id="rating" v-model.number="newReview.rating">
        <option value="1">1 Star</option>
        <option value="2">2 Stars</option>
        <option value="3">3 Stars</option>
        <option value="4">4 Stars</option>
        <option value="5">5 Stars</option>
      </select>
    </div>
    <div class="form-element">
      <label for="review">Review: </label>
      <textarea id="review" v-model="newReview.review_body"></textarea>
    </div>
    <div class="actions">
      <button v-on:click="returnToGamePage()" type="button" class="review-button cancel-button2">Cancel</button>
      <button class="review-button submit-button">Submit</button>
    </div>
  </form>
</template>

<script>
import GameService from '../services/GameServices.js';
export default {
  name: "add-review",
  data() {
    return {
      newReview: {
        game_id: 0,
        user_id: this.$store.state.user.id,
        review_title: "",
        rating: 0,
        review_body: "",
      },
      gameId: 0
    };
  },
  methods: {
    addNewReview() {
      this.newReview.game_id = this.gameId;
     
      GameService.addReview(this.newReview)
    //   console.log("Game ID is",this.newReview.game_id)
    //   console.log("User Id is",this.$store.state.user.id)

        .then( response => {
          if (response.status === 200) {
            this.$router.push( { 
              name: 'single-game-view',
              params: {
                  gameId: this.newReview.game_id,
              }
            });
          }
        })
        .catch( err => console.error(err) );
    },
    resetForm() {
      this.newReview = {};
    },
    returnToGamePage() {

      this.$router.push( {
        name: 'single-game-view',
        params: {
          gameId: this.gameId
        }
     
      })
    }
  },
  created() {
     this.gameId = this.$route.params.id;
  } 
};
</script>

<style scoped>
form {
  width: 500px;
  margin: 20px;
}
.form-element label {
  width: 100px;
  vertical-align: top;
}
.form-element input,
select,
textarea {
  width: 400px;
  font-size: 11pt;
  background: black;
  color: rgb(11, 226, 11);
  font-family: 'Press Start 2P', cursive;
  font-weight: 50;
  margin: 2px;
  padding: 5px;

  
}

.review-button {
  font-size: 10px;
  text-align: right;
  box-sizing: border-box;
  appearance: none;
  background-color: transparent;
  
  border-radius: 0.2em;
  
  font-family: 'Press Start 2P', cursive;
  font-weight: 50;
  display: flex;
  line-height: 1;
  margin: 5px;
  padding: 0.5em 1em;
  text-align: center;
  text-transform: uppercase;
  transition: box-shadow 300ms ease-in-out, color 300ms ease-in-out;
  display:inline-block;
}
.review-button:hover {
  color: #fff;
  outline: 0;
}
.cancel-button2{
border: 2px solid #e74c3c;
color: #e74c3c;
}
.submit-button{
border: 2px solid rgb(11, 226, 11);
color: rgb(11, 226, 11);
}
.cancel-button2:hover{
  background-color: rgb(107, 7, 7);
  box-shadow: 0 0 20px 20px #e74c3c inset;
}
.submit-button:hover{
  background-color: rgb(4, 78, 20);
  box-shadow: 0 0 20px 20px #4ae73c inset;
}
.form-element textarea {
  height: 150px;
}
.actions button {
  margin-left: 10px;
}
</style>
