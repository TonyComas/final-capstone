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
    genres: [],
    developers: [],
    publishers: [],
    filter: 0,
    reviews: []

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
    },
    genres(state) {
      return state.genres;
    },
    developers(state) {
      return state.developers;
    },
    publishers(state) {
      return state.publishers
    },
    reviews(state) {
      return state.reviews;
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
    SET_ALL_GAMES(state, games) {
      state.games = games;
    },
    SET_ALL_GENRES(state, genres) {
      state.genres = genres;
    },
    SET_ALL_DEVELOPERS(state, developers) {
      state.developers = developers;
    },
    SET_ALL_PUBLISHERS(state, publishers) {
      state.publishers = publishers;
    },
    DELETE_GAME(state,id) {
      state.games.splice(
        state.games.findIndex(game => game.game_id === id),
        1
      )
    },
    UPDATE_GAME(state,gameObject){
      let gameIndex = state.games.findIndex(game => game.game_id === gameObject.game_id)
      state.games[gameIndex] = gameObject;
    },
    UPDATE_FILTER(state, filter) {
      state.filter = filter;
    },
    SET_ALL_REVIEWS(state, reviews) {
      state.reviews = reviews;
    } 

  },
  actions: {
    loadGames(state) {
      GameServices.getAllGames().then( response => {
        const arrayFromApi = response.data;
         state.commit("SET_ALL_GAMES",arrayFromApi);
      }).catch( error => console.error(error));
      
    },
    loadGenres(state) {
      GameServices.getAllGenres().then( response => {
        const arrayFromApi = response.data;
         state.commit("SET_ALL_GENRES",arrayFromApi);
      }).catch( error => console.error(error));
      
    },
    loadDevelopers(state) {
      GameServices.getAllDevelopers().then( response => {
        const arrayFromApi = response.data;
         state.commit("SET_ALL_DEVELOPERS",arrayFromApi);
      }).catch( error => console.error(error));
      
    },
    loadPublishers(state) {
      GameServices.getAllPublishers().then( response => {
        const arrayFromApi = response.data;
         state.commit("SET_ALL_PUBLISHERS",arrayFromApi);
      }).catch( error => console.error(error));
      
    },
    loadReviews(state, game) {
      GameServices.getReviewByGameId(game).then (response => {
        const arrayFromApi = response.data;
        state.commit("SET_ALL_REVIEWS",arrayFromApi);
      }).catch( err => console.error(err));
    },

    updateGame(state, gameObject) {
      //Find the game object in state
     state.commit("UPDATE_GAME",gameObject);

      //Update the object in state

      // Call API to update server
      GameServices.updateGame(gameObject.game_id, gameObject)
        .catch(err => console.error(err));
    }
    
  },
  modules: {

  },
  strict: true
})
