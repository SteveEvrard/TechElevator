<template>
  <tile-format class="single-event">
    <div v-on:click="showdetail(event)">
      <h2>{{event.title}}</h2>
      <!-- <img src="{{event.imgUrl}}"> -->
      <div id="line-div">
        <h4>When:</h4>
        <p>{{event.date[1]}} / {{event.date[2]}} / {{event.date[0]}} at {{event.time}}</p>
        <p>{{event.eventId}}</p>
      </div>
      <div id="line-div">
        <h4>Where:</h4>
        <p>{{event.location}}</p>
      </div>
      <div v-if="!isHomePage">
        <div id="description">
          <h4>About the Event</h4>
          <p>{{event.eventDescription}}</p>
        </div>
        <h4 id="table-label">Your Ratings:</h4>
        <table class="table" v-if="!isHomePage">
          <tr>
            <th>Whiskey</th>
            <th>Taste</th>
            <th>Smell</th>
            <th>Color</th>
            <th>Finish</th>
            <th>Price</th>
            <th>Overall</th>
          </tr>
          <tr v-for="rating in whiskeyRatingList" :key="rating">
            <td>{{rating.whiskey.brand}}</td>
            <td>{{rating.tasteRating}}</td>
            <td>{{rating.smellRating}}</td>
            <td>{{rating.colorRating}}</td>
            <td>{{rating.finishRating}}</td>
            <td>{{rating.priceRating}}</td>
            <td>{{rating.overallRating}}</td>
          </tr>
        </table>
      </div>
    </div>
  </tile-format>
</template>

<script>
import TileFormat from "@/components/Formatting/TileFormat.vue";

export default {
  components: {
    TileFormat
  },
  props: {
    isHOME: Boolean,
    event: {
      eventId: Number,
      title: String,
      // imgUrl: String,
      date: new Date(),
      time: String,
      location: String,
      eventDescription: String,
      isPrivate: Boolean,
      isBlindTasting: Boolean
    }
  },
  data() {
    return {
      isHomePage: Boolean(this.isHOME),
      isLoggedIn: true,
      hasRated: false,
      whiskeyRating: {
        whiskey: {
          brand: "",
          price: 0
        },
        whiskeyId: 0,
        userId: 0,
        eventId: 0,
        tasteRating: 0,
        smellRating: 0,
        colorRating: 0,
        bodyRating: 0,
        finishRating: 0,
        priceRating: 0,
        overallRating: 0
      },
      whiskeyRatingList: [],
      eventId: 0,
      thisEvent: this.event
    };
  },
  name: "SingleEvent",
  created() {
    this.eventId = event.eventId;
    console.log(this.eventId);
  },
  methods: {
    showdetail(event) {
      let eventId = this.event.eventId;
      this.$router.push({ name: "eventDetailPage", params: { eventId } });
    }
  }
};
</script>

<style>
</style>