<template>
  <div class="user_lists">
      <h1>Your Lists</h1>
     
      <ShowListsOfGamesVue :list="list" v-for="list in filteredLists" :key="list.list_id" />
    
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
        filteredLists(){
            const userList = this.$store.getters.getUserLists
            let filteredLists = [];
            userList.forEach(list => {
                filteredLists.push(list);
            })
            return filteredLists;
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