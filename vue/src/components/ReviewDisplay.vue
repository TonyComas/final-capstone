<template>
  <div>

    <div class="review" 
    v-for="review in this.$store.getters.reviews" 
    v-bind:key="review.id">
      <h4>{{ review.reviewer }}</h4>
      <span class="rating">
        <img
          src="../assets/star.png"
          v-bind:title="review.rating + ' Star Review'"
          class="ratingStar"
          v-for="n in review.rating"
          v-bind:key="n"
        />
      </span>
      <h3 class="review-title" v-on:click="showForm = !showForm">- {{ review.review_title }}</h3>
      
      <div >
        <p class="review-body">{{ review.review_body }}</p>
      </div>
      <router-link v-if="$store.getters.isAdmin === true"
          tag="button"
          :to="{
            name: 'confirm-delete',
            params: { reviewId: review.review_id },
          }"
        >
          Delete Review
        </router-link>
      
      
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
  /* text-justify: center; */
}
.review-title{
  display: inline;
  /* text-justify: center; */
  /* padding: 5px; */
  font-size: 22px;
  line-height: 35px;
}
/* .review-body {
  
} */
</style>