<template>
  <div>

    <div class="review" 
    v-for="review in this.$store.getters.reviews" 
    v-bind:key="review.id">
      <h4>{{ review.reviewer }}</h4>
      <span class="rating">
        <img
          src="../assets/giphy.gif"
          v-bind:title="review.rating + ' Star Review'"
          class="ratingStar"
          v-for="n in review.rating"
          v-bind:key="n"
        />
      </span>
      <h3 class="review-title" v-on:click="showForm = !showForm">- {{ review.review_title }}</h3>
      
      <div>
        <p class="review-body">{{ review.review_body }}</p>
      </div>
      <router-link v-if="$store.getters.isAdmin === true || review.user_id === $store.state.user.id"
          tag="button"
          :to="{
            name: 'confirm-delete',
            params: { reviewId: review.review_id },
          }"
          class="delete-review-button"
        >
          Delete Review
        </router-link>

        <div class="update-review">
          <form action="">

          </form>
        </div>
      
      
    </div>
  </div>
</template>

<script>
export default {
  name: "review-display",
//   props: ["review"],
  // data(){
  //   showForm: false,
  // },
  methods: {
    // toggleForm() {
    //   this.showForm = !this.showForm;
    // }

    updateReview() {
      this.$store.dispatch("updateReview", this.review);
      this.toggleReviewForm();
    },
  },
};
</script>

<style>
.review{
  text-align: left;
  min-width: 40vw;
}
.ratingStar {
  width:35px;
  display: inline;
  float: left;
}
.review-title{
  display: inline;
  font-size: 22px;
  line-height: 35px;
}

.delete-review-button {
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

.delete-review-button:hover,
.delete-review-button:focus {
  color: #fff;
  outline: 0;
}

.delete-review-button:hover {
  background-color: rgb(107, 7, 7);
  font-size: 12.8px;
  box-shadow: 0 0 20px 20px #e74c3c inset;
}

</style>