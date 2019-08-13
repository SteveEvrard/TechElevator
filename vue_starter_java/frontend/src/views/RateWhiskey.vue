<template>
  <div class="event">
    <div v-for="item in tastingWhiskeys" v-bind:key="item">
      <h1 v-if="!event.isBlindTasting">{{item.brand}}</h1>
      <h1 v-if="event.isBlindTasting">Whiskey #{{getWhiskeyCount()}}</h1>
      <rate-single-whiskey v-bind:rswEvent="event" v-bind:whiskey="item"></rate-single-whiskey>
    </div>
  </div>
</template>

<script>
import RateSingleWhiskey from "@/components/Whiskey/RateSingleWhiskey.vue";

export default {
  components: {
    RateSingleWhiskey
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
      },
      whiskeyCount: 0,
      eventId: null,
      jamison: {
        brand: "Jamison 18 Year Select",
        price: 40
      },
      writer: {
        brand: "Writer's Tears",
        price: 45
      },
      tastingWhiskeys: [this.jamison, this.writer]
    };
  },
  created() {
    this.eventId = this.$route.params.eventId;
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
    },
    getWhiskeyCount() {
      this.whiskeyCount += 1;
      return this.whiskeyCount;
    }
  }
};
</script>


