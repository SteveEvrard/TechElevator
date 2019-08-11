<template>
  <tile-format class="single-event" v-on:click="showdetail(event)">
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
      <li v-if(!isHome)>
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
    isLoggedIn: Boolean,
    isHOME: Boolean,
    event: {
      title: String,
      imgUrl: String,
      date: new Date(),
      time: String,
      location: String,
      info: String
    }
  },
  name: "SingleEvent",
  method: {
    showdetail(event) {}
  }
};
</script>

<style scoped>
h4,
p {
  display: inline-block;
}
p {
  margin-right: 8%;
}
h2 {
  text-align: center;
}
.single-event {
  padding: 2%;
  background-color: white;
  width: 320px;
  min-height: 320px;
}
</style>