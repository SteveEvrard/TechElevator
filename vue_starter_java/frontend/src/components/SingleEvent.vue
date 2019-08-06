<template>
  <div class="event">
    <h2>{{event.title}}</h2>
    <img src="{{event.imgUrl}}" >
    <h4>Date:{{event.date}} Time:{{event.time}}</h4>
  </div>
</template>

<script>
export default {
  props: {
    event: {
      title: String,
      imgUrl: String
      date: Date,
      time: String,
      location: String,
      info: String
    }
  },
  name: "SingleEvent"
};
</script>