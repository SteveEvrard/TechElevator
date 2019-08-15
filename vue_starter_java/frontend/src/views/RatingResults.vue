<template>
  <div>
    <div class="nav">
      <router-link class="nav-link" v-bind:to="{ name: 'homeLoggedIn' }">Home</router-link>
      <router-link class="nav-link" v-bind:to="{ name: 'login' }">Login</router-link>
      <router-link class="nav-link" v-bind:to="{ name: 'createEvent' }">Create Event</router-link>
      <router-link class="nav-link" v-bind:to="{ name: 'resetPassword' }">Reset Password</router-link>
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
      eventID: 0
    };
  },
  created() {
    this.eventID = this.$route.params.eventId;
  }
};
</script>