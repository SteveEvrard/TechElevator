<template>
  <div>
    <div class="home">
      <h1>Home</h1>
      <p>You must be authenticated to see this</p>
      CheckIn
    </div>
    <div>
      <event-list :apiURL="API_URL">{{API_URL}}</event-list>
    </div>
  </div>
</template>

<script>
<<<<<<< HEAD
import EventList from "../components/EventList.vue";

=======
// import EventList from "../components/EventList.vue";
import checkIn from "../components/CheckIn.vue";
>>>>>>> 35cefe4ea252077ad803c241b9ff3fe70b1efbe9
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
