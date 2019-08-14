<template>
  <div class="event">
    <div v-if="submitted == 0" v-on:submitrating="submitted += 1">
      <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[0].brand}}</h1>
      <h1 v-if="event.isBlindTasting">Whiskey #1</h1>
      <rate-single-whiskey
        v-if="event.tastingWhiskeys.length > 0"
        v-bind:rswEvent="event"
        v-bind:whiskey="event.tastingWhiskeys[0]"
      ></rate-single-whiskey>
    </div>
    <div v-if="submitted == 1" v-on:submitrating="submitted += 1">
      <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[1].brand}}</h1>
      <h1 v-if="event.isBlindTasting">Whiskey #2</h1>
      <rate-single-whiskey
        v-if="event.tastingWhiskeys.length > 1"
        v-bind:rswEvent="event"
        v-bind:whiskey="event.tastingWhiskeys[1]"
      ></rate-single-whiskey>
    </div>
    <div v-if="submitted == 2" v-on:submitrating="submitted += 1">
      <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[2].brand}}</h1>
      <h1 v-if="event.isBlindTasting">Whiskey #3</h1>
      <rate-single-whiskey
        v-if="event.tastingWhiskeys.length > 2"
        v-bind:rswEvent="event"
        v-bind:whiskey="event.tastingWhiskeys[2]"
      ></rate-single-whiskey>
    </div>
    <div v-if="submitted == 3" v-on:submitrating="submitted += 1">
      <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[3].brand}}</h1>
      <h1 v-if="event.isBlindTasting">Whiskey #4</h1>
      <rate-single-whiskey
        v-if="event.tastingWhiskeys.length > 3"
        v-bind:rswEvent="event"
        v-bind:whiskey="event.tastingWhiskeys[3]"
      ></rate-single-whiskey>
    </div>
    <div v-if="submitted == 4" v-on:submitrating="submitted += 1">
      <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[4].brand}}</h1>
      <h1 v-if="event.isBlindTasting">Whiskey #5</h1>
      <rate-single-whiskey
        v-if="event.tastingWhiskeys.length > 4"
        v-bind:rswEvent="event"
        v-bind:whiskey="event.tastingWhiskeys[4]"
      ></rate-single-whiskey>
    </div>
    <div v-if="submitted == 5" v-on:submitrating="submitted += 1">
      <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[5].brand}}</h1>
      <h1 v-if="event.isBlindTasting">Whiskey #6</h1>
      <rate-single-whiskey
        v-if="event.tastingWhiskeys.length > 5"
        v-bind:rswEvent="event"
        v-bind:whiskey="event.tastingWhiskeys[5]"
      ></rate-single-whiskey>
    </div>
    <div v-if="submitted == 6" v-on:submitrating="submitted += 1">
      <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[6].brand}}</h1>
      <h1 v-if="event.isBlindTasting">Whiskey #7</h1>
      <rate-single-whiskey
        v-if="event.tastingWhiskeys.length > 6"
        v-bind:rswEvent="event"
        v-bind:whiskey="event.tastingWhiskeys[6]"
      ></rate-single-whiskey>
    </div>
    <div v-if="submitted == 7" v-on:submitrating="submitted += 1">
      <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[7].brand}}</h1>
      <h1 v-if="event.isBlindTasting">Whiskey #8</h1>
      <rate-single-whiskey
        v-if="event.tastingWhiskeys.length > 7"
        v-bind:rswEvent="event"
        v-bind:whiskey="event.tastingWhiskeys[7]"
      ></rate-single-whiskey>
    </div>
    <div v-if="submitted == 8" v-on:submitrating="submitted += 1">
      <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[8].brand}}</h1>
      <h1 v-if="event.isBlindTasting">Whiskey #9</h1>
      <rate-single-whiskey
        v-if="event.tastingWhiskeys.length > 8"
        v-bind:rswEvent="event"
        v-bind:whiskey="event.tastingWhiskeys[8]"
      ></rate-single-whiskey>
    </div>
    <div v-if="submitted == 9" v-on:submitrating="submitted += 1">
      <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[9].brand}}</h1>
      <h1 v-if="event.isBlindTasting">Whiskey #10</h1>
      <rate-single-whiskey
        v-if="event.tastingWhiskeys.length > 9"
        v-bind:rswEvent="event"
        v-bind:whiskey="event.tastingWhiskeys[9]"
      ></rate-single-whiskey>
    </div>
  </div>
</template>

<script>
import RateSingleWhiskey from "@/components/Whiskey/RateSingleWhiskey.vue";
import auth from "../auth";

export default {
  components: {
    RateSingleWhiskey
  },
  data() {
    return {
      hasCheckedIn: false,
      API_URL: "http://localhost:8080/AuthenticationApplication/api/event/",
      event: {
        eventId: 0,
        title: "",
        // imgUrl: "",
        date: new Date(),
        time: "",
        location: "",
        info: "",
        tastingWhiskeys: [],
        isPrivate: true,
        isBlindTasting: true
      },
      submitted: 0,
      whiskeyCount: 0,
      eventId: null
    };
  },
  created() {
    this.eventId = this.$route.params.eventId;
    this.getEventDetails();
  },
  methods: {
    getEventDetails() {
      fetch(this.API_URL + this.eventId, {
        method: "GET",
        headers: {
          Authorization: "Bearer " + auth.getToken()
        }
      })
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

<style scoped>
h1 {
  color: white;
}
</style>


