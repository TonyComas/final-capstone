<template>
  <header class = "header">
      <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;       
      <router-link :to="{ name: 'games' }">Game List</router-link>&nbsp;|&nbsp;
       <router-link :to="{ name: 'AddGame' }" > Add a Game!</router-link>&nbsp;|&nbsp;
       <!-- v-if="$store.state.token"  this goes ^ -->
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <h1 class = "page-title waviy">
      <span>Welcome to our video game database!</span>
      </h1>
    <body>
</body>
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

.waviy {
  font-size: 24px;
  margin: 0;
}

.spacer {
  flex-grow: 1;
}

@import url('https://fonts.googleapis.com/css2?family=Alfa+Slab+One&display=swap');
* {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}

body {
  
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 10vh;
}
.waviy {
  position: relative;
  -webkit-box-reflect: below -1px linear-gradient(transparent, rgba(0,0,0,.2));
  font-size: 30px;
}
.waviy span {
  font-family: 'Fantasy';
  position: relative;
  display: inline-block;
  color: rgb(11, 6, 85);
  text-transform: uppercase;
  animation: waviy 1s infinite;
  animation-delay: calc(.1s * var(--i));
  
}
@keyframes waviy {
  0%,40%,100% {
    transform: translateY(0)
  }
  20% {
    transform: translateY(-10px)
  }
}
</style>