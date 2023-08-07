<template>
  <div>
      <h1>Add New Game</h1>
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

</style>