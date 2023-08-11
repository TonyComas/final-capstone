<template>
  
      <div class="review">
        <p>
          Are you sure you want to delete this review?
        </p>
        &nbsp;
        &nbsp;
        <div class="review-details">
            <p>Reviewer: {{ review.review_reviewer }}</p>
            <p>Title: {{ review.review_title }}</p>
            <p>{{review.review_body}}</p>
        </div>
        <div class="buttons">
            <button v-on:click.prevent="deleteConfirmed"
            class="confirm-button"
            >Yes</button>
            <button v-on:click.prevent="returnToGameView"
            class="cancel-button"
            >Cancel</button>
        </div>
  </div>
</template>

<script>
import GameServices from "../services/GameServices";

export default {
  data() {
    return {
      review: {},
      isLoading: true,
    };
  },
  methods: {
    deleteConfirmed() { 
        console.log("In Delete Confirmed",this.review.review_id)
      GameServices.deleteReview(this.review.review_id)
        .then((response) => {
          if (response.status === 200) {  
    this.$store.commit("DELETE_REVIEW",this.review.review_id)  

            this.returnToGameView();
          }
        })
        .catch((err) => console.error(err));

    },
    returnToGameView() {
        console.log(this.review.game_id)
      this.$router.push({
        name: "single-game-view",
        params: {
          gameId: this.review.game_id,
        },
      });
    },
  },
  created() {
    const reviewId = Number(this.$route.params.reviewId);
    console.log(reviewId)
    GameServices.getReviewById(reviewId)
      .then((response) => {
        this.review = response.data;
      })
      .catch((err) => console.error(err));
  },
};
</script>

<style>

body{
  color: rgb(11, 226, 11);
}

.review {
  height: 80vh
}

div#confirm {
  display: flex;
  justify-content: center;
  margin-top: 50px;
}

p {
  margin-top: 20px;
}

div#confirm .review {
  display: flex;
  align-items: center;
  flex-direction: column;
  padding-top: 20px;
}

div#confirm .review div.buttons {
  margin-bottom: 30px;
}

.confirm-button {
  font-size: 10px;
  height: 30px;
  border-radius: 30px;
  border-width: 1px;
  text-align: right;
  box-sizing: border-box;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  background-color: transparent;
  border: 2px solid #0aa546;
  border-radius: 0.6em;
  color: #0aa546;
  cursor: pointer;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-align-self: center;
  -ms-flex-item-align: center;
  align-self: center;
  font-size: 0.8rem;
  font-weight: 400;
  line-height: 1.2;
  margin: 5px;
  padding: 0.5em 1em;
  text-decoration: none;
  text-align: center;
  text-transform: uppercase;
  font-weight: 700;
  position: relative;
  z-index: 1;
  transition: box-shadow 200ms ease-in-out, color 200ms ease-in-out;
  font-family: 'Press Start 2P', cursive;
}

.confirm-button:hover,
.confirm-button:focus {
  color: #fff;
  outline: 0;
}

.confirm-button:hover {
  background-color: rgb(4, 78, 20);
  font-size: 12.8px;
  box-shadow: 0 0 20px 20px #4ae73c inset;
}

.cancel-button {
  font-size: 10px;
  height: 30px;
  border-radius: 30px;
  border-width: 1px;
  text-align: right;
  box-sizing: border-box;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  background-color: transparent;
  border: 2px solid #e74c3c;
  border-radius: 0.6em;
  color: #e74c3c;
  cursor: pointer;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-align-self: center;
  -ms-flex-item-align: center;
  align-self: center;
  font-size: 0.8rem;
  font-weight: 400;
  line-height: 1.2;
  margin: 5px;
  padding: 0.5em 1em;
  text-decoration: none;
  text-align: center;
  text-transform: uppercase;
  font-weight: 700;
  position: relative;
  z-index: 1;
  transition: box-shadow 200ms ease-in-out, color 200ms ease-in-out;
  font-family: 'Press Start 2P', cursive;
}

.cancel-button:hover,
.cancel-button:focus {
  color: #fff;
  outline: 0;
}

.cancel-button:hover {
  background-color: rgb(107, 7, 7);
  font-size: 12.8px;
  box-shadow: 0 0 20px 20px #e74c3c inset;
}


div#confirm .review-details {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

div#confirm .review-details p {
  font-size: 1.2rem;
  padding: 5px;
  margin: 0;
}

.buttons {
  margin-top: 20px;
}


</style>