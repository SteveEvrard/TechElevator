<template>
  <div>
    <div class="nav">
      <router-link class="nav-link" v-bind:to="{ name: 'homeLoggedIn' }">Home</router-link>
      <router-link :to="{ name: 'logout' }">Logout</router-link>
      <router-link class="nav-link" v-bind:to="{ name: 'register' }">Register</router-link>
    </div>
    <div class="rate-whiskey" id="scroll-to-this">
      <div v-if="readyFor1()">
        <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[0].brand}}</h1>
        <h1 v-if="event.isBlindTasting">Whiskey #1</h1>
        <rate-single-whiskey
          v-on:submitrating="incrementAndLoadHigher()"
          v-bind:rswEvent="event"
          v-bind:whiskey="event.tastingWhiskeys[0]"
        ></rate-single-whiskey>
      </div>
      <div v-if="readyFor2()">
        <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[1].brand}}</h1>
        <h1 v-if="event.isBlindTasting">Whiskey #2</h1>
        <rate-single-whiskey
          v-on:submitrating="incrementAndLoadHigher()"
          v-bind:rswEvent="event"
          v-bind:whiskey="event.tastingWhiskeys[1]"
        ></rate-single-whiskey>
      </div>
      <div v-if="readyFor3()">
        <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[2].brand}}</h1>
        <h1 v-if="event.isBlindTasting">Whiskey #3</h1>
        <rate-single-whiskey
          v-on:submitrating="incrementAndLoadHigher()"
          v-bind:rswEvent="event"
          v-bind:whiskey="event.tastingWhiskeys[2]"
        ></rate-single-whiskey>
      </div>
      <div v-if="readyFor4()">
        <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[3].brand}}</h1>
        <h1 v-if="event.isBlindTasting">Whiskey #4</h1>
        <rate-single-whiskey
          v-on:submitrating="incrementAndLoadHigher()"
          v-bind:rswEvent="event"
          v-bind:whiskey="event.tastingWhiskeys[3]"
        ></rate-single-whiskey>
      </div>
      <div v-if="readyFor5()">
        <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[4].brand}}</h1>
        <h1 v-if="event.isBlindTasting">Whiskey #5</h1>
        <rate-single-whiskey
          v-on:submitrating="incrementAndLoadHigher()"
          v-bind:rswEvent="event"
          v-bind:whiskey="event.tastingWhiskeys[4]"
        ></rate-single-whiskey>
      </div>
      <div v-if="readyFor6()">
        <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[5].brand}}</h1>
        <h1 v-if="event.isBlindTasting">Whiskey #6</h1>
        <rate-single-whiskey
          v-on:submitrating="incrementAndLoadHigher()"
          v-bind:rswEvent="event"
          v-bind:whiskey="event.tastingWhiskeys[5]"
        ></rate-single-whiskey>
      </div>
      <div v-if="readyFor7()">
        <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[6].brand}}</h1>
        <h1 v-if="event.isBlindTasting">Whiskey #7</h1>
        <rate-single-whiskey
          v-on:submitrating="incrementAndLoadHigher()"
          v-bind:rswEvent="event"
          v-bind:whiskey="event.tastingWhiskeys[6]"
        ></rate-single-whiskey>
      </div>
      <div v-if="readyFor8()">
        <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[7].brand}}</h1>
        <h1 v-if="event.isBlindTasting">Whiskey #8</h1>
        <rate-single-whiskey
          v-on:submitrating="incrementAndLoadHigher()"
          v-bind:rswEvent="event"
          v-bind:whiskey="event.tastingWhiskeys[7]"
        ></rate-single-whiskey>
      </div>
      <div v-if="readyFor9()">
        <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[8].brand}}</h1>
        <h1 v-if="event.isBlindTasting">Whiskey #9</h1>
        <rate-single-whiskey
          v-on:submitrating="incrementAndLoadHigher()"
          v-bind:rswEvent="event"
          v-bind:whiskey="event.tastingWhiskeys[8]"
        ></rate-single-whiskey>
      </div>
      <div v-if="readyFor10()">
        <h1 v-if="!event.isBlindTasting">{{event.tastingWhiskeys[9].brand}}</h1>
        <h1 v-if="event.isBlindTasting">Whiskey #10</h1>
        <rate-single-whiskey
          v-on:submitrating="incrementAndLoadHigher()"
          v-bind:rswEvent="event"
          v-bind:whiskey="event.tastingWhiskeys[9]"
        ></rate-single-whiskey>
      </div>
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
      submitReset: false,
      submitted: 0,
      eventId: null,
      user: null
    };
  },
  created() {
    this.eventId = this.$route.params.eventId;
    this.getEventDetails();
    location.hash = "#" + "scroll-to-this";
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
    },
    incrementAndLoadHigher() {
      location.hash = "#" + "scroll-to-this";
      this.submitted += 1;
      if (this.allRated()) {
        let eventId = this.event.eventId;
        this.$router.push({ name: "eventDetailPage", params: { eventId } });
      }
    },
    readyFor1() {
      if (this.submitted == 0) {
        return true;
      } else {
        return false;
      }
    },
    readyFor2() {
      if (this.submitted == 1 && this.event.tastingWhiskeys.length > 1) {
        return true;
      } else {
        return false;
      }
    },
    readyFor3() {
      if (this.submitted == 2 && this.event.tastingWhiskeys.length > 2) {
        return true;
      } else {
        return false;
      }
    },
    readyFor4() {
      if (this.submitted == 3 && this.event.tastingWhiskeys.length > 3) {
        return true;
      } else {
        return false;
      }
    },
    readyFor5() {
      if (this.submitted == 4 && this.event.tastingWhiskeys.length > 4) {
        return true;
      } else {
        return false;
      }
    },
    readyFor6() {
      if (this.submitted == 5 && this.event.tastingWhiskeys.length > 5) {
        return true;
      } else {
        return false;
      }
    },
    readyFor7() {
      if (this.submitted == 6 && this.event.tastingWhiskeys.length > 6) {
        return true;
      } else {
        return false;
      }
    },
    readyFor8() {
      if (this.submitted == 7 && this.event.tastingWhiskeys.length > 7) {
        return true;
      } else {
        return false;
      }
    },
    readyFor9() {
      if (this.submitted == 8 && this.event.tastingWhiskeys.length > 8) {
        return true;
      } else {
        return false;
      }
    },
    readyFor10() {
      if (this.submitted == 9 && this.event.tastingWhiskeys.length > 9) {
        return true;
      } else {
        return false;
      }
    },
    allRated() {
      if (this.submitted == this.event.tastingWhiskeys.length) {
        return true;
      } else {
        return false;
      }
    }
  }
};
</script>

<style scoped>
h1 {
  color: white;
  text-align: center;
}
.rate-whiskey {
  background-image: url("../assets/img/whiskey-glasses.jpg");
  background-repeat: no-repeat;
  background-size: 150%;
  padding: 5%;
  background-attachment: fixed;
}
</style>


