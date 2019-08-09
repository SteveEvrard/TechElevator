<template>
  <div>
    <div class="home">
      <img src="@/assets/img/TKWS_Black Logo.png">
    </div>
    <div class="list-of-events">
      <event-list :apiURL="API_URL"></event-list>
      <check-in></check-in>
    </div>
  </div>
</template>

<script>
import EventList from "../components/EventList.vue";

export default {
  components: {
    EventList
  },
  name: "home",
  data() {
    return {
      API_URL: "http://localhost:8080/AuthenticationApplication/api/events"
    };
  }
};
</script>

<style>
.home img {
  max-width: 800px;
}
.list-of-events {
  background-color: #2e4d58;
  padding: 1px;
  width: 80%;
  height: fit-content;
  border-radius: 5px;
  margin: 5%;
  box-shadow: -2px 10px 18px -4px rgba(0, 0, 0, 0.75);
}
</style>
