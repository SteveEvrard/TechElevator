<template>
  <form-format>
    <form class="event">
      <h1>Create a New Event</h1>
      <div>
        <label for="titleOfEvent">Title:</label>
        <input type="text" id="titleOfEvent" v-model="userData.eventTitle">
      </div>
      <div>
        <label for="urlOfEventImage">URL for the event image:</label>
        <input id="rlOfEventImage" type="text" v-model="userData.eventImageURL">
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
        <vue-timepicker format="hh:mm A" :minute-interval="30"></vue-timepicker>
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
        <label for="blindEventBoolean">Is this a blind tasting?</label>
        <input id="blindEventBoolean" type="checkbox" v-model="userData.isBlindEvent">
        {{userData.isBlindEvent ? "yes" : "no"}}
      </div>
    </form>
  </form-format>
</template>

<script>
import Datepicker from "vuejs-datepicker";
import { en } from "vuejs-datepicker/dist/locale";
import FormFormat from "../components/FormFormat.vue";
import VueTimepicker from "vue2-timepicker";

export default {
  components: {
    Datepicker,
    FormFormat,
    VueTimepicker
  },

  data() {
    return {
      en: en,
      userData: {
        eventTitle: "",
        eventImageURL: "",
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


