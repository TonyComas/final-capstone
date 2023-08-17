<template>
  <div class="body">
    <div class="single-game" v-if="game">
      <div class="game-column">
        <!-- <div class="img-button-cluster"> -->
        <img class="logo" v-bind:src="game.game_logo" alt="" />
        <div></div>
        <div class="button-container">
          <div class="buttons">
            <input
              v-if="$store.getters.isAdmin === true"
              class="delete-button"
              type="button"
              value="Remove"
              @click="deleteGame(game.game_id)"
            />
            <button
              class="update-button"
              v-if="$store.getters.isAdmin === true"
              v-on:click="showForm = !showForm"
            >
              Update
            </button>
            <router-link
              v-bind:to="{
                name: 'add-review',
                params: { id: game.game_id },
              }"
              class="add-review-button"
            >
              Add Review
            </router-link>
          </div>
        </div>

        <div class="game_info" v-if="!showForm">
          <p id="name"><span>Game Name</span>: {{ game.game_name }}</p>
          <p id="description" class="input-description">
            <span>Description</span>: {{ game.description }}
          </p>
          <p id="release_date">
            <span>Release Date</span>: {{ game.release_date }}
          </p>
          <p id="developers">
            <span>Developers</span>: {{ game.developer_names }}
          </p>
          <p id="publishers">
            <span>Publishers</span>: {{ game.publisher_names }}
          </p>
          <p id="genres"><span>Genres</span>: {{ game.genres }}</p>
        </div>
        <form
          v-on:submit.prevent="updateGame()"
          action=""
          v-if="showForm"
          class="game-form"
        >
          <label for="Game Name">Game Name</label>
          <input type="text" v-model="game.game_name" placeholder="Game Name" />

          <label for="Description">Description</label>
          <input
            type="text"
            v-model="game.description"
            placeholder="Description"
          />

          <label for="Release Date">Release Date</label>
          <input type="date" v-model="game.release_date" />

          <label for="Developers">Developers</label>
          <input
            type="text"
            v-model="game.developer_names"
            placeholder="Developers"
          />

          <label for="Publishers">Publishers</label>
          <input
            type="text"
            v-model="game.publisher_names"
            placeholder="Publishers"
          />

          <label for="Genres">Genres</label>
          <input type="text" v-model="game.genres" placeholder="Genres" />
          <button class="update-button">Update</button>
        </form>

        <div class="back-link">
          <button
            class="back-link-style"
            @click="$router.push({ name: 'games' })"
          >
            Back to Game Listing
          </button>
        </div>
      </div>
      <div class="list-column">
        <h1>Your Lists</h1>
        <label class="listy">
          <input type="checkbox" v-model="list[0].checked" />
          <div class="list-names">Currently Playing</div>
        </label>
        <label class="listy">
          <input type="checkbox" v-model="list[1].checked" />
          <div class="list-names">Want to Play</div>
        </label>
        <label class="listy">
          <input type="checkbox" v-model="list[2].checked" />
          <div class="list-names">Finished Games</div>
        </label>
        <label class="listy">
          <input type="checkbox" v-model="list[3].checked" />
          <div class="list-names">Favorite Games</div>
        </label>
        <label class="listy">
          <input type="checkbox" v-model="list[4].checked" />
          <div class="list-names">Game Library</div>
        </label>
        <button class="add-to-lists" @click="addGameToLists(list)">
          Add to lists
        </button>
        <button class="remove-from-lists" @click="deleteGameFromLists(list)">
          Remove from lists
        </button>
        <div class="popup" ref="popup">
          <div class="popup-content" v-if="ifPopup">Success!</div>
        </div>
      </div>
      <div class="review-column">
        <ReviewDisplayVue :reviews="game.reviews" />
      </div>
    </div>
  </div>
