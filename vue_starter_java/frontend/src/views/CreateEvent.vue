<template>
  <form class="event">
    <label for="titleOfEvent">Title:</label>
    <input type="text" id="titleOfEvent" v-model="eventTitle">
    <label for="privateEventBoolean">Is this a private event?</label>
    <input id="privateEventBoolean" type="checkbox" v-model="isPrivateEvent">
    {{isPrivateEvent ? "yes" : "no"}}
    <datepicker v-model="state.date" name="uniquename"></datepicker>
    <label for="locationOfEvent">Location:</label>
    <input type="text" id="locationOfEvent" v-model="eventLocation">
  </form>
</template>

<script>
import Datepicker from "vuejs-datepicker";

export default {
  components: {
    Datepicker
  },

  data() {
    return {
      userData: {
        eventTitle: "",
        isPrivateEvent: true,
        state: {
          date: new Date(2016, 9, 16)
        },
        eventLocation: ""
      }
    };
  }
};
</script>
