<template>
  <div class="event">
    <single-event v-bind:event="event"></single-event>
    <!-- <check-in v-if(!hasCheckedIn) @checked="saveUserAndEvent"></check-in> -->
    <select-box v-on:click="passEventToRate(event.eventId)">Rate Event</select-box>
    <select-box v-on:click="passEventToDisplay(event.eventId)">Display Ratings</select-box>
  </div>
</template>

<script>
import SingleEvent from "@/components/Events/SingleEvent.vue";
import CheckIn from "../components/CheckIn.vue";
import SelectBox from "@/components/Formatting/SelectBox.vue";

export default {
  components: {
    SingleEvent,
    CheckIn
  },
  data() {
    return {
      hasCheckedIn: false,
      API_URL: "http://localhost:8080/AuthenticationApplication//api/event/",
      eventId: null,
      event: {
        eventId: Number,
        title: String,
        imgUrl: String,
        date: new Date(),
        time: String,
        location: String,
        info: String,
        tastingWhiskeys: Array
      }
    };
  },
  created() {
    this.eventId = this.$route.params.eventId;
    this.getEventDetails();
  },
  methods: {
    getEventDetails() {
      fetch(this.API_URL + this.eventId)
        .then(response => {
          console.log(response);
          return response.json();
        })
        .then(jsonEvent => {
          this.event = jsonEvent;
        })

        .catch(err => console.error(err));
    },
    passEventToRate(eventId) {
      this.$router.push({ name: "eventResponse", params: { eventId } });
    }
  }
};
</script>

<style>
</style>
