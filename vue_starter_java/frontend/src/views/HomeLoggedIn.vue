<template>
  <div class="home">
    <div class="list-of-events">
      <event-list-logged-in
        :childUserId="userId"
        :isHOME="isHomePage"
        :apiURL="API_URL"
        :isLoggedInBool="isLoggedIn"
      ></event-list-logged-in>
    </div>
    <div class="list-of-events">
      <event-list :childUserId="userId" :isHOME="isHomePage" :apiUrlByUser="byUserAPI_URL"></event-list>
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
      API_URL: "http://localhost:8080/AuthenticationApplication/api/events",
      byUserAPI_URL:
        "http://localhost:8080/AuthenticationApplication/api/users/",
      userDetailURL:
        "http://localhost:8080/AuthenticationApplication/api/user/",
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
    this.getUser();
  },
  methods: {
    getUser() {
      fetch(this.userDetailURL + this.userId, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
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

<style>
.home {
  background: #f1f1f1;
  display: flex;
  justify-content: center;
}
.list-of-events {
  background-color: #2e4d58;
  padding: 1px;
  width: 90%;
  border-radius: 10px;
  margin: 5%;
  box-shadow: -2px 10px 18px -4px rgba(0, 0, 0, 0.75);
}
.slider {
  height: 20px;
  width: 300px;
  padding: 5%;
  margin: 5%;
  color: white;
}
</style>
