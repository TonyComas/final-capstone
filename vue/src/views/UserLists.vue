<template>
  <div class="user_lists">
      <h1>Your Lists</h1>
      <ul>
      <li v-for="list in lists" :key="list.list_id">
          {{ list.list_name }}
          <button @click="viewList(list.list_id)">View Lists</button>
      </li>
      </ul>
      </div>
</template>

<script>
export default {
    name: "user_lists",
    data() {
        return {
            lists: [],
        };
    },
    methods: {
        viewList(listId) {
            this.$router.push(`/lists/${listId}`);
        },
    },
    created() {
        this.lists = this.$store.getters.getUserLists(this.userId);
    },
};
</script>

<style scoped>

</style>