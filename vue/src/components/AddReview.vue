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
      <label for="review">Review</label>
      <textarea id="review" v-model="newReview.review_body"></textarea>
    </div>
    <div class="actions">
      <button v-on:click="resetForm" type="button">Cancel</button>
      <button>Submit</button>
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
        game_id: this.$store.getters.game,
        user_id: this.$store.state.user.id,
        review_title: "",
        rating: 0,
        review_body: "",
      }
    };
  },
  methods: {
    addNewReview() {
      const gameId = this.$route.params.id;
      this.newReview.game_id = gameId;
     
      GameService.addReview(this.newReview)
    //   console.log("Game ID is",this.newReview.game_id)
    //   console.log("User Id is",this.$store.state.user.id)

        .then( response => {
          if (response.status === 201) {
            this.$router.push( { 
              name: 'single-game-view',
              params: {
                  gameId: this.review.game_id
              }
            });
          }
        })
        .catch( err => console.error(err) );
    },
    resetForm() {
      this.newReview = {};
    }
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
  font-size: 1.1rem;
}
.form-element textarea {
  height: 150px;
}
.actions {
  float: right;
}
.actions button {
  margin-left: 10px;
}
</style>
