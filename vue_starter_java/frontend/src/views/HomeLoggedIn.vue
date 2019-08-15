<template>
  <div>
    <div v-if="!isAdmin" class="nav">
      <router-link class="nav-link" v-bind:to="{ name: 'homeLoggedIn' }">Home</router-link>
      <router-link class="nav-link" :to="{ name: 'logout' }">Logout</router-link>
    </div>
    <div v-if="isAdmin" class="admin-nav">
      <router-link class="admin-nav-link" v-bind:to="{ name: 'homeLoggedIn' }">Home</router-link>
      <router-link class="admin-nav-link" :to="{ name: 'logout' }">Logout</router-link>
      <router-link class="admin-nav-link" v-bind:to="{ name: 'createEvent' }">Create Event</router-link>
      <router-link class="admin-nav-link" v-bind:to="{ name: 'resetPassword' }">Reset Password</router-link>
    </div>
    <div class="home">
      <download-excel
        class="download-button"
        :data="json_data"
        :fields="json_fields"
        worksheet="My Worksheet"
        name="ratings.xls"
      >Download all ratings to csv.</download-excel>
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
import Vue from "vue";
import JsonExcel from "vue-json-excel";

Vue.component("downloadExcel", JsonExcel);
import auth from "../auth";

export default {
  components: {
    EventListLoggedIn,
    JsonExcel
  },
  name: "home",
  data() {
    json_fields: {


    }

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
    this.user.role = auth.getUser().rol;
    if (this.user.role == "admin") {
      this.isAdmin = true;
    }
  }
};
</script>

<style scoped>
.list-of-events h2 {
  color: white;
  text-align: center;
}

/* Set a style for the download button */
.download-button {
  text-align: center;
  background-color: #9b5a1a;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  font-size: 1.2em;
  color: black;
  font-weight: bold;
}

/* Add a hover effect for buttons */
.download-button button:hover {
  opacity: 0.8;
}
</style>
