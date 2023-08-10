<template>
  <div>

    <div class="review" 
    v-for="review in this.$store.getters.reviews" 
    v-bind:key="review.id">
      <h4>{{ review.reviewer }}</h4>
      <div class="rating">
        <img
          src="../assets/star.png"
          v-bind:title="review.rating + ' Star Review'"
          class="ratingStar"
          v-for="n in review.rating"
          v-bind:key="n"
        />
      </div>
      <h3>{{ review.review_title }}</h3>
      <p>{{ review.review_body }}</p>

      <div>
        <router-link
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
  </div>
</template>

<script>
export default {
  name: "review-display",
//   props: ["review"],
  methods: {},
};
</script>

<style>
</style>