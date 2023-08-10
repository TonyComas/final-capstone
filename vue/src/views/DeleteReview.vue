<template>
  
      <div class="review">
        <p>
          Are you sure you want to delete this review?
        </p>
        <div class="buttons">
            <button v-on:click.prevent="deleteConfirmed">Yes</button>
            <button v-on:click.prevent="returnToGameView">Cancel</button>
        </div>
        <div class="review-details">
            <p>Reviewer: {{ review.review_reviewer }}</p>
            <p>Title: {{ review.review_title }}</p>
            <p>{{review.review_body}}</p>
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
div#confirm {
  display: flex;
  justify-content: center;
  margin-top: 50px;
}

div#confirm .review {
  display: flex;
  align-items: center;
  flex-direction: column;
}

div#confirm .review div.buttons {
  margin-bottom: 30px;
}

div#confirm .review div.buttons button {
  margin: 20px;
  width: 100px;
  border-radius: 10px;
  border: 1px solid black;
  font-weight: 600;
}

div#confirm .review div.buttons button:first-child {
  background-color: lightgreen;
}

div#confirm .review div.buttons button:last-child {
  background-color: rgb(240, 94, 94);
}

div#confirm .review div.buttons button:hover {
  cursor: pointer;
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
</style>