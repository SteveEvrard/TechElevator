<template>
  <div>
    <div class="admin-nav">
      <router-link class="admin-nav-link" v-bind:to="{ name: 'homeLoggedIn' }">Home</router-link>
      <router-link class="admin-nav-link" :to="{ name: 'home' }">Logout</router-link>
      <router-link class="admin-nav-link" v-bind:to="{ name: 'createEvent' }">Create Event</router-link>
      <router-link class="admin-nav-link" v-bind:to="{ name: 'resetPassword' }">Reset Password</router-link>
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