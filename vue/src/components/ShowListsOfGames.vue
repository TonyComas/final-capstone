<template>
  <div class="list">
      <router-link :to="{
          name: 'user-list',
          params:{
              name: list.list_name,
              id: list.list_id
          },
      }">
        <div>
            <h1 class="name">{{list.list_name}}</h1>

        </div>
      </router-link>

  </div>

</template>

<script>
export default {
    name: "list-of-lists",
    props: ["list"],
    data() {
        return {
        }
    },

}
</script>

<style>

</style>