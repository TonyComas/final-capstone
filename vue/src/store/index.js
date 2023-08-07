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
    gameCategories: []
  },
  getters: {
    categories(state) {
      return state.categories;
    },
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

  },
  actions: {
    loadGames(state) {
      GameServices.getAllGames().then( response => {
        const arrayFromApi = response.data;
        arrayFromApi.unshift('All');
        state.commit('',arrayFromApi);
      }).catch( error => console.error(error));
      
    }

  },
  modules: {

  },
  strict: true
})
