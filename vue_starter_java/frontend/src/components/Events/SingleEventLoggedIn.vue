<template>
  <tile-format class="single-event">
    <div v-on:click="showdetail(event)">
      <h2>{{event.title}}</h2>
      <!-- <img src="{{event.imgUrl}}"> -->
      <div id="line-div">
        <h4>When:</h4>
        <p>{{event.date[1]}} / {{event.date[2]}} / {{event.date[0]}} at {{event.time}}</p>
      </div>
      <div id="line-div">
        <h4>Where:</h4>
        <p>{{event.location}}</p>
      </div>
      <div id="line-div">
        <p v-if="!isHomePage">{{event.eventDescription}}</p>
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
      isLoggedIn: true
    };
  },
  name: "SingleEvent",
  methods: {
    showdetail(event) {
      let eventId = this.event.eventId;
      this.$router.push({ name: "eventDetailPage", params: { eventId } });
    }
  }
};
</script>

<style>
.single-event h4 {
  margin: 10px 0px 5px 0px;
  font-size: 1.2em;
  font-weight: 500px;
  padding-right: 10px;
  display: inline-block;
}
.single-event p {
  margin: 0px;
  display: inline-block;
}
.single-event h2 {
  text-align: center;
  margin: 10px;
  font-family: "Great Vibes", sans-serif;
  font-size: 2em;
  font-weight: 300px;
}
.single-event {
  padding: 5px;
  background-color: white;
  width: 300px;
  height: 200px;
}

#line-div {
  width: 100%;
  display: block;
  padding: 5px 20px 5px 20px;
}
</style>