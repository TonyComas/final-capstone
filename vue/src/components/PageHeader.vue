<template>
  <header>
      <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
       <router-link :to="{ name: 'AddGame' }" > Add a Game!</router-link>&nbsp;|&nbsp;
       <router-link :to="{ name: 'games' }">Game List</router-link>
       <!-- v-if="$store.state.token"  this goes ^ -->
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <h1 class = "page-title">Welcome to our Video Game Database!</h1>
    <div class = "spacer"></div>
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
  justify-content: space-between;
  align-items: center;
  height: 100px;
  
  border-bottom: 2px darkblue solid;
}

#nav {
  display: flex;
  align-items: center;
  flex-grow: 1;
}

.page-title {
  font-size: 36px;
  margin: 0;
}

.spacer {
  flex-grow: 1;
}

</style>