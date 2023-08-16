import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import Games from '../views/Games.vue'
import SingleGameView from '../views/SingleGameView.vue'
import AddGameView from '../views/AddGameView.vue'
import DeleteReview from '../views/DeleteReview.vue'
import AddReviewView from '../views/AddReviewView.vue'
import PlayGamesView from '../views/PlayGamesView.vue'
import UserLists from '../views/UserLists.vue'
import PongView from '../views/PongView.vue'
import AsteroidsView from '../views/AsteroidsView.vue'
import SingleListView from '../views/SingleListView.vue'
Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/lists/user/:userId',
      name: 'UserLists',
      component: UserLists,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/games',
      name: 'games',
      component: Games
    },
    {
      path: '/games/add-game',
      name: 'AddGame',
      component: AddGameView
    },
    {
      path: '/confirm/:reviewId',
      name: 'confirm-delete',
      component: DeleteReview
    },
    {
      path: '/games/:id/add-review',
      name: 'add-review',
      component: AddReviewView
    },
    {
      path: '/play',
      name: 'play-games',
      component: PlayGamesView
    },
    {
      path: '/pong',
      name: 'pong',
      component: PongView
    },
    {
      path: '/asteroids',
      name: 'asteroids',
      component: AsteroidsView
    },
    {
      path: '/list/:id',
      name: 'user-list',
      component: SingleListView
    },
    {
    //KEEP THIS LAST
    path: '/games/:gameId',
    name: 'single-game-view',
    component: SingleGameView
    },


  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
