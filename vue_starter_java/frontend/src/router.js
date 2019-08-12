import Vue from 'vue'
import Router from 'vue-router'
import auth from './auth'
import Home from './views/Home.vue'
import Login from './views/Login.vue'
import Register from './views/Register.vue'
import CreateEvent from './views/CreateEvent.vue'
import RatingResults from './views/RatingResults.vue'
import EventResponse from './views/EventResponse.vue'
import EventPage from './views/EventPage.vue'


Vue.use(Router)

function routeEventPage(route) {
  return {
    name: (parseLong(route.params.eventId))
  }
}

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
        requiresAuth: false
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
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/createEvent",
      name: "createEvent",
      component: CreateEvent,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/ratingResults",
      name: "ratingResults",
      component: RatingResults,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/eventResponse",
      name: "eventResponse",
      component: EventResponse,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/eventPage/:eventId",
      // path: "/eventPage",
      name: "eventPage",
      component: EventPage,
      props: routeEventPage,
      meta: {
        requiresAuth: false
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);
  const user = auth.getUser();

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && !user) {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
