<template>
  <div class="whiskey-list">
    <div v-if="event.tastingWhiskeys[0] != undefined">
      <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[0].brand}}</h1>
      <h1 v-if="event.isBlindTasting">1st Whiskey</h1>
      <single-whiskey-rating
        v-bind:whiskey="tastingWhiskeys[0]"
        v-bind:blind="event.isBlindTasting"
      ></single-whiskey-rating>
    </div>
    <div v-if="event.tastingWhiskeys[1] != undefined">
      <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[1].brand}}</h1>
      <h1 v-if="event.isBlindTasting">2nd Whiskey</h1>
      <single-whiskey-rating
        v-bind:whiskey="event.tastingWhiskeys[1]"
        v-bind:blind="event.isBlindTasting"
      ></single-whiskey-rating>
    </div>
    <div v-if="event.tastingWhiskeys[2] != undefined">
      <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[2].brand}}</h1>
      <h1 v-if="event.isBlindTasting">3rd Whiskey</h1>
      <single-whiskey-rating
        v-bind:whiskey="event.tastingWhiskeys[2]"
        v-bind:blind="event.isBlindTasting"
      ></single-whiskey-rating>
    </div>
    <div v-if="event.tastingWhiskeys[3] != undefined">
      <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[3].brand}}</h1>
      <h1 v-if="event.isBlindTasting">4th Whiskey</h1>
      <single-whiskey-rating
        v-bind:whiskey="event.tastingWhiskeys[3]"
        v-bind:blind="event.isBlindTasting"
      ></single-whiskey-rating>
    </div>
    <div v-if="event.tastingWhiskeys[4] != undefined">
      <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[4].brand}}</h1>
      <h1 v-if="event.isBlindTasting">5th Whiskey</h1>
      <single-whiskey-rating
        v-bind:whiskey="event.tastingWhiskeys[4]"
        v-bind:blind="event.isBlindTasting"
      ></single-whiskey-rating>
    </div>
    <div v-if="event.tastingWhiskeys[5] != undefined">
      <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[5].brand}}</h1>
      <h1 v-if="event.isBlindTasting">6th Whiskey</h1>
      <single-whiskey-rating
        v-bind:whiskey="event.tastingWhiskeys[5]"
        v-bind:blind="event.isBlindTasting"
      ></single-whiskey-rating>
    </div>
    <div v-if="event.tastingWhiskeys[6] != undefined">
      <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[6].brand}}</h1>
      <h1 v-if="event.isBlindTasting">7th Whiskey</h1>
      <single-whiskey-rating
        v-bind:whiskey="event.tastingWhiskeys[6]"
        v-bind:blind="event.isBlindTasting"
      ></single-whiskey-rating>
    </div>
    <div v-if="event.tastingWhiskeys[7] != undefined">
      <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[7].brand}}</h1>
      <h1 v-if="event.isBlindTasting">8th Whiskey</h1>
      <single-whiskey-rating
        v-bind:whiskey="event.tastingWhiskeys[7]"
        v-bind:blind="event.isBlindTasting"
      ></single-whiskey-rating>
    </div>
    <div v-if="event.tastingWhiskeys[8] != undefined">
      <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[8].brand}}</h1>
      <h1 v-if="event.isBlindTasting">9th Whiskey</h1>
      <single-whiskey-rating
        v-bind:whiskey="event.tastingWhiskeys[8]"
        v-bind:blind="event.isBlindTasting"
      ></single-whiskey-rating>
    </div>
    <div v-if="event.tastingWhiskeys[9] != undefined">
      <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[9].brand}}</h1>
      <h1 v-if="event.isBlindTasting">10th Whiskey</h1>
      <single-whiskey-rating
        v-bind:whiskey="event.tastingWhiskeys[9]"
        v-bind:blind="event.isBlindTasting"
      ></single-whiskey-rating>
    </div>
  </div>
</template>

<script>
import SingleEvent from "@/components/Events/SingleEvent.vue";
import CheckIn from "@/components/CheckIn.vue";
import TileFormat from "@/components/Formatting/TileFormat.vue";
import SingleWhiskeyRating from "@/components/Whiskey/SingleWhiskeyRating.vue";
import auth from "@/auth";

export default {
  components: {
    SingleEvent,
    CheckIn,
    TileFormat,
    SingleWhiskeyRating
  },
  name: "WhiskeyRatingList",
  props: {
    eventId: null,
    whiskeys: Array,
    singleWhiskey: {
      brand: String,
      price: Number,
      whiskeyId: Number
    }
  },
  data() {
    return {
      API_URL: "http://localhost:8080/AuthenticationApplication/api/event/",
      whiskeyApiURL:
        "http://localhost:8080/AuthenticationApplication/api/event/",
      event: {
        eventId: Number,
        title: String,
        imgUrl: String,
        date: new Date(),
        time: String,
        eventDescription: String,
        isPrivate: Boolean,
        isBlindTasting: Boolean
        // tastingWhiskeys: Array
      },
      whiskey: {
        brand: String,
        price: String,
        whiskeyId: Number
      }
    };
  },
  created() {
    this.getEventDetails();
  },
  methods: {
    getEventDetails() {
      fetch(this.API_URL + this.eventId, {
        method: "GET",
        headers: {
          "Access-Control-Allow-Origin": "application/json",
          Authorization: "Bearer " + auth.getToken()
        },
        body: JSON.stringify(this.eventData)
      })
        .then(response => {
          console.log(response);
          return response.json();
        })
        .then(jsonEvent => {
          this.event = jsonEvent;
        })
        .catch(err => console.error(err));
    }
  }
};
</script>

<style>
.event-list {
  display: flex;
  justify-content: space-around;
  align-content: flex-start;
  margin: 15px 0px 15px 0px;
  flex-wrap: wrap;
}
li {
  list-style: none;
}
</style>