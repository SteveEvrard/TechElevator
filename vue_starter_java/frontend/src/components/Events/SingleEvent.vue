<template>
  <tile-format class="single-event" v-on:click="showdetail(event)">
    <h2>{{event.title}}</h2>
    <!-- <img src="{{event.imgUrl}}"> -->

    <ul>
      <li>
        <h1>{{isHOME}}{{isHomePage}}</h1>
        <h4>Date:</h4>
        <p> {{event.date[1]}} / {{event.date[2]}} / {{event.date[0]}}</p>
      </li>

      <li>
        <h4>Time:</h4>
        <p> {{event.time}}</p>
      </li>
      <li>
        <h4>Location:</h4>
        <p> {{event.location}}</p>
      </li>
      <li v-if(isHomePage.equals(false))>
        <p>{{event.eventDescription}}</p>
      </li>
    </ul>
  </tile-format>
</template>

<script>
import TileFormat from "@/components/Formatting/TileFormat.vue";

export default {
  components: {
    TileFormat
  },
  props: {
    isLoggedIn: Boolean,
    isHOME: Boolean,
    event: {
      eventId: Number,
      title: String,
      imgUrl: String,
      date: new Date(),
      time: String,
      location: String,
      info: String,
      isPrivate: Boolean,
      isBlindTasting: Boolean
    }
  },
  data() {
    return {
      isHomePage: Boolean(this.isHOME)
    };
  },
  name: "SingleEvent",
  method: {
    showdetail(event) {
      let eventId = this.event.eventId;
      this.$router.push({ name: "eventPage", params: { eventId } });
    }
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