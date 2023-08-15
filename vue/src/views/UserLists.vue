<template>
  <div class="user_lists">
      <h1>Your Lists</h1>
     
      <ShowListsOfGamesVue v-for="list in lists" :key="list.list_id" />
    
      </div>
</template>

<script>
import ShowListsOfGamesVue from '../components/ShowListsOfGames.vue';

export default {
    components: {
        ShowListsOfGamesVue,
    },
    name: "user_lists",
    data() {
        return {
            userId: 0,
            lists: [],
            listId: 0,
        };
    },
    methods: {
      
    },
    created() {
        this.$store.dispatch("loadLists",this.userId);
    },
};
</script>

<style scoped>

</style>