<template>
  <div>
    <div class="game">
      <router-link
        :to="{
          name: 'single-game-view',
          params: {
            gameId: game.game_id,
          },
        }"
      >
        <div class="individual" @mouseover="hover = true" @mouseleave="hover = false">
          <img class="logo" v-bind:src="game.game_logo" alt="" />
          <h1 class="name">{{ game.game_name }}</h1>
          <p class="genres" v-if="hover" >{{ game.genres }}</p>
          
        </div>
        
      </router-link>
      
    </div>
   
  </div>
</template>

<script>
export default {
  data() {
    return {
      hover: false
    }
  },
  name: "game-detail",
  props: ["game"],
  computed: {},
  methods: {
    //This is where we will add games to list for users. Dope
   
  },
};
</script>

<style>
img.logo {
  width: 300px;
  height: 300px;
  object-fit: contain;
  border-radius: 25px;
  background-color: white;
}
h1.name {
    font-size: 15px;
    padding-left: 5px;
    color: black;
    text-decoration: none;
}

a {
    text-decoration: none;
}


div.game {
    display: flexbox;
    width: 313px;
    margin-right: 30px;
    height: 370px;
}

div.individual {
    display: flexbox;
    padding-top: 4px;
    padding-right: 5px;
    padding-left: 5px;
    padding-bottom: 4px;
    margin-top: 30px;
    border-color: rgba(80, 80, 80, 0.459);

}

div.individual:hover {
    border-width: 2px;
    border-style: solid;
    border-radius: 25px;
    background-color: rgba(80, 80, 80, 0.4);
    border-color: rgba(80, 80, 80, 0.658)
}

.deleteButton {
  box-sizing: border-box;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  background-color: transparent;
  border: 2px solid #e74c3c;
  border-radius: 0.6em;
  color: #e74c3c;
  cursor: pointer;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-align-self: center;
  -ms-flex-item-align: center;
  align-self: center;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1;
  margin: 5px;
  padding: 0.5em 1em;
  text-decoration: none;
  text-align: center;
  text-transform: uppercase;
  font-family: 'Fantasy';
  font-weight: 700;
  position: relative;
  z-index: 1;
  transition: box-shadow 300ms ease-in-out, color 300ms ease-in-out;
}

.deleteButton:hover,
.deleteButton:focus {
  color: #fff;
  outline: 0;
}

.deleteButton:hover {
  box-shadow: 0 0 20px 20px #e74c3c inset;
}

.genres {
  font-size: 12px;
  padding-left: 5px;
  color: black;
}
</style>