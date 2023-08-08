<template>
  <header>
      <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
       <router-link :to="{ name: 'AddGame' }" > Add a Game!</router-link>
       <!-- v-if="$store.state.token"  this goes ^ -->
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
  </header>
</template>

<script>
export default {
    name: 'page-header',
    components: {
    }
}
</script>

<style>
header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 100px;
  border-bottom: 2px darkblue solid;
}

</style>