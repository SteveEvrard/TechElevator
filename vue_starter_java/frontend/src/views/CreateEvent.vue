<template>
  <div class="form">
    <form class="event">
      <div>
        <label for="titleOfEvent">Title:</label>
        <input type="text" id="titleOfEvent" v-model="userData.eventTitle">
      </div>
      <div>
        <label for="privateEventBoolean">Is this a private event?</label>
        <input id="privateEventBoolean" type="checkbox" v-model="userData.isPrivateEvent">
        {{userData.isPrivateEvent ? "yes" : "no"}}
      </div>
      <div>
        <datepicker v-model="userData.state.date" name="uniquename">
          <span slot="afterDateInput" class="animated-placeholder">Choose a Date</span>
        </datepicker>
      </div>
      <div>
        <label for="locationOfEvent">Location:</label>
        <input type="text" id="locationOfEvent" v-model="userData.eventLocation">
      </div>
      <div>
        <label for="descriptionOfEvent">Additional Information:</label>
        <textarea id="descriptionOfEvent" v-model="userData.eventDescription"></textarea>
      </div>
      <div>
        <label for="blindEventBoolean">Is this a private event?</label>
        <input id="blindEventBoolean" type="checkbox" v-model="userData.isBlindEvent">
        {{userData.isBlindEvent ? "yes" : "no"}}
      </div>
    </form>
  </div>
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
          date: new Date(2019, 7, 6),
          placeholder: "Select a date."
        },
        eventLocation: "",
        eventDescription: "",
        isBlindEvent: true
      }
    };
  }
};
</script>
