<template>
  <div class="reviews">
      <!-- v-for="review in this.$store.getters.reviews"
      v-bind:key="review.id"
      v-bind:review="review"
      /> -->
  </div>
</template>

<script>
export default {
name: "review-list",
props: ['reviews'],
components: {
},
computed: {
    
  }
}

</script>

<style>

</style>