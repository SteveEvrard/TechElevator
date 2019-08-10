<template>
  <single-whiskey-rating></single-whiskey-rating>
</template>

<script>
import SingleWhiskeyRating from "../components/SingleWhiskeyRating.vue";

export default {
  components: {
    SingleWhiskeyRating
  },
  name: "ratingResults",
  data() {
    return {
      API_URL: "http://localhost:8080/AuthenticationApplication/api/whiskeys"
    };
  }
};
</script>