<template>
  <tile-format class="single-event">
    <div>
      <h2>{{event.title}}</h2>
      <!-- <img src="{{event.imgUrl}}"> -->

      <ul>
        <li>
          <h4>Date:</h4>
          <p>{{event.date[1]}} / {{event.date[2]}} / {{event.date[0]}}</p>
        </li>

        <li>
          <h4>Time:</h4>
          <p>{{event.time}}</p>
        </li>
        <li>
          <h4>Location:</h4>
          <p>{{event.location}}</p>
        </li>
        <li v-if="!isHomePage">
          <p>{{event.eventDescription}}</p>
        </li>
      </ul>
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
