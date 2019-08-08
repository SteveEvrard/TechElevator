<template>
  <div class="event">
    <h2>{{event.title}}</h2>
    <!-- <img src="{{event.imgUrl}}"> -->
    <h4>Date:</h4>
    <p>{{event.date.month}} {{event.date.dayOfMonth}}, {{event.date.year}}</p>
    <h4>Time:</h4>
    <p>{{event.time.hour}}</p>
    <h4>Location:</h4>
    <p>{{event.location}}</p>
    <p>{{event.info}}</p>
  </div>
</template>

<script>
export default {
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

<style>
.event {
  padding: 2%;
  margin: 2%;
}
</style>