<template>
  <div>
    <div class="home">
      <h1>Home</h1>
      <p>You must be authenticated to see this</p>
    </div>
    <div>
      <event-list :apiURL="API_URL"></event-list>
      <check-in></check-in>
    </div>
  </div>
</template>

<script>
import EventList from "../components/EventList.vue";


export default {
  components: {
    EventList,
  },
  name: "home",
  data() {
    return {
      API_URL: "http://localhost:8080/AuthenticationApplication/api/events"
    };
  }
};
</script>
