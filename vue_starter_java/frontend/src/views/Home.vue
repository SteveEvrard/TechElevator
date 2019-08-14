<template>
  <div class="home">
    <div class="list-of-events">
      <event-list :isHOME="isHomePage" :apiURL="API_URL" :isLoggedIn="isLoggedIn"></event-list>
    </div>
  </div>
</template>

<script>
import EventList from "../components/Events/EventList.vue";

export default {
  components: {
    EventList
  },
  name: "home",
  data() {
    return {
      API_URL: "http://localhost:8080/AuthenticationApplication/api/events",
      isLoggedIn: false,
      isHomePage: true
    };
  }
};
</script>

<style>
.home {
  background-image: url("../assets/img/whiskey-glasses.jpg");
  background-repeat: no-repeat;
  background-size: 120%;
  background-attachment: fixed;
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
}
.list-of-events {
  background-color: rgba(0, 0, 0, 0.6);
  padding: 1px;
  width: 90%;
  border-radius: 10px;
  margin: 5%;
  box-shadow: -2px 10px 18px -4px rgba(0, 0, 0, 0.75);
}
</style>
