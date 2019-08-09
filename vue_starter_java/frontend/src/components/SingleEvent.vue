<template>
  <tile-format class="event">
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
  // parseJsonDate(jsonDate) {
  //   var fullDate = new Date(parseInt(jsonDate.substr(6)));
  //   var twoDigitMonth = fullDate.getMonth() + 1 + "";
  //   if (twoDigitMonth.length == 1) twoDigitMonth = "0" + twoDigitMonth;

  //   var twoDigitDate = fullDate.getDate() + "";
  //   if (twoDigitDate.length == 1) twoDigitDate = "0" + twoDigitDate;
  //   var currentDate =
  //     twoDigitMonth + "/" + twoDigitDate + "/" + fullDate.getFullYear();

  //   return currentDate;
  // },
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