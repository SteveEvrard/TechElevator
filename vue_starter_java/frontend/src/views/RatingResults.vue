<template>
  <div>
    <div class="nav">
      <router-link class="nav-link" v-bind:to="{ name: 'home' }">Home</router-link>
      <router-link class="nav-link" v-bind:to="{ name: 'createEvent' }">Create Event</router-link>
      <router-link class="nav-link" v-bind:to="{ name: 'login' }">Login</router-link>
      <router-link class="nav-link" v-bind:to="{ name: 'register' }">Register</router-link>
    </div>
    <whiskey-rating-list :eventId="eventID"></whiskey-rating-list>
  </div>
</template>

<script>
import WhiskeyRatingList from "@/components/Whiskey/WhiskeyRatingList.vue";
import auth from "../auth";

export default {
  components: {
    WhiskeyRatingList
  },
  name: "ratingResults",

  data() {
    return {
      eventID: Number
    };
  },
  created() {
    this.eventID = this.$route.params.eventId;
  }
};
</script>