<template>
  <div>
      <h1 class ="add-game">Add A New Game!</h1>
        <AddGameVue :fullGame="{}"/>
  </div>
</template>

<script>
import AddGameVue from '../components/AddGame.vue'
export default {
    components: {
        AddGameVue
    }
}
</script>

<style>
h1 {
  font-size: 24px;
  margin-top: 15px;
  margin-bottom: 15px;
}

.add-game {
   text-transform: uppercase;
  background-image: linear-gradient(
    -225deg,
    #281372 0%,
    #6c23b9 29%,
    #ff1361 67%,
    #1eff00 100%
  );
  background-size: auto auto;
  background-clip: border-box;
  background-size: 200% auto;
  color: #fff;
  background-clip: text;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  animation: textclip 2s linear infinite;
  display: inline-block;
  font-size: 20px;
  
}

@keyframes textclip {
  to {
    background-position: 200% center;
  }
}

h1 {
  display:flex;
  flex-wrap: wrap;
}
</style>