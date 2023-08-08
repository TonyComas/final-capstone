import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import GameServices from '../services/GameServices.js'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    categoryFilter: 'All',
    games: [],

  },
  getters: {
    categories(state) {
      return state.categories;
    },
    games(state) {
      return state.games;
    },
    game(state,id) {
      return state.games.find(game => game.game_id === id);
    }
  
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_ALL_GAMES(state,games) {
      state.games = games;
    },
    DELETE_GAME(state,id) {
      state.games.splice(
        state.games.findIndex(game => game.game_id === id),
        1
      )
    },

  },
  actions: {
    loadGames(state) {
      GameServices.getAllGames().then( response => {
        const arrayFromApi = response.data;
         state.commit("SET_ALL_GAMES",arrayFromApi);
      }).catch( error => console.error(error));
      
    },
    // updateGame(state,game) {
    //   //Find the game object in state
    //   //Update the object in state
    //   // Call API to update server
      
    // }

  },
  modules: {

  },
  strict: true
})
