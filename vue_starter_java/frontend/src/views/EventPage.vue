<template>
  <div class="event">
    <single-event v-bind:event="event"></single-event>
    <check-in v-if(!hasCheckedIn) @checked="saveUserAndEvent"></check-in>
  </div>
</template>

<script>
import SingleEvent from "../components/SingleEvent.vue";
import CheckIn from "../components/CheckIn.vue";
export default {
  components: {
    SingleEvent,
    CheckIn
  },
  props: {
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
  },
  data() {
    return {
      hasCheckedIn: false
    };
  },
  methods: {
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
            this.$emit("showReviews");
            this.hasCheckedIn = true;
          }
        })
        .catch(err => console.error(err));
    }
  }
};
</script>

<style>
</style>
