<template>
  <div class="list">
      <router-link :to="{
          name: '',
          params:{

          },
      }">
        <div>
            <h1 class="name">{{lists.list_name}}</h1>

        </div>
      </router-link>

  </div>

</template>

<script>
export default {
    name: "list-of-lists",
    data() {
        return {

        }
    }

}
</script>

<style>

</style>