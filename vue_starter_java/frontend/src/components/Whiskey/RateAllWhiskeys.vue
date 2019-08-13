<template>
  <div>
    <h1>Rate the Whiskey!</h1>
    <div v-for="item in tastingWhiskeys" v-bind:key="item">
      <rate-single-whiskey v-bind:rswEventData="rawEventData" v-bind:whiskey="item"></rate-single-whiskey>
    </div>
  </div>
</template>

<script>
import RateSingleWhiskey from "@/components/Whiskey/RateSingleWhiskey.vue";

export default {
  components: {
    RateSingleWhiskey
  },
  prop: {
    eventData: {
      title: String,
      // eventImageURL: "",
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
    whiskey: {
      brand: String,
      whiskeyId: Number
    }
  },
  data() {
    return {
      jamison: {
        brand: "Jamison 18 Year Select",
        price: 40
      },
      writer: {
        brand: "Writer's Tears",
        price: 45
      },
      tastingWhiskeys: [this.jamison, this.writer],
      API_URL: "http://localhost:8080/AuthenticationApplication/api/whiskeys",
      rawEventId: this.eventData.eventId,
      eventData: {
        title: String,
        // eventImageURL: "",
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
      }
    };
  },
  created() {
    this.eventData = eventData;
  },
  methods: {
    saveWhiskey() {
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
          }
        })
        .catch(err => console.error(err));
    }
  }
};
</script>

<style>
.create {
  text-align: center;
  align-items: center;
  align-self: center;
}
</style>




