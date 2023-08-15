<template>
  <div>
        <h1>Play A Game</h1>
        <div id="pong">
            <router-link :to="{ name: 'pong' }" class="nav-link">Pong</router-link>
            <router-link :to="{ name: 'asteroids' }" class="nav-link">Asteroids</router-link>
        </div>
        
  </div>
</template>

<script>

export default {
    
}
</script>

<style>

</style>