</template>
<script>
import GameServices from "@/services/GameServices.js";
import ReviewDisplayVue from "../components/ReviewDisplay.vue";
export default {
  components: {
    ReviewDisplayVue,
  },
  data() {
    return {
      gameId: 0,
      game: null,
      showForm: false,

      allReviews: this.$store.getters.reviews,

      ifPopup: false,
      list: [
        {
          checked: false,
          list_name: "Currently Playing",
          game_id: this.$route.params.gameId,
          user_id: this.$store.state.user.id,
        },
        {
          checked: false,
          list_name: "Want To Play",
          game_id: this.$route.params.gameId,
          user_id: this.$store.state.user.id,
        },
        {
          checked: false,
          list_name: "Finished Games",
          game_id: this.$route.params.gameId,
          user_id: this.$store.state.user.id,
        },
        {
          checked: false,
          list_name: "Favorite Games",
          game_id: this.$route.params.gameId,
          user_id: this.$store.state.user.id,
        },
        {
          checked: false,
          list_name: "Game Library",
          game_id: this.$route.params.gameId,
          user_id: this.$store.state.user.id,
        },
      ],
    };
  },
  methods: {
    addGameToLists(list) {
      list.forEach((currentList) => {
        if (currentList.checked) {
          GameServices.addGameToLists(currentList);
          currentList.checked = false;
        }
      });
      this.ifPopup = true;
      this.showPopup();
    },

    deleteGameFromLists(list) {
      list.forEach((currentList) => {
        if (currentList.checked) {
          GameServices.deleteGameFromLists(currentList);
          currentList.checked = false;
        }
      });
      this.ifPopup = true;
      this.showPopup();
    },

    showPopup() {
      if (this.ifPopup) {
        setTimeout(() => {
          this.ifPopup = false;
        }, 1500);
      }
    },

    updateGame() {
      this.$store.dispatch("updateGame", this.game);
      this.toggleForm();
    },

    deleteGame(gameId) {
      GameServices.deleteGame(gameId).then((response) => {
        if (response.status === 200) {
          this.$store.commit("DELETE_GAME", gameId);
          this.$router.push({
            name: "games",
          });
        }
      });
    },
    toggleForm() {
      this.showForm = !this.showForm;
    },
  },
  created() {
    if (this.$store.getters.games.length === 0) {
      this.$store.dispatch("loadGames");
    }
    this.gameId = this.$route.params.gameId;
    GameServices.getGameById(this.gameId)
      .then((response) => {
        this.game = response.data;
      })
      .catch((err) => console.error(err));

    console.log("Fetching", this.gameId);
    this.$store.dispatch("loadReviews", this.gameId);
  },
};
</script>
<style>
@import url("https://fonts.cdnfonts.com/css/gotham-6");

.body {
  font-family: "Press Start 2P", cursive;
  color: green;
  line-height: 1.2;
  font-size: 14px;
}

.popup {
  margin-left: 40px;
  margin-top: 10px;
}

.add-to-lists {
  font-size: 10px;
  height: 30px;
  border-radius: 30px;
  border-width: 1px;
  text-align: right;
  box-sizing: border-box;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  background-color: transparent;
  border: 2px solid #0aa546;
  border-radius: 0.6em;
  color: #0aa546;
  cursor: pointer;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-align-self: center;
  -ms-flex-item-align: center;
  align-self: center;
  font-size: 0.8rem;
  font-weight: 400;
  line-height: 1.2;
  margin: 5px;
  padding: 0.5em 1em;
  text-decoration: none;
  text-align: center;
  text-transform: uppercase;
  font-weight: 700;
  position: relative;
  z-index: 1;
  font-family: "Press Start 2P", cursive;
  transition: box-shadow 300ms ease-in-out, color 300ms ease-in-out;
}

.add-to-lists:hover,
.add-to-lists:focus {
  color: #fff;
  outline: 0;
}

.add-to-lists:hover {
  background-color: rgb(4, 78, 20);
  font-size: 12.8px;
  box-shadow: 0 0 20px 20px #4ae73c inset;
}

:root {
  --color-one: #52117d;
  --color-two: #a944ec;
  --color-three: #ffc800;
  --color-accent: #19ff70;
  --color-text: #fffed7;

  /* Typography */
  --font-family: "Inter", sans-serif;
  --font-family-headings: "Inter", sans-serif;
}

.listy {
  display: inline-flex;
  color: var(--color-text);
  font-size: 1rem;
  font-weight: 600;
  text-transform: uppercase;
  align-items: center;
  line-height: 1;
  border-radius: 5px;

  padding: 5px 7px 5px 7px;
  user-select: none;
}

@media (min-width: 992px) {
  .listy {
    font-size: 1.25rem;
  }
}

.listy .list-names {
  position: relative;
  display: flex;
  align-items: center;
}

