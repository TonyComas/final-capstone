<template>
  <div>
      <img
          src="../assets/giphy.gif"
          v-bind:title="userReviews.rating + ' Star Review'"
          class="ratingStar"
          v-for="n in userReviews.rating"
          v-bind:key="n"
        />
      <h3 class="name">{{userReviews.review_title}} - </h3>
      <p>{{userReviews.review_body}}</p>


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