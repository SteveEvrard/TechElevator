<template>
  <whiskey-rating-list :ratingApiURL="API_URL" :apiURLWhiskey="API_URL_Whiskey"></whiskey-rating-list>
</template>

<script>
import WhiskeyRatingList from "@/components/Whiskey/WhiskeyRatingList.vue";

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