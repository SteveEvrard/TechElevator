<template>
  <div>
    <h1>Rate the Whiskey!</h1>
    <div v-for="item in tastingWhiskeys" v-bind:key="item">
      <rate-single-whiskey v-bind:whiskey="item"></rate-single-whiskey>
    </div>

    <h4>
      <button type="submit">Submit your ratings!</button>
    </h4>
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
      tastingWhiskeys: Array
    },
    whiskey: {
      brand: String,
      whiskeyId: Number
    }
  },
  data() {
    return {
      API_URL: "http://localhost:8080/AuthenticationApplication/api/whiskeys"
    };
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
            this.$router.push("/");
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




