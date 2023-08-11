<template>
  <header class="header">
    <div id="nav" class="nav-elements">
      <router-link v-bind:to="{ name: 'home' }" class="nav-link"
        >>Home</router-link
      >&nbsp;
      <router-link :to="{ name: 'games' }" class="nav-link"
        >>Game List</router-link
      >&nbsp;
      
      <router-link v-bind:to="{ name: 'logout' }" class = "nav-link"
        v-if="$store.state.token != ''"
        >>Logout</router-link
      >&nbsp;
      <router-link v-if="$store.getters.isAdmin === true"
      :to="{ name: 'AddGame' }" class="nav-link"
        >>Add a Game!</router-link
      >
    </div>
    <h1 class="page-title waviy">
      <span>Welcome to our video game database!</span>
    </h1>
    <body></body>
    <div class="spacer"></div>
  </header>
</template>

<script>
export default {
  name: "page-header",
  components: {},
};
</script>

<style>
@import url('https://fonts.cdnfonts.com/css/gotham-6');
@import url('https://fonts.googleapis.com/css2?family=Handjet:wght@500&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap');


header {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  height: 120px;
  border-bottom: 2px rgb(11, 226, 11) solid;
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

@import url("https://fonts.googleapis.com/css2?family=Alfa+Slab+One&display=swap");
* {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}

body {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 8vh;
  font-family: 'Press Start 2P', cursive;
}
.waviy {
  position: relative;
  -webkit-box-reflect: below -5px linear-gradient(transparent, rgba(0, 0, 0, 0.2));
  font-size: 44px;
  margin-top: -8px;
}
.page-title.waviy {
  font-family: 'Handjet', cursive;
}
.waviy span {
  position: relative;
  display: inline-block;
  color: rgb(11, 226, 11);
  text-transform: uppercase;
}

.waviy span:hover {
  animation: waviy 3s infinite;
  animation-delay: calc(0.1s * var(--i));
}

@keyframes waviy {
  0%,
  40%,
  100% {
    transform: translateY(0);
  }
  20% {
    transform: translateY(-10px);
  }
}

.nav-link {
  background-image: linear-gradient(to right, #920ab4, rgb(11, 226, 11) 50%, white 50%);
  background-size: 200% 100%;
  background-position: -100%;
  display: inline-block;
  padding: 5px 0;
  position: relative;
  font-weight: bolder;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  transition: all 0.3s ease-in-out;
  text-decoration: none; /* Remove default underline */
}

.nav-link:before {
  content: "";
  background: rgb(11, 226, 11);
  display: block;
  position: absolute;
  bottom: -3px;
  left: 0;
  width: 0;
  height: 1px;
  transition: all 0.2s ease-in-out;
}

.nav-link:hover {
  background-position: 0;
}

.nav-link:hover::before {
  width: 100%;
}

h1 {
  font-size: 10px;
}
</style>