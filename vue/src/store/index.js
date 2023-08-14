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
    filter: 0,
    reviews: [],
    lists: []

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
    reviews(state) {
      return state.reviews;
    },
   
    isAdmin(state) {
      if(state.token != ''){
        return state.user.authorities[0].name === "ROLE_ADMIN" 
      } else{
        return false;
      }
    },
    getUserLists(state) {
      return state.lists
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
    },
    DELETE_REVIEW(state, id) {
      console.log("Reached Delete Review")
      console.log(id)
      state.reviews.splice(
        state.reviews.findIndex(review => review.review_id === id),
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
    loadReviews(state, game) {
      GameServices.getReviewByGameId(game).then (response => {
        const arrayFromApi = response.data;
        state.commit("SET_ALL_REVIEWS",arrayFromApi);
      }).catch( err => console.error(err));
    },
    /*loadLists(state, list){
      GameServices.getListsByLists(list).then (response => {
        const arrayFromApi = response.data;
        state.commit("SET_ALL_REVIEWS",arrayFromApi);
      }).catch( err => console.error(err));
    }, */

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
