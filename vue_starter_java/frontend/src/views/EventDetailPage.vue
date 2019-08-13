<template>
  <div class="event">
    <div class="flex-box">
      <single-event id="this-event" v-bind:event="event"></single-event>
      <check-in v-if="!hasCheckedIn" @checked="saveUserAndEvent"></check-in>
    </div>
    <div
      v-if="hasCheckedIn"
      id="to-next-page"
      class="select-box"
      v-on:click="passEventToRate(event.eventId)"
    >Rate Event</div>
    <div
      class="select-box"
      id="to-next-page"
      v-on:click="passEventToDisplay(event.eventId)"
    >View Ratings</div>
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
      API_URL: "http://localhost:8080/AuthenticationApplication/api/event/",
      event: {
        eventId: Number,
        title: String,
        imgUrl: String,
        date: new Date(),
        time: String,
        eventDescription: String,
        isPrivate: Boolean,
        isBlindTasting: Boolean
        // tastingWhiskeys: Array
      },
      eventId: null
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
    saveUserAndEvent() {
      fetch(this.apiURLEvent, {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(this.eventData)
      })
        .then(response => {
          if (response.ok) {
            hasCheckedIn = true;
          }
        })
        .catch(err => console.error(err));
    },
    passEventToRate(eventId) {
      this.$router.push({ name: "rateWhiskey", params: { eventId } });
    },
    passEventToDisplay(eventId) {
      this.$router.push({ name: "ratingResults", params: { eventId } });
    }
  }
};
</script>

<style>
.flex-box {
  display: flex;
  justify-content: flex-start;
}
#this-event {
  width: 50%;
  height: 50%;
}
#to-next-page {
  display: block;
}
</style>


