<template>
  <form class="event">
    <label for="titleOfEvent">Title:</label>
    <input type="text" id="titleOfEvent" v-model="userData.eventTitle">
    <label for="privateEventBoolean">Is this a private event?</label>
    <input id="privateEventBoolean" type="checkbox" v-model="userData.isPrivateEvent">
    {{userData.isPrivateEvent ? "yes" : "no"}}
    <datepicker v-model="userData.state.date" name="uniquename">
      <span slot="afterDateInput" class="animated-placeholder">Choose a Date</span>
    </datepicker>
    <label for="locationOfEvent">Location:</label>
    <input type="text" id="locationOfEvent" v-model="userData.eventLocation">
  </form>
</template>

<script>
import Datepicker from "vuejs-datepicker";
import { en } from "vuejs-datepicker/dist/locale";

export default {
  components: {
    Datepicker
  },

  data() {
    return {
      en: en,
      userData: {
        eventTitle: "",
        isPrivateEvent: true,
        state: {
          date: new Date(2019, 9, 16),
          placeholder: "Select a date."
        },
        eventLocation: ""
      }
    };
  }
};
</script>