.listy input[type="checkbox"] {
  position: absolute;
  clip: rect(1px, 1px, 1px, 1px);
  padding: 0;
  border: 0;
  height: 1px;
  width: 1px;
  overflow: hidden;
}

.listy input[type="checkbox"]:checked + .list-names::after {
  background-color: var(--color-accent);
}

.listy input[type="checkbox"]:checked + .list-names {
  color: var(--color-accent);
}

.listy input[type="checkbox"]:focus + .list-names::before {
  border-color: var(--color-accent);
}

.listy input[type="checkbox"]:disabled {
  opacity: 0.85;
}

.listy input[type="checkbox"]:disabled + .list-names {
  color: #9a9a88;
}

.listy .list-names::before {
  content: "";
  display: inline-block;
  border-radius: 3px;
  background-color: #414051;
  margin-right: 8px;
  height: 16px;
  width: 16px;
  border: 1px solid transparent;
}

.listy .list-names::after {
  content: "";
  display: inline-block;
  height: 12px;
  width: 12px;
  border-radius: 3px;
  background-color: transparent;
  left: 3px;
  position: absolute;
}

.button-container {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-bottom: 10px;
  padding-top: 20px;
}

.add-review-button {
  font-size: 10px;
  height: 30px;
  border-radius: 30px;
  border-width: 1px;
  text-align: right;
  box-sizing: border-box;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  background-color: transparent;
  border: 2px solid #d808b5;
  border-radius: 0.6em;
  color: #d808b5;
  cursor: pointer;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-align-self: center;
  -ms-flex-item-align: center;
  align-self: center;
  font-size: 0.8rem;
  font-weight: 400;
  line-height: 1.2;
  margin: 0px;
  padding: 0.5em 0.6em;
  text-decoration: none;
  text-align: center;
  text-transform: uppercase;
  position: relative;
  z-index: 1;
  transition: box-shadow 200ms ease-in-out, color 200ms ease-in-out;
}

.add-review-button:hover,
.add-review-button:focus {
  color: #fff;
  outline: 0;
}

.add-review-button:hover {
  background-color: #640453;
  font-size: 12.8px;
  box-shadow: 0 0 20px 20px #d808b5 inset;
}

.buttons {
  display: flex;
}

span {
  color: rgb(11, 226, 11);
  max-width: 600px;
}
.img-button-cluster {
  float: left;
  margin-right: 10px;
}

.single-game {
  display: grid;
  grid-template-rows: auto 1fr;
  /* margin: 0 auto; */
  padding: 20px;
  border-radius: 5px;
  grid-template-columns: 500px 500px 1fr;
  grid-template-areas: "game-column list-column review-column";
}
.game-column {
  grid-area: game-column;
}

.list-column {
  grid-area: list-column;
}

.review-column {
  grid-area: review-column;
}
img.logo {
  width: 300px;
  height: 300px;
  object-fit: contain;
  border-radius: 25px;
  background-color: white;
}

.update-button {
  font-size: 10px;
  height: 20px;
  border-radius: 30px;
  border-width: 1px;
  text-align: left;
  font-family: "Press Start 2P", cursive;
}
.update-button:hover {
  background-color: rgb(255, 255, 255);
  font-size: 11px;
}
.game-info p {
  margin-bottom: 15px;
}
.game-info strong {
  font-weight: bold;
}

.game-form {
  margin-top: 10px;
  max-width: 600px;
  width: 100%;
  box-sizing: border-box;
  color: rgb(11, 226, 11);
}

.game-form input {
  display: block;
  width: 80%;
  padding: 10px;
  border-radius: 3px;
  font-size: 16px;
  margin-bottom: 10px;
  background: black;
  color: green;
  font-family: "Press Start 2P", cursive;
  font-weight: 50;
  margin: 2px;
  padding: 5px;
}

.game-form button {
  font-size: 10px;
  height: 30px;
  border-radius: 30px;
  border-width: 1px;
  text-align: right;
  box-sizing: border-box;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  background-color: transparent;
  border: 2px solid #0aa546;
  border-radius: 0.6em;
  color: #0aa546;
  cursor: pointer;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-align-self: center;
  -ms-flex-item-align: center;
  align-self: center;
  font-size: 0.8rem;
  font-weight: 400;
  line-height: 1.2;
  margin: 0px;
  padding: 0.5em 1.0em;
  text-decoration: none;
  text-align: center;
  text-transform: uppercase;
  position: relative;
  z-index: 1;
  transition: box-shadow 200ms ease-in-out, color 200ms ease-in-out;
}
.game-form button:hover {
  background-color: #027e09;
}

