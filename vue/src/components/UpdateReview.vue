<template>
  <div class="update-review" >
    <form
      v-on:submit.prevent="updateReview()"
      action=""
      class="review-form"
    >
      <label for="Review Title">Review Title</label>
      <input type="text" v-model="updatedReview.review_title" name="Review Title" />

      <label for="Review Body">Review Body</label>
      <input
        type="text"
        v-model="updatedReview.review_body"
        placeholder="Review"
        cols="5"
      />

      <label for="Review Rating">Rating</label>
      <select id="rating" v-model="updatedReview.rating">
        <option value="1">1 Star</option>
        <option value="2">2 Stars</option>
        <option value="3">3 Stars</option>
        <option value="4">4 Stars</option>
        <option value="5">5 Stars</option>
      </select>

      <button class="update-form-button">Update Review</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "update-review",
  props: ["updateCurrentReview"],
  data() {
    return {
    updatedReview: this.updateCurrentReview,

    };
  },
  methods: {
   updateReview() {
      this.$store.commit("UPDATE_REVIEW", this.updateCurrentReview);
      this.toggleReviewForm();
    },
    toggleReviewForm() {
      this.showReviewForm = !this.showReviewForm;
    },
  },
};
</script>

<style>
</style>