<template>
  <div class="whole_page">
    <div class="user_lists" v-if="filteredLists.length > 0">
      <h1>Your List</h1>

      <ShowListsOfGamesVue 
        :list="list"
        v-for="list in filteredLists"
        :key="list.list_id"
      />
    </div>
    <div class="user_reviews">
        <h1>Your Reviews</h1>
        
        <UserReviewsVue 
        :userReviews="review"
        v-for="review in filteredReviews"
        :key="review.review_id"
        />

    </div>
  </div>
</template>

<script>
import ShowListsOfGamesVue from "../components/ShowListsOfGames.vue";
import UserReviewsVue from '../components/UserReviews.vue';

export default {
  components: {
    ShowListsOfGamesVue,
    UserReviewsVue

  },
  name: "user_lists",
  data() {
    return {
      userId: this.$store.state.user.id,
      lists: [],
      listId: 0,
      reviews: [],
      allReviews: this.$store.getters.reviews,
    };
  },
  computed: {
    filteredLists() {
      const userList = this.$store.getters.getUserLists;
      let filteredLists = [];
      userList.forEach((list) => {
        filteredLists.push(list);
      });
      return filteredLists;
    },
    filteredReviews() {
        const userReviews = this.$store.getters.reviews;
        let filteredReviews = [];
        userReviews.forEach((review) => {
            filteredReviews.push(review);
        })
        return filteredReviews;
    }
  },
  methods: {},
  created() {
    
    this.$store.dispatch("loadLists", this.$store.state.user.id);
    this.$store.dispatch("loadUserReviews",this.$store.state.user.id);
  },
};
</script>

<style scoped>
.whole_page {
  display: grid;
  grid-template-rows: 1fr 1fr;
  grid-template-areas: "left right";
}
</style>