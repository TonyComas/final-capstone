<template>
  <form v-on:submit.prevent>
    <div id="add_game"> 
      <div class="Game_Title">
        <label class="search" for="title">Game Name: </label>
        <input class="search" type="text" name="title" v-model="search.game_name" />
      </div>
      <div class="Release_Date">
        <label class="search" for="release">Release Date: </label>
        <input class="search" type="date" name="release" v-model="search.release_date" />
      </div>
      <div class="Developer">
          <label class="search" for="developer">Developer Name: </label>
          <input class="search" type="text" name="developer" v-model="search.developer_names" />
      </div>
      <div class="Publisher">
          <label class="search" for="publisher">Publisher Name: </label>
          <input class="search" type="text" name="publisher" v-model="search.publisher_names" />
      </div>
      <div class="genre">
          <label class="search" for="genre">Game Genres: </label>
          <input class="search" type="text" name="genre" v-model="search.genres">
      </div>
      <div class="actions">
      </div>
    </div>
  </form>
</template>

<script>
export default {
    data() {
        return{
            search:{
                game_name: "",
                description: "",
                release_date: "",
                developer_names: "",
                publisher_names: "",
                genres: ""
            }
        }
    },

}
</script>

<style>
</style>