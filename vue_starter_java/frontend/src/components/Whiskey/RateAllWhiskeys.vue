<template>
  <div>
    <h1>Rate the Whiskey!</h1>
    <div v-for="whiskey in whiskeyList" v-bind:key="whiskey">
      <rate-single-whiskey v-on:finished="isVisible=false" v-bind:whiskey="whiskey"></rate-single-whiskey>
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
      title: "",
      // eventImageURL: "",
      isPrivate: true,
      date: new Date(),
      time: "",
      location: "",
      eventDescription: "",
      isBlindTasting: true
    },
    whiskey: {
      brand: String,
      whiskeyId: Number
    }
  },
  created() {
    this.whiskeyList.push(this.whiskey1);
    this.whiskeyList.push(this.whiskey2);
    this.whiskeyList.push(this.whiskey3);
  },
  data() {
    return {
      whiskeyList: [],
      whiskey1: {
        brand: "Jamisons",
        whiskeyId: 1
      },
      whiskey2: {
        brand: "Three Gingers",
        whiskeyId: 2
      },
      whiskey3: {
        brand: "Writer's Tears",
        whiskeyId: 3
      },
      apiURLEvent: "http://localhost:8080/AuthenticationApplication/api/events",
      API_URL: "http://localhost:8080/AuthenticationApplication/api/whiskeys"
    };
  },
  methods: {
    saveEvent() {
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




