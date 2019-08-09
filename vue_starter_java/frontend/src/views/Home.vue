<template>
  <div class="home">
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
.list-of-events {
  background-color: #2e4d58;
  padding: 1px;
  width: 80%;
  height: fit-content;
  border-radius: 10px;
  margin: 5%;
  box-shadow: -2px 10px 18px -4px rgba(0, 0, 0, 0.75);
}
</style>
