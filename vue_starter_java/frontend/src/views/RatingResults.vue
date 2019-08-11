<template>
  <single-whiskey-rating :ratingApiURL="API_URL" :apiURLWhiskey="API_URL_Whiskey"></single-whiskey-rating>
</template>

<script>
import WhiskeyRatingList from "../components/WhiskeyRatingList.vue";

export default {
  components: {
    WhiskeyRatingList
  },
  name: "ratingResults",
  data() {
    return {
      API_URL:
        "http://localhost:8080/AuthenticationApplication/api/event/{eventId}/whiskeyRating"
    };
  }
};
</script>