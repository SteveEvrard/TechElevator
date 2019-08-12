<template>
  <div class="event">
    <rate-all-whiskeys v-bind:eventData="event"></rate-all-whiskeys>
  </div>
</template>

<script>
import RateAllWhiskeys from "@/components/Whiskey/RateAllWhiskeys.vue";

export default {
  components: {
    RateAllWhiskeys
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
        location: String,
        info: String,
        // tastingWhiskeys: Array,
        isPrivate: Boolean,
        isBlindTasting: Boolean
      }
    };
  },
  created() {
    this.event.eventId = this.$route.params.eventId;
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
    }
  }
};
</script>


