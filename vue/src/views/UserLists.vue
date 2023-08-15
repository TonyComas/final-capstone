<template>
  <div class="user_lists">
      <h1>Your Lists</h1>
     
      <ShowListsOfGamesVue v-for="list in list" :key="list.list_id" />
    
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
    computed: {
        list(){
            console.log("This gets the user lists",this.$store.getters.getUserLists)
            return this.$store.getters.getUserLists
        }
    },
    methods: {
      
    },
    created() {
        this.$store.dispatch("loadLists",this.$store.state.user.id);
    },
};
</script>

<style scoped>

</style>