.delete-button {
  font-size: 10px;
  height: 30px;
  border-radius: 30px;
  border-width: 1px;
  text-align: right;
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
  font-size: 0.8rem;
  font-weight: 400;
  line-height: 1.2;
  margin: 0px;
  padding-top: 0.6em;
  text-decoration: none;
  text-align: center;
  text-transform: uppercase;
  position: relative;
  z-index: 1;
  transition: box-shadow 200ms ease-in-out, color 200ms ease-in-out;
  font-family: "Press Start 2P", cursive;
}

.delete-button:hover,
.delete-button:focus {
  color: #fff;
  outline: 0;
}

.delete-button:hover {
  background-color: rgb(107, 7, 7);
  font-size: 12.8px;
  box-shadow: 0 0 20px 20px #e74c3c inset;
}

.remove-from-lists {
  font-size: 10px;
  height: 30px;
  border-radius: 30px;
  border-width: 1px;
  text-align: right;
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
  font-size: 0.8rem;
  font-weight: 400;
  line-height: 1.2;
  margin-left: 5px;
  padding-top: 0.6em;
  padding-left: 5px;
  padding-right: 5px;
  text-decoration: none;
  text-align: center;
  text-transform: uppercase;
  position: relative;
  z-index: 1;
  transition: box-shadow 200ms ease-in-out, color 200ms ease-in-out;
  font-family: "Press Start 2P", cursive;
}

.remove-from-lists:hover,
.remove-from-lists:focus {
  color: #fff;
  outline: 0;
}

.remove-from-lists:hover {
  background-color: rgb(107, 7, 7);
  font-size: 12.8px;
  box-shadow: 0 0 20px 20px #e74c3c inset;
}

.update-button {
  font-size: 10px;
  height: 20px;
  border-radius: 30px;
  border-width: 1px;
  text-align: right;
}

.update-button:hover {
  background-color: rgb(0, 0, 0);
  font-size: 13px;
}

.game-form button:hover {
  background-color: rgb(0, 0, 0);
}

.update-button {
  font-size: 10px;
  height: 30px;
  border-radius: 30px;
  border-width: 1px;
  text-align: right;
  box-sizing: border-box;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  background-color: transparent;
  border: 2px solid #0aa546;
  border-radius: 0.6em;
  color: #0aa546;
  cursor: pointer;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-align-self: center;
  -ms-flex-item-align: center;
  align-self: center;
  font-size: 0.8rem;
  font-weight: 400;
  line-height: 1.2;
  margin: 5px;
  padding: 0.5em 1em;
  text-decoration: none;
  text-align: center;
  text-transform: uppercase;
  position: relative;
  z-index: 1;
  transition: box-shadow 300ms ease-in-out, color 300ms ease-in-out;
}

.update-button:hover,
.update-button:focus {
  color: #fff;
  outline: 0;
}

.update-button:hover {
  background-color: rgb(4, 78, 20);
  font-size: 12.8px;
  box-shadow: 0 0 20px 20px #4ae73c inset;
}

.back-link-style {
  -webkit-touch-callout: none;
  -webkit-user-select: none;
  -khtml-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  -webkit-tap-highlight-color: transparent;
}

.back-link-style {
  width: 150px;
  height: 60px;
  cursor: pointer;
  font-size: 14px;
  font-weight: bold;
  color: rgb(11, 226, 11);
  background-color: transparent;
  border: 1px solid rgb(16, 87, 2);
  box-shadow: 1px 1px 0 rgb(16, 87, 2), -1px -1px 0 rgb(16, 87, 2),
    -1px 1px 0 rgb(16, 87, 2), 1px -1px 0 rgb(16, 87, 2);
  transition: 500ms ease-in-out;
  margin-top: 20px;
  font-family: "Press Start 2P", cursive;
  line-height: 1.2;
}

.back-link-style:hover {
  box-shadow: 13px 5px 0 rgb(53, 255, 3), -13px -5px 0 rgb(53, 255, 3);
}

.b:focus {
  outline: none;
}

#description {
  color: green;
}
</style>