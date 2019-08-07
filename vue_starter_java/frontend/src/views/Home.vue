<template>
  <div>
    <div class="home">
      <h1>Home</h1>
      <p>You must be authenticated to see this</p>
    </div>
    <event-list :apiURL="API_URL"/>
  </div>
</template>

<script>
// import EventList from "../components/EventList.vue";

export default {
  // components: {
  //   EventList
  // },
  name: "home",
  data() {
    return {
      API_URL: "http://localhost:8080/whiskey-events-api/api/events"
    };
  }
};
</script>
