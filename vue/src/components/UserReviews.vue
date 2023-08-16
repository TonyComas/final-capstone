<template>
  <div>
      <img
          src="../assets/giphy.gif"
          v-bind:title="review.rating + ' Star Review'"
          class="ratingStar"
          v-for="n in review.rating"
          v-bind:key="n"
        />
      <h3 class="name">{{review.review_title}}</h3>
      <p>{{review.review_body}}</p>


  </div>
</template>

<script>
export default {
    name: "list-of-reviews",
    props: ["userReviews"]

}
</script>

<style>

</style>