<template>
  <header class = "header">

      <div id="nav" class = "nav-elements">
      <router-link v-bind:to="{ name: 'home' }" class = "nav-link">Home</router-link>&nbsp;|&nbsp;
       <router-link :to="{ name: 'AddGame' }" class = "nav-link">Add a Game!</router-link>&nbsp;|&nbsp;
       <router-link :to="{ name: 'games' }" class = "nav-link">Game List</router-link>&nbsp;|&nbsp;
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
  animation: waviy 5s infinite;
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

.nav-link {
  background-image: linear-gradient(
    to right,
    #000008,
    #54b3d6 50%,
    #000 50%
  );
  background-size: 200% 100%;
  background-position: -100%;
  display: inline-block;
  padding: 5px 0;
  position: relative;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  transition: all 0.3s ease-in-out;
  text-decoration: none; /* Remove default underline */
}

.nav-link:before {
  content: '';
  background: #02126b;
  display: block;
  position: absolute;
  bottom: -3px;
  left: 0;
  width: 0;
  height: 3px;
  transition: all 0.2s ease-in-out;
}

.nav-link:hover {
  background-position: 0;
}

.nav-link:hover::before {
  width: 100%;
}
</style>