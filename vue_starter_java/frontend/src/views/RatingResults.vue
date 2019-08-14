<template>
  <whiskey-rating-list :eventId="eventID"></whiskey-rating-list>
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