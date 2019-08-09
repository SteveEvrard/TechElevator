<template>
  <tile-format class="event">
    <h2>{{event.title}}</h2>
    <!-- <img src="{{event.imgUrl}}"> -->

    <ul>
      <li>
        <h4>Date:</h4>
        <p>{{event.date.month}} {{event.date.dayOfMonth}}, {{event.date.year}}</p>
      </li>

      <li>
        <h4>Time:</h4>
        <p>{{event.time}}</p>
      </li>
      <li>
        <h4>Location:</h4>
        <p>{{event.location}}</p>
      </li>
      <li>
        <p>{{event.eventDescription}}</p>
      </li>
    </ul>
  </tile-format>
</template>

<script>
import TileFormat from "../components/TileFormat.vue";

export default {
  components: {
    TileFormat
  },
  props: {
    event: {
      title: String,
      imgUrl: String,
      date: new Date(),
      time: String,
      location: String,
      info: String
    }
  },
  name: "SingleEvent"
};
</script>

<style scoped>
h4,
p {
  display: inline-block;
}
h2 {
  text-align: center;
}
.event {
  padding: 2%;
  background-color: white;
  width: 320px;
  min-height: 320px;
}
</style>