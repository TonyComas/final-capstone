<template>
  <div>
        <h1>Play A Game</h1>
        <div id="pong">
            <router-link :to="{ name: 'pong' }" class="nav-link">Pong</router-link>
        </div>
        <div id="asteroids">
            <router-link :to="{ name: 'asteroids' }" class="nav-link">Asteroids</router-link>
        </div>
        <div id="tetris">
            <router-link :to="{ name: 'tetris' }" class="nav-link">Tetris</router-link>
        </div>
  </div>
</template>

<script>

export default {
    
}
</script>

<style>
</style>