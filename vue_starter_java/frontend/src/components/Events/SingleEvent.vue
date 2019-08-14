<template>
  <tile-format class="single-event">
    <h2>{{event.title}}</h2>
    <!-- <img src="{{event.imgUrl}}"> -->
    <div id="line-div">
      <h4>When:</h4>
      <p>{{event.date[1]}} / {{event.date[2]}} / {{event.date[0]}} at {{event.time}}</p>
    </div>
    <div id="line-div">
      <h4>Where:</h4>
      <p>{{event.location}}</p>
      <p v-if="!isHomePage">{{event.eventDescription}}</p>
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
      isHomePage: Boolean(this.isHOME)
    };
  },
  name: "SingleEvent"
};
</script>
