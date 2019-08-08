<template>
  <form-format>
    <form class="event">
      <h1>Create a New Event</h1>
      <form-line>
        <h4>Title:</h4>
        <input type="text" v-model="userData.eventTitle">
      </form-line>
      <!-- <form-line>
        <h4>URL for the event image:</h4>
        <input type="text" v-model="userData.eventImageURL">
      </form-line>-->
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
        <h4>Time of Event:</h4>
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
      <h4>What whiskeys will be tasted?</h4>
      <whiskey-brands-to-select :apiURL="API_URL"></whiskey-brands-to-select>
      <button type="submit" v-on:click.prevent="saveEvent">Create Event</button>
    </form>
  </form-format>
</template>

<script>
import Datepicker from "vuejs-datepicker";
import { en } from "vuejs-datepicker/dist/locale";
import FormFormat from "../components/FormFormat.vue";
import VueTimepicker from "vue2-timepicker";
import FormLine from "../components/FormLine.vue";
import WhiskeyBrandsToSelect from "../components/WhiskeyBrandsToSelect.vue";

export default {
  components: {
    Datepicker,
    FormFormat,
    VueTimepicker,
    FormLine,
    WhiskeyBrandsToSelect
  },

  data() {
    return {
      en: en,
      apiURLEvent: "http://localhost:8080/AuthenticationApplication/api/events",
      API_URL: "http://localhost:8080/AuthenticationApplication/api/whiskeys",
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
  },
  methods: {
    saveEvent() {
      fetch(this.apiURLEvent, {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(this.userData)
      })
        .then(response => {
          if (response.ok) {
            this.$emit("showReviews");
            this.$router.push("/");
          }
        })
        .catch(err => console.error(err));
    }
  }
};
</script>

<style scoped>
button {
  display: block;
  text-align: center;
  margin: 5%;
}

/* .select-box {
  max-height: max-content;
} */
</style>




