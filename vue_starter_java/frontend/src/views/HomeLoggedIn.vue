<template>
  <div>
    <div class="nav">
      <router-link class="nav-link" v-bind:to="{ name: 'home' }">Home</router-link>
      <router-link class="nav-link" v-bind:to="{ name: 'createEvent' }">Create Event</router-link>
      <router-link class="nav-link" v-bind:to="{ name: 'login' }">Login</router-link>
      <router-link class="nav-link" v-bind:to="{ name: 'register' }">Register</router-link>
    </div>
    <div class="home">
      <div class="list-of-events">
        <h2>All Events</h2>
        <event-list-logged-in
          :childUserId="userId"
          :isHOME="isHomePage"
          :isLoggedInBool="isLoggedIn"
        ></event-list-logged-in>
      </div>
      <div class="list-of-events">
        <h2>My Past Events</h2>
        <event-list-logged-in :childUserId="userId" :isHOME="isHomePage"></event-list-logged-in>
      </div>
    </div>
  </div>
</template>

<script>
import EventListLoggedIn from "../components/Events/EventListLoggedIn.vue";
import auth from "../auth";

export default {
  components: {
    EventListLoggedIn
  },
  name: "home",
  data() {
    return {
      userDetailURL: "http://localhost:8080/AuthenticationApplication/user/",
      isLoggedIn: true,
      isHomePage: true,
      isAdmin: false,
      userId: null,
      user: {
        username: "",
        password: "",
        role: "",
        id: null
      }
    };
  },
  created() {
    this.userId = this.$route.params.userId;
    console.log("HomeLoggedIn.vue, created", this.userId);
    this.getUser();
  },
  methods: {
    getUser() {
      fetch(this.userDetailURL + this.userId, {
        method: "GET",
        headers: {
          Authorization: "Bearer " + auth.getToken()
        },
        body: JSON.stringify(this.eventData)
      })
        .then(response => {
          console.log(response);
          return response.json();
        })
        .then(jsonUser => {
          this.user = jsonUser;
          if (this.user.role == "admin") {
            this.isAdmin = true;
          }
        })
        .catch(err => console.error(err));
    }
  }
};
</script>

<style scoped>
.list-of-events h2 {
  color: white;
  text-align: center;
}
</style>
