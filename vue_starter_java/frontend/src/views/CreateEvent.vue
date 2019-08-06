<template>
  <form-format>
    <form class="event">
      <h1>Create a New Event</h1>
      <form-line>
        <h4>Title:</h4>
        <input type="text" v-model="userData.eventTitle">
      </form-line>
      <form-line>
        <h4>URL for the event image:</h4>
        <input type="text" v-model="userData.eventImageURL">
      </form-line>
      <form-line class="checkbox">
        <h4>Is this a private event?</h4>
        <input type="checkbox" v-model="userData.isPrivateEvent">
        {{userData.isPrivateEvent ? "yes" : "no"}}
      </form-line>
      <form-line>
        <h4>Date of Event:</h4>
        <datepicker v-model="userData.state.date" name="uniquename"></datepicker>
      </form-line>
      <form-line>
        <vue-timepicker format="hh:mm A" :minute-interval="30"></vue-timepicker>
      </form-line>
      <form-line>
        <h4>Location:</h4>
        <input type="text" v-model="userData.eventLocation">
      </form-line>
      <form-line>
        <h4>Additional Information:</h4>
        <textarea v-model="userData.eventDescription"></textarea>
      </form-line>
      <form-line class="checkbox">
        <h4>Is this a blind tasting?</h4>
        <input type="checkbox" v-model="userData.isBlindEvent">
        {{userData.isBlindEvent ? "yes" : "no"}}
      </form-line>
    </form>
  </form-format>
</template>

<script>
import Datepicker from "vuejs-datepicker";
import { en } from "vuejs-datepicker/dist/locale";
import FormFormat from "../components/FormFormat.vue";
import FormLine from "../components/FormLine.vue";
import VueTimepicker from "vue2-timepicker";

export default {
  components: {
    Datepicker,
    FormFormat,
    VueTimepicker,
    FormLine
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


