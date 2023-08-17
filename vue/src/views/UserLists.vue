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
    <div class="user_list_blank_space_card_long_name"> </div>
    <div class="user_reviews">
        <h1 class="user_reviews_header" >Your Reviews</h1>
        
        <UserReviewsVue 
        :userReviews="review"
        v-for="review in filteredReviews"
        :key="review.review_id"
        />
        <div v-if="!filteredReviews.length"> <h1 class="no_reviews"> You haven't reviewed anything!</h1></div>

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
      emptyReviews: true,
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
.user_lists{
  float:left;
  grid-area:"left";
}

.whole_page {
  display: grid;
  grid-template-rows: 1fr 2fr 2fr;
  grid-template-areas: "left center right";
}

.user_reviews {
  /* display: inline-block;
  text-align: center; */
    /* margin-left: 217px; */
    grid-area:"right"
}

img.logo_card {
    margin-right: 15px;
}
.user_list_blank_space_card_long_name{
  grid-area:"center"
}
.user_reviews_header {
  text-align:left;
}
.no_reviews {
  color: white;
}

</style>