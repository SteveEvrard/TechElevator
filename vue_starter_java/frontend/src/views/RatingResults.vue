<template>
  <whiskey-rating-list :eventId="eventID"></whiskey-rating-list>
</template>

<script>
import WhiskeyRatingList from "@/components/Whiskey/WhiskeyRatingList.vue";

export default {
  components: {
    WhiskeyRatingList
  },
  name: "ratingResults",
  prop: {
    eventId: Number
  },
  data() {
    return {
      eventID: this.eventId
    };
  }
};
</script>